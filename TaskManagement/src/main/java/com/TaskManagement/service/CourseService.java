package com.TaskManagement.service;

import java.util.List;

import com.TaskManagement.dto.CourseSearch;
import com.TaskManagement.entity.Course;

public interface CourseService {
	Course addCourse(Course c);
	
	List<Course> getAllCourse();
	Course updateCourse(int id,Course c);
	boolean deleteCourse(int id);
	List<Course> searchCourses(CourseSearch search);

	Course getCourse(Integer id);


	List<Course> getCoursesByCourseName(String courseName);

	List<Course> getCoursesByInstructor(String instructor);

	boolean validateCourseName(String courseName);

	boolean validateInstructor(String instructor);

	
}
