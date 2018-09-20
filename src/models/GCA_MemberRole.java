package models;

import java.time.LocalDate;
import java.time.LocalTime;

public class GCA_MemberRole {
	private int memberRoleId;
	private int memberId;
	private int roleId;
	public GCA_MemberRole() {
		super();
	}
	public GCA_MemberRole(int memberRoleId, int memberId, int roleId) {
		super();
		this.memberRoleId = memberRoleId;
		this.memberId = memberId;
		this.roleId = roleId;
	}
	public int getMemberRoleId() {
		return memberRoleId;
	}
	public void setMemberRoleId(int memberRoleId) {
		this.memberRoleId = memberRoleId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

}
