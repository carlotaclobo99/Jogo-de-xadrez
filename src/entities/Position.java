package entities;

public class Position {

	private int file; //Columns
	private int rank; //Rows
	private Piece piece; //piece
	
	public Position() {}
	
	public Position(int rank, int file, Piece piece) {
		super();
		this.file = file;
		this.rank = rank;
		this.piece = piece;
	}

	@Override
	public String toString() {
		return "Position [file=" + file + ", rank=" + rank + "]";
	}

	public int getFile() {
		return file;
	}

	public void setFile(int file) {
		this.file = file;
	}

	public int getRank() {
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

}