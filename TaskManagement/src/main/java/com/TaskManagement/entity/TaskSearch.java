package com.TaskManagement.entity;

import java.time.LocalDate;

public class TaskSearch {
	
	private int id;
    private User user;
    private Course course;
	private String taskName;
	private String status;
	private LocalDate startDate;
	private LocalDate endDate;
	public TaskSearch() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TaskSearch(int id, User user, Course course, String taskName, String status, LocalDate startDate,
			LocalDate endDate) {
		super();
		this.id = id;
		this.user = user;
		this.course = course;
		this.taskName = taskName;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "TaskSearch [id=" + id + ", user=" + user + ", course=" + course + ", taskName=" + taskName + ", status="
				+ status + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

}
