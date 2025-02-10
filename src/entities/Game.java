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
	List<Position> possible_moves = new ArrayList<Position>();

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

	public List<Position> legalRook(Position position, Player p) {
		List<Position> legal_moves = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Position pos = board.getPositions()[i][j];
				if (pos.getPiece().getPlayer() == p) {

				} else {

				}
			}
		}

		return legal_moves;
	}

	public List<Position> legalBishop(Position position, Player p) {
		List<Position> legal_moves = new ArrayList<>();

		return legal_moves;
	}

	public List<Position> legalKnight(Position position, Player p) {
		List<Position> legal_moves = new ArrayList<>();
		int file = position.getFile();
		int rank = position.getRank();
		for (int i = -2; i < 3; i++) {
			for (int j = -2; j < 3; j++) {
				if ((Math.abs(i) == 2) && (Math.abs(j) == 1) || (Math.abs(i) == 1) && (Math.abs(j) == 2)) {
					try {
						Position pos = board.getPositions()[rank+j][file+i];
						if (pos.getPiece().getPlayer()!=p) {
							legal_moves.add(pos);
						}
						}
					catch (ArrayIndexOutOfBoundsException e) {

					}
				}
			}
		}

		return legal_moves;
	}

	public List<Position> legalPawn(Position position, Player p) {
		List<Position> legal_moves = new ArrayList<>();
		int file = position.getFile();
		int rank = position.getRank();
		if (p == p1) {
			try {
				Position pos = board.getPositions()[rank + 1][file];
				if (pos.getPiece().getPlayer()!=p) {
					legal_moves.add(pos);
				}
				}
			catch (ArrayIndexOutOfBoundsException e) {

			}
			if (rank == 1) {
				try {
					Position pos = board.getPositions()[rank + 2][file];
					if (pos.getPiece().getPlayer()!=p) {
						legal_moves.add(pos);
					}
					}
				catch (ArrayIndexOutOfBoundsException e) {

				}
			}

		}
		if (p == p2) {
			try {
				Position pos = board.getPositions()[rank - 1][file];
				if (pos.getPiece().getPlayer()!=p) {
					legal_moves.add(pos);
				}
				}
			catch (ArrayIndexOutOfBoundsException e) {

			}
			if (rank == 1) {
				try {
					Position pos = board.getPositions()[rank - 2][file];
					if (pos.getPiece().getPlayer()!=p) {
						legal_moves.add(pos);
					}
					}
				catch (ArrayIndexOutOfBoundsException e) {

				}
			}

		}

		return legal_moves;
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

	public List<Position> legalMoves(Position position, Player p) {
		List<Position> legal_moves = new ArrayList<>();

		Piece piece = position.getPiece();
		String val = piece.getValue().toLowerCase().trim();
		String bishop = "b";
		String rook = "r";
		String knight = "n";
		String pawn = "p";
		if (val.equals(pawn)) {
			legal_moves = legalPawn(position, p);
		}
		if (val.equals(rook)) {
			legal_moves = legalRook(position, p);
		}
		if (val.equals(bishop)) {
			legal_moves = legalBishop(position, p);
		}
		if (val.equals(knight)) {
			legal_moves = legalKnight(position, p);
		}

		return legal_moves;

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
