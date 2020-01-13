package com.bookstore.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.bookstore.domain.User;

@Component
public class MailConstructor {
	
	@Autowired
	private Environment env;
	
	public SimpleMailMessage constructNewUserEmail(User user,String password) {
		String message="\nPlease use the following credentials to log in and edit your personal information including your own password."
		+ "\nUsername: "+user.getUsername() + "\nPassword: "+ password;
		
		SimpleMailMessage email= new SimpleMailMessage();
		email.setText(user.getEmail());
		email.setSubject("Tiedros' Book Store - New User");
		email.setText(message);
		email.setTo(user.getEmail());
		email.setFrom(env.getProperty("support.email"));
		return email;
		
	}

}
