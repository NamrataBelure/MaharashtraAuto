package com.JavaMaharashtraAutoService.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.JavaMaharashtraAutoService.Dao.RoleDao;
import com.JavaMaharashtraAutoService.Model.Roles;

@RestController
public class RolesController {

	@RequestMapping(value="/AddRole",method=RequestMethod.POST)
	@ResponseBody
	public String AddRoles(@ModelAttribute ("Roles") Roles role)
	{
		RoleDao obj=new RoleDao();			
		return obj.AddRole(role);
	}
	
	@RequestMapping(value="/GetRoles",method=RequestMethod.GET)
	public List<Roles> GetRoleList()
	{
		RoleDao obj=new RoleDao();
		return obj.GetRoles();
	}
}
