package cc.cdtime.loansys_api.controller;

import cc.cdtime.loansys_api.framework.vo.AdminRequest;
import cc.cdtime.loansys_api.framework.vo.ApplicationRequest;
import cc.cdtime.loansys_api.framework.vo.Response;
import cc.cdtime.loansys_api.service.IAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/loansys_api/admin")
public class AdminController {
    private final IAdminService iAdminService;

    public AdminController(IAdminService iAdminService) {
        this.iAdminService = iAdminService;
    }

    @ResponseBody
    @PostMapping("/createRootAdmin")
    public Response createRootAdmin(@RequestBody AdminRequest request) {
        Response response = new Response();
        Map in = new HashMap();
        try {
            in.put("loginName", request.getLoginName());
            in.put("loginPassword", request.getLoginPassword());

            iAdminService.createAdmin(in);
        } catch (Exception ex) {
            try {
                response.setCode(Integer.parseInt(ex.getMessage()));
            } catch (Exception ex2) {
                response.setCode(10001);
                log.error("Create root admin user error: " + ex.getMessage());
            }
        }
        return response;
    }

    @ResponseBody
    @PostMapping("/loginAdmin")
    public Response loginAdmin(@RequestBody AdminRequest request) {
        Response response = new Response();
        Map in = new HashMap();
        try {
            in.put("loginName", request.getLoginName());
            in.put("loginPassword", request.getLoginPassword());

            Map out = iAdminService.loginAdmin(in);
            response.setData(out);
        } catch (Exception ex) {
            try {
                response.setCode(Integer.parseInt(ex.getMessage()));
            } catch (Exception ex2) {
                response.setCode(10001);
                log.error("loginAdmin error: " + ex.getMessage());
            }
        }
        return response;
    }

    @ResponseBody
    @PostMapping("/loadStatisticLoan")
    public Response loadStatisticLoan(@RequestBody AdminRequest request,
                                      HttpServletRequest httpServletRequest) {
        Response response = new Response();
        Map in = new HashMap();
        try {
            String token = httpServletRequest.getHeader("token");
            in.put("token", token);

            Map out = iAdminService.loadStatisticLoan(in);
            response.setData(out);
        } catch (Exception ex) {
            try {
                response.setCode(Integer.parseInt(ex.getMessage()));
            } catch (Exception ex2) {
                response.setCode(10001);
                log.error("loadStatisticLoan error: " + ex.getMessage());
            }
        }
        return response;
    }

    @ResponseBody
    @PostMapping("/listLoanApplication")
    public Response listLoanApplication(@RequestBody ApplicationRequest request,
                                        HttpServletRequest httpServletRequest) {
        Response response = new Response();
        Map in = new HashMap();
        try {
            String token = httpServletRequest.getHeader("token");
            in.put("token", token);
            in.put("pageIndex", request.getPageIndex());
            in.put("pageSize", request.getPageSize());

            Map out = iAdminService.listLoanApplication(in);
            response.setData(out);
        } catch (Exception ex) {
            try {
                response.setCode(Integer.parseInt(ex.getMessage()));
            } catch (Exception ex2) {
                response.setCode(10001);
                log.error("listLoanApplication error: " + ex.getMessage());
            }
        }
        return response;
    }
}
