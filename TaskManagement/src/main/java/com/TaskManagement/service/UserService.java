package com.TaskManagement.service;

import java.util.List;

import com.TaskManagement.entity.User;

public interface UserService {
	User saveUser(User user);

	User updateUser(int id, User user);

	List<User> getAllUsers();

	boolean deleteUser(int id);
}
