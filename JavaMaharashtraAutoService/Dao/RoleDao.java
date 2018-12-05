package com.JavaMaharashtraAutoService.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.JavaMaharashtraAutoService.Model.Roles;
import com.JavaMaharashtraAutoService.Util.MySQLConnection;

public class RoleDao implements IRoleDao {

	Connection con;

	public RoleDao() {
		con = MySQLConnection.GetConnection();
	}

	@Override
	public String AddRole(Roles roles) {
		String output = "";
		try {
			PreparedStatement pre = con.prepareStatement(
					"insert into Roles (rolName,rolDes,delInd,updateUser,updateDate) values(?,?,?,?,?)");
			pre.setString(1, roles.getRolName());
			pre.setString(2, roles.getRolDes());
			pre.setString(3, "N");
			pre.setString(4, roles.getUpdateUser());
			pre.setDate(5, roles.getUpdateDate());
			pre.executeUpdate();
			output = "Data has been inserted successfully.";

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output;
	}

	@Override
	public String UpdateRole(Roles roles) {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String DeleteRole() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public List<Roles> GetRoles() {

		List<Roles> listRoles = new ArrayList<Roles>();
		try {
			Statement st = con.createStatement();
			String sql = "SELECT rolId,rolName,rolDes,updateUser,updateDate\r\n" + " FROM roles where delInd='N';";
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				Roles role = new Roles();
				role.setRolId(rs.getInt("rolId"));
				role.setRolName(rs.getString("rolName"));
				role.setRolDes(rs.getString("rolDes"));
				role.setUpdateUser(rs.getString("updateUser"));
				role.setUpdateDate(rs.getDate("updateDate"));

				listRoles.add(role);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listRoles;
	}
}
