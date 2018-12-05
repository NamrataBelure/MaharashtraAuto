package com.JavaMaharashtraAutoService.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.JavaMaharashtraAutoService.Model.Jobs;
import com.JavaMaharashtraAutoService.Util.DBConnection;

public class JobDao implements IJobDao {

	Connection con;

	public JobDao() {
		con = DBConnection.GetConnection();
	}

	@Override
	public String AddJob(Jobs jobs) {
		String output = "";
		try {
			PreparedStatement pre = con.prepareStatement(
					"insert into job(vin,vinKm,serDate,delDate,jobDes,estAmt,userId,delInd,updateUser,updateDate) values(?,?,?,?,?,?,?,?,?,?)");

			pre.setString(1, jobs.getVin());
			pre.setString(2, jobs.getVinKm());
			pre.setString(3, jobs.getSerDate());
			pre.setString(4, jobs.getDelDate());
			pre.setString(5, jobs.getJobDes());
			pre.setString(6, jobs.getEstAmt());
			pre.setString(7, jobs.getUserId());
			pre.setString(8, "N");
			pre.setString(9, jobs.getUpdateUser());
			pre.setDate(10, jobs.getUpdateDate());
			pre.executeUpdate();
			output = "Data has been inserted successfully.";

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output;
	}

	@Override
	public String UpdateJob(Jobs job) {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String DeleteJob() {
		// TODO Auto-generated method stub
		return "";
	}

	//*public List<Jobs> GetJobList() {
	//	List<Jobs> listJobs = new ArrayList<Jobs>();
	////	try {
	//		Statement st = con.createStatement();
	///		String sql = "SELECT jobid,vin FROM masdb.job where delInd='N';";
	//		ResultSet rs = st.executeQuery(sql);

	///		while (rs.next()) {
	// Jobs user = new Jobs();
	//			user.setJobId(rs.getInt("jobid"));
	//			user.setVin(rs.getString("vin"));
	//			listJobs.add(user);
	//		}
	//	} catch (Exception e) {
		//	e.printStackTrace();
		//}

		//return listJobs;
	//}  


	@Override
	public List<Jobs> GetJobs() {
		List<Jobs> listjobs = new ArrayList<Jobs>();
		try
		{
			Statement st = con.createStatement();
			String sql = "SELECT jobId, vin, vinKm, serDate, delDate, jobDes, estAmt, userId from job where delInd = 'N'; ";
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next())
			{
				Jobs job = new Jobs();
				job.setJobId(rs.getInt("jobId"));
				job.setVin(rs.getString("vin"));
				job.setVinKm(rs.getString("vinKm"));
				job.setSerDate(rs.getString("serDate"));
				job.setDelDate(rs.getString("delDate"));
				job.setJobDes(rs.getString("jobDes"));
				job.setEstAmt(rs.getString("estAmt"));
				job.setUserId(rs.getString("userId"));
				
				listjobs.add(job);
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return listjobs;
	}
}