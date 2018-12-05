package com.JavaMaharashtraAutoService.Dao;

import java.util.List;

import com.JavaMaharashtraAutoService.Model.Jobs;

public interface IJobDao {

	String AddJob(Jobs jobs);
	String UpdateJob(Jobs jobs);
	String DeleteJob();
	List<Jobs> GetJobs();
	

}
