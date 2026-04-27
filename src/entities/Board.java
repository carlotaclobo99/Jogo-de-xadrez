package entities;

import java.util.ArrayList;
import java.util.List;

import enums.PieceType;

public class Board {

	
	private List<List<Position>> grid = new ArrayList<List<Position>>();
	List<PieceType> setup = new ArrayList<PieceType>(List.of(PieceType.ROOK, PieceType.KNIGHT, PieceType.BISHOP, 
			PieceType.QUEEN, PieceType.KING, PieceType.BISHOP, PieceType.KNIGHT, PieceType.ROOK));
	
	public Board() {
		
		for (int rank = 8; rank >= 1; rank--) {
		    List<Position> row = new ArrayList<>();
		    for (char file = 'a'; file <= 'h'; file++) {
		    	Position pos = new Position(Character.toString(file), rank, new Piece());
		    	row.add(pos);
		    }
		    grid.add(row);
		}
		
	}

	public List<List<Position>> getGrid() {
		return grid;
	}

	public void setGrid(List<List<Position>> grid) {
		this.grid = grid;
	}

	public void setSetup(List<PieceType> setup) {
		this.setup = setup;
	}

	public List<PieceType> getSetup() {
		return setup;
	}
	
	public void printGrid() {
		for (List<Position> row : this.grid) {
			for(Position pos : row) {
				System.out.print(pos.printPos());
			}
			System.out.println();
		}
	}
	
	public void printRow(int i) {
		for (Position pos : this.grid.get(i)) System.out.print(pos.printPos());
	}	
}


