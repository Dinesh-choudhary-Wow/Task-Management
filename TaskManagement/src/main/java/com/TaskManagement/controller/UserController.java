package com.TaskManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TaskManagement.entity.User;
import com.TaskManagement.exceptions.UserNotFoundException;
import com.TaskManagement.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/adduser")
	public ResponseEntity<?> saveUser(@RequestBody User user) {
		if(user.getEmail() == null || user.getName() == null || user.getPassword() == null || user.getRole() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Details Cannot be Empty!");
		}
		User createdUser = userService.saveUser(user);
		return ResponseEntity.ok(createdUser);
	}
	
	@PostMapping("/updateuser")
	public ResponseEntity<?> updateUser(@RequestBody User user) {
		int id = user.getId();
		User updatedUser = userService.updateUser(id, user);
		
		try {
			if(user.getId() == 0) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Id cannot be Null. Please Enter Id!");
			if(updatedUser != null) {
				return ResponseEntity.ok(updatedUser);
			}else {
				throw new UserNotFoundException("User Not Found!");
			}
		}catch(UserNotFoundException m) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(m.getMessage());
		}
	}

	@DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        boolean user =  userService.deleteUser(id);
        try {
        	if(user == false) {
        		throw new UserNotFoundException("Invalid User Id!");
        	}else {  		
        		return ResponseEntity.ok().body("The User Deleted Successfully!");
        	}
        }catch(UserNotFoundException m) {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(m.getMessage());
        }
    }
	
    @GetMapping("/allusers")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if(users.isEmpty()) return ResponseEntity.ok("No Users Found!");
        return ResponseEntity.ok(users);
    }
}
