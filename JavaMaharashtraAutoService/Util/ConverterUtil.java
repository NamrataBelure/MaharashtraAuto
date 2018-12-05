package com.JavaMaharashtraAutoService.Util;

public class ConverterUtil {

	public static java.sql.Date convertFromJAVADateToSQLDate(
	        java.util.Date javaDate) {
	    java.sql.Date sqlDate = null;
	    
	    if (javaDate != null) {
	        sqlDate = new java.sql.Date(javaDate.getTime());
	    }
	    return sqlDate;
	}
}
