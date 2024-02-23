package com.TaskManagement.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.TaskManagement.entity.Task;
import com.TaskManagement.entity.TaskSearch;
import com.TaskManagement.service.TaskService;

@RestController
@RequestMapping("Task")
@Validated
public class TaskController {
	
	TaskService tserv;

	public TaskController(TaskService tserv) {
		super();
		this.tserv = tserv;
	}
	
	@PostMapping
	public String createTask(@RequestBody Task t) {
		String msg = tserv.createTask(t);
		return msg;
	}
	
	@GetMapping("SearchTask")
	public List<Task> searchTask(@RequestBody  TaskSearch taskSearch){
		return tserv.searchTask(taskSearch);
	}
	
	@GetMapping
	public List<Task> getAllTask(){
		List<Task> tall= tserv.getAllTask();
		return tall;
	}
	
	@PutMapping
	public String updateTask(@RequestBody Task updatedTask) {
		String msg = tserv.updateTask(updatedTask);
		return msg;
	}
	
	@DeleteMapping(value="{id}")
	public String deleteTask(@PathVariable int id) {
		String msg = tserv.deleteTask(id);
		return msg;
		
	}
	
	

	

}
