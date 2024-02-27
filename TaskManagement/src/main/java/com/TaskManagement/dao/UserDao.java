package com.TaskManagement.dao;

import com.TaskManagement.entity.User;

public interface UserDao extends JpaRepository<User, Integer> {

	User findByName(String name);
}
