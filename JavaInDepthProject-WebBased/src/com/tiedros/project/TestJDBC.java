package com.tiedros.project;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJDBC {

	public static void main(String[] args) {
		String jdbcUrl = "jdbc:mysql://localhost:3306/CoreJavaProject?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=US/Central";
		String user="springstudent";
		String pass="springstudent";
		
		try {
			System.out.println("Connecting to:  "+ jdbcUrl);
			
			Connection myConnection = DriverManager.getConnection(jdbcUrl, user, pass);
			
			System.out.println("Connection Succesful!!!");
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}

	}

}
