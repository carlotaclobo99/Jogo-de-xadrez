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
	List<Integer> ranks = new ArrayList<Integer>(List.of(1, 2, 3, 4, 5, 6, 7, 8));

	public Board() {
	}
	
	public Board(Position[][] positions, Player p1, Player p2) {
		super();
		this.positions = positions;
		this.p1 = p1;
		this.p2 = p2;
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
		for (int j = 0; j<8; j++) {
			String row = ranks.get(j).toString()+"| ";
			for (int i = 0; i<8; i++) {
				Piece piece = positions[i][j].getPiece();
				String pos = piece.getValue();
				row += " "+pos+" ";
			}
			table += row +" |"+ ranks.get(j).toString()+"\n";
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
	
	public void updatePosition(Position position) {
		position.getPiece().setValue((position.getPiece().getValue()));
		Character file = position.getFile();
		Character rank = position.getRank();
		positions[files.indexOf(file)][ranks.indexOf(rank)] = position;
	}

	public void captureWhite(Piece piece) {
		captured_white.add(piece);
	}

	public void captureBlack(Piece piece) {
		captured_black.add(piece);
	}
}
