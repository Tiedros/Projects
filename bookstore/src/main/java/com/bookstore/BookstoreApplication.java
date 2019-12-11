package com.bookstore;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bookstore.domain.User;
import com.bookstore.domain.security.Role;
import com.bookstore.domain.security.UserRole;
import com.bookstore.service.UserService;
import com.bookstore.utility.SecurityUtility;

@SpringBootApplication
public class BookstoreApplication implements CommandLineRunner {
	
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
	
		
		User user1=new User();
		user1.setFirstName("code");
		user1.setLastName("code");
		user1.setUsername("c");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("c"));
		user1.setEmail("codedriveslife@gmail.com");
		Set<UserRole> userRoles=new HashSet<>();
		Role role1=new Role();
		//role1.setRoleId(1);
		role1.setName("Role_User");
		userRoles.add(new UserRole(user1, role1));
		
		userService.createUser(user1,userRoles);
	
	}

}
