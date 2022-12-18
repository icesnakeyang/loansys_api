package cc.cdtime.loansys_api.entity;

import lombok.Data;

/**
 * Loan Application Form
 */
@Data
public class ApplicationView {
    private Integer ids;
    private String yourName;
    private String applyId;
    private String contactName;
    private String email;
    private Double loanAmount;
    private String loanRequired;
    private String location;
    private String jobTitle;
    private String employerName;
    private Double monthlySalary;
    private String salaryMethod;
    private String userId;
}
