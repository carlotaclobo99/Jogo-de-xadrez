package entities;

public class Position {

	private Character file; //Columns
	private Character rank; //Rows
	private String value; //piece
	

	public Position(Character file, Character rank, String value) {
		super();
		this.file = file;
		this.rank = rank;
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Character getFile() {
		return file;
	}

	public void setFile(Character file) {
		this.file = file;
	}

	public Character getRank() {
		return rank;
	}

	public void setRank(Character rank) {
		this.rank = rank;
	}
	
	public String getPosition() {
		return file.toString()+rank.toString();
	
	}

}