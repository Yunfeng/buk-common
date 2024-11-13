package cn.buk.common.dto;

import java.util.Objects;

/**
 * 发送信息的结构体
 */
public class QinfoMessage {

    /**
     * 版本：默认版本为0
     */
    private int version;

    //以下为version = 1 时增加的内容

    /**
     * 消息类型
     */
    private String msgType;

    /**
     * 消息内容：字符串（json格式）
     */
    private String msgContent;


    //以下为version = 0 时的内容
    /**
     * 企业id
     */
    private int enterpriseId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 消息主题：通用消息
     */
    private String subject;
    /**
     * 消息内容:通用消息
     */
    private String message;

    private String pnrNo;

    /**
     * 需要发送通知的email地址
     */
    private String email;
    /**
     * 电子邮件：消息内容
     */
    private String emailBody;
    /**
     * 电子邮件：消息主体
     */
    private String emailSubject;

    /**
     * 需要发送短信的手机号
     */
    private String mobile;
    /**
     * 手机：短信内容
     */
    private String smsContent;

    /**
     * 以下三项不能同时为空；多个值用分号分割；值中间不能有 |
     */
    private String weixin;

    private String deptIds;

    private String tagIds;


    public int getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(int enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addEmail(String email) {
        if (email == null || email.trim().length() < 5) {
            return;
        }

        if (this.email == null || this.email.trim().length() == 0 ) {
            this.email = email;
        } else {
            this.email += ";" + email;
        }
    }

    public String getMobile() {
        return Objects.requireNonNullElse(mobile, "");
    }

    public void setMobile(String mobile) {
        if (mobile == null) {
            mobile = "";
        } else {
            mobile = mobile.trim();
        }

        this.mobile = mobile;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public void addWeixin(String weixin) {
        if (weixin == null || weixin.trim().length() < 5) {
            return;
        }

        if (this.weixin == null || this.weixin.trim().length() == 0 ) {
            this.weixin = weixin;
        } else {
            this.weixin += ";" + weixin;
        }
    }

    public void addMobile(String mobile) {
        if (mobile == null || mobile.trim().length() != 11) {
            return;
        }

        if (this.mobile == null || this.mobile.trim().length() == 0 ) {
            this.mobile = mobile;
        } else {
            this.mobile += ";" + mobile;
        }
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPnrNo() {
        return pnrNo;
    }

    public void setPnrNo(String pnrNo) {
        this.pnrNo = pnrNo;
    }

    public String getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(String deptIds) {
        this.deptIds = deptIds;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public String getEmailBody() {
        return emailBody;
    }

    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }
}
