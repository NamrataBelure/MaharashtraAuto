package com.JavaMaharashtraAutoService.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.JavaMaharashtraAutoService.Dao.JobDao;
import com.JavaMaharashtraAutoService.Model.Jobs;

@RestController
public class JobController {

	@RequestMapping(value = "/AddJobs", method = RequestMethod.POST)
	public @ResponseBody String AddJobs(@ModelAttribute("Jobs") Jobs job) {
		JobDao obj = new JobDao();
		return obj.AddJob(job);
	}
	
	@RequestMapping(value = "/GetJobsList", method = RequestMethod.GET)
	public List<Jobs> GetJobsList() {
		JobDao obj = new JobDao();
		return obj.GetJobs();

	}
	@RequestMapping(value = "/GetJobDetails", method = RequestMethod.GET)
	public List<Jobs> GetJobDetails() {
		JobDao obj = new JobDao();
		return obj.GetJobDetails();

	}
	
	
}
