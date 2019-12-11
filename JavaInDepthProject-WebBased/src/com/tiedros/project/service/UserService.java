package com.tiedros.project.service;

import java.util.List;

import com.mysql.cj.util.StringUtils;
import com.tiedros.project.dao.UserDAO;
import com.tiedros.project.entity.User;
import com.tiedros.project.enums.Gender;
import com.tiedros.project.enums.UserType;
import com.tiedros.project.util.StringUtil;

public class UserService {

	private static UserService userService= new UserService();
	private static UserDAO dao=new UserDAO();
	
	private UserService() {
		
	}
	
	public static UserService getInstance() {
		return userService;
	}
	
	public User createUser(long id,String email, String password, String firstName, String lastName,  Gender gender, UserType userType) {
		
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

	public User getUser(long userId) {
		return dao.getUser(userId);
		
	}

	public long authenticate(String email, String password) {
		return dao.authenticate(email,StringUtil.encodePassword(password));
		
	}
	
}
