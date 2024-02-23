package com.TaskManagement.dto;

public class CourseSearch {
	private String courseName;

    private String instructor;

    private Integer id;

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CourseSearch(String courseName, String instructor, Integer id) {
		super();
		this.courseName = courseName;
		this.instructor = instructor;
		this.id = id;
	}

	public CourseSearch() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
