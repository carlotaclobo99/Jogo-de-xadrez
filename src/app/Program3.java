package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Board;
import entities.Game;
import entities.Move;
import entities.Player;
import entities.Position;

public class Program3 {

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
				//
				//
				//
				// Choose starting piece:
				//
				//
				//
				// Choose starting piece:
				String start = sc.next();
				// Check if input is valid:
				// Check if position exists:
				Character start_file = (char) start.charAt(0);
				Character start_rank = (char) start.charAt(1);
				while (!board.getFiles().contains(start_file) || !board.getRanks().contains(start_rank)) {
					System.out.println("Invalid input! Please try again: ");
					start = sc.next();

					// Check if input is valid
					while (game.validInput(start) != true) {
						System.out.println("Try again: ");
						start = sc.next();
					}

					start_file = (char) start.charAt(0);
					start_rank = (char) start.charAt(1);
				}

				Position current = board.getPositions()[board.getRanks().indexOf(start_rank)][board.getFiles()
						.indexOf(start_file)];

				// Check if piece is legal:
				while (game.legalPiece(current.getPiece(), p) != true) {
					// Choose until its not:
					start = sc.next();
					// Check if input is valid
					while (game.validInput(start) != true) {
						System.out.println("Try again: ");
						start = sc.next();
					}

					// Check if position exists:
					start_file = (char) start.charAt(0);
					start_rank = (char) start.charAt(1);
					while (!board.getFiles().contains(start_file) || !board.getRanks().contains(start_rank)) {
						System.out.println("Invalid input! Please try again: ");
						start = sc.next();

						// Check if input is valid
						while (game.validInput(start) != true) {
							System.out.println("Try again: ");
							start = sc.next();
						}

						start_file = (char) start.charAt(0);
						start_rank = (char) start.charAt(1);
					}
					// Set current position:
					current = board.getPositions()[board.getRanks().indexOf(start_rank)][board.getFiles()
							.indexOf(start_file)];

				}

				//
				//
				// If legal, choose either to move or change the starting piece:
				//
				//
				System.out.println("Go back and change starting piece (y/n)");
				String back = sc.next();

				if (back.equals("n")) {
					// Check if position exists:
					System.out.println("Moving to: ");
					String end = sc.next();
					Character end_file = (char) end.charAt(0);
					Character end_rank = (char) end.charAt(1);
					while (!board.getFiles().contains(end_file) || !board.getRanks().contains(end_rank)) {
						System.out.println("Invalid input! Please try again: ");
						end = sc.next();

						// Check if input is valid
						while (game.validInput(end) != true) {
							System.out.println("Try again: ");
							end = sc.next();
						}

						end_file = (char) end.charAt(0);
						end_rank = (char) end.charAt(1);
					}
				}
				// Choose to go back and choose starting piece again:
				while (back.equals("y")) {
					System.out.println("Choose starting piece again: ");

					// Choose starting piece:
					start = sc.next();
					// Check if input is valid
					while (game.validInput(start) != true) {
						System.out.println("Try again: ");
						start = sc.next();
					}

					// Check if position exists:
					start_file = (char) start.charAt(0);
					start_rank = (char) start.charAt(1);
					while (!board.getFiles().contains(start_file) || !board.getRanks().contains(start_rank)) {
						System.out.println("Invalid input! Please try again: ");
						start = sc.next();

						// Check if input is valid
						while (game.validInput(start) != true) {
							System.out.println("Try again: ");
							start = sc.next();
						}

						start_file = (char) start.charAt(0);
						start_rank = (char) start.charAt(1);
					}

					current = board.getPositions()[board.getRanks().indexOf(start_rank)][board.getFiles()
							.indexOf(start_file)];

					// Check if piece is legal:
					while (game.legalPiece(current.getPiece(), p) != true) {
						// Choose starting piece until its legal:
						start = sc.next();
						// Check if input is valid
						while (game.validInput(start) != true) {
							System.out.println("Try again: ");
							start = sc.next();
						}
						// Check if position exists:
						start_file = (char) start.charAt(0);
						start_rank = (char) start.charAt(1);
						while (!board.getFiles().contains(start_file) || !board.getRanks().contains(start_rank)) {
							System.out.println("Invalid input! Please try again: ");
							start = sc.next();

							// Check if input is valid
							while (game.validInput(start) != true) {
								System.out.println("Try again: ");
								start = sc.next();
							}

							start_file = (char) start.charAt(0);
							start_rank = (char) start.charAt(1);

						}
						current = board.getPositions()[board.getRanks().indexOf(start_rank)][board.getFiles()
								.indexOf(start_file)];

					}
					System.out.println("Go back (y/n)?");
					back = sc.next();
					if (back.equals("n")) {
						break;
					}

					if (back.equals("n")) {
						break;
					}

				}

				Position new_pos = board.getPositions()[board.getRanks().indexOf(end_rank)][board.getFiles()
						.indexOf(end_file)];
				Move move = new Move(current, new_pos);

				//
				//
				// Check if move is legal,
				// if not, choose either to move or change the starting piece:
				//
				//
				while (game.legalMove(move, p) == false) {
					System.out.println("Illegal move! Go back to start (y/n)?");
					back = sc.next();

					// Check if position exists:
					System.out.println("Moving to: ");
					end = sc.next();
					end_file = (char) end.charAt(0);
					end_rank = (char) end.charAt(1);
					while (!board.getFiles().contains(end_file) || !board.getRanks().contains(end_rank)) {
						System.out.println("Invalid input! Please try again: ");
						end = sc.next();

						// Check if input is valid
						while (game.validInput(end) != true) {
							System.out.println("Try again: ");
							end = sc.next();
						}

						end_file = (char) end.charAt(0);
						end_rank = (char) end.charAt(1);
					}

					// Choose to go back and choose starting piece again:
					while (back.equals("y")) {
						System.out.println("Choose starting piece again: ");

						// Choose starting piece:
						start = sc.next();
						// Check if input is valid
						while (game.validInput(start) != true) {
							System.out.println("Try again: ");
							start = sc.next();
						}

						// Check if position exists:
						start_file = (char) start.charAt(0);
						start_rank = (char) start.charAt(1);
						while (!board.getFiles().contains(start_file) || !board.getRanks().contains(start_rank)) {
							System.out.println("Invalid input! Please try again: ");
							start = sc.next();

							// Check if input is valid
							while (game.validInput(start) != true) {
								System.out.println("Try again: ");
								start = sc.next();
							}

							start_file = (char) start.charAt(0);
							start_rank = (char) start.charAt(1);
						}

						current = board.getPositions()[board.getRanks().indexOf(start_rank)][board.getFiles()
								.indexOf(start_file)];

						// Check if piece is legal:
						while (game.legalPiece(current.getPiece(), p) != true) {
							// Choose starting piece until its legal:
							start = sc.next();
							// Check if input is valid
							while (game.validInput(start) != true) {
								System.out.println("Try again: ");
								start = sc.next();
							}
							// Check if position exists:
							start_file = (char) start.charAt(0);
							start_rank = (char) start.charAt(1);
							while (!board.getFiles().contains(start_file) || !board.getRanks().contains(start_rank)) {
								System.out.println("Invalid input! Please try again: ");
								start = sc.next();

								// Check if input is valid
								while (game.validInput(start) != true) {
									System.out.println("Try again: ");
									start = sc.next();
								}

								start_file = (char) start.charAt(0);
								start_rank = (char) start.charAt(1);

							}
							current = board.getPositions()[board.getRanks().indexOf(start_rank)][board.getFiles()
									.indexOf(start_file)];

						}
						System.out.println("Go back (y/n)?");
						back = sc.next();
						if (back.equals("n")) {
							break;
						}
					}
					// Check if position exists:
					System.out.println("Moving to2: ");
					end = sc.next();
					end_file = (char) end.charAt(0);
					end_rank = (char) end.charAt(1);
					while (!board.getFiles().contains(end_file) || !board.getRanks().contains(end_rank)) {
						System.out.println("Invalid input! Please try again: ");
						end = sc.next();

						// Check if input is valid
						while (game.validInput(end) != true) {
							System.out.println("Try again: ");
							end = sc.next();
						}

						end_file = (char) end.charAt(0);
						end_rank = (char) end.charAt(1);

					}
					new_pos = board.getPositions()[board.getRanks().indexOf(end_rank)][board.getFiles()
							.indexOf(end_file)];
					move = new Move(current, new_pos);
				}
				game.movePiece(move, p);
				System.out.println(game.loadBoard());

			}

			// If a winner is declared, the game stops.
			if (check == "y") {
				System.out.println("The winner is: " + winner + "!");
				break;

			}
		}

		// Close scanner;
		sc.close();
	}
}