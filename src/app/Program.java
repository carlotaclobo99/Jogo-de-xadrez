package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Board;
import entities.Position;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		List<Character> files = new ArrayList<Character>(List.of('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'));
		List<Character> ranks = new ArrayList<Character>(List.of('1', '2', '3', '4', '5', '6', '7', '8'));
		//List<String> pieces = new ArrayList<String>(List.of("p", "K", "N","Q", "R", "B"));
		List<String> setup = new ArrayList<String>(List.of("R", "B", "N", "Q", "K","N","B","R"));
		Board board = new Board();
		Position aux[][] = new Position[8][8];
		for (int j = 0; j<8; j++) {
			for (int i = 0; i<8; i++) {
				Position pos = null;
				if (i == 0 || i == 7) {
					pos = new Position(files.get(j), ranks.get(i), setup.get(j));
				}
				else {
					if (i == 1 || i == 6) {
						 pos = new Position(files.get(j), ranks.get(i), "p");
					}
					else { 
						pos = new Position(files.get(j), ranks.get(i), "-");
					}
				}
				aux[j][i] = pos;
			}
		}
		
		board.setPositions(aux);
		System.out.println(board.loadBoard());
		System.out.println();
		String check = "";
		
		for (int i = 0; i<3; i++) {
			for (int j = 0; j<2; j++) {
				if (j == 0) {
					System.out.println("White to move:");
				}
				else {
					System.out.println("Black to move:");
				}
				String move = sc.next();
				Character file = (char) move.charAt(0);
				Character rank = (char) move.charAt(1);
				Position current = board.getPositions()[files.indexOf(file)][ranks.indexOf(rank)];
				System.out.println("Moving to: ");
				String move1 = sc.next();
				Character file1 = move1.charAt(0);
				Character rank1 = move1.charAt(1);
		
				Position new_pos = board.getPositions()[files.indexOf(file1)][ranks.indexOf(rank1)];
				if (new_pos.getValue()=="K") {
					System.out.println("Check-mate!");
					check = "Yes";
					break;
				}
				new_pos.setValue(current.getValue());
				current.setValue("-");
				
				System.out.println(board.loadBoard());

			}		
			if (check == "Yes") {
				break;
			}
		}
		
		sc.close();
	}

}
