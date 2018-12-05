package com.JavaMaharashtraAutoService.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.JavaMaharashtraAutoService.Model.Log;
import com.JavaMaharashtraAutoService.Model.ProductType;
import com.JavaMaharashtraAutoService.Util.DBConnection;
import com.JavaMaharashtraAutoService.Util.Logger;

public class Products {

	Connection con;

	public Products() {
		con = DBConnection.GetConnection();
	}

	public List<ProductType> GetProductTypes() {
		List<ProductType> proTypeList = null;

		try {
			Statement st = con.createStatement();
			String sql = "SELECT proTypeId,proType, proTypeDes FROM prodtype";
			ResultSet rs = st.executeQuery(sql);

			proTypeList = new ArrayList<ProductType>();
			while (rs.next()) {
				ProductType obj = new ProductType();
				obj.setProTypeId(rs.getInt("proTypeId"));
				obj.setProType(rs.getString("proType"));
				obj.setProTypeDes(rs.getString("proTypeDes"));
				proTypeList.add(obj);
			}
		} catch (Exception e) {
			Log obj = new Log();
			obj.setEventName("Products");
			obj.setEventMessage(e.getMessage());
			obj.setEventType("Exception");

			Logger.WriteLog(obj);
		}
		return proTypeList;
	}

	public String AddProductTypes(ProductType proType) {
		String message = "";
		try {
			PreparedStatement pre = con.prepareStatement("INSERT INTO prodtype (proType,proTypeDes) VALUES (?,?)");
			pre.setString(1, proType.getProType());
			pre.setString(2, proType.getProTypeDes());
			pre.executeUpdate();
			message = "Data inserted";
		} catch (Exception e) {
			Log obj = new Log();
			obj.setEventName("ProductsClass");
			obj.setEventMessage(e.getMessage());
			obj.setEventType("Exception");

			Logger.WriteLog(obj);
		}

		return message;
	}


}
