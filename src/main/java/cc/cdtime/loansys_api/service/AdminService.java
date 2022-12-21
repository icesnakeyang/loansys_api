package cc.cdtime.loansys_api.service;

import cc.cdtime.loansys_api.dao.AdminDao;
import cc.cdtime.loansys_api.dao.ApplicationDao;
import cc.cdtime.loansys_api.entity.Admin;
import cc.cdtime.loansys_api.entity.AdminView;
import cc.cdtime.loansys_api.entity.ApplicationView;
import cc.cdtime.loansys_api.framework.vo.GogoTools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AdminService implements IAdminService {
    private final AdminDao adminDao;
    private final ApplicationDao applicationDao;

    public AdminService(AdminDao adminDao,
                        ApplicationDao applicationDao) {
        this.adminDao = adminDao;
        this.applicationDao = applicationDao;
    }

    @Override
    public void createAdmin(Map in) throws Exception {
        String loginName = in.get("loginName").toString();
        String loginPassword = in.get("loginPassword").toString();

        Map qIn = new HashMap();
        qIn.put("loginName", loginName);
        AdminView adminView = adminDao.getAdmin(qIn);
        if (adminView != null) {
            // this login name has been occupied already
            throw new Exception("10003");
        }

        Admin admin = new Admin();
        admin.setAdminId(GogoTools.UUID32());
        admin.setCreateTime(new Date());
        admin.setLoginName(loginName);
        admin.setLoginPassword(GogoTools.encoderByMd5(loginPassword));
        adminDao.createAdmin(admin);
    }

    @Override
    public AdminView getAdmin(Map qIn) throws Exception {
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Map loginAdmin(Map in) throws Exception {
        String loginName = in.get("loginName").toString();
        String loginPassword = in.get("loginPassword").toString();

        Map qIn = new HashMap();
        qIn.put("loginName", loginName);
        AdminView adminView = loadAdminUser(qIn, false);
        if (!adminView.getLoginPassword().equals(GogoTools.encoderByMd5(loginPassword))) {
            //password error
            throw new Exception("10005");
        }

        String token = GogoTools.UUID32();
        qIn = new HashMap();
        qIn.put("token", token);
        qIn.put("tokenTime", new Date());
        qIn.put("adminId", adminView.getAdminId());
        adminDao.updateAdmin(qIn);

        Map out = new HashMap();
        out.put("token", token);

        return out;
    }

    @Override
    public Map loadStatisticLoan(Map in) throws Exception {
        String token = in.get("token").toString();

        Map qIn = new HashMap();
        qIn.put("token", token);
        AdminView adminView = loadAdminUser(qIn, false);

        Map out = applicationDao.sumApplyLoan(qIn);

        return out;

    }

    @Override
    public Map listLoanApplication(Map in) throws Exception {
        String token = in.get("token").toString();
        Integer pageIndex = (Integer) in.get("pageIndex");
        Integer pageSize = (Integer) in.get("pageSize");

        Map qIn = new HashMap();
        qIn.put("token", token);
        AdminView adminView = loadAdminUser(qIn, false);

        qIn = new HashMap();
        Integer offset = (pageIndex - 1) * pageSize;
        qIn.put("offset", offset);
        qIn.put("size", pageSize);
        ArrayList<ApplicationView> applicationViews = applicationDao.listApplication(qIn);

        Map out = new HashMap();
        out.put("applyList", applicationViews);

        return out;
    }

    private AdminView loadAdminUser(Map qIn, Boolean returnNull) throws Exception {
        AdminView adminView = adminDao.getAdmin(qIn);
        if (adminView == null) {
            if (returnNull) {
                return null;
            }
            throw new Exception("10004");
        }

        return adminView;
    }
}
