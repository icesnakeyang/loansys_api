package cc.cdtime.loansys_api.controller;

import cc.cdtime.loansys_api.framework.vo.ApplicationRequest;
import cc.cdtime.loansys_api.framework.vo.Response;
import cc.cdtime.loansys_api.service.IApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/loansys_api/application")
public class ApplicationController {
    private final IApplicationService iApplicationService;


    public ApplicationController(IApplicationService iApplicationService) {
        this.iApplicationService = iApplicationService;
    }

    @ResponseBody
    @PostMapping("/applyLoan")
    public Response createApplicationForm(@RequestBody ApplicationRequest request) {
        Response response = new Response();
        Map in = new HashMap();
        try {
            in.put("yourName", request.getYourName());
            in.put("contactNumber", request.getContactNumber());
            in.put("email", request.getEmail());
            in.put("loanAmount", request.getLoanAmount());
            in.put("personalLoan", request.getPersonalLoan());
            in.put("businessLoan", request.getBusinessLoan());
            in.put("collateralizedLoan", request.getCollateralizedLoan());
            in.put("balanceTransfer", request.getBalanceTransfer());
            in.put("location", request.getLocation());
            in.put("jobTitle", request.getJobTitle());
            in.put("employerName", request.getEmployerName());
            in.put("monthlySalary", request.getMonthlySalary());
            in.put("salaryMethod", request.getSalaryMethod());

            iApplicationService.createLoanApplication(in);
        } catch (Exception ex) {
            try {
                response.setCode(Integer.parseInt(ex.getMessage()));
            } catch (Exception ex2) {
                response.setCode(10001);
                log.error("createApplication error:" + ex.getMessage());
            }
        }
        return response;
    }
}
