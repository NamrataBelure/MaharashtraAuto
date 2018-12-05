package com.JavaMaharashtraAutoService.Dao;

import java.util.List;

import com.JavaMaharashtraAutoService.Model.Roles;

public interface IRoleDao {

	String AddRole(Roles roles);
	String UpdateRole(Roles roles);
	String DeleteRole();
	List<Roles> GetRoles();
}
