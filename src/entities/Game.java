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
	Player p3 = new Player("Ana", "blue");
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
		String roo ="";

		if (init_pos.getFile() != dest_pos.getFile() && init_pos.getRank() != dest_pos.getRank()) {
			warning += "The rook can only move along the same file or rank!";
			legal = false;
		}

		for (int rank = init_pos.getRank(); rank < dest_pos.getRank(); rank++) {
			for (int file = init_pos.getFile(); file < dest_pos.getFile(); file++) {
				Position test_pos = board.getPositions()[file][rank];

				if (test_pos.getPiece().getValue() != "-") {
					legal = false;
					roo = "The rook cannot skip over pieces!";
				}
			}
		}
		warning+=roo;
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

		if (init_pos.getFile() == dest_pos.getFile() || init_pos.getRank() == dest_pos.getRank()) {
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
		if ((dest_pos.getFile() - file == 2) && (dest_pos.getRank() - rank == 1)
				|| (dest_pos.getFile() - file == 1) && (dest_pos.getRank() - rank == 2)) {
		} else {
			legal = false;
			warning = "Neigh! Illegal horse move!";
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

	public boolean legalMove(Move move, Player p) {
		Position init_pos = move.getStart();
		boolean legal = true;
		Piece piece = init_pos.getPiece();
		String val = piece.getValue().toLowerCase().trim();
		String bishop = "b";
		String rook = "r";
		String knight = "n";

		if (val.equals(rook)) {
			legal = legalRook(move, p);
		}

		if (val.equals(bishop)) {
			legal = legalBishop(move, p);
		}

		if (val.equals(knight)) {
			legal = legalKnight(move, p);
		}
		
		legal = legalTake(move, p);

		if (legal == false) {
			System.out.println("Choose another cell: ");
		}
		
		return legal;

	}

	public void movePiece(Move move, Player player) {

		Position init_pos = move.getStart();
		Position dest_pos = move.getEnd();
		dest_pos.getPiece().setValue(init_pos.getPiece().getValue());
		dest_pos.getPiece().setPlayer(player);
		init_pos.getPiece().setValue("-");
		init_pos.getPiece().setPlayer(null);

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
					piece.setPlayer(p3);
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
		table += "            " + p2.getName() + "\n + \n";
		
		return table;
	}

}

