package com.tiedros.project.service;

import java.util.List;

import com.tiedros.project.dao.UserDAO;
import com.tiedros.project.entity.User;

public class UserService {

	private static UserService userService= new UserService();
	private static UserDAO dao=new UserDAO();
	
	private UserService() {
		
	}
	
	public static UserService getInstance() {
		return userService;
	}
	
	public User createUser(long id,String email, String password, String firstName, String lastName,  int gender, String userType) {
		
		User user=new User();
		
		user.setId(id);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPassword(password);
		user.setGender(gender);
		user.setUserType(userType);
		
		return user;
	}
	
	public List<User> getUsers() {
		return dao.getUsers();
	}
	
}
