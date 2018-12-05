package com.JavaMaharashtraAutoService.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.JavaMaharashtraAutoService.Model.Contact;
import com.JavaMaharashtraAutoService.Util.ConstantsClass;
import com.JavaMaharashtraAutoService.Util.MySQLConnection;

public class ContactDao implements IContactDao {
	Connection con;

	public ContactDao() {
		con = MySQLConnection.GetConnection();
	}

	public String AddContact(Contact contact) {
		String output = "";

		try {
			PreparedStatement pre = con.prepareStatement(ConstantsClass.ContactSQL);

			pre.setString(1, contact.getName());
			pre.setString(2, contact.getEmail());
			pre.setString(3, contact.getPhone());
			pre.setString(4, contact.getCompanyName());
			pre.setString(5, contact.getSubject());
			pre.setString(6, contact.getMessage());
			pre.setString(7, ("N"));
			pre.setString(8, contact.getUpdateUser());
			pre.setDate(9, contact.getUpdateDate());

			pre.executeUpdate();
			output = "Data has been inserted successfully.";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}

	public String UpdateContact(Contact contact) {
		// TODO Auto-generated method stub
		return null;
	}

	public String Delete() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Contact> GetContacts() {
		List<Contact> listContacts = null;

		try {
			Statement st = con.createStatement();
			String sql = "SELECT name,email,phone,companyName,subject,message FROM contact";
			ResultSet rs = st.executeQuery(sql);

			listContacts = new ArrayList<Contact>();
			while (rs.next()) {
				Contact obj = new Contact();
				obj.setName(rs.getString("name"));
				obj.setEmail(rs.getString("email"));
				obj.setPhone(rs.getString("phone"));
				obj.setCompanyName(rs.getString("companyName"));
				obj.setSubject(rs.getString("subject"));
				obj.setMessage(rs.getString("message"));
				listContacts.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listContacts;
	}

}
