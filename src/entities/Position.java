package entities;

import java.util.ArrayList;
import java.util.List;

public class Position {

	List<Character> files = new ArrayList<Character>(List.of('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'));
	List<Character> ranks = new ArrayList<Character>(List.of('1', '2', '3', '4', '5', '6', '7', '8'));
	List<String> setup = new ArrayList<String>(List.of("R", "B", "N", "Q", "K", "N", "B", "R"));

	private int file; // Columns
	private int rank; // Rows
	private Piece piece; // piece

	public Position() {
	}

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

	public String printPos() {
		String f = files.get(file).toString();
		String r = ranks.get(rank).toString();
		return f+r;
	}

}