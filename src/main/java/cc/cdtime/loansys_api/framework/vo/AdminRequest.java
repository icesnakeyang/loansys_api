package cc.cdtime.loansys_api.framework.vo;

import lombok.Data;

@Data
public class AdminRequest {
    private String loginName;
    private String loginPassword;
}
