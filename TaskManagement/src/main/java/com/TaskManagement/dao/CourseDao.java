package com.TaskManagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TaskManagement.entity.Course;



public interface CourseDao extends JpaRepository<Course, Integer> {

	
	List<Course> findByCourseName(String courseName);

	List<Course> findByInstructor(String instructor);

	boolean existsByCourseName(String courseName);

	boolean existsByInstructor(String instructor);

	List<Course> findByCourseNameAndInstructor(String courseName, String instructor);

	boolean existsByCourseNameAndInstructor(String courseName, String instructor);

	
	
	
	
	
   
}
