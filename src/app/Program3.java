package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Board;
import entities.Piece;
import entities.Player;
import entities.Position;

public class Program3 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		List<Character> files = new ArrayList<Character>(List.of('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'));
		List<Character> ranks = new ArrayList<Character>(List.of('1', '2', '3', '4', '5', '6', '7', '8'));
		List<String> setup = new ArrayList<String>(List.of("R", "B", "N", "Q", "K", "N", "B", "R"));

		Position aux[][] = new Position[8][8];
		Player p1 = new Player("Maria", "white");
		Player p2 = new Player("Sara", "black");
		Player pnone = new Player("AnA", "azul");

		for (int rank = 0; rank < 8; rank++) {
			for (int file = 0; file < 8; file++) {
				
				Piece piece = new Piece("-", pnone);
				Position pos = new Position(files.get(file), ranks.get(rank), piece);
				
				if (rank == 0 || rank == 7) {
					piece.setValue(setup.get(file));
					pos = new Position(files.get(file), ranks.get(rank), piece);

					if (rank == 0) {
						piece.setPlayer(p1);
					}
					if (rank == 7) {
						piece.setPlayer(p2);
					}

				}
				if (rank == 1 || rank == 6) {
					piece.setValue("p");
					pos = new Position(files.get(file), ranks.get(rank), piece);
					if (rank == 1) {
						piece.setPlayer(p1);
					}

					if (rank == 2) {
						piece.setPlayer(pnone);
					}
				
				if (pos.getPiece().getPlayer() == p1) {
					piece.setPlayer(p1);
					piece.setValue(piece.getValue().toUpperCase());

				} 
				if (pos.getPiece().getPlayer() == p2) {
					piece.setPlayer(pnone);
					piece.setValue(piece.getValue().toLowerCase());

				}

				aux[rank][file] = pos;

			}
		}

		System.out.println("Creating new board:" + "\n");
		Board board = new Board(aux, p1, p2);

		System.out.println(board.loadBoard());
		}

		sc.close();
	}

}
