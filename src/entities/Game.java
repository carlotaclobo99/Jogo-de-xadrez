package entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import enums.Color;
import enums.PieceType;

public class Game {

	private Board board;
	Player white = new Player();
	Player black = new Player();
	public List<Character> files = new ArrayList<Character>(List.of('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'));
	public List<Character> ranks = new ArrayList<Character>(List.of('1', '2', '3', '4', '5', '6', '7', '8'));
	public static final String RESET = "\u001B[0m";

	public Game() {
	}

	public Game(Board board, Player white, Player black) {
		super();
		Collections.reverse(ranks);
		this.board = new Board();
		this.white = white;
		this.black = black;
	}

	public Player getWhite() {
		return white;
	}

	public void setWhite(Player white) {
		this.white = white;
	}

	public Player getBlack() {
		return black;
	}

	public void setBlack(Player black) {
		this.black = black;
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

	public void createBoard() {
		List<PieceType> setup = this.board.getSetup();
		List<List<Position>> grid = this.board.getGrid();
		for (List<Position> row : grid) {
			for (Position pos : row) {
				Piece piece = new Piece();
				piece.setPieceType(PieceType.EMPTY);
				piece.setPlayer(null);

				if (pos.getRank() == 8) {
					piece.setPieceType(setup.get(row.indexOf(pos)));
					piece.setPlayer(white);
				}

				if (pos.getRank() == 1) {
					piece.setPieceType(setup.get(row.indexOf(pos)));
					piece.setPlayer(black);
				}

				if (pos.getRank() == 7) {
					piece.setPieceType(PieceType.PAWN);
					piece.setPlayer(white);
				}

				if (pos.getRank() == 2) {
					piece.setPieceType(PieceType.PAWN);
					piece.setPlayer(black);
				}
				pos.setPiece(piece);
			}
		}
	}

	public void SetBoard() {

	}

	public String loadBoard() {
		List<List<Position>> grid = this.getBoard().getGrid();
		String table = "";
		String header = "   ";
		for (Character file : files) {
			header += " " + file.toString() + " ";
		}
		table += "           " + white.getName() + "\n";
		table += "           " + white.getColor() + "\n";
		System.out.println();
		table += header + " \n";
		table += "  --------------------------\n";
		for (int i = 0; i < grid.size(); i++) {
			List<Position> row = grid.get(i);
			String line = ranks.get(i).toString() + "| ";
			for (Position pos : row) {
				Piece piece = pos.getPiece();
				// StringBuilder sb = new StringBuilder();
				// sb.append(piece.getPlayer().getColor().getCode()); // apply color
				// sb.append(piece.getPieceType().getLabel()); // append piece name
				// sb.append("\u001B[0m ");
				String val = piece.getPieceType().getLabel();
				line += " " + val + " ";

			}
			table += line + " |" + ranks.get(i).toString() + "\n";
		}
		table += "  --------------------------\n";

		table += header + "\n";
		table += "            " + black.getColor() + "\n";
		table += "            " + black.getName() + "\n  \n";
		table += "Pieces captured by " + white.getName() + ": ";
		for (Piece piece : white.getCapturedPieces()) {
			table += piece.getPieceType() + " ";
		}
		table += "\n";
		table += "Pieces captured by " + black.getName() + ": ";
		for (Piece piece : black.getCapturedPieces()) {
			table += piece.getPieceType() + "\n";
		}
		table += "\n";
		return table;
	}

	public String loadValidMoves(Position position, Player player) {
		List<Position> legalMoves = this.legalMoves(position, player);
		List<List<Position>> grid = this.getBoard().getGrid();
		String table = "";
		String header = "   ";
		String moves = "";
		for (Position pos : legalMoves) {
			moves += pos.printPos() + " ";
		}
		for (Character file : files) {
			header += " " + file.toString() + " ";
		}
		table += "           " + white.getName() + "\n";
		table += "           " + white.getColor() + "\n";
		System.out.println();
		table += header + " \n";
		table += "  --------------------------\n";
		for (int i = 0; i < grid.size(); i++) {
			List<Position> row = grid.get(i);
			String line = ranks.get(i).toString() + "| ";
			for (Position pos : row) {
				Piece piece = pos.getPiece();
				String val = piece.getPieceType().getLabel();
				if (legalMoves.contains(pos) && piece.getPieceType() == PieceType.EMPTY)
					val = "*";
				line += " " + val + " ";

			}
			table += line + " |" + ranks.get(i).toString() + "\n";
		}
		table += "  --------------------------\n";

		table += header + "\n";
		table += "            " + black.getColor() + "\n";
		table += "            " + black.getName() + "\n  \n";
		table += "Valid moves: " + moves;
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

	public List<Position> legalMoves(Position position, Player p) {
		List<Position> legal_moves = new ArrayList<>();
		Piece piece = position.getPiece();
		String val = piece.getPieceType().getLabel().toLowerCase().trim();
		String bishop = "b";
		String rook = "r";
		String knight = "n";
		String pawn = "p";
		String queen = "q";
		String king = "k";
		if (val.equals(pawn)) {
			legal_moves = legalPawn(position, p);
			this.checkEnPassant(legal_moves, position, p);
		}
		if (val.equals(rook)) {
			legal_moves = legalRook(position, p);
			System.out.println("Can small castle?: " + smallCastling(p));
			System.out.println("Can large castle?: " + largeCastling(p));
			smallCastling(p);
			largeCastling(p);
		}
		if (val.equals(bishop)) {
			legal_moves = legalBishop(position, p);
		}
		if (val.equals(knight)) {
			legal_moves = legalKnight(position, p);
		}
		if (val.equals(queen)) {
			legal_moves = legalBishop(position, p);
			legal_moves.addAll(legalRook(position, p));
			Set<Position> set = new HashSet<>(legal_moves);
			legal_moves = new ArrayList<>(set);
		}
		if (val.equals(king)) {
			legal_moves = legalKing(position, p);
		}
		return legal_moves;

	}

	public void movePiece(Move move, Player player) {

		Position init_pos = move.getStart();
		Position dest_pos = move.getEnd();
		int initFileIndex = this.files.indexOf(init_pos.getFile().charAt(0));
		int initRankIndex = this.ranks.indexOf((char) ('0' + init_pos.getRank()));
		int destFileIndex = this.files.indexOf(dest_pos.getFile().charAt(0));
		int destRankIndex = this.ranks.indexOf((char) ('0' + dest_pos.getRank()));
		Piece initPiece = move.getStart().getPiece();
		Piece destPiece = move.getEnd().getPiece();
		if (destPiece.getPieceType() != PieceType.EMPTY)
			capturePiece(destPiece, player);
		init_pos.setPiece(new Piece(PieceType.EMPTY, null));
		System.out.println(init_pos.getPiece().getPlayer() == null);
		initPiece.setNumber_moves(initPiece.getNumber_moves() + 1);

		dest_pos.setPiece(initPiece);
		if (player.getColor() == Color.BLUE) {
			if (initPiece.getPieceType() == PieceType.PAWN) {
				if (dest_pos.getRank() == 1) {
					this.promotion(initPiece, player);
				}
				if ((Math.abs(destRankIndex - initRankIndex) == 1) && (Math.abs(destFileIndex - initFileIndex)) == 1) {
					this.board.getGrid().get(destRankIndex - 1).get(destFileIndex).getPiece()
							.setPieceType(PieceType.EMPTY);
				}
			}
		}
		if (player.getColor() == Color.RED) {
			if (initPiece.getPieceType() == PieceType.PAWN) {
				if (dest_pos.getRank() == 8) {
					this.promotion(initPiece, player);

				}
				if ((Math.abs(destRankIndex - initRankIndex) == 1) && (Math.abs(destFileIndex - initFileIndex)) == 1) {
					this.board.getGrid().get(destRankIndex + 1).get(destFileIndex).getPiece()
							.setPieceType(PieceType.EMPTY);
				}
			}
		}
		if (initPiece.getPieceType() == PieceType.PAWN) {

		}
	}

	public void capturePiece(Piece piece, Player player) {
		player.getCapturedPieces().add(piece);
	}

	public boolean emptySquare(Position position) {
		if (position.getPiece().getPlayer() == null)
			return true;
		return false;
	}

	public List<Position> legalPawn(Position position, Player p) {
		List<Position> legal_moves = new ArrayList<>();
		String file = position.getFile();
		int rank = position.getRank();
		int fileIndex = this.files.indexOf(file.charAt(0));
		int rankIndex = this.ranks.indexOf((char) ('0' + rank));
		Piece pawn = position.getPiece();
		if (p.getColor() == Color.BLUE) {
			Position firstPos = this.board.getGrid().get(rankIndex + 1).get(fileIndex);
			if (this.emptySquare(firstPos))
				legal_moves.add(firstPos);
			if (pawn.getNumber_moves() == 0) {
				Position secondPos = this.board.getGrid().get(rankIndex + 2).get(fileIndex);
				if (this.emptySquare(secondPos))
					legal_moves.add(secondPos);
			}
			try {
				Position captRight = this.board.getGrid().get(rankIndex + 1).get(fileIndex + 1);
				Position captLeft = this.board.getGrid().get(rankIndex + 1).get(fileIndex - 1);
				try {
					if (captRight.getPiece().getPlayer().getColor() == Color.RED)
						legal_moves.add(captRight);
					if (captLeft.getPiece().getPlayer().getColor() == Color.RED)
						legal_moves.add(captLeft);
				} catch (NullPointerException e) {
				}
			} catch (IndexOutOfBoundsException e) {
			}
		}
		if (p.getColor() == Color.RED)

		{

			Position firstPos = this.board.getGrid().get(rankIndex - 1).get(fileIndex);
			if (this.emptySquare(firstPos))
				legal_moves.add(firstPos);
			if (pawn.getNumber_moves() == 0) {
				Position secondPos = this.board.getGrid().get(rankIndex - 2).get(fileIndex);
				if (this.emptySquare(secondPos))
					legal_moves.add(secondPos);
			}
			try {
				Position captRight = this.board.getGrid().get(rankIndex - 1).get(fileIndex + 1);
				Position captLeft = this.board.getGrid().get(rankIndex - 1).get(fileIndex - 1);
				try {
					if (captRight.getPiece().getPlayer().getColor() == Color.BLUE)
						legal_moves.add(captRight);
					if (captLeft.getPiece().getPlayer().getColor() == Color.BLUE)
						legal_moves.add(captLeft);
				} catch (NullPointerException e) {
				}
			} catch (IndexOutOfBoundsException e) {
			}
		}
		return legal_moves;
	}

	public List<Position> legalRook(Position position, Player p) {
		List<Position> legal_moves = new ArrayList<>();
		String file = position.getFile();
		int rank = position.getRank();
		int fileIndex = this.files.indexOf(file.charAt(0));
		int rankIndex = this.ranks.indexOf((char) ('0' + rank));

		// Same row but check columns left
		for (int i = fileIndex - 1; i >= 0; i--) {
			Position pos = this.board.getGrid().get(rankIndex).get(i);
			if (this.emptySquare(pos)) {
				legal_moves.add(pos);
			} else {
				if (pos.getPiece().getPlayer() != null && pos.getPiece().getPlayer() != p) {
					legal_moves.add(pos);
					break;
				} else
					break;
			}
		}

		// Same row but check columns right
		for (int i = fileIndex + 1; i < 8; i++) {
			Position pos = this.board.getGrid().get(rankIndex).get(i);
			if (this.emptySquare(pos)) {
				legal_moves.add(pos);
			} else {
				if (pos.getPiece().getPlayer() != null && pos.getPiece().getPlayer() != p) {
					legal_moves.add(pos);
					break;
				} else
					break;
			}
		}

		// Same column but rows above
		for (int i = rankIndex - 1; i >= 0; i--) {
			Position pos = this.board.getGrid().get(i).get(fileIndex);
			if (this.emptySquare(pos)) {
				legal_moves.add(pos);
			} else {
				if (pos.getPiece().getPlayer() != null && pos.getPiece().getPlayer() != p) {
					legal_moves.add(pos);
					break;
				} else
					break;
			}
		}

		// Same column but rows below
		for (int i = rankIndex + 1; i < 8; i++) {
			Position pos = this.board.getGrid().get(i).get(fileIndex);
			if (this.emptySquare(pos)) {
				legal_moves.add(pos);
			} else {
				if (pos.getPiece().getPlayer() != null && pos.getPiece().getPlayer() != p) {
					legal_moves.add(pos);
					break;
				} else
					break;
			}
		}

		return legal_moves;
	}

	public List<Position> legalBishop(Position position, Player p) {
		List<Position> legal_moves = new ArrayList<>();
		String file = position.getFile();
		int rank = position.getRank();
		int fileIndex = this.files.indexOf(file.charAt(0));
		int rankIndex = this.ranks.indexOf((char) ('0' + rank));
		int rankOver = 8 - rankIndex;
		int rankBelow = rankIndex - 1;
		int fileOver = 8 - fileIndex;
		int fileBelow = fileIndex - 1;

		// Down on rank, left on file
		int limit = Math.max(rankBelow, fileBelow);
		System.out.println(limit);
		for (int i = 1; i < limit; i++) {
			try {
				Position pos = this.board.getGrid().get(rankIndex - i).get(fileIndex - i);
				System.out.println(pos);
				if (this.emptySquare(pos))
					legal_moves.add(pos);
				else {
					if (pos.getPiece().getPlayer() != null && pos.getPiece().getPlayer() != p) {
						legal_moves.add(pos);
						break;
					} else
						break;
				}
			} catch (IndexOutOfBoundsException e) {
				continue;
			}
		}

		// Down on rank, right on file
		limit = Math.max(rankBelow, fileOver);
		for (int i = 1; i < limit; i++) {
			try {
				Position pos = this.board.getGrid().get(rankIndex - i).get(fileIndex + i);
				System.out.println(pos);
				if (this.emptySquare(pos))
					legal_moves.add(pos);
				else {
					if (pos.getPiece().getPlayer() != null && pos.getPiece().getPlayer() != p) {
						legal_moves.add(pos);
						break;
					} else
						break;
				}
			}
			catch (IndexOutOfBoundsException e) {continue;}
		}

		// Up on rank, left on file
		limit = Math.max(rankOver, fileBelow);
		for (int i = 1; i < limit; i++) {
			try {
				Position pos = this.board.getGrid().get(rankIndex + i).get(fileIndex - i);
				System.out.println(pos);
				if (this.emptySquare(pos))
					legal_moves.add(pos);
				else {
					if (pos.getPiece().getPlayer() != null && pos.getPiece().getPlayer() != p) {
						legal_moves.add(pos);
						break;
					} else
						break;
				}
			}
			catch (IndexOutOfBoundsException e) {continue;}
		}

		// up on rank, right on file
		limit = Math.max(rankOver, fileOver);
		for (int i = 1; i < limit; i++) {
			try {
				Position pos = this.board.getGrid().get(rankIndex + i).get(fileIndex + i);
				System.out.println(pos);
				if (this.emptySquare(pos))
					legal_moves.add(pos);
				else {
					if (pos.getPiece().getPlayer() != null && pos.getPiece().getPlayer() != p) {
						legal_moves.add(pos);
						break;
					} else
						break;
				}
			}
			catch (IndexOutOfBoundsException e) {continue;}
		}

		return legal_moves;
	}

	public List<Position> legalKing(Position position, Player p) {
		List<Position> legal_moves = new ArrayList<>();
		String file = position.getFile();
		int rank = position.getRank();
		int fileIndex = this.files.indexOf(file.charAt(0));
		int rankIndex = this.ranks.indexOf((char) ('0' + rank));
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				try {
					Position pos = this.board.getGrid().get(rankIndex + i).get(fileIndex + j);
					System.out.println(pos);
					if (this.emptySquare(pos))
						legal_moves.add(pos);
					else {
						if (pos.getPiece().getPlayer() != null && pos.getPiece().getPlayer() != p) {
							legal_moves.add(pos);
						}
					}
				} catch (IndexOutOfBoundsException e) {
					continue;
				}
			}
		}

		return legal_moves;
	}

	public List<Position> legalKnight(Position position, Player p) {
		List<Position> legal_moves = new ArrayList<>();
		String file = position.getFile();
		int rank = position.getRank();
		int fileIndex = this.files.indexOf(file.charAt(0));
		int rankIndex = this.ranks.indexOf((char) ('0' + rank));
		List<int[]> disp = new ArrayList<>();
		disp.add(new int[] { -2, -1 });
		disp.add(new int[] { -2, 1 });
		disp.add(new int[] { 2, -1 });
		disp.add(new int[] { 2, 1 });
		disp.add(new int[] { -1, -2 });
		disp.add(new int[] { -1, 2 });
		disp.add(new int[] { 1, -2 });
		disp.add(new int[] { 1, 2 });

		for (int[] values : disp) {
			int i = values[0];
			int j = values[1];
			try {
				Position pos = this.board.getGrid().get(rankIndex + i).get(fileIndex + j);
				if (this.emptySquare(pos))
					legal_moves.add(pos);
				else {
					if (pos.getPiece().getPlayer() != null && pos.getPiece().getPlayer() != p) {
						legal_moves.add(pos);
					}
				}
			} catch (IndexOutOfBoundsException e) {
				continue;

			}
		}

		return legal_moves;
	}

	public boolean smallCastling(Player p) {
		Piece kingRook = null;
		Piece king = null;
		int rank = 0;
		if (p.getColor() == Color.BLUE) {
			kingRook = this.board.getGrid().get(0).get(7).getPiece();
			king = this.board.getGrid().get(0).get(4).getPiece();
		}
		if (p.getColor() == Color.RED) {
			kingRook = this.board.getGrid().get(7).get(7).getPiece();
			king = this.board.getGrid().get(7).get(4).getPiece();
			rank = 7;
		}
		if (kingRook.getPieceType() != PieceType.ROOK || kingRook.getPlayer() != p)
			return false;
		if (king.getPieceType() != PieceType.KING || king.getPlayer() != p)
			return false;
		if (king.getNumber_moves() != 0 || kingRook.getNumber_moves() != 0)
			return false;
		for (int i = 5; i < 7; i++) {
			Piece piece = this.board.getGrid().get(rank).get(i).getPiece();
			if (piece.getPlayer() != null) {
				return false;
			}
		}
		return true;
	}

	public boolean largeCastling(Player p) {
		Piece queenRook = null;
		Piece king = null;
		int rank = 0;
		if (p.getColor() == Color.BLUE) {
			queenRook = this.board.getGrid().get(0).get(0).getPiece();
			king = this.board.getGrid().get(0).get(4).getPiece();
		}
		if (p.getColor() == Color.RED) {
			queenRook = this.board.getGrid().get(7).get(0).getPiece();
			king = this.board.getGrid().get(7).get(4).getPiece();
			rank = 7;
		}
		if (queenRook.getPieceType() != PieceType.ROOK || queenRook.getPlayer() != p)
			return false;
		if (king.getPieceType() != PieceType.KING || king.getPlayer() != p)
			return false;
		if (king.getNumber_moves() != 0 || queenRook.getNumber_moves() != 0)
			return false;
		for (int i = 1; i < 4; i++) {
			Piece piece = this.board.getGrid().get(rank).get(i).getPiece();
			if (piece.getPlayer() != null) {
				return false;
			}
		}
		return true;
	}

	public boolean checkEnPassant(List<Position> moves, Position position, Player p) {
		String file = position.getFile();
		int rank = position.getRank();
		if (p.getColor() == Color.BLUE && rank != 4)
			return false;
		if (p.getColor() == Color.RED && rank != 5)
			return false;
		System.out.println(rank);
		int fileIndex = this.files.indexOf(file.charAt(0));
		int rankIndex = this.ranks.indexOf((char) ('0' + rank));
		System.out.println(rankIndex);
		Position left = this.board.getGrid().get(rankIndex).get(fileIndex - 1);
		Position right = this.board.getGrid().get(rankIndex).get(fileIndex + 1);
		System.out.println(left);
		System.out.println(right);
		Piece leftPiece = left.getPiece();
		Piece rightPiece = right.getPiece();
		System.out.println(leftPiece.getPieceType());
		System.out.println(rightPiece.getPieceType());
		if (leftPiece.getPieceType() == PieceType.PAWN && leftPiece.getPlayer() != p && leftPiece.getNumber_moves() == 1
				&& left.getRank() == rank) {
			Position move = this.board.getGrid().get(rankIndex + 1).get(fileIndex - 1);
			if (move.getPiece().getPieceType() == PieceType.EMPTY)
				moves.add(move);
		}
		if (rightPiece.getPieceType() == PieceType.PAWN && rightPiece.getPlayer() != p
				&& rightPiece.getNumber_moves() == 1 && right.getRank() == rank) {
			Position move = this.board.getGrid().get(rankIndex + 1).get(fileIndex + 1);
			if (move.getPiece().getPieceType() == PieceType.EMPTY)
				moves.add(move);
		}
		return true;
	}

	public void promotion(Piece piece, Player p) {
		System.out.println("Promote pawn: ");
		StringBuilder sb = new StringBuilder();
		for (PieceType pieceType : PieceType.values()) {
			if (pieceType != PieceType.EMPTY) {
				sb.append(pieceType);
				sb.append(" ");
			}
		}
		sb.append("\n");
		System.out.println(new String(sb));
		Scanner sc = new Scanner(System.in);
		String prom = sc.next();
		PieceType pt = Arrays.stream(PieceType.values()).filter(v -> v.getLabel().equals(prom.toUpperCase()))
				.findFirst().orElse(null);
		piece.setPieceType(pt);
		sc.close();
	}
}
