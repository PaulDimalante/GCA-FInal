package models;

import java.util.Date;

public class GCA_CourseTee {
	private int courseTeeId;
	private int courseId;
	private Date fromDate;
	private Date thruDate;
	private String tee;
	private double rating;
	private int slope;
	public GCA_CourseTee() {
		super();
	}
	public GCA_CourseTee(int courseTeeId, int courseId, Date fromDate, Date thruDate, String tee,
			double rating, int slope) {
		super();
		this.courseTeeId = courseTeeId;
		this.courseId = courseId;
		this.fromDate = fromDate;
		this.thruDate = thruDate;
		this.tee = tee;
		this.rating = rating;
		this.slope = slope;
	}
	public int getCourseTeeId() {
		return courseTeeId;
	}
	public void setCourseTeeId(int courseTeeId) {
		this.courseTeeId = courseTeeId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getThruDate() {
		return thruDate;
	}
	public void setThruDate(Date thruDate) {
		this.thruDate = thruDate;
	}
	public String getTee() {
		return tee;
	}
	public void setTee(String tee) {
		this.tee = tee;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public int getSlope() {
		return slope;
	}
	public void setSlope(int slope) {
		this.slope = slope;
	}

}
