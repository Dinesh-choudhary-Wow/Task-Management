package com.TaskManagement.service;

import java.util.List;
import java.util.Optional;

import com.TaskManagement.entity.Task;
import com.TaskManagement.entity.TaskSearch;

public interface TaskService {
	
	String createTask(Task t);
	List<Task> searchTask(TaskSearch taskSearch);
	List<Task> getAllTask();
	String updateTask(Task updatedTask);
	String deleteTask(int id);
	 

}
