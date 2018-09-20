package models;

public class GCA_PairingsMember {
	private int pairingsMemberId;
	private int pairingId;
	private int memberId;
	private String team;
	private String groupName;
	private int position;
	public GCA_PairingsMember() {
		super();
	}
	public GCA_PairingsMember(int pairingsMemberId, int pairingId, int memberId, String team, String groupName,
			int position) {
		super();
		this.pairingsMemberId = pairingsMemberId;
		this.pairingId = pairingId;
		this.memberId = memberId;
		this.team = team;
		this.groupName = groupName;
		this.position = position;
	}
	public int getPairingsMemberId() {
		return pairingsMemberId;
	}
	public void setPairingsMemberId(int pairingsMemberId) {
		this.pairingsMemberId = pairingsMemberId;
	}
	public int getPairingId() {
		return pairingId;
	}
	public void setPairingId(int pairingId) {
		this.pairingId = pairingId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}

}