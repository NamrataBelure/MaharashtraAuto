package com.JavaMaharashtraAutoService.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.JavaMaharashtraAutoService.Dao.EngAssDao;
import com.JavaMaharashtraAutoService.Model.EngAssModel;

@RestController
public class EngAssController {

	@RequestMapping(value = "/AddAssignment", method = RequestMethod.POST)
	@ResponseBody
	public void AddAssignment(@ModelAttribute("EngAssModel") EngAssModel engass, HttpServletResponse response)
			throws IOException {
		EngAssDao obj = new EngAssDao();
		obj.AddEngAss(engass);
		response.sendRedirect("/ViewAssignments.html");

	}

	@RequestMapping(value = "/GetAssignments", method = RequestMethod.GET)
	List<EngAssModel> GetAssignment() {

		EngAssDao obj = new EngAssDao();
		return obj.GetAssignments();

	}

}
