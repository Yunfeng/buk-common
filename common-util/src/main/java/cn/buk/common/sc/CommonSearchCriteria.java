package cn.buk.common.sc;

import java.util.Date;

/**
 * @author yfdai
 */
public class CommonSearchCriteria extends AbstractSearchCriteria {

    private String name;
    private String idno;

    private String username; // 用户名
    private String mobile;  // 手机号

    private String dport;
    private String aport;
    private String subclass;
    private String carrier;
    private String opCarrier;

    /**
     * 票号
     */
    private String ticketNo;

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



    private int approvalStatus = 0; //是否需要审批



    private int flightCount;
    private int seatCount;
    private String flightNo;

    private Date ddate;

    /**
     * 0-非代码共享
     * 1-代码共享
     * 其它-全部
     */
    private int codeShared;

    /**
     * 排除的航段状态
     */
    private String excludeSegStatus;

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


    public String getSubClass() {
        return getSubclass();
    }

    public void setSubClass(String subclass) {
        this.setSubclass(subclass);
    }


    public String getPnrNo() {
        return pnrNo == null ? "": pnrNo.trim();
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
        return xeStatus == null ? "": xeStatus.trim();
    }

    public void setXeStatus(String xeStatus) {
        this.xeStatus = xeStatus;
    }

    public String getSegStatus() {
        return segStatus == null ? "": segStatus.trim();
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
        return etermUsername == null ? "": etermUsername.trim();
    }

    public void setEtermUsername(String etermUsername) {
        this.etermUsername = etermUsername;
    }

    public String getAport() {
        if (aport == null) {
          return  "";
        }
        return aport;
    }

    public void setAport(String aport) {
        this.aport = aport;
    }

    public String getDport() {
        if (dport == null) {
          return "";
        }
        return dport;
    }

    public void setDport(String dport) {
        this.dport = dport;
    }

    public String getSubclass() {
        if (subclass == null) {
          return "";
        }
        return subclass;
    }

    public void setSubclass(String subclass) {
        this.subclass = subclass;
    }


    public String getCarrier() {
        if (carrier == null) {
          return "";
        }
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public int getStatus() {
        if (status == null) {
          return -1;
        }
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTicketStatus() {
        return ticketStatus == null ? "": ticketStatus.trim();
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
        if (remark == null) {
          return "";
        }
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSearchDate() {
        return searchDate == null ? "": searchDate.trim();
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

    public String getName() {
        return name == null ? "": name.trim();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdno() {
        return idno == null ? "": idno.trim();
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

    public String getUsername() {
        return username == null ? "": username.trim();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile == null ? "": mobile.trim();
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOpCarrier() {
        return opCarrier == null ? "": opCarrier.trim();
    }

    public void setOpCarrier(String opCarrier) {
        this.opCarrier = opCarrier;
    }

    public int getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(int approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public int getFlightCount() {
        return flightCount;
    }

    public void setFlightCount(int flightCount) {
        this.flightCount = flightCount;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }

    public String getFlightNo() {
        return flightNo == null ? "": flightNo.trim();
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public Date getDdate() {
        return ddate;
    }

    public void setDdate(Date ddate) {
        this.ddate = ddate;
    }

    public int getCodeShared() {
        return codeShared;
    }

    public void setCodeShared(int codeShared) {
        this.codeShared = codeShared;
    }

    public String getExcludeSegStatus() {
        return excludeSegStatus == null ? "": excludeSegStatus.trim();
    }

    public void setExcludeSegStatus(String excludeSegStatus) {
        this.excludeSegStatus = excludeSegStatus;
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

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }
}
