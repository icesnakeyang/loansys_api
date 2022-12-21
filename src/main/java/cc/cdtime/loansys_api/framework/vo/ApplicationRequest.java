package cc.cdtime.loansys_api.framework.vo;

import lombok.Data;

@Data
public class ApplicationRequest extends Request {
    private String yourName;
    private String contactNumber;
    private String email;
    private Double loanAmount;
    private Boolean personalLoan;
    private Boolean businessLoan;
    private Boolean collateralizedLoan;
    private Boolean balanceTransfer;
    private String location;
    private String jobTitle;
    private String employerName;
    private Double monthlySalary;
    private String salaryMethod;
}
