package cn.buk.common.dto.eterm;

/**
 * @author yfdai
 */
public class PnrTicketDto {
	
	private int id;
	
	private String ticketNo;
	
	private int passengerIndex;

	private String psgName;
	
	/**
	 * 所包含的航段的序号列表 
	 */
	private String segments;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public int getPassengerIndex() {
		return passengerIndex;
	}

	public void setPassengerIndex(int passengerIndex) {
		this.passengerIndex = passengerIndex;
	}

	/**
	 * @return the segments
	 */
	public String getSegments() {
		return segments;
	}

	/**
	 * @param segments the segments to set
	 */
	public void setSegments(String segments) {
		this.segments = segments;
	}

	public String getPsgName() {
		return psgName;
	}

	public void setPsgName(String psgName) {
		this.psgName = psgName;
	}
}
