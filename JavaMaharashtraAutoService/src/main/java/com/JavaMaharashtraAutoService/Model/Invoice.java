package com.JavaMaharashtraAutoService.Model;

import java.util.Date;
import java.util.List;

public class Invoice {
	private String invNo;
	private Date invDate;
	private String invType;
	private Date serDate;
	private String custName;
	private String custAddress;
	private String custMob;
	private String custVehNo;
	private String custVehType;
	private String custVehModel;
	private String remark;
	private Date nextSerDate;
	private String totalBillAmount;	
	private String disAmount;
	private String grossTotal;
	private String delInd;
	private String updateUser;
	private Date updateDate;
	private List<InvoiceProductDetails> listProducts;
	
	public String getTotalBillAmount() {
		return totalBillAmount;
	}
	public void setTotalBillAmount(String totalBillAmount) {
		this.totalBillAmount = totalBillAmount;
	}
	public List<InvoiceProductDetails> getListProducts() {
		return listProducts;
	}
	public void setListProducts(List<InvoiceProductDetails> listProducts) {
		this.listProducts = listProducts;
	}
	public String getInvNo() {
		return invNo;
	}
	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}
	public Date getInvDate() {
		return invDate;
	}
	public void setInvDate(Date invDate) {
		this.invDate = invDate;
	}
	public String getInvType() {
		return invType;
	}
	public void setInvType(String invType) {
		this.invType = invType;
	}
	public Date getSerDate() {
		return serDate;
	}
	public void setSerDate(Date serDate) {
		this.serDate = serDate;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustAddress() {
		return custAddress;
	}
	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}
	public String getCustMob() {
		return custMob;
	}
	public void setCustMob(String custMob) {
		this.custMob = custMob;
	}
	public String getCustVehNo() {
		return custVehNo;
	}
	public void setCustVehNo(String custVehNo) {
		this.custVehNo = custVehNo;
	}
	public String getCustVehType() {
		return custVehType;
	}
	public void setCustVehType(String custVehType) {
		this.custVehType = custVehType;
	}
	public String getCustVehModel() {
		return custVehModel;
	}
	public void setCustVehModel(String custVehModel) {
		this.custVehModel = custVehModel;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getNextSerDate() {
		return nextSerDate;
	}
	public void setNextSerDate(Date nextSerDate) {
		this.nextSerDate = nextSerDate;
	}
	public String getDisAmount() {
		return disAmount;
	}
	public void setDisAmount(String disAmount) {
		this.disAmount = disAmount;
	}
	public String getGrossTotal() {
		return grossTotal;
	}
	public void setGrossTotal(String grossTotal) {
		this.grossTotal = grossTotal;
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
