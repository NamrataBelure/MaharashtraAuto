package com.JavaMaharashtraAutoService.Util;

import java.sql.Connection;
import java.sql.DriverManager;

import com.JavaMaharashtraAutoService.Model.Log;

public class DBConnection {

	private static Connection con = null;

	public static Connection GetConnection() {
		try {
			if (con == null) {
				String driver = "com.mysql.jdbc.Driver";
				String url = "jdbc:mysql://localhost:3306/masdb";
				String user = "root";
				String password = "root";
				Class.forName(driver);

				con = DriverManager.getConnection(url, user, password);
			}
		} catch (Exception e) {
			
			Log obj = new Log();
			obj.setEventName("DBConnectionClass");
			obj.setEventMessage(e.getMessage());			
			obj.setEventType("Exception");
			
			Logger.WriteLog(obj);
		}

		return con;
	}
}
