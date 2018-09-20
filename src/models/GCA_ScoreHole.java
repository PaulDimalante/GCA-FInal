package models;

import java.time.LocalDate;
import java.time.LocalTime;

public class GCA_ScoreHole {
	private int scoreHoleId;
	private int scoreId;
	private int hole;
	private int score;
	private String xOut;
	public GCA_ScoreHole() {
		super();
	}
	public GCA_ScoreHole(int scoreHoleId, int scoreId, int hole, int score, String xOut) {
		super();
		this.scoreHoleId = scoreHoleId;
		this.scoreId = scoreId;
		this.hole = hole;
		this.score = score;
		this.xOut = xOut;
	}
	public int getScoreHoleId() {
		return scoreHoleId;
	}
	public void setScoreHoleId(int scoreHoleId) {
		this.scoreHoleId = scoreHoleId;
	}
	public int getScoreId() {
		return scoreId;
	}
	public void setScoreId(int scoreId) {
		this.scoreId = scoreId;
	}
	public int getHole() {
		return hole;
	}
	public void setHole(int hole) {
		this.hole = hole;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getxOut() {
		return xOut;
	}
	public void setxOut(String xOut) {
		this.xOut = xOut;
	}

}
