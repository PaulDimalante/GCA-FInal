package models;

import java.time.LocalDate;
import java.time.LocalTime;

public class GCA_HandicapScore {
	private int handicapScoreId;
	private int scoreHoleId;
	private int adjustedScore;
	public GCA_HandicapScore() {
		super();
	}
	public GCA_HandicapScore(int handicapScoreId, int scoreHoleId, int adjustedScore) {
		super();
		this.handicapScoreId = handicapScoreId;
		this.scoreHoleId = scoreHoleId;
		this.adjustedScore = adjustedScore;
	}
	public int getHandicapScoreId() {
		return handicapScoreId;
	}
	public void setHandicapScoreId(int handicapScoreId) {
		this.handicapScoreId = handicapScoreId;
	}
	public int getScoreHoleId() {
		return scoreHoleId;
	}
	public void setScoreHoleId(int scoreHoleId) {
		this.scoreHoleId = scoreHoleId;
	}
	public int getAdjustedScore() {
		return adjustedScore;
	}
	public void setAdjustedScore(int adjustedScore) {
		this.adjustedScore = adjustedScore;
	}

}
