package cn.buk.tms.common.dto.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * TODO 用于替换BaseDto
 * @author yfdai
 */
@JsonPropertyOrder({"version", "errcode", "errmsg", "returnCode"})
public class BaseDto {

    /**
     * 版本：用于标记接口JSON格式的版本
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer version;

    /**
     * 错误代码
     * 0 - 成功
     * 其它 - 具体的错误代码
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer errcode;

    /**
     * 错误信息
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errmsg;

    /**
     * 返回接口必要的代码
     * 如果是生成订单，则返回订单id
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer returnCode;


    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Integer getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(Integer returnCode) {
        this.returnCode = returnCode;
    }

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }
}
