package com.JavaMaharashtraAutoService.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import com.JavaMaharashtraAutoService.Model.Inventory;
import com.JavaMaharashtraAutoService.Model.Products;
import com.JavaMaharashtraAutoService.Util.ConstantsClass;
import com.JavaMaharashtraAutoService.Util.MySQLConnection;

public class InventoryDao {

	Connection con;

	public InventoryDao() {
		con = MySQLConnection.GetConnection();
	}

	public String AddInventory(List<Inventory> inventory) {

		String output = "";
		try {

			for (Inventory invent : inventory) {

				PreparedStatement pre = con.prepareStatement("UPDATE inventory SET " + "proOldQty="
						+ invent.getProOldQty() + "," + "proNewQty=" + invent.getProNewQty() + "," + "inStock="
						+ invent.getInStock() + " WHERE proId=" + invent.getProId() + ";");
				pre.executeUpdate();
			}
			output = "Data has been inserted successfully.";
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}

	public Inventory GetSelectedProd(String proId) {

		Inventory pro = new Inventory();
		try {
			Statement st = con.createStatement();
			String sql = "SELECT proId, proOldQty, proNewQty, inStock FROM inventory where proId='" + proId + "';";

			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {

				pro.setProId(rs.getInt("proId"));
				pro.setProOldQty(rs.getInt("proOldQty"));
				pro.setProNewQty(rs.getInt("proNewQty"));
				pro.setInStock(rs.getInt("inStock"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pro;

	}
}
