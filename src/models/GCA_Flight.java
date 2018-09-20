package models;

import java.time.LocalDate;
import java.time.LocalTime;

public class GCA_Flight {
	private int flightId;
	private int tournamentId;
	private String flightName;
	private int fromHandicap;
	private int toHandicap;
	public GCA_Flight() {
		super();
	}
	public GCA_Flight(int flightId, int tournamentId, String flightName, int fromHandicap, int toHandicap) {
		super();
		this.flightId = flightId;
		this.tournamentId = tournamentId;
		this.flightName = flightName;
		this.fromHandicap = fromHandicap;
		this.toHandicap = toHandicap;
	}
	public int getFlightId() {
		return flightId;
	}
	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}
	public int getTournamentId() {
		return tournamentId;
	}
	public void setTournamentId(int tournamentId) {
		this.tournamentId = tournamentId;
	}
	public String getFlightName() {
		return flightName;
	}
	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}
	public int getFromHandicap() {
		return fromHandicap;
	}
	public void setFromHandicap(int fromHandicap) {
		this.fromHandicap = fromHandicap;
	}
	public int getToHandicap() {
		return toHandicap;
	}
	public void setToHandicap(int toHandicap) {
		this.toHandicap = toHandicap;
	}

}
