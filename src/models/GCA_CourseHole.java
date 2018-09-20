package models;

import java.time.LocalDate;
import java.time.LocalTime;

public class GCA_CourseHole {
	private int courseHoleId;
	private int courseId;
	private int hole;
	private int handicap;
	public GCA_CourseHole() {
		super();
	}
	public GCA_CourseHole(int courseHoleId, int courseId, int hole, int handicap) {
		super();
		this.courseHoleId = courseHoleId;
		this.courseId = courseId;
		this.hole = hole;
		this.handicap = handicap;
	}
	public int getCourseHoleId() {
		return courseHoleId;
	}
	public void setCourseHoleId(int courseHoleId) {
		this.courseHoleId = courseHoleId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getHole() {
		return hole;
	}
	public void setHole(int hole) {
		this.hole = hole;
	}
	public int getHandicap() {
		return handicap;
	}
	public void setHandicap(int handicap) {
		this.handicap = handicap;
	}

}
