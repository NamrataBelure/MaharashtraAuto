package com.JavaMaharashtraAutoService.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.JavaMaharashtraAutoService.Model.offerandcoupon;
import com.JavaMaharashtraAutoService.Util.ConstantsClass;
import com.JavaMaharashtraAutoService.Util.MySQLConnection;

public class offerandcouponDao implements IofferandcouponDao
{
	Connection con;

	public offerandcouponDao() 
	{
		con = MySQLConnection.GetConnection();
	}

	public String Addofferandcoupon(offerandcoupon offcou) 
	{
		String output = "";
		try 
		{
			PreparedStatement pre = con.prepareStatement(ConstantsClass.OfferInsertSQL);
		
			pre.setString(1, offcou.getOffCode());
			pre.setString(2, offcou.getOffStartDate());
			pre.setString(3, offcou.getOffEndDate());
			pre.setString(4, offcou.getOffDes());
			pre.setString(5, offcou.getOffType());
			pre.setFloat(6, offcou.getOffAmt());
			pre.setString(7, ("N"));
			pre.setString(8, offcou.getUpdateUser());
			pre.setDate(9, offcou.getUpdateDate());
			
			pre.executeUpdate();
			output = "Data has been inserted successfully.";

		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return output;

	}

	@Override
	public String Updateofferandcoupon(offerandcoupon offcou) {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String Deleteofferandcoupon() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public List<offerandcoupon> GetOffer() {
		List<offerandcoupon> listoffer=new ArrayList<offerandcoupon>();
		try
		{
			Statement st=con.createStatement();
			String sql="Select * from offerandcoupon where delInd='N'";
			ResultSet rs=st.executeQuery(sql);
			
			while(rs.next())
			{
				offerandcoupon off=new offerandcoupon();
				off.setOffId(rs.getInt("offId"));
				off.setOffCode(rs.getString("offCode"));
				off.setOffStartDate(rs.getString("offStartDate"));
				off.setOffEndDate(rs.getString("offEndDate"));
				off.setOffDes(rs.getString("offDes"));
				off.setOffType(rs.getString("offType"));
				off.setOffAmt(rs.getFloat("offAmt"));
				
				listoffer.add(off);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return listoffer;
	}
}