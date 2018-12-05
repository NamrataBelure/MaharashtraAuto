package com.JavaMaharashtraAutoService.Dao;

import java.util.List;

import com.JavaMaharashtraAutoService.Model.offerandcoupon;

public interface IofferandcouponDao {
	
	String Addofferandcoupon(offerandcoupon offcou);
	String Updateofferandcoupon(offerandcoupon offcou);
	String Deleteofferandcoupon();
	List<offerandcoupon> GetOffer();
}


