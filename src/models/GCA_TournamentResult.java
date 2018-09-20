package models;

import java.time.LocalDate;
import java.time.LocalTime;

public class GCA_TournamentResult {
	private int tournamentResultId;
	private int scoreId;
	private int frontNine;
	private int backNine;
	private int overAll;
	public GCA_TournamentResult() {
		super();
	}
	public GCA_TournamentResult(int tournamentResultId, int scoreId, int frontNine, int backNine, int overAll) {
		super();
		this.tournamentResultId = tournamentResultId;
		this.scoreId = scoreId;
		this.frontNine = frontNine;
		this.backNine = backNine;
		this.overAll = overAll;
	}
	public int getTournamentResultId() {
		return tournamentResultId;
	}
	public void setTournamentResultId(int tournamentResultId) {
		this.tournamentResultId = tournamentResultId;
	}
	public int getScoreId() {
		return scoreId;
	}
	public void setScoreId(int scoreId) {
		this.scoreId = scoreId;
	}
	public int getFrontNine() {
		return frontNine;
	}
	public void setFrontNine(int frontNine) {
		this.frontNine = frontNine;
	}
	public int getBackNine() {
		return backNine;
	}
	public void setBackNine(int backNine) {
		this.backNine = backNine;
	}
	public int getOverAll() {
		return overAll;
	}
	public void setOverAll(int overAll) {
		this.overAll = overAll;
	}

}
