package com.JavaMaharashtraAutoService.Model;

import java.sql.Date;

public class Jobs {
	
	private int jobId;
	private String vin;
	private String vinKm;
	private String serDate;
	private String delDate;
	private String jobDes;
	private String estAmt;
	private String delInd;
	private String userId;
	
	private String updateUser;
	private Date updateDate;
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getVinKm() {
		return vinKm;
	}
	public void setVinKm(String vinKm) {
		this.vinKm = vinKm;
	}
	public String getSerDate() {
		return serDate;
	}
	public void setSerDate(String serDate) {
		this.serDate = serDate;
	}
	public String getDelDate() {
		return delDate;
	}
	public void setDelDate(String delDate) {
		this.delDate = delDate;
	}
	public String getJobDes() {
		return jobDes;
	}
	public void setJobDes(String jobDes) {
		this.jobDes = jobDes;
	}
	public String getEstAmt() {
		return estAmt;
	}
	public void setEstAmt(String estAmt) {
		this.estAmt = estAmt;
	}
	public String getDelInd() {
		return delInd;
	}
	public void setDelInd(String delInd) {
		this.delInd = delInd;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	
	

}
