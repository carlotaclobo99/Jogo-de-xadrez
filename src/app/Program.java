package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Board;
import entities.Piece;
import entities.Player;
import entities.Position;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		List<Character> files = new ArrayList<Character>(List.of('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'));
		List<Character> ranks = new ArrayList<Character>(List.of('1', '2', '3', '4', '5', '6', '7', '8'));
		List<String> setup = new ArrayList<String>(List.of("R", "B", "N", "Q", "K", "N", "B", "R"));
		Position aux[][] = new Position[8][8];
		
		Player p1 = new Player("Maria", "white");
		Player p2 = new Player("Sara", "black");
		Player pnone = null;
		List<Player> players = new ArrayList<Player>(List.of(p1, p2));
		Board board = new Board(aux, p1, p2);
		board.createBoard();

		for (int rank = 0; rank < 8; rank++) {
			for (int file = 0; file < 8; file++) {
				Piece piece = new Piece();
				Position pos = new Position(rank, file, piece);

				if (rank == 0 || rank == 7) {
					piece.setValue(setup.get(file));
					System.out.println(setup.get(file));
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
					piece.setPlayer(pnone);
				}
				aux[rank][file] = pos;
				board.getPositions()[rank][file] = pos;
			}
		}
		
		for (int i = 0; i<8; i++) {
			for (int j = 0; j<8; j++) {
				System.out.println(aux[i][j].getPiece());
				
			}
		}

		System.out.println("Creating new board:" + "\n");

		System.out.println(board.loadBoard());
		System.out.println();
		String check = "";

		for (int i = 0; i < 3; i++) {
			for (Player p : players) {

				System.out.print(p.getName() + "'s turn, ");
				System.out.println(p.getColor() + " to move:");

				String move = sc.next();
				Character file = (char) move.charAt(0);
				Character rank = (char) move.charAt(1);

				Position current = board.getPositions()[ranks.indexOf(rank)][files.indexOf(file)];

				while (board.legalPiece(current.getPiece(), p)!=true) {
					
					move = sc.next();
					file = (char) move.charAt(0);
					rank = (char) move.charAt(1);
					current = board.getPositions()[ranks.indexOf(rank)][files.indexOf(file)];
				}

				System.out.println("Moving to: ");
				String move1 = sc.next();
				Character file1 = move1.charAt(0);
				Character rank1 = move1.charAt(1);
				Position new_pos = board.getPositions()[ranks.indexOf(rank1)][files.indexOf(file1)];

				while (board.legalMove(current, new_pos, p)!=true) {
					
					move1 = sc.next();
					file1 = move1.charAt(0);
					rank1 = move1.charAt(1);
					new_pos = board.getPositions()[ranks.indexOf(rank1)][files.indexOf(file1)];
				}

				if (new_pos.getPiece().getValue() == "K") {
					System.out.println("Check-mate!");
					check = "Yes";
					break;
				}
				
				board.movePiece(current, new_pos);
				System.out.println(board.loadBoard());

			}
			if (check == "Yes") {
				break;

			}
		}

		sc.close();
	}

}