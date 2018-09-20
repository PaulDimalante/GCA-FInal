package models;

import java.util.Date;

public class GCA_Score {
	private int scoreId;
	private int memberId;
	private int tournamentId;
	private Date dateOfRound;
	private int courseId;
	private int courseTeeId;
	private String groupName;
	private String team;
	private int roundNo;
	private String tournamentFlag;
	public GCA_Score() {
		super();
	}
	public GCA_Score(int scoreId, int memberId, int tournamentId, Date dateOfRound, int courseId, int courseTeeId,
			String groupName, String team, int roundNo, String tournamentFlag) {
		super();
		this.scoreId = scoreId;
		this.memberId = memberId;
		this.tournamentId = tournamentId;
		this.dateOfRound = dateOfRound;
		this.courseId = courseId;
		this.courseTeeId = courseTeeId;
		this.groupName = groupName;
		this.team = team;
		this.roundNo = roundNo;
		this.tournamentFlag = tournamentFlag;
	}
	public int getScoreId() {
		return scoreId;
	}
	public void setScoreId(int scoreId) {
		this.scoreId = scoreId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public int getTournamentId() {
		return tournamentId;
	}
	public void setTournamentId(int tournamentId) {
		this.tournamentId = tournamentId;
	}
	public Date getDateOfRound() {
		return dateOfRound;
	}
	public void setDateOfRound(Date dateOfRound) {
		this.dateOfRound = dateOfRound;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getCourseTeeId() {
		return courseTeeId;
	}
	public void setCourseTeeId(int courseTeeId) {
		this.courseTeeId = courseTeeId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public int getRoundNo() {
		return roundNo;
	}
	public void setRoundNo(int roundNo) {
		this.roundNo = roundNo;
	}
	public String getTournamentFlag() {
		return tournamentFlag;
	}
	public void setTournamentFlag(String tournamentFlag) {
		this.tournamentFlag = tournamentFlag;
	}

}
