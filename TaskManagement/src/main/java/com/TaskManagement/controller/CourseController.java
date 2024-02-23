package com.TaskManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.TaskManagement.dto.CourseSearch;
import com.TaskManagement.entity.Course;
import com.TaskManagement.exceptions.CourseNotFoundException;
import com.TaskManagement.service.CourseService;
import org.springframework.util.StringUtils;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
	@Autowired
	CourseService cservice;

	public CourseController(CourseService cservice) {
		super();
		this.cservice = cservice;
	}

	@PostMapping("/add")
	public ResponseEntity<?> addCourse(@RequestBody Course c) {
	    try {
	        
	        if (c.getCourseName() == null || c.getInstructor() == null || c.getDescription() == null ||
	                c.getDuration() == null || c.getCreatedBy() == null || c.getCreatedOn() == null ||
	                c.getUpdatedBy() == null || c.getUpdatedOn() == null) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Course Details Cannot be Empty!");
	        }
	        Course addedCourse = cservice.addCourse(c);
	        return new ResponseEntity<>(addedCourse, HttpStatus.CREATED);
	    } catch (CourseNotFoundException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    }
	}

	



	@GetMapping("/allcourses")
	public ResponseEntity<?> getAllCourse() {
		List<Course> course = cservice.getAllCourse();
		if (course.isEmpty())
			return ResponseEntity.ok("No Courses Found!");
		return ResponseEntity.ok(course);

	}

	@PostMapping("/updatecourse")
	public ResponseEntity<?> updateCourse(@RequestBody Course c) {
		int id = c.getId();
		Course updatecourse = cservice.updateCourse(id, c);
		try {
			if (c.getId() == 0)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Course Id cannot be Null. Please Enter Id!");
			if (updatecourse != null) {
				return ResponseEntity.ok(updatecourse);
			} else {
				throw new CourseNotFoundException("Course Not Found!");
			}
		} catch (CourseNotFoundException m) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(m.getMessage());
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteCourse(@PathVariable int id) {
		boolean course = cservice.deleteCourse(id);
		try {
			if (course == false) {
				throw new CourseNotFoundException("Invalid Course Id!");
			} else {
				return ResponseEntity.ok().body("The User Deleted Successfully!");
			}
		} catch (CourseNotFoundException m) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(m.getMessage());
		}
	}

	@GetMapping("/search")
	public ResponseEntity<?> searchCourses(@RequestBody CourseSearch searchDTO) {
	    try {
	    	List<Course> courses = cservice.searchCourses(searchDTO);
            return ResponseEntity.ok(courses);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    }
	}
}
