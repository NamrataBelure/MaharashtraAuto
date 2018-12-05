package com.JavaMaharashtraAutoService.Dao;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.JavaMaharashtraAutoService.Model.Log;
import com.JavaMaharashtraAutoService.Model.LoginDetails;
import com.JavaMaharashtraAutoService.Model.Users;
import com.JavaMaharashtraAutoService.Util.ConverterUtil;
import com.JavaMaharashtraAutoService.Util.Logger;
import com.JavaMaharashtraAutoService.Util.MySQLConnection;

import net.minidev.asm.ConvertDate;

public class UserRegistration implements IUserRegistration {
	Connection con;

	public UserRegistration() {
		con = MySQLConnection.GetConnection();
	}

	@Override
	public String AddUser(Users user) {

		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		    Date date = new Date();  
		    
			PreparedStatement pre = con.prepareStatement(
					"insert into users(userFirstName, userLastName, userEmail, userMob, password,userName,delInd,isActive,updateUser,updateDate,userImagePath) values(?,?,?,?,?,?,?,?,?,?,?)");
			pre.setString(1, user.getUserFirstName());
			pre.setString(2, user.getUserLastName());
			pre.setString(3, user.getUserEmail());
			pre.setString(4, user.getUserMob());
			pre.setString(5, user.getPassword());
			pre.setString(6, user.getUserEmail());
			pre.setString(7, "N");
			pre.setString(8, "Y");
			pre.setString(9, user.getUserEmail());
			pre.setDate(10, ConverterUtil.convertFromJAVADateToSQLDate(date));
			pre.setString(11, "/img/default.jpg");
						
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
		String sysPath = "F:\\JavaGit\\NewMaster\\OmegaSoft2018EMP\\JavaMaharashtraAutoService\\src\\main\\resources\\static\\upload\\";
		
		try {
			String imagePath="/img/default.jpg";
			if (!user.getUpload().isEmpty()) {
				imagePath= "/upload/" + user.getUserEmail();
			}
			PreparedStatement pre = con.prepareStatement("UPDATE masdb.users\r\n" + "SET\r\n" + "userFirstName = '"
					+ user.getUserFirstName() + "'," + "userLastName = '" + user.getUserLastName() + "',"
					+ "userDob = '" + user.getUserDob() + "'" + ",userContact = '" + user.getUserContact() + "'"
					+ ", userMob = '" + user.getUserMob() + "'" + ", userPan = '" + user.getUserPan() + "'"
					+ ", userAdhar = '" + user.getUserAdhar() + "'" + ", userWorkExp = '" + user.getUserWorkExp() + "'"
					+ ", userAddress = '" + user.getUserAddress() + "'" + ", userImagePath ='"+imagePath+"' , isActive ='Y'"
					+ ", userJoiningDate='"+user.getUserJoiningDate()+"' WHERE  userEmail = '" + user.getUserEmail() + "'");

			pre.executeUpdate();
			SaveImageOnSharePath(user.getUpload(),sysPath,user.getUserEmail());
			String message = "Data Updated";
			return message;

		} catch (Exception e) {
			e.printStackTrace();
			String message = "Data Not Updated";
			return message;

		}
	}
		
		
		public String UpdateRole (Users user) {
		try {
			
					PreparedStatement pre = con.prepareStatement("UPDATE masdb.users\r\n" + "SET\r\n" + "rolId = '"
					+ user.getRolId() + "' WHERE  userId = '" + user.getUserId() + "'");

			pre.executeUpdate();
			
			String message = "Role Has been Assigned Successfully";
			
			return message;

		} catch (Exception e) {
			e.printStackTrace();
			String message = "Data Not Updated";
			return message;

		}
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
		List<Users> listusers = new ArrayList<Users>();
		try {
			Statement st = con.createStatement();
			String sql = "Select userId, userFirstName, userLastName, userEmail, userDob, userContact, userMob, userName, rolId, userPan, userAdhar, userWorkExp, userAddress, userJoiningDate, userResignDate, isActive, updateUser, updateDate FROM users where delInd = 'N';";
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				Users user = new Users();

				user.setUserId(rs.getInt("userId"));
				user.setUserFirstName(rs.getString("userFirstName"));
				user.setUserLastName(rs.getString("userLastName"));
				user.setFullName(rs.getInt("userId") + "," + rs.getString("userFirstName") + " " + rs.getString("userLastName"));
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
				user.setFullName(
						rs.getInt("userId") + "," + rs.getString("userFirstName") + " " + rs.getString("userLastName"));
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

	public Users GetUsers(String userEmail) {

		Users users = new Users();
		try {
			String sql = "SELECT * FROM users where delInd='N' and userEmail='" + userEmail + "';";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				users.setUserId(rs.getInt("userId"));
				users.setUserFirstName(rs.getString("userFirstName"));
				users.setUserLastName(rs.getString("userLastName"));
				users.setUserEmail(rs.getString("userEmail"));
				users.setUserDob(rs.getDate("userDob"));
				users.setUserContact(rs.getString("userContact"));
				users.setUserMob(rs.getString("userMob"));
				users.setUserName(rs.getString("userName"));
				users.setUserPan(rs.getString("userPan"));
				users.setUserAdhar(rs.getString("userAdhar"));
				users.setUserWorkExp(rs.getString("userWorkExp"));
				users.setUserAddress(rs.getString("userAddress"));
				users.setUserJoiningDate(rs.getDate("userJoiningDate"));
				users.setUserResignDate(rs.getDate("userResignDate"));
				users.setIsActive(rs.getString("isActive"));
				users.setUserImagePath(rs.getString("userImagePath"));
				users.setUpdateDate(rs.getDate("updateDate"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return users;

	}

	private void SaveImageOnSharePath(MultipartFile file, String sysPath, String email) {
		try {
			if (!file.isEmpty()) {
				byte[] bytes = file.getBytes();
				Path path = Paths.get(sysPath + email);
				Files.write(path, bytes);
			}
		} catch (Exception e) {

		}
	}

}
