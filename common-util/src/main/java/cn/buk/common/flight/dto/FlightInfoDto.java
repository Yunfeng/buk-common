package cn.buk.common.flight.dto;

import cn.buk.common.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static cn.buk.common.Constant.DATE_YYYY_MM_DD;

/**
 * 航班信息
 * @author yfdai
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlightInfoDto {

	/**
	 * 接口参数
	 * 价格：用于订单生成时的价格传递
	 */
	private int price;

	/**
	 * 接口参数
	 * 预订舱位：用于订单生成时的舱位传递
	 */
	private String subclass;

	/**
	 * 接口参数
	 * 物理舱位，可不填
	 */
	private String cabinClass;

	/**
	 * 接口参数
	 * 改签规则：用于生成订单时的传递
	 */
	private String changeRule;

	/**
	 * 接口参数
	 * 退票规则：用于生产订单时的传递
	 */
	private String refundRule;

	/**
	 * 出发城市三字代码
	 */
	private String dcity;

	/**
	 * 到达城市三字代码
	 */
	private String acity;

	/**
	 * 出发城市名称
	 */
	private String dcityName;

	/**
	 * 到达城市名称
	 */
	private String acityName;

	/**
	 * 接口参数
	 * 出发机场三字代码
	 */
	private String dport;

	/**
	 * 出发机场名称
	 */
	private String dportName;

	/**
	 * 接口参数
	 * 到达机场三字代码
	 */
	private String aport;

	/**
	 * 到达机场名称
	 */
	private String aportName;

	/**
	 * 接口参数
	 * 航班号
	 */
	private String flightNo;

	/**
	 * 接口参数
	 * 出发日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date ddate;

	/**
	 * 到达日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date adate;

	/**
	 * 到达日期相比出发日期增加的天数
	 * 与AV结果中的+1匹配
	 */
	private int addedDays;

	/**
	 * 是否代码共享
	 * 0-否
	 * 1-是
	 */
	private int codeShare;

	/**
	 * 航司代码
	 */
	private String carrier;

	/**
	 * 航司名称
	 */
	private String carrierName;

	/**
	 * 承运航班号
	 */
	private String opFlightNo;

	/**
	 * 承运航司代码
	 */
	private String opCarrier;

	/**
	 * 承运航司名称
	 */
	private String opCarrierName;

	/**
	 * 接口参数
	 * 出发时间：2359
	 */
	private String dtime;

	/**
	 * 接口参数
	 * 到达时间
	 */
	private String atime;

	/**
	 * 接口参数
	 * 出发航站楼
	 */
	private String dterm;

	/**
	 * 接口参数
	 * 到达航站楼
	 */
	private String aterm;

	/**
	 * 机型
	 * craftType
	 */
	private String aircraft;

	/**
	 * 机场税
	 */
	private int airportTax;

	/**
	 * 燃油费
	 */
	private int fuelSurcharge;

	/**
	 * 经停数
	 */
	private int stopover;

	/**
	 * 经停机场的代码
	 */
	private String stopoverAirportCode;

	/**
	 * 餐食
	 */
	private String meal;

	private String subClassDesc;

	private String shortSubClassDesc;

	/**
	 * 出票状态
	 */
	private String tktStatus;

	/**
	 * 航段状态
	 */
	private String segmentStatus;

	/**
	 * 行程详情
	 */
	private String routeDetail;

	/**
	 * 飞行时长
	 */
	@Deprecated
	private String flyTime;

	/**
	 * 飞行时长（分钟）
	 */
	private int duration;

	/**
	 * 距离数（公里）
	 */
	private int distance;

	/**
	 * 数据新鲜度
	 */
	private int freshness;

	/**
	 * 查询多航段时对应的 行程id
	 */
	private int legId;

	/**
	 * CZ NDC查询结果返回的segment_key
	 */
	private String segmentKey;

	private SubClassDto lowestPrice;

	private List<SubClassDto> subClassList;

	public void calcLowestPrice() {
		for (SubClassDto dto : getSubClassList()) {
			if (dto.getPrice() < 1) {
				continue;
			}

			if (getLowestPrice() == null || getLowestPrice().getPrice() == 0 || getLowestPrice().getPrice() > dto.getPrice()) {
				setLowestPrice(dto);
			}
		}
	}

	public String getDdateFormatted() {
		return DateUtil.formatDate(this.ddate, DATE_YYYY_MM_DD);
	}

	public String getAdateFormatted() {
		return DateUtil.formatDate(this.adate, DATE_YYYY_MM_DD);
	}


	/**
	 * departure city
	 *
	 * @return 获取
	 */
	public String getDcity() {
		return dcity;
	}

	public void setDcity(String dcity) {
		this.dcity = dcity;
	}

	/**
	 * arrival city
	 *
	 * @return 获取
	 */
	public String getAcity() {
		return acity;
	}

	public void setAcity(String acity) {
		this.acity = acity;
	}

	public String getDcityName() {
		return dcityName;
	}

	public void setDcityName(String dcityName) {
		this.dcityName = dcityName;
	}

	public String getAcityName() {
		return acityName;
	}

	public void setAcityName(String acityName) {
		this.acityName = acityName;
	}

	/**
	 * 出发机场代码
	 *
	 * @return 获取
	 */
	public String getDport() {
		return dport;
	}

	public void setDport(String dport) {
		this.dport = dport;
	}

	/**
	 * 出发机场名称
	 *
	 * @return 获取
	 */
	public String getDportName() {
		return dportName;
	}

	public void setDportName(String dportName) {
		this.dportName = dportName;
	}

	/**
	 * 到达机场代码
	 */
	public String getAport() {
		return aport;
	}

	public void setAport(String aport) {
		this.aport = aport;
	}

	/**
	 * 到达机场名称
	 *
	 * @return 获取
	 */
	public String getAportName() {
		return aportName;
	}

	public void setAportName(String aportName) {
		this.aportName = aportName;
	}

	/**
	 * 航班号
	 *
	 * @return 获取
	 */
	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	/**
	 * 出发日期
	 *
	 * @return 获取
	 */
	public Date getDdate() {
		return ddate;
	}

	public void setDdate(Date ddate) {
		this.ddate = ddate;
	}

	public Date getAdate() {
		if (adate == null && ddate != null) {
			adate = DateUtil.addDays(this.ddate, this.addedDays);
		}
		return adate;
	}

	public void setAdate(Date adate) {
		this.adate = adate;
	}

	/**
	 * 是否代码共享航班
	 * 0-否
	 * 1-是
	 *
	 * @return 获取
	 */
	public int getCodeShare() {
		return codeShare;
	}

	public void setCodeShare(int codeShare) {
		this.codeShare = codeShare;
	}

	/**
	 * 航空公司2字代码
	 */
	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	/**
	 * 航空公司名称
	 *
	 * @return 获取
	 */
	public String getCarrierName() {
		return carrierName;
	}

	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	/**
	 * 承运航班号（为空时，flightNo就是承运航班）
	 *
	 * @return 获取
	 */
	public String getOpFlightNo() {
		return opFlightNo;
	}

	public void setOpFlightNo(String opFlightNo) {
		this.opFlightNo = opFlightNo;
	}

	/**
	 * 承运航司代码
	 */
	public String getOpCarrier() {
		return opCarrier;
	}

	public void setOpCarrier(String opCarrier) {
		this.opCarrier = opCarrier;
	}

	/**
	 * 承运航司名称
	 *
	 * @return 获取
	 */
	public String getOpCarrierName() {
		return opCarrierName;
	}

	public void setOpCarrierName(String opCarrierName) {
		this.opCarrierName = opCarrierName;
	}

	/**
	 * 出发时间
	 */
	public String getDtime() {
		return dtime;
	}

	public void setDtime(String dtime) {
		this.dtime = dtime;
	}

	/**
	 * 到达时间
	 *
	 * @return 获取
	 */
	public String getAtime() {
		return atime;
	}

	public void setAtime(String atime) {
		this.atime = atime;
	}

	/**
	 * 出发航站楼
	 *
	 * @return 获取
	 */
	public String getDterm() {
		return dterm;
	}

	public void setDterm(String dterm) {
		this.dterm = dterm;
	}

	/**
	 * 到达航站楼
	 *
	 * @return 获取
	 */
	public String getAterm() {
		return aterm;
	}

	public void setAterm(String aterm) {
		this.aterm = aterm;
	}

	/**
	 * 机型
	 *
	 * @return 获取
	 */
	public String getAircraft() {
		return aircraft;
	}

	public void setAircraft(String aircraft) {
		this.aircraft = aircraft;
	}

	/**
	 * 机场建设税
	 */
	public int getAirportTax() {
		return airportTax;
	}

	public void setAirportTax(int airportTax) {
		this.airportTax = airportTax;
	}

	/**
	 * 燃油附加费
	 *
	 * @return 获取
	 */
	public int getFuelSurcharge() {
		return fuelSurcharge;
	}

	public void setFuelSurcharge(int fuelSurcharge) {
		this.fuelSurcharge = fuelSurcharge;
	}

	/**
	 * 经停
	 *
	 * @return 获取
	 */
	public int getStopover() {
		return stopover;
	}

	public void setStopover(int stopover) {
		this.stopover = stopover;
	}

	/**
	 * 餐食
	 *
	 * @return 获取
	 */
	public String getMeal() {
		return meal;
	}

	public void setMeal(String meal) {
		this.meal = meal;
	}

	public String getSubClassDesc() {
		return subClassDesc;
	}

	public void setSubClassDesc(String subClassDesc) {
		if (subClassDesc == null) {
			return;
		}
		this.subClassDesc = subClassDesc;
		if (getSubClassList().isEmpty()) {
			String[] arr = subClassDesc.split(" ");
			for (String temp : arr) {
				if (temp.length() != 2) {
					continue;
				}

				SubClassDto subClassDto = new SubClassDto();
				subClassDto.setSubClass(temp.substring(0, 1));
				subClassDto.setSeatStatus(temp.substring(1, 2));
				getSubClassList().add(subClassDto);
			}
		}
	}

	/**
	 * 简短子仓位描述, 将subClassDesc中没有座位的子仓位隐藏掉
	 *
	 * @return 获取
	 */
	public String getShortSubClassDesc() {
		return shortSubClassDesc;
	}

	public void setShortSubClassDesc(String shortSubClassDesc) {
		this.shortSubClassDesc = shortSubClassDesc;
	}

	/**
	 * 数据新鲜度,用分钟计量
	 *
	 * @return 获取
	 */
	public int getFreshness() {
		return freshness;
	}

	public void setFreshness(int freshness) {
		this.freshness = freshness;
	}

	public SubClassDto getLowestPrice() {
		return lowestPrice;
	}

	public void setLowestPrice(SubClassDto lowestPrice) {
		this.lowestPrice = lowestPrice;
	}

	public List<SubClassDto> getSubClassList() {
		if (subClassList == null) {
			subClassList = new ArrayList<>();
		}
		return subClassList;
	}

	public void setSubClassList(List<SubClassDto> subClassList) {
		this.subClassList = subClassList;
	}

	public String getFlyTime() {
		return flyTime;
	}

	public void setFlyTime(String flyTime) {
		this.flyTime = flyTime;
		this.calcDuration();
	}

	private void calcDuration() {
		//计算飞行时长
		if (this.flyTime == null) return;
		int idx = this.flyTime.indexOf(":");
		if (idx < 1) return;

		try {
			String temp1 = this.flyTime.substring(0, idx);
			String temp2 = this.flyTime.substring(idx + 1);
			this.duration = 60 * Integer.parseInt(temp1) + Integer.parseInt(temp2);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public int getAddedDays() {
		return addedDays;
	}

	public void setAddedDays(int addedDays) {
		this.addedDays = addedDays;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getSubclass() {
		return subclass;
	}

	public void setSubclass(String subclass) {
		this.subclass = subclass;
	}

	public String getChangeRule() {
		return changeRule;
	}

	public void setChangeRule(String changeRule) {
		this.changeRule = changeRule;
	}

	public String getRefundRule() {
		return refundRule;
	}

	public void setRefundRule(String refundRule) {
		this.refundRule = refundRule;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getCabinClass() {
		return cabinClass;
	}

	public void setCabinClass(String cabinClass) {
		this.cabinClass = cabinClass;
	}

	public int getSubClassSeatCount(String subClass) {
		for (SubClassDto subClassDto : subClassList) {
			if (subClassDto.getSubClass().equalsIgnoreCase(subClass)) {
				String temp = subClassDto.getSeatStatus();
				if (temp == null) {
					return 0;
				} else if (temp.equalsIgnoreCase("A")) {
					return 10;
				} else if (temp.equalsIgnoreCase("Q")) {
					return -1;
				}

				try {
					return Integer.parseInt(temp);
				} catch (Exception ex) {
					return 0;
				}
			}
		}
		return 0;
	}


	public String getTktStatus() {
		return tktStatus;
	}

	public void setTktStatus(String tktStatus) {
		this.tktStatus = tktStatus;
	}

	public String getSegmentStatus() {
		return segmentStatus;
	}

	public void setSegmentStatus(String segmentStatus) {
		this.segmentStatus = segmentStatus;
	}

	public String getRouteDetail() {
		return routeDetail;
	}

	public void setRouteDetail(String routeDetail) {
		this.routeDetail = routeDetail;
	}

	public int getLegId() {
		return legId;
	}

	public void setLegId(int legId) {
		this.legId = legId;
	}

	public String getSegmentKey() {
		return segmentKey;
	}

	public void setSegmentKey(String segmentKey) {
		this.segmentKey = segmentKey;
	}

	public String getStopoverAirportCode() {
		return stopoverAirportCode;
	}

	public void setStopoverAirportCode(String stopoverAirportCode) {
		this.stopoverAirportCode = stopoverAirportCode;
	}
}
