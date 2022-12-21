package cc.cdtime.loansys_api.entity;

import lombok.Data;

import java.util.Date;

@Data
public class AdminView {
    private Integer ids;
    private String adminId;
    private Date createTime;
    private String loginName;
    private String loginPassword;
    private String token;
    private Date tokenTime;
}
