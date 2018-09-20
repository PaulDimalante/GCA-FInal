package springwork.model;

import java.util.Map;
import java.util.TreeMap;

import models.GCA_Course;
import models.GCA_CourseTee;

public class Courses {
	private Map<String,GCA_Course> coursesByName = new TreeMap<String,GCA_Course>();
	private Map<Integer,GCA_Course> coursesById = new TreeMap<Integer,GCA_Course>();
	private Map<Integer,Map<String, GCA_CourseTee>> courseTees = new TreeMap<Integer,Map<String, GCA_CourseTee>>();
	
	public Courses() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Courses(Map<String, GCA_Course> coursesByName, 
			Map<Integer, GCA_Course> coursesById, 
			Map<Integer, Map<String, GCA_CourseTee>> courseTees) {
		
		super();
		this.coursesByName = coursesByName;
		this.coursesById = coursesById;
		this.courseTees = courseTees;
	}


	public Map<String, GCA_Course> getCoursesByName() {
		return coursesByName;
	}
	public void setCoursesByName(Map<String, GCA_Course> courses) {
		this.coursesByName = courses;
	}
	public Map<Integer, GCA_Course> getCoursesById() {
		return coursesById;
	}
	public void setCoursesById(Map<Integer, GCA_Course> courses) {
		this.coursesById = courses;
	}
	public Map<Integer, Map<String, GCA_CourseTee>> getCourseTees() {
		return courseTees;
	}
	public void setCourseTees(Map<Integer, Map<String, GCA_CourseTee>> courseTees) {
		this.courseTees = courseTees;
	}
}
