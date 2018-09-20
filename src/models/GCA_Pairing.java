package models;

import java.sql.Timestamp;

public class GCA_Pairing {
	private int pairingId;
	private int tournamentId;
	private Timestamp teeTime;
	public GCA_Pairing() {
		super();
	}
	public GCA_Pairing(int pairingId, int tournamentId, Timestamp teeTime) {
		super();
		this.pairingId = pairingId;
		this.tournamentId = tournamentId;
		this.teeTime = teeTime;
	}
	public int getPairingId() {
		return pairingId;
	}
	public void setPairingId(int pairingId) {
		this.pairingId = pairingId;
	}
	public int getTournamentId() {
		return tournamentId;
	}
	public void setTournamentId(int tournamentId) {
		this.tournamentId = tournamentId;
	}
	public Timestamp getTeeTime() {
		return teeTime;
	}
	public void setTeeTime(Timestamp teeTime) {
		this.teeTime = teeTime;
	}

}
