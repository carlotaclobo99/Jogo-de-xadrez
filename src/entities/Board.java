package entities;

import java.util.ArrayList;
import java.util.List;

public class Board {

	private Position positions[][];

	List<Piece> captured_white = new ArrayList<>();
	List<Piece> captured_black = new ArrayList<>();
	
	private Player p1;
	private Player p2;

	List<Character> files = new ArrayList<Character>(List.of('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'));
	List<Character> ranks = new ArrayList<Character>(List.of('1', '2', '3', '4', '5', '6', '7', '8'));

	public Board() {
	}
	
	public Board(Position[][] positions, Player p1, Player p2) {
		super();
		this.positions = positions;
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public void createBoard() {
		for (int rank = 0; rank<8; rank++) {
			for (int file = 0; file<8; file++) { 
				
				positions[rank][file] = new Position(rank, file, null);
			
			}
		}
	}

	public String loadBoard() {
		String table = "";
		String header = "   ";
		for (Character file: files) {
			header += " "+file.toString()+" ";
		}
		table += "           "+p1.getName()+"\n";
		table += "           "+p1.getColor()+"\n";
		System.out.println();
		table += header+" \n";
		table += "  --------------------------\n";
		for (int rank = 0; rank<8; rank++) {
			String row = ranks.get(rank).toString()+"| ";
			for (int file = 0; file<8; file++) {
				Position pos = positions[rank][file];
				Piece piece = pos.getPiece();
				if (piece != null) {
					String val = piece.getValue();
					row += " "+pos.getPiece().getValue()+" ";
			}
				else {
					row += " "+"-"+" ";
				}
			}
			table += row +" |"+ ranks.get(rank).toString()+"\n";
		}
		table += "  --------------------------\n";
		
		table += header+"\n";
		table += "            "+p2.getColor()+"\n";
		table += "            "+p2.getName()+"\n";
		return table;
	}

	public Position[][] getPositions() {
		return positions;
	}

	public void setPositions(Position[][] positions) {
		this.positions = positions;
	}

	public void captureWhite(Piece piece) {
		captured_white.add(piece);
	}

	public void captureBlack(Piece piece) {
		captured_black.add(piece);
	}
}
