package com.JavaMaharashtraAutoService.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.JavaMaharashtraAutoService.Model.EngAssModel;
import com.JavaMaharashtraAutoService.Util.DBConnection;

public class EngAssDao implements IEngAss {

	Connection con;

	public EngAssDao() {
		con = DBConnection.GetConnection();

	}

	@Override
	public String AddEngAss(EngAssModel engass) {
		String output = "";
		try {
			PreparedStatement pre = con.prepareStatement(
					"Insert into engassignment (userId, jobId, assDate, esttimeTaken, isAvailable, jobStatus, delInd) values (?,?,?,?,?,?,?)");

			pre.setString(1, engass.getUserId());
			pre.setString(2, engass.getJobId());
			pre.setString(3, engass.getAssDate());
			pre.setString(4, engass.getEsttimeTaken());
			pre.setString(5, engass.getIsAvailable());
			pre.setString(6, engass.getJobStatus());
			pre.setString(7, "N");

			pre.executeUpdate();
			output = "Data has been inserted successfully.";

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output;
	}

	@Override
	public String UpdateEngAss(EngAssModel engass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String DeleteEngAss() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EngAssModel> GetAssignments() {

		List<EngAssModel> listassign = new ArrayList<EngAssModel>();
		try {
			Statement st = con.createStatement();
			String sql = "SELECT * from engassignment;";
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				EngAssModel es = new EngAssModel();
				es.setUserId(rs.getString("userId"));
				es.setJobId(rs.getString("jobId"));
				es.setAssDate(rs.getString("assDate"));
				es.setEsttimeTaken(rs.getString("esttimeTaken"));
				es.setIsAvailable(rs.getString("isAvailable"));
				es.setJobStatus(rs.getString("jobStatus"));
				es.setUpdateUser(rs.getString("updateUser"));
				es.setUpdateDate(rs.getString("updateDate"));

				listassign.add(es);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listassign;
	}

}
