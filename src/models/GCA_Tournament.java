package models;

import java.util.Date;
import java.util.Locale;

import org.springframework.format.annotation.DateTimeFormat;

import commonFunctions.CommonFunctions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

public class GCA_Tournament {
	private int tournamentId;
	private int clubId;
	private String tournamentName;
	private String alwaysFlag;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date tournamentStartDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date tournamentEndDate;

	private int courseId;
	private String strokeMatchRule;
	private String handicapRule;
	private int handicapReductionPct;
	private int teamSize;
	private String groupFlag;
	private String pointsFlag;
	private int pointsFrontNine;
	private int pointsBackNine;
	private int pointsOverAll;
	private String inFlightFlag;
	private String qualifierFlag;
	private int qualifyingTournamentId;
	public GCA_Tournament() {
		super();
	}
	public GCA_Tournament(int tournamentId, int clubId, String tournamentName, String alwaysFlag,
			Date tournamentStartDate, Date tournamentEndDate, int courseId, String strokeMatchRule,
			String handicapRule, int handicapReductionPct, int teamSize, String groupFlag, String pointsFlag,
			int pointsFrontNine, int pointsBackNine, int pointsOverAll, String inFlightFlag, String qualifierFlag,
			int qualifyingTournamentId) {
		super();
		this.tournamentId = tournamentId;
		this.clubId = clubId;
		this.tournamentName = tournamentName;
		this.alwaysFlag = alwaysFlag;
		this.tournamentStartDate = tournamentStartDate;
		this.tournamentEndDate = tournamentEndDate;
		this.courseId = courseId;
		this.strokeMatchRule = strokeMatchRule;
		this.handicapRule = handicapRule;
		this.handicapReductionPct = handicapReductionPct;
		this.teamSize = teamSize;
		this.groupFlag = groupFlag;
		this.pointsFlag = pointsFlag;
		this.pointsFrontNine = pointsFrontNine;
		this.pointsBackNine = pointsBackNine;
		this.pointsOverAll = pointsOverAll;
		this.inFlightFlag = inFlightFlag;
		this.qualifierFlag = qualifierFlag;
		this.qualifyingTournamentId = qualifyingTournamentId;
	}
	public int getTournamentId() {
		return tournamentId;
	}
	public void setTournamentId(int tournamentId) {
		this.tournamentId = tournamentId;
	}
	public int getClubId() {
		return clubId;
	}
	public void setClubId(int clubId) {
		this.clubId = clubId;
	}
	public String getTournamentName() {
		return tournamentName;
	}
	public void setTournamentName(String tournamentName) {
		this.tournamentName = tournamentName;
	}
	public String getAlwaysFlag() {
		return alwaysFlag;
	}
	public void setAlwaysFlag(String alwaysFlag) {
		this.alwaysFlag = alwaysFlag;
	}
	public Date getTournamentStartDate() {
		return tournamentStartDate;
	}
	public void setTournamentStartDate(Date tournamentStartDate) {
		this.tournamentStartDate = tournamentStartDate;
	}
	public void setTournamentStartDate(String tournamentStartDate) {
		this.tournamentStartDate = CommonFunctions.parseDate(tournamentStartDate);
	}
	public Date getTournamentEndDate() {
		return tournamentEndDate;
	}
	public void setTournamentEndDate(Date tournamentEndDate) {
		this.tournamentEndDate = tournamentEndDate;
	}
	public void setTournamentEndDate(String tournamentEndDate) {
		this.tournamentEndDate = CommonFunctions.parseDate(tournamentEndDate);
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getStrokeMatchRule() {
		return strokeMatchRule;
	}
	public void setStrokeMatchRule(String strokeMatchRule) {
		this.strokeMatchRule = strokeMatchRule;
	}
	public String getHandicapRule() {
		return handicapRule;
	}
	public void setHandicapRule(String handicapRule) {
		this.handicapRule = handicapRule;
	}
	public int getHandicapReductionPct() {
		return handicapReductionPct;
	}
	public void setHandicapReductionPct(int handicapReductionPct) {
		this.handicapReductionPct = handicapReductionPct;
	}
	public int getTeamSize() {
		return teamSize;
	}
	public void setTeamSize(int teamSize) {
		this.teamSize = teamSize;
	}
	public String getGroupFlag() {
		return groupFlag;
	}
	public void setGroupFlag(String groupFlag) {
		this.groupFlag = groupFlag;
	}
	public String getPointsFlag() {
		return pointsFlag;
	}
	public void setPointsFlag(String pointsFlag) {
		this.pointsFlag = pointsFlag;
	}
	public int getPointsFrontNine() {
		return pointsFrontNine;
	}
	public void setPointsFrontNine(int pointsFrontNine) {
		this.pointsFrontNine = pointsFrontNine;
	}
	public int getPointsBackNine() {
		return pointsBackNine;
	}
	public void setPointsBackNine(int pointsBackNine) {
		this.pointsBackNine = pointsBackNine;
	}
	public int getPointsOverAll() {
		return pointsOverAll;
	}
	public void setPointsOverAll(int pointsOverAll) {
		this.pointsOverAll = pointsOverAll;
	}
	public String getInFlightFlag() {
		return inFlightFlag;
	}
	public void setInFlightFlag(String inFlightFlag) {
		this.inFlightFlag = inFlightFlag;
	}
	public String getQualifierFlag() {
		return qualifierFlag;
	}
	public void setQualifierFlag(String qualifierFlag) {
		this.qualifierFlag = qualifierFlag;
	}
	public int getQualifyingTournamentId() {
		return qualifyingTournamentId;
	}
	public void setQualifyingTournamentId(int qualifyingTournamentId) {
		this.qualifyingTournamentId = qualifyingTournamentId;
	}

}
