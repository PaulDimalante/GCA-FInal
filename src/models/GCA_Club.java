package models;

import java.time.LocalDate;
import java.time.LocalTime;

public class GCA_Club {
	private int clubId;
	private String clubName;
	private String clubUsgaId;
	private String clubHomeCourseName;
	private String clubCity;
	private String clubState;
	public GCA_Club() {
		super();
	}
	public GCA_Club(int clubId, String clubName, String clubUsgaId, String clubHomeCourseName, String clubCity,
			String clubState) {
		super();
		this.clubId = clubId;
		this.clubName = clubName;
		this.clubUsgaId = clubUsgaId;
		this.clubHomeCourseName = clubHomeCourseName;
		this.clubCity = clubCity;
		this.clubState = clubState;
	}
	public int getClubId() {
		return clubId;
	}
	public void setClubId(int clubId) {
		this.clubId = clubId;
	}
	public String getClubName() {
		return clubName;
	}
	public void setClubName(String clubName) {
		this.clubName = clubName;
	}
	public String getClubUsgaId() {
		return clubUsgaId;
	}
	public void setClubUsgaId(String clubUsgaId) {
		this.clubUsgaId = clubUsgaId;
	}
	public String getClubHomeCourseName() {
		return clubHomeCourseName;
	}
	public void setClubHomeCourseName(String clubHomeCourseName) {
		this.clubHomeCourseName = clubHomeCourseName;
	}
	public String getClubCity() {
		return clubCity;
	}
	public void setClubCity(String clubCity) {
		this.clubCity = clubCity;
	}
	public String getClubState() {
		return clubState;
	}
	public void setClubState(String clubState) {
		this.clubState = clubState;
	}

	
}
