package springwork.model;

import java.util.Date;

public class Score {
	private int scoreId;
	private int tournamentId;
	private Date dateOfRound;
	private int courseId;
	private int courseTeeId;
	private int roundNo;
	private int tournamentflag;
	private int memberId;
	private int[] score = new int[18];
	private int frontnine;
	private int backnine;
	private int overall;
	public int getScoreId() {
		return scoreId;
	}
	public void setScoreId(int scoreId) {
		this.scoreId = scoreId;
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
	public int getRoundNo() {
		return roundNo;
	}
	public void setRoundNo(int roundNo) {
		this.roundNo = roundNo;
	}
	public int getTournamentflag() {
		return tournamentflag;
	}
	public void setTournamentflag(int tournamentflag) {
		this.tournamentflag = tournamentflag;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public int[] getScore() {
		return score;
	}
	public void setScore(int[] score) {
		this.score = score;
	}
	public int getFrontnine() {
		return frontnine;
	}
	public void setFrontnine(int frontnine) {
		this.frontnine = frontnine;
	}
	public int getBacknine() {
		return backnine;
	}
	public void setBacknine(int backnine) {
		this.backnine = backnine;
	}
	public int getOverall() {
		return overall;
	}
	public void setOverall(int overall) {
		this.overall = overall;
	}

}
