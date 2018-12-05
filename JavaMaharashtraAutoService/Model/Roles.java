package com.JavaMaharashtraAutoService.Model;

import java.sql.Date;

public class Roles {
	
	private int rolId;
	private String rolName;
	private String rolDes;
	private String delInd;
	private String updateUser;
	private Date updateDate;
	
	public int getRolId() {
		return rolId;
	}
	public void setRolId(int rolId) {
		this.rolId = rolId;
	}
	public String getRolName() {
		return rolName;
	}
	public void setRolName(String rolName) {
		this.rolName = rolName;
	}
	public String getRolDes() {
		return rolDes;
	}
	public void setRolDes(String rolDes) {
		this.rolDes = rolDes;
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
