package com.TaskManagement.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.TaskManagement.dao.TaskDao;
import com.TaskManagement.entity.Task;
import com.TaskManagement.entity.TaskSearch;
import com.TaskManagement.exception.TaskNotFoundException;

@Service
public class TaskServiceImpl implements TaskService {
	TaskDao trepo;
	
	

	public TaskServiceImpl(TaskDao trepo) {
		super();
		this.trepo = trepo;
	}
	
	@Override
	public String createTask(Task t) {
	    // Validate that required fields are not null or empty
	    if (anyFieldIsNullOrEmpty(
	            t.getTaskName(), t.getDescription(), t.getStatus(),
	            formatDate(t.getStartDate()), formatDate(t.getEndDate()),
	            t.getCreatedBy(), t.getCreatedOn(), t.getUpdatedBy(), t.getUpdatedOn())) {
	        return "Error: All fields must be provided and not empty";
	    }

	    // Check if a task with the same taskName already exists
	    if (trepo.existsByTaskName(t.getTaskName())) {
	        throw new TaskNotFoundException("Task with name '" + t.getTaskName() + "' already exists");
	    }

	    // Save the task if validation passes
	    trepo.save(t);
	    return "Task is created";
	}

	// Helper method to check if any of the provided strings is null or empty
	private boolean anyFieldIsNullOrEmpty(Object... objects) {
	    for (Object obj : objects) {
	        if (obj == null) {
	            return true;
	        }
	        if (obj instanceof String && ((String) obj).trim().isEmpty()) {
	            return true;
	        }
	        if (obj instanceof LocalDate && ((LocalDate) obj).equals(LocalDate.MIN)) {
	            return true;
	        }
	    }
	    return false;
	}

	// Helper method to format LocalDate to String and handle null values
	private String formatDate(LocalDate date) {
	    return (date != null) ? date.toString() : null;
	}

	
//	@Override
//	public String createTask(Task t) {
//	    // Validate that required fields are not null
//	    if (anyFieldIsNull(t.getTaskName(), t.getDescription(), t.getStatus(),
//	            t.getStartDate(), t.getEndDate(), t.getCreatedBy(),
//	            t.getCreatedOn(), t.getUpdatedBy(), t.getUpdatedOn())) {
//	        return "Error: All fields must be provided";
//	    }
//
//	    // Check if a task with the same taskName already exists
//	    if (trepo.existsByTaskName(t.getTaskName())) {
//	        throw new TaskNotFoundException("Task with name '" + t.getTaskName() + "' already exists");
//	    }
//
//	    // Save the task if validation passes
//	    trepo.save(t);
//	    return "Task is created";
//	}
//
//	// Helper method to check if any of the provided objects is null
//	private boolean anyFieldIsNull(Object... objects) {
//	    for (Object obj : objects) {
//	        if (obj == null) {
//	            return true;
//	        }
//	    }
//	    return false;
//	}


	@Override
    public List<Task> searchTask(TaskSearch taskSearch) {
        List<Task> tasks = trepo.findAll().stream()
                .filter(task -> (taskSearch.getStatus() == null || task.getStatus().equalsIgnoreCase(taskSearch.getStatus()))
                        && (taskSearch.getTaskName() == null || task.getTaskName().toLowerCase().contains(taskSearch.getTaskName().toLowerCase()))
                        && (taskSearch.getStartDate() == null || task.getStartDate().compareTo(taskSearch.getStartDate()) >= 0)
                        && (taskSearch.getEndDate() == null || task.getEndDate().compareTo(taskSearch.getEndDate()) <= 0)
                        && (taskSearch.getUser() == null || task.getUser().equals(taskSearch.getUser()))
                        && (taskSearch.getId() == 0 || task.getId() == taskSearch.getId())
                        && (taskSearch.getCourse() == null || task.getCourse().equals(taskSearch.getCourse())))
                .collect(Collectors.toList());

        if (taskSearch.getTaskName() != null && tasks.isEmpty()) {
            throw new TaskNotFoundException("Task with name '" + taskSearch.getTaskName() + "' not found");
        }

        return tasks;
    }


	@Override
	public List<Task> getAllTask() {
		List<Task> tList = trepo.findAll();
		return tList;
	}
	
	
	@Override
	public String updateTask(Task updatedTask) {
	    // Check if a task with the same taskName already exists
	    if (trepo.existsByTaskNameAndIdNot(updatedTask.getTaskName(), updatedTask.getId())) {
	        throw new TaskNotFoundException("Task with name '" + updatedTask.getTaskName() + "' already exists");
	    }

	    Task existingTask = trepo.findById(updatedTask.getId()).orElse(null);

	    if (existingTask != null) {
	        // Update fields only if they are not null in updatedTask
	        if (updatedTask.getTaskName() != null) {
	            existingTask.setTaskName(updatedTask.getTaskName());
	        }
	        if (updatedTask.getDescription() != null) {
	            existingTask.setDescription(updatedTask.getDescription());
	        }
	        if (updatedTask.getStatus() != null) {
	            existingTask.setStatus(updatedTask.getStatus());
	        }
	        if (updatedTask.getStartDate() != null) {
	            existingTask.setStartDate(updatedTask.getStartDate());
	        }
	        if (updatedTask.getEndDate() != null) {
	            existingTask.setEndDate(updatedTask.getEndDate());
	        }
	        if (updatedTask.getUpdatedBy() != null) {
	            existingTask.setUpdatedBy(updatedTask.getUpdatedBy());
	        }
	        if (updatedTask.getUpdatedOn() != null) {
	            existingTask.setUpdatedOn(updatedTask.getUpdatedOn());
	        }

	        trepo.save(existingTask);
	        return "Task is updated";
	    } else {
	        return "Task with ID " + updatedTask.getId() + " not found.";
	    }
	}

	
	
	

//	@Override
//	public String updateTask(Task updatedTask) {
//		
//		if (trepo.existsByTaskName(updatedTask.getTaskName())) {
//	        throw new TaskNotFoundException("Task with name '" + updatedTask.getTaskName() + "' already exists");
//	    }
//		
//	    Task existingTask = trepo.findById(updatedTask.getId()).orElse(null);
//
//	    if (existingTask != null) {
//	        existingTask.setTaskName(updatedTask.getTaskName());
//	        existingTask.setDescription(updatedTask.getDescription());
//	        existingTask.setStatus(updatedTask.getStatus());
//	        existingTask.setStartDate(updatedTask.getStartDate());
//	        existingTask.setEndDate(updatedTask.getEndDate());
//	        existingTask.setUpdatedBy(updatedTask.getUpdatedBy());
//	        existingTask.setUpdatedOn(updatedTask.getUpdatedOn());
//
//	        trepo.save(existingTask);
//	        return "Task is updated";
//	    } 
//	    else {
//	        return "Task with ID " + updatedTask.getId() + " not found.";
//	    }
//	}

	@Override
	public String deleteTask(int id) {
		trepo.deleteById(id);
		return "Task is deleted";
	}

}
