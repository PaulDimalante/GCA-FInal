package enums;

public enum ACCESSRIGHTS {
	ALL("All"),
	READONLY("ReadOnly"),
	SELF_ONLY("SelfOnly"),
	NONE("None")
	;
	private final String value;
	private ACCESSRIGHTS(String s) {this.value = s;}
	public String getValue() {return this.value;}
}
