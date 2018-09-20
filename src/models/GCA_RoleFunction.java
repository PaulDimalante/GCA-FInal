package models;

import java.time.LocalDate;
import java.time.LocalTime;

public class GCA_RoleFunction {
	private int roleFunctionId;
	private int roleId;
	private int functionId;
	private String accessRights;
	public GCA_RoleFunction() {
		super();
	}
	public GCA_RoleFunction(int roleFunctionId, int roleId, int functionId, String accessRights) {
		super();
		this.roleFunctionId = roleFunctionId;
		this.roleId = roleId;
		this.functionId = functionId;
		this.accessRights = accessRights;
	}
	public int getRoleFunctionId() {
		return roleFunctionId;
	}
	public void setRoleFunctionId(int roleFunctionId) {
		this.roleFunctionId = roleFunctionId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getFunctionId() {
		return functionId;
	}
	public void setFunctionId(int functionId) {
		this.functionId = functionId;
	}
	public String getAccessRights() {
		return accessRights;
	}
	public void setAccessRights(String accessRights) {
		this.accessRights = accessRights;
	}

}
