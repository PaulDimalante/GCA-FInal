package enums;

public enum ROLENAME {
	PLAYER("Player"),
	ADMINSTRATOR("Administrator")
	;
	private final String value;
	private ROLENAME(String s) {this.value = s;}
	public String getValue() {return this.value;}
}
