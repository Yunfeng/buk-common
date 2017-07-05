package cn.buk.common;

import cn.buk.util.DateUtil;

import java.text.ParseException;
import java.util.Date;

public class SearchFlightInfoCriteria extends CommonSearchCriteria {

    private int approvalStatus = 0; //是否需要审批

    private String dport;
    private String aport;
    private String carrier;
    private String opCarrier;
    private String subClass;

    private int flightCount;
    private int seatCount;

    private String name;

    private String pnrNo="";
    private String flightNo;

    private String departureDate="";
    private Date ddate;

    private boolean PnrContentExported;

    /**
     * 出票状态
     * 0-未出票
     * 1-已出票
     */
    private int dzStatus=-1;

    /**
     * 0-非代码共享
     * 1-代码共享
     * 其它-全部
     */
    private int codeShared;

    /**
     * 编码状态
     * -1-所有
     * 0-正常
     * 1-已取消
     */
    private String xeStatus="-1";

    /**
     * 航段状态
     */
    private String segStatus;

    /**
     * 排除的航段状态
     */
    private String excludeSegStatus;

    /**
     * 是否启用只查询今天模式
     * 0-不启用
     * 1-启用
     */
    private int todayMode;

    /**
     * 要查询的具体的eterm用户名
     */
    private String etermUsername;

    private String remark;

    /**
     * 0 - 所有
     * 1 - 仅自己
     * 2 - 不包含自己
     */
    private int searchMode;

    /**
     * -1-所有
     * 0-国内政策
     * 1-国际政策
     */
    private int intlPolicy;

    /**
     * 政策类型
     * -1 - 所有
     * 0 - 普通
     * 1 - 特殊
     */
    private int policyType;

    private int buyerId;

    private int sellerId;

    private String username;

    @Override
    public int getPageNo() {
        return getPage().getPageNo();
    }

    @Override
    public void setPageNo(int pageNo) {
        getPage().setPageNo(pageNo);
    }

    @Override
    public int getPageSize() {
        return getPage().getPageSize();
    }

    @Override
    public void setPageSize(int pageSize) {
        getPage().setPageSize(pageSize);
    }

    public String getPnrNo() {
        if (pnrNo == null) return "";
        return pnrNo;
    }

    public void setPnrNo(String pnrNo) {
        this.pnrNo = pnrNo;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public boolean isPnrContentExported() {
        return PnrContentExported;
    }

    public void setPnrContentExported(boolean pnrContentExported) {
        PnrContentExported = pnrContentExported;
    }

    public int getDzStatus() {
        return dzStatus;
    }

    public void setDzStatus(int dzStatus) {
        this.dzStatus = dzStatus;
    }

    public String getXeStatus() {
        return xeStatus;
    }

    public void setXeStatus(String xeStatus) {
        this.xeStatus = xeStatus;
    }

    public String getSegStatus() {
        if (segStatus == null) return "";
        return segStatus;
    }

    public void setSegStatus(String segStatus) {
        this.segStatus = segStatus;
    }

    public int getTodayMode() {
        return todayMode;
    }

    public void setTodayMode(int todayMode) {
        this.todayMode = todayMode;
    }

    public String getEtermUsername() {
        return etermUsername;
    }

    public void setEtermUsername(String etermUsername) {
        this.etermUsername = etermUsername;
    }

    public String getSubClass() {
        if (subClass == null) return "";
        return subClass;
    }

    public void setSubClass(String subClass) {
        this.subClass = subClass;
    }

    public String getAport() {
        if (aport == null) return "";
        return aport;
    }

    public void setAport(String aport) {
        this.aport = aport;
    }

    public String getDport() {
        if (dport == null) return "";
        return dport;
    }

    public void setDport(String dport) {
        this.dport = dport;
    }

    public String getFlightNo() {
        if (flightNo == null) return "";
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getRemark() {
        if (remark == null) return "";
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getDepartureDate0() {
        if (this.departureDate == null || this.departureDate.length() == 0) return null;

        Date newDate = null;

        try {
            newDate = DateUtil.convertToDate(this.departureDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return newDate;

    }

    public String getCarrier() {
        if (carrier == null) return "";
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public int getFlightCount() {
        return flightCount;
    }

    public void setFlightCount(int flightCount) {
        this.flightCount = flightCount;
    }

    public int getCodeShared() {
        return codeShared;
    }

    public void setCodeShared(int codeShared) {
        this.codeShared = codeShared;
    }

    public Date getDdate() {
        return ddate;
    }

    public void setDdate(Date ddate) {
        this.ddate = ddate;
    }

    public String getOpCarrier() {
        return opCarrier;
    }

    public void setOpCarrier(String opCarrier) {
        this.opCarrier = opCarrier;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }

    public int getSearchMode() {
        return searchMode;
    }

    public void setSearchMode(int searchMode) {
        this.searchMode = searchMode;
    }

    public int getIntlPolicy() {
        return intlPolicy;
    }

    public void setIntlPolicy(int intlPolicy) {
        this.intlPolicy = intlPolicy;
    }

    public int getPolicyType() {
        return policyType;
    }

    public void setPolicyType(int policyType) {
        this.policyType = policyType;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getName() {
        if (name == null)
            return "";
        else
            return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(int approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getUsername() {
        return username == null ? "": username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getExcludeSegStatus() {
        return excludeSegStatus;
    }

    public void setExcludeSegStatus(String excludeSegStatus) {
        this.excludeSegStatus = excludeSegStatus;
    }
}
