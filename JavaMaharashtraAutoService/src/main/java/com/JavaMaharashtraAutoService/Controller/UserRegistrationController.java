package com.JavaMaharashtraAutoService.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
	void AddUsers(@ModelAttribute("Users") Users user, HttpServletResponse response) throws IOException {
		UserRegistration regdao = new UserRegistration();
		regdao.AddUser(user);
		response.sendRedirect("/UserProfile.html?userEmail=" + user.getUserEmail());

	}

	@RequestMapping(value = "/GetEmail", method = RequestMethod.GET)

	public Users GetUsers(String userEmail) {
		UserRegistration user = new UserRegistration();
		return user.GetUsers(userEmail);

	}

	@CrossOrigin
	@RequestMapping(value = "/Login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)

	public @ResponseBody LoginDetails Login(@RequestBody com.JavaMaharashtraAutoService.Model.LoginModel loginModel) {

		LoginDetails json = new LoginDetails();

		UserRegistration obj = new UserRegistration();

		json = obj.Login(loginModel.getUserName(), loginModel.getPassword());

		return json;
	}

	@RequestMapping(value = "/ViewAllUsers", method = RequestMethod.GET)
	public List<Users> ViewAllUsers() {
		UserRegistration object = new UserRegistration();

		return object.ViewAllUsers();

	}

	@RequestMapping(value = "/GetEngineerList", method = RequestMethod.GET)
	public List<Users> GetEngineerList() {
		UserRegistration object = new UserRegistration();

		return object.GetEngineerList();

	}

	@RequestMapping(value = "/UpdateUser", method = RequestMethod.POST, consumes = "multipart/form-data")
	public @ResponseBody String UpdateUser(@ModelAttribute("Users") Users user) {
		UserRegistration obj = new UserRegistration();
		return obj.UpdateUser(user);

	}

	@RequestMapping(value = "/UpdateRole", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String UpdateRole(@ModelAttribute(" Users") Users user) {
		UserRegistration obj = new UserRegistration();
		return obj.UpdateRole(user);
	}

}
