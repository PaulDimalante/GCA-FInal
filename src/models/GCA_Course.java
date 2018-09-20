package models;

import java.time.LocalDate;
import java.time.LocalTime;

public class GCA_Course {
	private int courseId;
	private String courseName;
	private String courseCity;
	private String courseState;
	public GCA_Course() {
		super();
	}
	public GCA_Course(int courseId, String courseName, String courseCity, String courseState) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseCity = courseCity;
		this.courseState = courseState;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseCity() {
		return courseCity;
	}
	public void setCourseCity(String courseCity) {
		this.courseCity = courseCity;
	}
	public String getCourseState() {
		return courseState;
	}
	public void setCourseState(String courseState) {
		this.courseState = courseState;
	}

}
