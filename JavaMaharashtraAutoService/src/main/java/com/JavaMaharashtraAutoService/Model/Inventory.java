package com.JavaMaharashtraAutoService.Model;

import java.util.List;

public class Inventory {
	
	private int proId;
	private String proName;
	private int proOldQty;
	private int proNewQty;
	private int inStock;
	
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public int getProOldQty() {
		return proOldQty;
	}
	public void setProOldQty(int proOldQty) {
		this.proOldQty = proOldQty;
	}
	public int getProNewQty() {
		return proNewQty;
	}
	public void setProNewQty(int proNewQty) {
		this.proNewQty = proNewQty;
	}
	public int getInStock() {
		return inStock;
	}
	public void setInStock(int inStock) {
		this.inStock = inStock;
	}
	public int getProId() {
		return proId;
	}
	public void setProId(int proId) {
		this.proId = proId;
	}
	
	

}
