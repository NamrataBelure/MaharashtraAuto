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
		List<Jobs> listJobs = new ArrayList<Jobs>();
		try
		{
			Statement st = con.createStatement();
			String sql = "SELECT jobid,vin FROM masdb.job where delInd='N';";
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				Jobs user = new Jobs();
				user.setJobId(rs.getInt("jobid"));
				user.setVin(rs.getString("vin"));
				listJobs.add(user);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return listJobs;
	}
	
	public List<Jobs> GetJobDetails() {
		List<Jobs> listjobs = new ArrayList<Jobs>();
		try
		{
			Statement st = con.createStatement();
			String sql = "select A.vin,A.jobid,a.userId,b.userFirstName,b.userLastName,\r\n" + 
					" a.vinKm,a.serdate,a.deldate,a.jobdes,a.estamt,c.jobstatus,\r\n" + 
					" c.assdate,c.esttimeTaken,c.isAvailable\r\n" + 
					" from job as A join users as B on A.userId=B.userID\r\n" + 
					" join engassignment as C on A.jobId=C.jobId where a.delInd='N';";
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next())
			{
				Jobs job = new Jobs();
				job.setVin(rs.getString("vin"));
				job.setJobId(rs.getInt("jobId"));
				job.setUserId(rs.getString("userId"));
				job.setUserFirstName(rs.getString("userFirstName"));
				job.setUserLastName(rs.getString("userLastName"));
				job.setFullName(rs.getInt("userId")+","+rs.getString("userFirstName")+" "+rs.getString("userLastName"));
				job.setVinKm(rs.getString("vinKm"));
				job.setSerDate(rs.getString("serDate"));
				job.setDelDate(rs.getString("delDate"));
				job.setJobDes(rs.getString("jobDes"));
				job.setEstAmt(rs.getString("estAmt"));
				job.setJobStatus(rs.getString("jobStatus"));
				job.setAssDate(rs.getString("assdate"));
				job.setEstTimeTaken(rs.getString("esttimeTaken"));
				
							
				listjobs.add(job);
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return listjobs;
	}
}