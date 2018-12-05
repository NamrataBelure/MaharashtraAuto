package com.JavaMaharashtraAutoService.Dao;

import com.JavaMaharashtraAutoService.Model.Contact;

public interface IContactDao {

	String AddContact(Contact contact);
	String UpdateContact(Contact contact);
	String Delete();
}
