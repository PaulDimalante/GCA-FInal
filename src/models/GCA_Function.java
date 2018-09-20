package models;

import java.time.LocalDate;
import java.time.LocalTime;

public class GCA_Function {
	private int functionId;
	private String functionName;
	public GCA_Function() {
		super();
	}
	public GCA_Function(int functionId, String functionName) {
		super();
		this.functionId = functionId;
		this.functionName = functionName;
	}
	public int getFunctionId() {
		return functionId;
	}
	public void setFunctionId(int functionId) {
		this.functionId = functionId;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

}
