package cc.cdtime.loansys_api.framework.vo;

import lombok.Data;

@Data
public class Response {
    private Integer code = 0;
    private Object data;
}
