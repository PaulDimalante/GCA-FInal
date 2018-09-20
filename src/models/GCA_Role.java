package models;

import java.time.LocalDate;
import java.time.LocalTime;

public class GCA_Role {
	private int roleId;
	private int clubId;
	private String roleName;
	public GCA_Role() {
		super();
	}
	public GCA_Role(int roleId, int clubId, String roleName) {
		super();
		this.roleId = roleId;
		this.clubId = clubId;
		this.roleName = roleName;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getClubId() {
		return clubId;
	}
	public void setClubId(int clubId) {
		this.clubId = clubId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
