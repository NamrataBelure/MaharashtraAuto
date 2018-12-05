package com.JavaMaharashtraAutoService.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

import com.JavaMaharashtraAutoService.Model.Log;

public class Logger {

	static Connection con;

	public static void WriteLog(Log log) {
		try {

			con = MySQLConnection.GetConnection();

			Date curDate = new Date();

			String user = "Abhishek";

			PreparedStatement pre = con.prepareStatement(
					"INSERT INTO log (eventName,eventDate,errorMessage,eventType,updateUser,updateDate) VALUES (?,?,?,?,?,?)");

			pre.setString(1, log.getEventName());
			pre.setDate(2, ConverterUtil.convertFromJAVADateToSQLDate(curDate));
			pre.setString(3, log.getEventMessage());
			pre.setString(4, log.getEventType());
			pre.setString(5, user);
			pre.setDate(6, (java.sql.Date) curDate);
			pre.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
