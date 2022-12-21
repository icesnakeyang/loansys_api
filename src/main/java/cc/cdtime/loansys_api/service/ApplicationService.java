package cc.cdtime.loansys_api.service;

import cc.cdtime.loansys_api.dao.ApplicationDao;
import cc.cdtime.loansys_api.entity.Application;
import cc.cdtime.loansys_api.entity.ApplicationView;
import cc.cdtime.loansys_api.framework.vo.GogoTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class ApplicationService implements IApplicationService {
    private final ApplicationDao applicationDao;

    public ApplicationService(ApplicationDao applicationDao) {
        this.applicationDao = applicationDao;
    }

    @Override
    public void createLoanApplication(Map in) throws Exception {
        String yourName = in.get("yourName").toString();
        String contactNumber = in.get("contactNumber").toString();
        String email = in.get("email").toString();
        Double loanAmount = (Double) in.get("loanAmount");
        Boolean personalLoan = (Boolean) in.get("personalLoan");
        Boolean businessLoan = (Boolean) in.get("businessLoan");
        Boolean collateralizedLoan = (Boolean) in.get("collateralizedLoan");
        Boolean balanceTransfer = (Boolean) in.get("balanceTransfer");
        String location = in.get("location").toString();
        String jobTitle = in.get("jobTitle").toString();
        String employerName = in.get("employerName").toString();
        Double monthlySalary = (Double) in.get("monthlySalary");
        String salaryMethod = in.get("salaryMethod").toString();

        ApplicationView applicationView = applicationDao.getApplication(email);
        if (applicationView != null) {
            //This user has applied loan already
            throw new Exception("10002");
        }

        Application application = new Application();
        application.setApplyId(GogoTools.UUID32());
        application.setEmail(email);
        application.setContactNumber(contactNumber);
        application.setLoanAmount(loanAmount);
        application.setEmployerName(employerName);
        application.setPersonalLoan(personalLoan);
        application.setBusinessLoan(businessLoan);
        application.setCollateralizedLoan(collateralizedLoan);
        application.setBalanceTransfer(balanceTransfer);
        application.setJobTitle(jobTitle);
        application.setLocation(location);
        application.setMonthlySalary(monthlySalary);
        application.setSalaryMethod(salaryMethod);
        application.setYourName(yourName);
        application.setUserId(GogoTools.UUID32());
        application.setCreateTime(new Date());

        applicationDao.createLoanApplication(application);
    }
}
