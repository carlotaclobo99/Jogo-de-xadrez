package entities;

public class Position {

	private Character file; //Columns
	private Character rank; //Rows
	private Piece piece; //piece
	
	public Position() {}
	
	public Position(Character file, Character rank, Piece piece) {
		super();
		this.file = file;
		this.rank = rank;
		this.piece = piece;
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

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public String getPosition() {
		return file.toString()+rank.toString();
	
	}

}