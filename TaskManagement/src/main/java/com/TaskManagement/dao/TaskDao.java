package com.TaskManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TaskManagement.entity.Task;

public interface TaskDao extends JpaRepository<Task,Integer> {

	boolean existsBytaskName(String taskName);

	boolean existsByTaskName(String taskName);

}
