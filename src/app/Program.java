package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Board;
import entities.Game;
import entities.Move;
import entities.Player;
import entities.Position;
import enums.Color;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		List<String> choices = new ArrayList<String>();
		choices.add("y");
		choices.add("n");
		Player p1 = new Player("Maria", Color.BLUE, "south");
		Player p2 = new Player("Sara", Color.RED, "north");
		List<Player> players = new ArrayList<Player>(List.of(p1, p2));

		// Initializing game:
		Board board = new Board();
		Game game = new Game(board, p1, p2);
		game.createBoard();
		// game.getBoard().printGrid();
		System.out.println("Creating new board:" + "\n");
		System.out.println(game.loadBoard());
		System.out.println();
		String check = "";
		Player winner = null;
		Position newPosition = null;
		while (check != "y") {
			for (Player p : players) {

				System.out.print(p.getName() + "'s turn, ");
				System.out.println(p.getColor() + " to move:");
				//
				// Choose startingPieceingPieceing piece:
				//
				System.out.println("Choose piece to move:");
				String startingPiece = sc.next();
				while (!validInput(startingPiece)) {
					startingPiece = sc.next();
				}
				Position currentPosition = parsePosition(game, startingPiece);

				// Check if piece is legal:
				while (game.legalPiece(currentPosition.getPiece(), p) != true) {
					startingPiece = sc.next();
					while (!validInput(startingPiece)) {
						startingPiece = sc.next();
					}
					currentPosition = parsePosition(game, startingPiece);
				}

				// Check if there are any possible moves:
				while (game.legalMoves(currentPosition, p).isEmpty()) {
					System.out.println(game.loadBoard());
					System.out.println("No valid moves with this piece! Please pick a different one:");
					startingPiece = sc.next();
					while (!validInput(startingPiece)) {
						startingPiece = sc.next();
					}
					currentPosition = parsePosition(game, startingPiece);

					while (game.legalPiece(currentPosition.getPiece(), p) != true) {
						startingPiece = sc.next();
						while (!validInput(startingPiece)) {
							startingPiece = sc.next();
						}
						currentPosition = parsePosition(game, startingPiece);
					}
				}

				System.out.println(game.loadValidMoves(currentPosition, p));

				List<Position> legal_moves = game.legalMoves(currentPosition, p);
				System.out.print("Choose your next move or go back: ");
				String end = sc.next();
				while (!validInput(end)) {
					end = sc.next();
				}
				System.out.println();
				if (!end.equals("b")) {
					System.out.println("Not going back");
					newPosition = parsePosition(game, end);
					while (!legal_moves.contains(newPosition)) {
						System.out.println("Please choose a valid move!: ");
						end = sc.next();
						while (!validInput(end)) {
							end = sc.next();
						}
						newPosition = parsePosition(game, end);
					}
				}

				while (end.equals("b")) {
					System.out.println("Part 3");
					System.out.println(game.loadBoard());
					System.out.println("Choose piece to move:");
					end = sc.next();
					while (!validInput(end)) {
						end = sc.next();
					}
					currentPosition = parsePosition(game, end);

					// Check if piece is legal:
					while (game.legalPiece(currentPosition.getPiece(), p) != true) {
						end = sc.next();
						while (!validInput(end)) {
							end = sc.next();
						}
						currentPosition = parsePosition(game, end);
					}

					// Check if there are any possible moves:
					while (game.legalMoves(currentPosition, p).isEmpty()) {
						System.out.println(game.loadBoard());
						System.out.println("No valid moves with this piece! Please pick a different one:");
						end = sc.next();
						while (!validInput(end)) {
							end = sc.next();
						}
						currentPosition = parsePosition(game, end);

						while (game.legalPiece(currentPosition.getPiece(), p) != true) {
							end = sc.next();
							while (!validInput(end)) {
								end = sc.next();
							}
							currentPosition = parsePosition(game, end);
						}
					}
					System.out.println(game.loadValidMoves(currentPosition, p));

					legal_moves = game.legalMoves(currentPosition, p);
					System.out.print("Choose your next move or go back: ");
					end = sc.next();
					while (!validInput(end)) {
						end = sc.next();
					}
					System.out.println();
					if (!end.equals("b")) {
						newPosition = parsePosition(game, end);
						while (!legal_moves.contains(newPosition)) {
							System.out.println("Please choose a valid move!: ");
							end = sc.next();
							while (!validInput(end)) {
								end = sc.next();
							}
							newPosition = parsePosition(game, end);
						}
					}
				}

				Move move = new Move(currentPosition, newPosition);
				game.movePiece(move, p);
				System.out.println(game.loadBoard());

				if (winner != null) {
					break;
				}

			}
			// System.out.println(winner.getName() + " is the winner!");
		}
		sc.close();
	}

	// Auxiliary methods

	public static boolean validInput(String input) {
		boolean valid = true;
		if (input.length() != 2) {
			valid = false;
		}
		if (valid == false) {
			System.out.println("Invalid input!");
		}
		return valid;

	}

	public static Position parsePosition(Game game, String input) {
		Character file = (char) input.charAt(0);
		Character rank = (char) input.charAt(1);
		int fileIndex = game.files.indexOf(file);
		int rankIndex = game.ranks.indexOf(rank);
		Position position = game.getBoard().getGrid().get(rankIndex).get(fileIndex);
		return position;
	}

	public void checkLegal() {
	}

}