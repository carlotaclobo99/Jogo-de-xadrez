package enums;

public enum Color {

	BLUE("\u001B[34m"), 
	RED("\u001B[31m"),
	NONE("\u001B[0m");

	private final String code;
	
	Color(String code) {
		this.code=code;
	}

	public String getCode() {
		return code;
	}
	
}
