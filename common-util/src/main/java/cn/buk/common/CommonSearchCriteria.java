package cn.buk.common;

import java.util.Date;

public class CommonSearchCriteria extends AbstractSearchCriteria {

    private String name;
    private String idno;

    private int enterpriseId;

    private String dport;
    private String aport;
    private String subclass;
    private String carrier;

    private String pnrNo="";

    private String departureDate="";

    private boolean PnrContentExported;

    /**
     * 出票状态
     * 0-未出票
     * 1-已出票
     */
    private int dzStatus=-1;

    private Integer status;

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
     * 是否启用只查询今天模式
     * 0-不启用
     * 1-启用
     */
    private int todayMode;

    /**
     * 要查询的具体的eterm用户名
     */
    private String etermUsername;

    /**
     * detr 票号的使用状态
     */
    private String ticketStatus="";

    private int type = -1;
    /**
     * 行程类型:0-单程,1-往返
     */
    private int routeType = -1;

    private int planSetId = -1;

    private String remark;

    private String searchDate;

    /**
     * 排序
     */
    private int orderBy;

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

    public String getAport() {
        if (aport == null) return  "";
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

    public String getSubclass() {
        if (subclass == null) return "";
        return subclass;
    }

    public void setSubclass(String subclass) {
        this.subclass = subclass;
    }

    public String getCarrier() {
        if (carrier == null) return "";
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public Integer getStatus() {
        if (status == null) return -1;
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPlanSetId() {
        return planSetId;
    }

    public void setPlanSetId(int planSetId) {
        this.planSetId = planSetId;
    }

    public String getRemark() {
        if (remark == null) return "";
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(String searchDate) {
        this.searchDate = searchDate;
    }

    public int getRouteType() {
        return routeType;
    }

    public void setRouteType(int routeType) {
        this.routeType = routeType;
    }

    @Override
    public int getEnterpriseId() {
        return enterpriseId;
    }

    @Override
    public void setEnterpriseId(int enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public int getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(int orderBy) {
        this.orderBy = orderBy;
    }
}
