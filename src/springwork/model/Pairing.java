package springwork.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Pairing {
	private Date teeTime;
	private String player1, player2, player3, player4;
	public Date getTeeTime() {
		return teeTime;
	}
	
	
	public Pairing() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Pairing(Date teeTime, String player1, String player2, String player3, String player4) {
		super();
		this.teeTime = teeTime;
		this.player1 = player1;
		this.player2 = player2;
		this.player3 = player3;
		this.player4 = player4;
	}


	public void setTeeTime(Date teeTime) {
		this.teeTime = teeTime;
	}
	public String getTeeTimeFormatted() {
		SimpleDateFormat dt1 = new SimpleDateFormat("hh:mm");
		return dt1.format(this.teeTime);
	}
	public String getPlayer1() {
		return player1;
	}
	public void setPlayer1(String player1) {
		this.player1 = player1;
	}
	public String getPlayer2() {
		return player2;
	}
	public void setPlayer2(String player2) {
		this.player2 = player2;
	}
	public String getPlayer3() {
		return player3;
	}
	public void setPlayer3(String player3) {
		this.player3 = player3;
	}
	public String getPlayer4() {
		return player4;
	}
	public void setPlayer4(String player4) {
		this.player4 = player4;
	}
	
}
