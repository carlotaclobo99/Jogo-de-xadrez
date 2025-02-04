package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Board;
import entities.Game;
import entities.Move;
import entities.Player;
import entities.Position;

public class Program2 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		Player p1 = new Player("Maria", "white");
		Player p2 = new Player("Sara", "black");
		Player pnone = null;
		List<Player> players = new ArrayList<Player>(List.of(p1, p2));
		Board board = new Board();
		Game game = new Game(board, p1, p2);
		game.createBoard();
		System.out.println("Creating new board:" + "\n");
		System.out.println(game.loadBoard());
		System.out.println();
		String check = "";
		Player winner = pnone;

		while (check != "y") {
			for (Player p : players) {

				System.out.print(p.getName() + "'s turn, ");
				System.out.println(p.getColor() + " to move:");

				String play = sc.next();
				Character file = (char) play.charAt(0);
				Character rank = (char) play.charAt(1);

				Position current = board.getPositions()[board.getRanks().indexOf(rank)][board.getFiles().indexOf(file)];

				while (game.legalPiece(current.getPiece(), p) != true) {
					play = sc.next();
					file = (char) play.charAt(0);
					rank = (char) play.charAt(1);
					current = board.getPositions()[board.getRanks().indexOf(rank)][board.getFiles().indexOf(file)];
				}

				System.out.println("Moving to: ");
				String move1 = sc.next();
				while (move1.equals("back")) {
					System.out.println("Choose starting piece again: ");

					play = sc.next();
					file = (char) play.charAt(0);
					rank = (char) play.charAt(1);

					current = board.getPositions()[board.getRanks().indexOf(rank)][board.getFiles().indexOf(file)];

					while (game.legalPiece(current.getPiece(), p) != true) {
						play = sc.next();
						file = (char) play.charAt(0);
						rank = (char) play.charAt(1);
						current = board.getPositions()[board.getRanks().indexOf(rank)][board.getFiles().indexOf(file)];
						
					}
					System.out.println("Moving to: ");
					move1 = sc.next();
					if (!move1.equals("back")) {
						break;
					}

				}
				
				Character file1 = move1.charAt(0);
				Character rank1 = move1.charAt(1);
				Position new_pos = board.getPositions()[board.getRanks().indexOf(rank1)][board.getFiles()
						.indexOf(file1)];
				Move move = new Move(current, new_pos);
				while (game.legalMove(move, p) == false) {

					move1 = sc.next();
					while (move1.equals("back")) {
						System.out.println("Choose another cell: ");
						move1 = sc.next();
					}
					file1 = move1.charAt(0);
					rank1 = move1.charAt(1);
					new_pos = board.getPositions()[board.getRanks().indexOf(rank1)][board.getFiles().indexOf(file1)];
					move = new Move(current, new_pos);
				}

				if (new_pos.getPiece().getValue().toUpperCase() == "K") {
					System.out.println("Check-mate!");
					check = "y";
					winner = p;
					break;
				}

				game.movePiece(move, p);

				System.out.println(game.loadBoard());

			}
			if (check == "y") {
				System.out.println("The winner is: " + winner + "!");
				break;

			}
		}

		sc.close();
	}

}