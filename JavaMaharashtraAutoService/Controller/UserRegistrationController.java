package com.JavaMaharashtraAutoService.Controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.JavaMaharashtraAutoService.Dao.Products;
import com.JavaMaharashtraAutoService.Dao.UserRegistration;
import com.JavaMaharashtraAutoService.Model.LoginDetails;
import com.JavaMaharashtraAutoService.Model.Users;






@RestController
public class UserRegistrationController {

	@RequestMapping(value = "/AddUser", method = RequestMethod.POST)

	@ResponseBody
	String AddUsers(@ModelAttribute("Users") Users user) {
		UserRegistration regdao = new UserRegistration();
		return regdao.AddUser(user);
	}

	@CrossOrigin
	@RequestMapping(value = "/Login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)

	public @ResponseBody LoginDetails Login(@RequestBody com.JavaMaharashtraAutoService.Model.LoginModel loginModel) {

		LoginDetails json = new LoginDetails();

		UserRegistration obj = new UserRegistration();

		json = obj.Login(loginModel.getUserName(), loginModel.getPassword());

		return json;
	}
	
	
	

	@RequestMapping(value="/ViewAllUsers", method=RequestMethod.GET)
	public List<Users> ViewAllUsers()
	{
		UserRegistration object=new UserRegistration();
		
		return object.ViewAllUsers();
		
	}
	
	
	
	
	

	
	
	
	@RequestMapping(value = "/GetEngineerList", method = RequestMethod.GET)
	public List<Users> GetEngineerList() {
		UserRegistration object = new UserRegistration();

		return object.GetEngineerList();

	}

}





