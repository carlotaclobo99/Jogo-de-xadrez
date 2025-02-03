package entities;

import java.util.ArrayList;
import java.util.List;

public class Board {

	List<Character> files = new ArrayList<Character>(List.of('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'));
	List<Character> ranks = new ArrayList<Character>(List.of('1', '2', '3', '4', '5', '6', '7', '8'));
	List<String> setup = new ArrayList<String>(List.of("R", "B", "N", "Q", "K", "N", "B", "R"));
	
	private Position positions[][] = new Position[8][8];

	public Board() {
	}

	public List<Character> getFiles() {
		return files;
	}

	public List<Character> getRanks() {
		return ranks;
	}

	public List<String> getSetup() {
		return setup;
	}

	public Position[][] getPositions() {
		return positions;
	}


}

