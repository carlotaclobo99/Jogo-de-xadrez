package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Board;
import entities.Piece;
import entities.Player;
import entities.Position;

public class Program2 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		List<Character> files = new ArrayList<Character>(List.of('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'));
		List<Character> ranks = new ArrayList<Character>(List.of('1', '2', '3', '4', '5', '6', '7', '8'));
		List<String> setup = new ArrayList<String>(List.of("R", "B", "N", "Q", "K", "N", "B", "R"));

		Position aux[][] = new Position[8][8];
		Player p1 = new Player("Maria", "white");
		Player p2 = new Player("Sara", "black");
		List<Player> players = new ArrayList<Player>(List.of(p1, p2));

		for (int j = 0; j < 8; j++) {
			for (int i = 0; i < 8; i++) {
				Position pos = null;
				Piece piece = new Piece();

				if (i == 0 || i == 7) {
					piece.setValue(setup.get(j));
					pos = new Position(files.get(j), ranks.get(i), piece);
				} else {
					if (i == 1 || i == 6) {
						piece.setValue("p");
						pos = new Position(files.get(j), ranks.get(i), piece);
					} else {
						piece.setValue("-");
						pos = new Position(files.get(j), ranks.get(i), piece);
					}
				}
				if (j > 3) {
					piece.setPlayer(p2);
				}
				
				else {
					piece.setPlayer(p1);
				}

				aux[j][i] = pos;
			}
		}

		System.out.println("Creating new board:" + "\n");
		Board board = new Board(aux, p1, p2);

		System.out.println(board.loadBoard());
		System.out.println();
		String check = "";
		Player current_player;
		Position test = new Position('a', '2', new Piece("R", p1));
		System.out.println(test.getPiece().getPlayer().getName());

		for (int i = 0; i < 3; i++) {
			for (Player p : players) {

				System.out.print(p.getName() + "'s turn, ");
				System.out.println(p.getColor() + " to move:");

				String move = sc.next();
				Character file = (char) move.charAt(0);
				Character rank = (char) move.charAt(1);
				System.out.println(files.indexOf(file));
				Position current = board.getPositions()[files.indexOf(file)][ranks.indexOf(rank)];
				System.out.println(current.getPiece().getPlayer().getName());
				System.out.println(current.getPiece().getPlayer().getName() == p.getName());
				if (current.getPiece().getPlayer() != p) {
					System.out.println("This piece is not yours!");
					System.out.println("Please choose another piece: ");
					move = sc.next();
					file = (char) move.charAt(0);
					rank = (char) move.charAt(1);
					current = board.getPositions()[files.indexOf(file)][ranks.indexOf(rank)];
				}

				System.out.println("Moving to: ");
				String move1 = sc.next();
				Character file1 = move1.charAt(0);
				Character rank1 = move1.charAt(1);
				Position new_pos = board.getPositions()[files.indexOf(file1)][ranks.indexOf(rank1)];

				if (new_pos.getPiece().getPlayer() == p) {
					System.out.println("You cannot take one of your pieces!");
					System.out.println("Choose another cell: ");
					move1 = sc.next();
					file1 = move1.charAt(0);
					rank1 = move1.charAt(1);
					new_pos = board.getPositions()[files.indexOf(file1)][ranks.indexOf(rank1)];
				}

				if (new_pos.getPiece().getValue() == "K") {
					System.out.println("Check-mate!");
					check = "Yes";
					break;
				}
				new_pos.getPiece().setValue(current.getPiece().getValue());
				current.getPiece().setValue("-");

				System.out.println(board.loadBoard());

			}
			if (check == "Yes") {
				break;

			}
		}

		sc.close();
	}

}
