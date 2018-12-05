package com.JavaMaharashtraAutoService.Dao;

import java.util.List;

import com.JavaMaharashtraAutoService.Model.Users;

public interface IUserRegistration {
String AddUser(Users user);
String UpdateUser(Users user);
String DeleteUser();
List <Users> ViewAllUsers();
}
