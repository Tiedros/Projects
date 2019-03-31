package com.tiedros.project.dao;

import com.tiedros.project.DataStore;
import com.tiedros.project.entity.User;

public class UserDAO {

	public User [] getUsers() {
		return DataStore.getUsers();
	}
}
