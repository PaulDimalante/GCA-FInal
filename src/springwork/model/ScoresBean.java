package springwork.model;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import models.GCA_Course;
import models.GCA_CourseTee;
import models.GCA_Member;
import models.GCA_Score;

public class ScoresBean {
	private final int numberOfMembers = 4;
	private int tournamentId;
	private Date dateOfRound;
	private int courseId;
	private int courseTeeId;
	private int roundNo;
	private int clubId;
	private String groupName;
	private String team;
	private String tournamentFlag;
	private int[] scoreId = new int[numberOfMembers];
	private int[] memberId = new int[numberOfMembers];
	private int[][] holeScoreId = new int[numberOfMembers][18];
	private int[][] holeScore = new int[numberOfMembers][18];
	private String[][] xOut = new String[numberOfMembers][18];
	private int[] frontNine = new int[numberOfMembers];
	private int[] backNine = new int[numberOfMembers];
	private int[] overAll = new int[numberOfMembers];
	private Map<String,GCA_Member> members = new TreeMap<String,GCA_Member>();
	private Map<String,GCA_Course> courses = new TreeMap<String,GCA_Course>();
	private Map<Integer,Map<String, GCA_CourseTee>> courseTees = new TreeMap<Integer,Map<String, GCA_CourseTee>>();

	public void updateByScore(GCA_Score score) {
		this.tournamentId = score.getTournamentId();
		this.dateOfRound = score.getDateOfRound();
		this.courseId = score.getCourseId();
		this.courseTeeId = score.getCourseTeeId();
		this.roundNo = score.getRoundNo();
		this.groupName = score.getGroupName();
		this.team = score.getTeam();
		this.tournamentFlag = score.getTournamentFlag();
	}
	
	public void setScoreId(int scoreId, int p) {
		this.scoreId[p] = scoreId;
	}
	
	public void setHoleScoreId(int holeScoreId, int p, int h) {
		this.holeScoreId[p][h] = holeScoreId;
	}
	
	public String[][] getxOut() {
		return xOut;
	}
	public void setxOut(String[][] xOut) {
		this.xOut = xOut;
	}
	public void setxOut(String xOut, int p, int h) {
		this.xOut[p][h] = xOut;
	}
	public int[][] getHoleScoreId() {
		return holeScoreId;
	}
	public void setHoleScoreId(int[][] holeScoreId) {
		this.holeScoreId = holeScoreId;
	}
	public int[] getScoreId() {
		return scoreId;
	}
	public void setScoreId(int[] scoreId) {
		this.scoreId = scoreId;
	}
	public int getNumberOfMembers() {
		return numberOfMembers;
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
	public int getClubId() {
		return clubId;
	}
	public void setClubId(int clubId) {
		this.clubId = clubId;
	}
	public Map<String, GCA_Member> getMembers() {
		return members;
	}
	public void setMembers(Map<String, GCA_Member> members) {
		this.members = members;
	}
	public Map<String, GCA_Course> getCourses() {
		return courses;
	}
	public void setCourses(Map<String, GCA_Course> courses) {
		this.courses = courses;
	}
	public Map<Integer, Map<String, GCA_CourseTee>> getCourseTees() {
		return courseTees;
	}
	public void setCourseTees(Map<Integer, Map<String, GCA_CourseTee>> courseTees) {
		this.courseTees = courseTees;
	}
	public int[] getFrontNine() {
		return frontNine;
	}
	public void setFrontNine(int[] frontNine) {
		this.frontNine = frontNine;
	}
	public void setFrontNine(int frontNine, int p) {
		this.frontNine[p] = frontNine;
	}
	public int[] getBackNine() {
		return backNine;
	}
	public void setBackNine(int[] backNine) {
		this.backNine = backNine;
	}
	public void setBackNine(int backNine, int p) {
		this.backNine[p] = backNine;
	}
	public int[] getOverAll() {
		return overAll;
	}
	public void setOverAll(int[] overAll) {
		this.overAll = overAll;
	}
	public void setOverAll(int overAll, int p) {
		this.overAll[p] = overAll;
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
	public String getTournamentFlag() {
		return tournamentFlag;
	}
	public void setTournamentFlag(String tournamentFlag) {
		this.tournamentFlag = tournamentFlag;
	}
	public int[] getMemberId() {
		return memberId;
	}
	public void setMemberId(int[] memberId) {
		this.memberId = memberId;
	}
	public void setMemberId(int memberId, int p) {
		this.memberId[p] = memberId;
	}
	public int[][] getHoleScore() {
		return holeScore;
	}
	public void setHoleScore(int[][] holeScore) {
		this.holeScore = holeScore;
	}
	public void setHoleScore(int holeScore, int p, int h) {
		this.holeScore[p][h] = holeScore;
	}
	
	
}
