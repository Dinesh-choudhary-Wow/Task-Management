package com.TaskManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TaskManagement.entity.User;

public interface UserDao extends JpaRepository<User, Integer> {

	User findByName(String name);
}
