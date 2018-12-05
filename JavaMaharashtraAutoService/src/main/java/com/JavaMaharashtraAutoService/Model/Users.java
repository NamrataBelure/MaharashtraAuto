
package com.JavaMaharashtraAutoService.Model;

import java.sql.Date;

public class Users<MultipartFile> {
private int userId;
private String userFirstName;
private String userLastName;
private String userEmail;
private Date userDob;
private String userContact;
private String userMob;
private String userName;
private String password;
private int rolId;
private String userPan;
private String userAdhar;
private String userWorkExp;
private String userAddress;
private Date userJoiningDate;
private Date userResignDate;
private String userImagePath;
private String isActive;
private String delInd;
private String updateUser;
private Date updateDate;
private String rolName;
private String fullName;
private MultipartFile upload;

public MultipartFile getUpload() {
	return upload;
}
public void setUpload(MultipartFile upload) {
	this.upload = upload;
}
public String getFullName() {
	return fullName;
}
public void setFullName(String fullName) {
	this.fullName = fullName;
}
public String getRolName() {
	return rolName;
}
public void setRolName(String rolName) {
	this.rolName = rolName;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public String getUserFirstName() {
	return userFirstName;
}
public void setUserFirstName(String userFirstName) {
	this.userFirstName = userFirstName;
}
public String getUserLastName() {
	return userLastName;
}
public void setUserLastName(String userLastName) {
	this.userLastName = userLastName;
}
public String getUserEmail() {
	return userEmail;
}
public void setUserEmail(String userEmail) {
	this.userEmail = userEmail;
}
public Date getUserDob() {
	return userDob;
}
public void setUserDob(Date userDob) {
	this.userDob = userDob;
}
public String getUserContact() {
	return userContact;
}
public void setUserContact(String userContact) {
	this.userContact = userContact;
}
public String getUserMob() {
	return userMob;
}
public void setUserMob(String userMob) {
	this.userMob = userMob;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public int getRolId() {
	return rolId;
}
public void setRolId(int rolId) {
	this.rolId = rolId;
}
public String getUserPan() {
	return userPan;
}
public void setUserPan(String userPan) {
	this.userPan = userPan;
}
public String getUserAdhar() {
	return userAdhar;
}
public void setUserAdhar(String userAdhar) {
	this.userAdhar = userAdhar;
}
public String getUserWorkExp() {
	return userWorkExp;
}
public void setUserWorkExp(String userWorkExp) {
	this.userWorkExp = userWorkExp;
}
public String getUserAddress() {
	return userAddress;
}
public void setUserAddress(String userAddress) {
	this.userAddress = userAddress;
}
public Date getUserJoiningDate() {
	return userJoiningDate;
}
public void setUserJoiningDate(Date userJoiningDate) {
	this.userJoiningDate = userJoiningDate;
}
public Date getUserResignDate() {
	return userResignDate;
}
public void setUserResignDate(Date userResignDate) {
	this.userResignDate = userResignDate;
}
public String getUserImagePath() {
	return userImagePath;
}
public void setUserImagePath(String userImagePath) {
	this.userImagePath = userImagePath;
}
public String getIsActive() {
	return isActive;
}
public void setIsActive(String isActive) {
	this.isActive = isActive;
}
public String getDelInd() {
	return delInd;
}
public void setDelInd(String delInd) {
	this.delInd = delInd;
}
public String getUpdateUser() {
	return updateUser;
}
public void setUpdateUser(String updateUser) {
	this.updateUser = updateUser;
}
public Date getUpdateDate() {
	return updateDate;
}
public void setUpdateDate(Date updateDate) {
	this.updateDate = updateDate;
}



}
