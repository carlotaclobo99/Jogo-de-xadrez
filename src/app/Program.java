package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Board;
import entities.Game;
import entities.Move;
import entities.Player;
import entities.Position;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		List<String> choices = new ArrayList<String>();
		choices.add("y");
		choices.add("n");
		Player p1 = new Player("Maria", "white");
		Player p2 = new Player("Sara", "black");
		Player pnone = null;
		List<Player> players = new ArrayList<Player>(List.of(p1, p2));

		// Initializing game:
		Board board = new Board();
		Game game = new Game(board, p1, p2);
		game.createBoard();
		System.out.println("Creating new board:" + "\n");
		System.out.println(game.loadBoard());
		System.out.println();
		String check = "";
		Player winner = pnone;
		Position new_pos = null;
		while (check != "y") {
			for (Player p : players) {

				System.out.print(p.getName() + "'s turn, ");
				System.out.println(p.getColor() + " to move:");
				//
				//
				// Choose starting piece:
				//
				//
				System.out.println("Choose piece to move:");
				String start = sc.next();
				// Check if input is valid and if position exists:
				Character start_file = (char) start.charAt(0);
				Character start_rank = (char) start.charAt(1);

				Position current = board.getPositions()[board.getRanks().indexOf(start_rank)][board.getFiles()
						.indexOf(start_file)];

				// Check if piece is legal:
				while (game.legalPiece(current.getPiece(), p) != true) {
					// Choose until its not:
					start = sc.next();
					// Set current position:
					current = board.getPositions()[board.getRanks().indexOf(start_rank)][board.getFiles()
							.indexOf(start_file)];
				}

				//Check if there are any possible moves:
				while (game.legalMoves(current, p).isEmpty()) {
					System.out.println("No valid moves with this piece! Please pick a different one:");
					// Choose until its not:
					start = sc.next();
					start_file = (char) start.charAt(0);
					start_rank = (char) start.charAt(1);
					// Set current position:
					current = board.getPositions()[board.getRanks().indexOf(start_rank)][board.getFiles()
							.indexOf(start_file)];

				}

				String moves = "";
				List<Position> legal_moves = game.legalMoves(current, p);
				for (Position pos : legal_moves) {
					moves += pos.printPos() + " ";
				}

				System.out.print("Choose your next move or go back: ");
				System.out.println(moves);
				String end = sc.next();
				System.out.println();
				if (!end.equals("b")) {
					Character end_file = (char) end.charAt(0);
					Character end_rank = (char) end.charAt(1);
					new_pos = board.getPositions()[board.getRanks().indexOf(end_rank)][board.getFiles()
							.indexOf(end_file)];

					while (!legal_moves.contains(new_pos)) {
						System.out.println("Please choose a valid move!: " + moves);
						end = sc.next();
						end_file = (char) end.charAt(0);
						end_rank = (char) end.charAt(1);
						new_pos = board.getPositions()[board.getRanks().indexOf(end_rank)][board.getFiles()
								.indexOf(end_file)];
					}
				}
				
				while (end.equals("b")) {
					//
					//
					//
					// Choose starting piece:
					//
					//
					System.out.println("Choose a piece to move:");
					start = sc.next();
					// Check if input is valid and if position exists:
					start_file = (char) start.charAt(0);
					start_rank = (char) start.charAt(1);

					current = board.getPositions()[board.getRanks().indexOf(start_rank)][board.getFiles()
							.indexOf(start_file)];

					// Check if piece is legal:
					while (game.legalPiece(current.getPiece(), p) != true) {
						// Choose until its not:
						start = sc.next();
						// Set current position:
						current = board.getPositions()[board.getRanks().indexOf(start_rank)][board.getFiles()
								.indexOf(start_file)];

					}

					//Check if there are any possible moves:
					while (game.legalMoves(current, p).isEmpty()) {
						System.out.println("No valid moves with this piece! Please pick a different one:");
						// Choose until its not:
						start = sc.next();
						start_file = (char) start.charAt(0);
						start_rank = (char) start.charAt(1);
						// Set current position:
						current = board.getPositions()[board.getRanks().indexOf(start_rank)][board.getFiles()
								.indexOf(start_file)];
					}

					moves = "";
					legal_moves = game.legalMoves(current, p);
					for (Position pos : legal_moves) {
						moves += pos.printPos() + " ";
					}
					System.out.print("Choose your next move or go back: ");
					System.out.println(moves);
					end = sc.next();
					if (!end.equals("b")) {
						Character end_file = (char) end.charAt(0);
						Character end_rank = (char) end.charAt(1);
						new_pos = board.getPositions()[board.getRanks().indexOf(end_rank)][board.getFiles()
								.indexOf(end_file)];

						while (!legal_moves.contains(new_pos)) {
							System.out.println("Please choose a valid move!: " + moves);
							end = sc.next();
							end_file = (char) end.charAt(0);
							end_rank = (char) end.charAt(1);
							new_pos = board.getPositions()[board.getRanks().indexOf(end_rank)][board.getFiles()
									.indexOf(end_file)];
						}
						break;
					}
				}

				Move move = new Move(current, new_pos);
				game.movePiece(move, p);
				System.out.println(game.loadBoard());
			}

		}

		// Close scanner;
		sc.close();
	}
}