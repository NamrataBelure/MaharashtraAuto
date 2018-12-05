package com.JavaMaharashtraAutoService.Dao;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.JavaMaharashtraAutoService.Model.Products;
import com.JavaMaharashtraAutoService.Util.ConstantsClass;
import com.JavaMaharashtraAutoService.Util.MySQLConnection;

public class ProductDao implements IProductDao
{
	Connection con;
	
	public ProductDao() 
	{
		con = MySQLConnection.GetConnection();
	}



	@Override
	public String AddProduct(Products prod)
	{
		String output = "";
		String sysPath = "F:\\JavaGit\\NewMaster\\OmegaSoft2018EMP\\JavaMaharashtraAutoService\\src\\main\\resources\\static\\upload\\";
		try {
			PreparedStatement pre = con.prepareStatement(ConstantsClass.ProductInsertSQL);
			pre.setInt(1, prod.getProId());
			pre.setString(2, prod.getProName());
			pre.setString(3, prod.getProDes());
			pre.setDate(4, prod.getProPurDate());
			pre.setFloat(5, prod.getProPurRate());
			pre.setFloat(6,prod.getProSaleRate());
			pre.setDate(7, prod.getProManDate());
			pre.setDate(8, prod.getProExpDate());
			pre.setString(9, prod.getProManComp());
			pre.setString(10, prod.getProWarrPeriod());
			pre.setInt(11, prod.getProTypeId());
			pre.setString(12, "N");
			pre.setString(13, prod.getUpdateUser());
			pre.setDate(14, prod.getUpdateDate());
			if (!prod.getProImage().isEmpty()) {
				pre.setString(15, "/upload/" + prod.getProImage().getOriginalFilename());
			}
			pre.executeUpdate();

			SaveFileOnSharePath(prod.getProImage(),sysPath);
			pre.executeUpdate();
			output = "Data has been inserted successfully.";

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output;
	}


	
	
	@Override
	public String UpdateProduct(Products prod) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String DeleteProduct() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void SaveFileOnSharePath(MultipartFile file, String sysPath) {
		
		try {
			if (!file.isEmpty()) {
				byte[] bytes = file.getBytes();
				Path path = Paths.get(sysPath + file.getOriginalFilename());
				Files.write(path, bytes);
			}
		} catch (Exception e) {

		}
	}

	public List<Products> GetProducts() 
	{
		List<Products> listProducts=new ArrayList<Products>();
		try 
		{
			Statement st=con.createStatement();
			String sql="SELECT proId,proName,proDes,proPurDate,proPurRate,proSaleRate,\r\n" + 
					"    proManDate,proExpDate,proManComp,\r\n" + 
					"    proWarrPeriod,proTypeId,updateUser,updateDate\r\n" + 

					", proImagePath FROM product where delInd='N';" +

					"FROM product where delInd='N';";

			ResultSet rs=st.executeQuery(sql);
			
			while(rs.next())
			{
				Products pro=new Products();
				pro.setProId(rs.getInt("proId"));
				pro.setProName(rs.getString("proName"));
				pro.setProDes(rs.getString("proDes"));
				pro.setProManDate(rs.getDate("proManDate"));
				pro.setProManComp(rs.getString("proManComp"));
				pro.setProPurDate(rs.getDate("proPurDate"));
				pro.setProPurRate(rs.getFloat("proPurRate"));
				pro.setProSaleRate(rs.getFloat("proSaleRate"));
				pro.setProExpDate(rs.getDate("proExpDate"));
				pro.setProWarrPeriod(rs.getString("proWarrPeriod"));
				pro.setProTypeId(rs.getInt("proTypeId"));
				pro.setUpdateUser(rs.getString("updateUser"));
				pro.setUpdateDate(rs.getDate("updateDate"));

				pro.setProImagePath(rs.getString("proImagePath"));

				
				listProducts.add(pro);
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return listProducts;

	}

}
