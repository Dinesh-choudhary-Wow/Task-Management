package com.TaskManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TaskManagement.dao.CourseDao;
import com.TaskManagement.dto.CourseSearch;
import com.TaskManagement.entity.Course;
import com.TaskManagement.exceptions.CourseNotFoundException;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseDao cdao;

	public CourseServiceImpl(CourseDao cdao) {
		super();
		this.cdao = cdao;
	}

	@Override
    public Course addCourse(Course c) {
		 if (cdao.existsByCourseName(c.getCourseName())) {
	            // Course with the same name already exists, return a response or throw an exception
	            throw new CourseNotFoundException("Course with name '" + c.getCourseName() + "' already exists");
	        }
        return cdao.save(c);
    }

   

	@Override
	public List<Course> getAllCourse() {
		return cdao.findAll();

	}

	@Override
	public Course updateCourse(int id, Course c) {
		Optional<Course> optionalCourse = cdao.findById(id);

		if (optionalCourse.isPresent()) {
			Course existingCourse = optionalCourse.get();
			if (c.getCourseName() != null) {
				existingCourse.setCourseName(c.getCourseName());
			}
			if (c.getDescription() != null) {
				existingCourse.setDescription(c.getDescription());
			}
			if (c.getInstructor() != null) {
				existingCourse.setInstructor(c.getInstructor());
			}
			if (c.getDuration() != null) {
				existingCourse.setDuration(c.getDuration());
			}

			// Set updatedBy and updatedOn from the request
			existingCourse.setUpdatedBy(c.getUpdatedBy());
			existingCourse.setUpdatedOn(c.getUpdatedOn());
			return cdao.save(existingCourse);
		} else {
			return null;
		}

	}

	@Override
	public boolean deleteCourse(int id) {
		boolean course = cdao.existsById(id);
		if (course) {
			cdao.deleteById(id);
			return course;
		}
		return course;
	}

	@Override
	public List<Course> searchCourses(CourseSearch search) {
		if (search.getCourseName() != null && search.getInstructor() != null) {
			if (validateCourseName(search.getCourseName()) && (validateInstructor(search.getInstructor()))) {
				return cdao.findByCourseNameAndInstructor(search.getCourseName(), search.getInstructor());
			}
		} else if (search.getCourseName() != null && validateCourseName(search.getCourseName())) {
			return cdao.findByCourseName(search.getCourseName());
		} else if (search.getInstructor() != null && validateInstructor(search.getInstructor())) {
			return cdao.findByInstructor(search.getInstructor());
		}
		return null;

		// Handle other search criteria as neede // Placeholder, replace with actual
		// implementation
	}

	public boolean validateCourseName(String courseName) {
		if (!cdao.existsByCourseName(courseName)) {
			throw new CourseNotFoundException("Course with name '" + courseName + "' not found");
		}
		return true;
	}

	public boolean validateInstructor(String instructor) {
		if (!cdao.existsByInstructor(instructor)) {
			throw new CourseNotFoundException("Course with instructor '" + instructor + "' not found");
		}
		return true;
	}

	public boolean validateId(Integer id) {
		if (!cdao.existsById(id)) {
			throw new CourseNotFoundException("Course with ID '" + id + "' not found");
		}
		return true;
	}

	@Override
	public Course getCourse(Integer id) {
		return cdao.findById(id).orElse(null);
	}

	@Override
	public List<Course> getCoursesByCourseName(String courseName) {
		return cdao.findByCourseName(courseName);
	}

	@Override
	public List<Course> getCoursesByInstructor(String instructor) {
		return cdao.findByInstructor(instructor);
	}

}
