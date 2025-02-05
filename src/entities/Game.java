package entities;

import java.util.ArrayList;
import java.util.List;

public class Game {

	private Board board;
	List<Piece> captured_p1 = new ArrayList<>();
	List<Piece> captured_p2 = new ArrayList<>();
	List<Move> p1_moves = new ArrayList<Move>();
	List<Move> p2_moves = new ArrayList<Move>();
	List<Player> players = new ArrayList<Player>();

	private Player p1;
	private Player p2;

	public Game() {
	}

	public Game(Board board, Player p1, Player p2) {
		super();
		this.board = board;
		this.p1 = p1;
		this.p2 = p2;
	}

	public Player getP1() {
		return p1;
	}

	public void setP1(Player p1) {
		this.p1 = p1;
	}

	public Player getP2() {
		return p2;
	}

	public void setP2(Player p2) {
		this.p2 = p2;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public void capturePiece(List<Piece> captured, Piece piece) {
		captured.add(piece);
	}

	public void updateMoves(List<Move> moves, Move move) {
		moves.add(move);
	}

	public List<Piece> getCaptured_p1() {
		return captured_p1;
	}

	public List<Piece> getCaptured_p2() {
		return captured_p2;
	}

	public void createBoard() {

		List<String> setup = board.getSetup();
		for (int rank = 0; rank < 8; rank++) {
			for (int file = 0; file < 8; file++) {
				Piece piece = new Piece();
				Position pos = new Position(rank, file, piece);

				if (rank == 0 || rank == 7) {
					piece.setValue(setup.get(file));

					pos = new Position(rank, file, piece);
				} else {
					if (rank == 1 || rank == 6) {
						piece.setValue("p");
						pos = new Position(rank, file, piece);
					} else {
						piece.setValue("-");
						pos = new Position(rank, file, piece);
					}
				}
				if (rank < 2) {
					piece.setPlayer(p1);
					piece.setValue(piece.getValue().toUpperCase());
				}

				if (rank > 5) {
					piece.setPlayer(p2);
					piece.setValue(piece.getValue().toLowerCase());
				}

				if (rank >= 2 && rank <= 5) {
					piece.setPlayer(null);
				}
				board.getPositions()[rank][file] = pos;
			}
		}

	}

	public String loadBoard() {

		List<Character> files = board.getFiles();
		List<Character> ranks = board.getRanks();

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
				Position pos = board.getPositions()[rank][file];
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
		table += "            " + p2.getName() + "\n  \n";
		table += "Pieces captured by " + p1.getName() + ": ";
		for (Piece piece : captured_p1) {
			table += piece.getValue() + " ";
		}
		table += "\n";
		table += "Pieces captured by " + p2.getName() + ": ";
		for (Piece piece : captured_p2) {
			table += piece.getValue() + "\n";
		}
		table += "\n";
		return table;
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

	public boolean legalRook(Move move, Player p) {
		Position init_pos = move.getStart();
		Position dest_pos = move.getEnd();
		boolean legal = true;
		String warning = "";

		if (init_pos.getFile() != dest_pos.getFile() && init_pos.getRank() != dest_pos.getRank()) {
			warning += "The rook can only move along the same file or rank!";
			legal = false;
		}

		if (legal == false) {
			System.out.println(warning);
		}
		return legal;
	}

	public boolean legalBishop(Move move, Player p) {
		Position init_pos = move.getStart();
		Position dest_pos = move.getEnd();
		boolean legal = true;
		String warning = "";

		if (Math.abs((init_pos.getFile() - dest_pos.getFile())) != Math
				.abs((init_pos.getRank() - dest_pos.getRank()))) {
			warning += "The bishop can only move diagonally! \n";
			legal = false;
		}

		if (legal == false) {
			System.out.println(warning);
		}
		return legal;
	}

	public boolean legalKnight(Move move, Player p) {
		Position init_pos = move.getStart();
		Position dest_pos = move.getEnd();
		boolean legal = true;
		String warning = "";
		int file = init_pos.getFile();
		int rank = init_pos.getRank();
		if ((Math.abs(dest_pos.getFile() - file) == 2) && (Math.abs(dest_pos.getRank() - rank) == 1)
				|| (Math.abs(dest_pos.getFile() - file) == 1) && (Math.abs(dest_pos.getRank() - rank) == 2)) {
		} else {
			legal = false;
			warning = "Neigh! Illegal horse move!";
		}

		if (legal == false) {
			System.out.println(warning);
		}
		return legal;
	}

	public boolean legalPawn(Move move, Player p) {
		boolean legal = true;
		Position init_pos = move.getStart();
		Position dest_pos = move.getEnd();
		String warning = "";
		int file = init_pos.getFile();
		int rank = init_pos.getRank();
		if (rank == 1 || rank == 7) {
			if (Math.abs(dest_pos.getRank() - rank) > 2 || file != dest_pos.getFile()) {
				legal = false;
				warning += "The pawn can only move up to two cells forward! \n";
			}
		} else {
			if (Math.abs(dest_pos.getRank() - rank) > 1 || file != dest_pos.getFile()) {
				legal = false;
				warning += "The pawn can only move one cell forward! \n";

			}
		}
		if (p == p1) {
			if (dest_pos.getRank() < rank) {
				legal = false;
				warning += "The pawn can only move forward!";
			}
		}
		if (p == p2) {
			if (dest_pos.getRank() > rank) {
				legal = false;
				warning += "The pawn can only move forward!";
			}
		}
		if (legal == false) {
			System.out.println(warning);
		}

		return legal;
	}

	public boolean legalTake(Move move, Player p) {
		boolean legal = true;
		String warning = "";
		if (move.getEnd().getPiece().getPlayer() == p) {
			legal = false;
			warning = "You cannot take one of your pieces! \n";
		}
		if (legal == false) {
			System.out.println(warning);
		}
		return legal;
	}

	public boolean legalSkip(Move move) {
		boolean legal = true;
		Position start = move.getStart();
		Position end = move.getEnd();
		int range_ranks = end.getRank() - start.getRank();
		int range_files = end.getFile() - start.getFile();
		Piece piece = move.getStart().getPiece();
		String val = piece.getValue().toLowerCase();
		String warning = "You cannot skip over pieces!!";
		if (val.equals("b")) {
			for (int i = 0; i < range_ranks; i++) {
				for (int j = 0; j < range_files; j++) {
					Position aux = board.getPositions()[start.getRank() + i][start.getFile() + j];
					if (aux.getPiece().getPlayer() != null) {
						legal = false;
					}
				}
			}
		}
		if (val.equals("r")) {
			if (start.getRank() == end.getRank()) {
				for (int i = 0; i < range_files; i++) {
					Piece p = board.getPositions()[start.getRank()][start.getFile() + i].getPiece();
					if (p.getPlayer() != null) {
						legal = false;
					}
				}
			}
			if (start.getFile() == end.getFile()) {
				for (int i = 0; i < range_ranks; i++) {
					Piece p = board.getPositions()[start.getRank() + i][start.getFile()].getPiece();
					if (p.getPlayer() != null) {
						legal = false;
					}
				}
			}
		}

		if (legal == false) {
			System.out.println(warning);
		}
		return legal;
	}

	public boolean legalMove(Move move, Player p) {
		Position init_pos = move.getStart();
		boolean legal = false;
		boolean legal_move = true;
		boolean legal_piece = true;
		boolean legal_skip = true;
		Piece piece = init_pos.getPiece();
		String val = piece.getValue().toLowerCase().trim();
		String bishop = "b";
		String rook = "r";
		String knight = "n";
		String pawn = "p";

		if (val.equals(rook)) {
			legal_piece = legalRook(move, p);
		}

		if (val.equals(bishop)) {
			legal_piece = legalBishop(move, p);
		}

		if (val.equals(knight)) {
			legal_piece = legalKnight(move, p);
		}

		if (val.equals(pawn)) {
			legal_piece = legalPawn(move, p);
		}

		legal_move = legalTake(move, p);
		legal_skip = legalSkip(move);

		if (legal_piece && legal_move && legal_skip) {
			legal = true;
		}
		if (legal == false) {
			System.out.println("Choose another cell: ");
		}

		return legal;

	}

	public void movePiece(Move move, Player player) {

		Position init_pos = move.getStart();
		Position dest_pos = move.getEnd();
		Piece cap = dest_pos.getPiece();
		String val = cap.getValue();
		if (player == p1 && cap.getPlayer() != null) {
			captured_p1.add(new Piece(val, null));
		}
		if (player == p2 && cap.getPlayer() != null) {
			captured_p2.add(new Piece(val, null));
		}
		dest_pos.setPiece(init_pos.getPiece());
		init_pos.setPiece(new Piece("-", null));

	}

	public boolean validInput(String input) {
		boolean valid = true;
		if (input.length() != 2) {
			valid = false;
		}
		if (valid == false) {
			System.out.println("Invalid input!");
		}
		return valid;
	}

}
