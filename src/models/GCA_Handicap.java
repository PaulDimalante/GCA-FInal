package models;

import java.util.Date;

public class GCA_Handicap {
	private int handicapId;
	private int memberId;
	private Date asOfDate;
	private double handicapIndex;
	public GCA_Handicap() {
		super();
	}
	public GCA_Handicap(int handicapId, int memberId, Date asOfDate, double handicapIndex) {
		super();
		this.handicapId = handicapId;
		this.memberId = memberId;
		this.asOfDate = asOfDate;
		this.handicapIndex = handicapIndex;
	}
	public int getHandicapId() {
		return handicapId;
	}
	public void setHandicapId(int handicapId) {
		this.handicapId = handicapId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public Date getAsOfDate() {
		return asOfDate;
	}
	public void setAsOfDate(Date asOfDate) {
		this.asOfDate = asOfDate;
	}
	public double getHandicapIndex() {
		return handicapIndex;
	}
	public void setHandicapIndex(double handicapIndex) {
		this.handicapIndex = handicapIndex;
	}

}
