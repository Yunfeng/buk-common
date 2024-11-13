package cn.buk.common.sc;

import java.util.Date;

/**
 * @author yfdai
 */
public abstract class AbstractSearchCriteria {

	protected Page page = new Page();

	/**
	 * 是否需要分页，默认为需要
	 */
	private boolean paginationNeeded = true;
	
	private int enterpriseId;

	private Date beginDate;
	private Date endDate;


	/**
	 * @return the page
	 */
	public Page getPage() {
		if (this.page == null) {
			page = new Page();
		}
		return page;
	}

	public Date getBeginDate() {
		return this.beginDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(Page page) {
		this.page = page;
	}

	public boolean isPaginationNeeded() {
		return paginationNeeded;
	}

	public void setPaginationNeeded(boolean paginationNeeded) {
		this.paginationNeeded = paginationNeeded;
	}


	/**
	 * @return the enterpriseId
	 */
	public int getEnterpriseId() {
		return enterpriseId;
	}

	/**
	 * @param enterpriseId the enterpriseId to set
	 */
	public void setEnterpriseId(int enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public int getPageNo() {
		return this.page.getPageNo();
	}

	public void setPageNo(int pageNo) {
		this.page.setPageNo( pageNo);
	}

	public int getPageSize() {
		return this.page.getPageSize();
	}

	public void setPageSize(int pageSize) {
		this.page.setPageSize(pageSize);
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
