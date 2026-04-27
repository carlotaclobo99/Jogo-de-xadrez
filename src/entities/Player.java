package entities;

import java.util.ArrayList;
import java.util.List;

import enums.Color;

public class Player {
	
	private String name;
	private Color color;
	private List<Move> moves = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();
	private String orientation;
	
	public Player() {
		
	}

	public Player(String name, Color color, String ori) {
		super();
		this.name = name;
		this.color = color;
		this.orientation = ori;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public List<Move> getMoves() {
		return moves;
	}

	public void setMoves(List<Move> moves) {
		this.moves = moves;
	}
	

	public String getOrientation() {
		return orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

	public List<Piece> getCapturedPieces() {
		return capturedPieces;
	}

	public void setCapturedPieces(List<Piece> capturedPieces) {
		this.capturedPieces = capturedPieces;
	}
	
}
