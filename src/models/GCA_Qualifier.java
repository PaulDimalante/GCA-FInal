package models;

import java.time.LocalDate;
import java.time.LocalTime;

public class GCA_Qualifier {
	private int qualifierId;
	private int tournamentId;
	private int memberId;
	public GCA_Qualifier() {
		super();
	}
	public GCA_Qualifier(int qualifierId, int tournamentId, int memberId) {
		super();
		this.qualifierId = qualifierId;
		this.tournamentId = tournamentId;
		this.memberId = memberId;
	}
	public int getQualifierId() {
		return qualifierId;
	}
	public void setQualifierId(int qualifierId) {
		this.qualifierId = qualifierId;
	}
	public int getTournamentId() {
		return tournamentId;
	}
	public void setTournamentId(int tournamentId) {
		this.tournamentId = tournamentId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

}
