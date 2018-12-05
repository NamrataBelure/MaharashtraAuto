package com.JavaMaharashtraAutoService.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.JavaMaharashtraAutoService.Model.Log;
import com.JavaMaharashtraAutoService.Model.LoginDetails;
import com.JavaMaharashtraAutoService.Model.Users;
import com.JavaMaharashtraAutoService.Util.Logger;
import com.JavaMaharashtraAutoService.Util.MySQLConnection;

public class UserRegistration implements IUserRegistration {
	Connection con;

	public UserRegistration() {
		con = MySQLConnection.GetConnection();
	}

	@Override
	public String AddUser(Users user) {

		try {
			PreparedStatement pre = con.prepareStatement(
					"insert into users(userFirstName, userLastName, userEmail, userMob, password) values(?,?,?,?,?)");
			pre.setString(1, user.getUserFirstName());
			pre.setString(2, user.getUserLastName());
			pre.setString(3, user.getUserEmail());
			pre.setString(4, user.getUserMob());
			pre.setString(5, user.getPassword());
			pre.executeUpdate();

			String message = "Data Stored";
			return message;

		} catch (Exception e) {
			e.printStackTrace();
			String message = "Data Not Inserted";
			return message;

		}

	}

	@Override
	public String UpdateUser(Users user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String DeleteUser() {
		// TODO Auto-generated method stub
		return null;
	}

	public LoginDetails Login(String userName, String password) {

		LoginDetails output = new LoginDetails();
		try {
			String sql = "select userFirstName ,userLastName,userEmail,B.rolName \r\n"
					+ "from users as A join roles as B on A.rolId=B.rolId where userEmail='" + userName
					+ "' and password='" + password + "'";

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				output.setFirstName(rs.getString("userFirstName"));
				output.setLastName(rs.getString("userLastName"));
				output.setEmail(rs.getString("userEmail"));
				output.setRole(rs.getString("rolName"));
			}
		} catch (Exception e) {
			Log obj = new Log();
			obj.setEventName("UserRegistration");
			obj.setEventMessage(e.getMessage());
			obj.setEventType("Exception");
			Logger.WriteLog(obj);
		}

		return output;
	}


	

	@Override
	public List<Users> ViewAllUsers() {
		List<Users> listusers=new ArrayList<Users>();
		try {
			Statement st=con.createStatement();
			String sql="Select userId, userFirstName, userLastName, userEmail, userDob, userContact, userMob, userName, rolId, userPan, userAdhar, userWorkExp, userAddress, userJoiningDate, userResignDate, isActive, updateUser, updateDate FROM users where delInd = 'N';";
			ResultSet rs=st.executeQuery(sql);
			
			while (rs.next()) {
				Users user=new Users();

				user.setUserId(rs.getInt("userId"));
				user.setUserFirstName(rs.getString("userFirstName"));
				user.setUserLastName(rs.getString("userLastName"));
				user.setUserEmail(rs.getString("userEmail"));
				user.setUserDob(rs.getDate("userDob"));
				user.setUserContact(rs.getString("userContact"));
				user.setUserMob(rs.getString("userMob"));
				user.setUserName(rs.getString("userName"));
				user.setRolId(rs.getInt("rolId"));
				user.setUserPan(rs.getString("userPan"));
				user.setUserAdhar(rs.getString("userAdhar"));
				user.setUserWorkExp(rs.getString("userWorkExp"));
				user.setUserAddress(rs.getString("userAddress"));
				user.setUserJoiningDate(rs.getDate("userJoiningDate"));
				user.setUserResignDate(rs.getDate("userResignDate"));
				user.setIsActive(rs.getString("isActive"));
				user.setUpdateUser(rs.getString("updateUser"));
				user.setUpdateDate(rs.getDate("updateDate"));


				listusers.add(user);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return listusers;
	}

	public List<Users> GetEngineerList() {
		List<Users> listEngineers = new ArrayList<Users>();
		try {
			Statement st = con.createStatement();
			String sql = "select userId,userFirstName,userLastName,rolName from \r\n"
					+ "users as A join roles as B on A.rolId=B.rolId where \r\n"
					+ "A.delInd=\"N\" and rolName=\"Engineer\"";
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				Users user = new Users();
				user.setUserId(rs.getInt("userId"));
				user.setFullName(rs.getInt("userId")+","+rs.getString("userFirstName")+" "+rs.getString("userLastName"));
				user.setUserFirstName(rs.getString("userFirstName"));
				user.setUserLastName(rs.getString("userLastName"));
				user.setRolName(rs.getString("rolName"));
				listEngineers.add(user);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listEngineers;
	}
}
