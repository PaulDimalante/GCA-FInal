package models;

import java.time.LocalDate;
import java.time.LocalTime;

public class GCA_Member {
	private int memberId;
	private int clubId;
	private String memberName;
	private String memberNickName;
	private String memberUsgaId;
	private String memberPassWord;
	private String memberLoginId;

	public GCA_Member() {
		super();
	}

	public GCA_Member(int memberId, int clubId, String memberName, String memberNickName, String memberUsgaId,
			String memberPassWord, String memberLoginId) {
		super();
		this.memberId = memberId;
		this.clubId = clubId;
		this.memberName = memberName;
		setMemberNickName(memberNickName);
		this.memberUsgaId = memberUsgaId;
		this.memberPassWord = memberPassWord;
		setMemberLoginId(memberLoginId);
	}

	public String getMemberLoginId() {
		if (this.memberLoginId == null || this.memberLoginId.equals("") || this.memberLoginId.equals(" ")) {
			return this.memberName;
		} else {
			return this.memberLoginId;
		}
	}

	/**
	 * @param memberLoginId the memberLoginId to set
	 */
	public void setMemberLoginId(String memberLoginId) {
		if (memberLoginId.equals(null) || memberLoginId.equals("") || memberLoginId.equals(" ")) {
			this.memberLoginId = this.memberName;
		} else {
			this.memberLoginId = memberLoginId;
		}
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getClubId() {
		return clubId;
	}

	public void setClubId(int clubId) {
		this.clubId = clubId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberNickName() {
		return memberNickName;
	}

	public void setMemberNickName(String memberNickName) {
		this.memberNickName = memberNickName;
	}

	public String getMemberUsgaId() {
		return memberUsgaId;
	}

	public void setMemberUsgaId(String memberUsgaId) {
		this.memberUsgaId = memberUsgaId;
	}

	public String getMemberPassWord() {
		return memberPassWord;
	}

	public void setMemberPassWord(String memberPassWord) {
		this.memberPassWord = memberPassWord;
	}

	public String getMemberDisplayName() {
		String displayName = "";

		if (this.memberNickName.equals(null) || this.memberNickName.equals("") || this.memberNickName.equals(" ")) {
			displayName = this.memberName;
		} else {
			displayName = String.format("%s \"%s\"", this.memberName, this.memberNickName);
		}

		return displayName;
	}
}
