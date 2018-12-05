package com.JavaMaharashtraAutoService.Controller;

import java.util.List;


import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.JavaMaharashtraAutoService.Dao.ContactDao;
import com.JavaMaharashtraAutoService.Model.Contact;


@RestController
public class ContactController {
	@RequestMapping(value = "/AddContact", method = RequestMethod.POST)
	public @ResponseBody String AddContact(@ModelAttribute("Contact") Contact  contact) {
		ContactDao obj = new ContactDao();
		return obj.AddContact(contact);
	}
	
	@RequestMapping(value="/GetContacts",method=RequestMethod.GET)
	public List<Contact> GetContacts()
	{
		ContactDao obj=new ContactDao();
		return obj.GetContacts(); 
		
	}

}
