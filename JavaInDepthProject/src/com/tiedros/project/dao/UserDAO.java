package com.tiedros.project.dao;

import java.util.List;

import com.tiedros.project.DataStore;
import com.tiedros.project.entity.User;

public class UserDAO {

	public List<User> getUsers() {
		return DataStore.getUsers();
	}
}
