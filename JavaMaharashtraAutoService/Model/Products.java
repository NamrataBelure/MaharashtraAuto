package com.JavaMaharashtraAutoService.Model;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class Products {
	private int proId;
	private String proName;
	private String proDes;
	private Date proPurDate;
	private float proPurRate;
	private float proSaleRate;
	private Date proManDate;
	private Date proExpDate;
	private String proManComp;
	private String proWarrPeriod;	
	private int proTypeId;
	private String delInd;
	private String updateUser;
	private Date updateDate;
	private String proImagePath;
	private MultipartFile proImage;
	
	public String getProImagePath() {
		return proImagePath;
	}
	public void setProImagePath(String proImagePath) {
		this.proImagePath = proImagePath;
	}
	public MultipartFile getProImage() {
		return proImage;
	}
	public void setProImage(MultipartFile proImage) {
		this.proImage = proImage;
	}
	public Date getProPurDate() {
		return proPurDate;
	}
	public void setProPurDate(Date proPurDate) {
		this.proPurDate = proPurDate;
	}
	public float getProPurRate() {
		return proPurRate;
	}
	public void setProPurRate(float proPurRate) {
		this.proPurRate = proPurRate;
	}
	public float getProSaleRate() {
		return proSaleRate;
	}
	public void setProSaleRate(float proSaleRate) {
		this.proSaleRate = proSaleRate;
	}
	public Date getProManDate() {
		return proManDate;
	}
	public void setProManDate(Date proManDate) {
		this.proManDate = proManDate;
	}
	public Date getProExpDate() {
		return proExpDate;
	}
	public void setProExpDate(Date proExpDate) {
		this.proExpDate = proExpDate;
	}
	public String getProManComp() {
		return proManComp;
	}
	public void setProManComp(String proManComp) {
		this.proManComp = proManComp;
	}
	public String getProWarrPeriod() {
		return proWarrPeriod;
	}
	public void setProWarrPeriod(String proWarrPeriod) {
		this.proWarrPeriod = proWarrPeriod;
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
	
	
	public int getProId() {
		return proId;
	}
	public void setProId(int proId) {
		this.proId = proId;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getProDes() {
		return proDes;
	}
	public void setProDes(String proDes) {
		this.proDes = proDes;
	}
	public int getProTypeId() {
		return proTypeId;
	}
	public void setProTypeId(int proTypeId) {
		this.proTypeId = proTypeId;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	
}
