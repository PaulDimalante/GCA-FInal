package models;

import java.time.LocalDate;
import java.time.LocalTime;

public class GCA_TournamentResultHole {
	private int tournamentResultHoleId;
	private int tournamentResultId;
	private int scoreHoleId;
	private int netScore;
	public GCA_TournamentResultHole() {
		super();
	}
	public GCA_TournamentResultHole(int tournamentResultHoleId, int tournamentResultId, int scoreHoleId, int netScore) {
		super();
		this.tournamentResultHoleId = tournamentResultHoleId;
		this.tournamentResultId = tournamentResultId;
		this.scoreHoleId = scoreHoleId;
		this.netScore = netScore;
	}
	public int getTournamentResultHoleId() {
		return tournamentResultHoleId;
	}
	public void setTournamentResultHoleId(int tournamentResultHoleId) {
		this.tournamentResultHoleId = tournamentResultHoleId;
	}
	public int getTournamentResultId() {
		return tournamentResultId;
	}
	public void setTournamentResultId(int tournamentResultId) {
		this.tournamentResultId = tournamentResultId;
	}
	public int getScoreHoleId() {
		return scoreHoleId;
	}
	public void setScoreHoleId(int scoreHoleId) {
		this.scoreHoleId = scoreHoleId;
	}
	public int getNetScore() {
		return netScore;
	}
	public void setNetScore(int netScore) {
		this.netScore = netScore;
	}

}
