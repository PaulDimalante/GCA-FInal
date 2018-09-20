package models;

import java.util.Date;

public class GCA_SignUp {
	private int signUpId;
	private int clubId;
	private int memberId;
	private Date signUpDate;
	private int tournamentId;
	private String signedUpFlag;
	
	public String getSignedUpFlag() {
		return signedUpFlag;
	}
	public void setSignedUpFlag(String signedUpFlag) {
		this.signedUpFlag = signedUpFlag;
	}
	public GCA_SignUp() {
		super();
	}
	public GCA_SignUp(int signUpId, int clubId, int memberId, Date signUpDate, int tournamentId, String signedUpFlag) {
		super();
		this.signUpId = signUpId;
		this.clubId = clubId;
		this.memberId = memberId;
		this.signUpDate = signUpDate;
		this.tournamentId = tournamentId;
		this.signedUpFlag = signedUpFlag;
	}
	public int getSignUpId() {
		return signUpId;
	}
	public void setSignUpId(int signUpId) {
		this.signUpId = signUpId;
	}
	public int getClubId() {
		return clubId;
	}
	public void setClubId(int clubId) {
		this.clubId = clubId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public Date getSignUpDate() {
		return signUpDate;
	}
	public void setSignUpDate(Date signUpDate) {
		this.signUpDate = signUpDate;
	}
	public int getTournamentId() {
		return tournamentId;
	}
	public void setTournamentId(int tournamentId) {
		this.tournamentId = tournamentId;
	}

}
