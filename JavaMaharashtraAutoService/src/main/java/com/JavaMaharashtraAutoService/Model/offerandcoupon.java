package com.JavaMaharashtraAutoService.Model;

import java.sql.Date;

public class offerandcoupon {
	
	private int offId;
	private String offCode;
	private String offStartDate;
	private String offEndDate;
	private String offDes;
	private String offType;
	private Float offAmt;
	private String delInd;
	private String updateUser;
	private Date updateDate;
	public int getOffId() {
		return offId;
	}
	public void setOffId(int offId) {
		this.offId = offId;
	}
	public String getOffCode() {
		return offCode;
	}
	public void setOffCode(String offCode) {
		this.offCode = offCode;
	}
	public String getOffStartDate() {
		return offStartDate;
	}
	public void setOffStartDate(String offStartDate) {
		this.offStartDate = offStartDate;
	}
	public String getOffEndDate() {
		return offEndDate;
	}
	public void setOffEndDate(String offEndDate) {
		this.offEndDate = offEndDate;
	}
	public String getOffDes() {
		return offDes;
	}
	public void setOffDes(String offDes) {
		this.offDes = offDes;
	}
	public String getOffType() {
		return offType;
	}
	public void setOffType(String offType) {
		this.offType = offType;
	}
	public Float getOffAmt() {
		return offAmt;
	}
	public void setOffAmt(Float offAmt) {
		this.offAmt = offAmt;
	}
	public String getDelInd() {
		return delInd;
	}
	public void setDelInd(String delInd) {
		this.delInd = delInd;
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
