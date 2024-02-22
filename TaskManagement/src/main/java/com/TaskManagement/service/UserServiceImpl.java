package com.TaskManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TaskManagement.dao.UserDao;
import com.TaskManagement.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User saveUser(User user) {
		return userDao.save(user);
	}

	@Override
	public User updateUser(int id, User user)  {

		Optional<User> optionalUser = userDao.findById(id);

		if (optionalUser.isPresent()) {
			User existingUser = optionalUser.get();
			existingUser.setName(user.getName());
			existingUser.setEmail(user.getEmail());
			existingUser.setPassword(user.getPassword());
			existingUser.setRole(user.getRole());
			return userDao.save(existingUser);
		}else {
			return null;
		}
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.findAll();
	}

	@Override
	public boolean deleteUser(int id) {
		boolean user = userDao.existsById(id);
		if(user) {
			userDao.deleteById(id);
			return user;
		}
		return user;
	}

}
