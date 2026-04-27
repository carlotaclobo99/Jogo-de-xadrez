package entities;

public class Position {

	private String file; // Columns
	private int rank;    // Rows
	private Piece piece; // piece
	private boolean validCapture = false;

	public Position() {
	}

	public Position(String file, int rank, Piece piece) {
		super();
		this.file = file;
		this.rank = rank;
		this.piece = piece;
	}

	@Override
	public String toString() {
		return "Position [file=" + file + ", rank=" + rank + ", piece=" + piece + "]";
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public boolean isValidCapture() {
		return validCapture;
	}

	public void setValidCapture(boolean validCapture) {
		this.validCapture = validCapture;
	}

	public String printPos() {
		String file = this.getFile();
		String rank = String.valueOf(this.getRank());
		return file+rank;
	}

}