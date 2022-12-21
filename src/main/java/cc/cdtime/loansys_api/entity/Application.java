package cc.cdtime.loansys_api.entity;

import lombok.Data;

import java.util.Date;

/**
 * Loan Application Form
 */
@Data
public class Application {
    private Integer ids;
    private String yourName;
    private String applyId;
    private String contactNumber;
    private String email;
    private Double loanAmount;
    private Boolean personalLoan;
    private Boolean balanceTransfer;
    private Boolean businessLoan;
    private Boolean collateralizedLoan;
    private String location;
    private String jobTitle;
    private String employerName;
    private Double monthlySalary;
    private String salaryMethod;
    private String userId;
    private Date createTime;
}
