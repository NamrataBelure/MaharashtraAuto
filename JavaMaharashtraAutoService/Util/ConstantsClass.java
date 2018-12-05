package com.JavaMaharashtraAutoService.Util;

public class ConstantsClass {

	public static String ProductInsertSQL = "insert into product(proId,proName,proDes,proPurDate,proPurRate,proSaleRate,proManDate,proExpDate,proManComp,proWarrPeriod,proTypeId,delInd,updateUser,updateDate,proImagePath) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static String OfferInsertSQL = "insert into offerandcoupon(offCode,offStartDate,offEndDate,offDes,offType,offAmt,delInd,updateUser,updateDate)values(?,?,?,?,?,?,?,?,?)";

}
