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
		for (int rank = 0; rank < 8; rank++) {
			for (int file = 0; file < 8; file++) {

				positions[rank][file] = new Position(rank, file, null);

			}
		}
	}

	public String loadBoard() {
		String table = "";
		String header = "   ";
		for (Character file : files) {
			header += " " + file.toString() + " ";
		}
		table += "           " + p1.getName() + "\n";
		table += "           " + p1.getColor() + "\n";
		System.out.println();
		table += header + " \n";
		table += "  --------------------------\n";
		for (int rank = 0; rank < 8; rank++) {
			String row = ranks.get(rank).toString() + "| ";
			for (int file = 0; file < 8; file++) {
				Position pos = positions[rank][file];
				Piece piece = pos.getPiece();
				if (piece != null) {
					String val = piece.getValue();
					row += " " + val + " ";
				} else {
					row += " " + "-" + " ";
				}
			}
			table += row + " |" + ranks.get(rank).toString() + "\n";
		}
		table += "  --------------------------\n";

		table += header + "\n";
		table += "            " + p2.getColor() + "\n";
		table += "            " + p2.getName() + "\n";
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

	public boolean legalPiece(Piece piece, Player p) {
		boolean legal = true;
		if (piece.getPlayer() != p) {
			legal = false;
			System.out.println("This piece is not yours!");
			System.out.println("Please choose another piece: ");
		}
		return legal;
	}

	public boolean legalRook(Position init_pos, Position dest_pos, Player p) {
		boolean legal = true;
		String warning = "";

		if (init_pos.getFile() != dest_pos.getFile() && init_pos.getRank() != dest_pos.getRank()) {
			warning = "The rook can only move along the same file or rank!";
			legal = false;
		}

		for (int rank = init_pos.getRank(); rank < dest_pos.getRank(); rank++) {
			for (int file = init_pos.getFile(); file < dest_pos.getFile(); file++) {
				Position test_pos = positions[file][rank];

				if (test_pos.getPiece().getValue() != "-") {
					legal = false;
					warning = "The rook cannot skip over pieces!";
				}
			}
		}

		if (legal == false) {
			System.out.println(warning);
		}
		return legal;
	}

	public boolean legalBishop(Position init_pos, Position dest_pos, Player p) {
		boolean legal = true;
		String warning = "";
		String bis = "";

		if (init_pos.getFile() == dest_pos.getFile() || init_pos.getRank() == dest_pos.getRank()) {
			warning = "The bishop can only move diagonally! \n";
			legal = false;
		}
		
		for (int rank = init_pos.getRank(); rank < dest_pos.getRank(); rank++) {
			for (int file = init_pos.getFile(); file < dest_pos.getFile(); file++) {
				Position test_pos = positions[file][rank];

				if (test_pos.getPiece().getValue().equals("-")) {
					
				}
				else {
					legal = false;
					bis = "The bishop cannot skip over pieces!";
				}
			}
		}
		warning+=bis;

		if (legal == false) {
			System.out.println(warning);
		}
		return legal;
	}
	
	public boolean legalKnight(Position init_pos, Position dest_pos, Player p) {
		boolean legal = true;
		String warning = "";
		int file = init_pos.getFile();
		int rank = init_pos.getRank();
		if ((dest_pos.getFile()-file==2)&&(dest_pos.getRank()-rank == 1)||(dest_pos.getFile()-file==1)&&(dest_pos.getRank()-rank == 2)) {
		}
		else {
			legal = false;
		}
		
		if (legal == false) {
			System.out.println(warning);
		}
		return legal;
	}

	public boolean legalMove(Position init_pos, Position dest_pos, Player p) {
		boolean legal = true;
		String warning = "";
		Piece piece = init_pos.getPiece();
		String val = piece.getValue().toLowerCase().trim();
		String bishop = "b";
		String rook = "r";
		String knight = "n";
		
		if (val.equals(rook)) {
			legal = legalRook(init_pos, dest_pos, p); 
			}
		
		if (val.equals(bishop)) {
			legal = legalBishop(init_pos, dest_pos, p); 
			}
		
		if (val.equals(knight)) {
			legal = legalKnight(init_pos, dest_pos, p); 
			}
		
		if (dest_pos.getPiece().getPlayer() == p) {
			legal = false;
			warning += "You cannot take one of your pieces! \n";
		}

		if (legal == false) {
			System.out.println(warning);
		}
		return legal;

	}

	public void movePiece(Position init_pos, Position dest_pos) {

		dest_pos.getPiece().setValue(init_pos.getPiece().getValue());
		dest_pos.getPiece().setPlayer(init_pos.getPiece().getPlayer());
		init_pos.getPiece().setValue("-");
		init_pos.getPiece().setPlayer(null);
	}

}
