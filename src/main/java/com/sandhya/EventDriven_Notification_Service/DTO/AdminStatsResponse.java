package com.sandhya.EventDriven_Notification_Service.DTO;
public class AdminStatsResponse {
	
	private long total ;
	
	private Integer failed;
	
	private Integer success;
	
	public AdminStatsResponse(long total, Integer failed, Integer success, Integer pending, Integer dead) {
		super();
		this.total = total;
		this.failed = failed;
		this.success = success;
		this.pending = pending;
		this.dead = dead;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public Integer getFailed() {
		return failed;
	}

	public void setFailed(Integer failed) {
		this.failed = failed;
	}

	public Integer getSuccess() {
		return success;
	}

	public void setSuccess(Integer success) {
		this.success = success;
	}

	public Integer getPending() {
		return pending;
	}

	public void setPending(Integer pending) {
		this.pending = pending;
	}

	public Integer getDead() {
		return dead;
	}

	public void setDead(Integer dead) {
		this.dead = dead;
	}

	private Integer pending;
	
	private Integer dead;

}