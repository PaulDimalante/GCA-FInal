package models;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GCA_SessionInfo {
	private String memberName;
	private int memberId;
	private int courseId;
	private boolean loggedIn;
	private Date currentDate;
	private String currentDateFormated;
	private Map<Integer,GCA_Club> clubs = new HashMap<Integer,GCA_Club>();
	
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public boolean isLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	public Date getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}
	public String getCurrentDateFormated() {
		return currentDateFormated;
	}
	public void setCurrentDateFormated(String currentDateFormated) {
		this.currentDateFormated = currentDateFormated;
	}
	public Map<Integer, GCA_Club> getClubs() {
		return clubs;
	}
	public void setClubs(Map<Integer, GCA_Club> clubs) {
		this.clubs = clubs;
	}
	
	
}
