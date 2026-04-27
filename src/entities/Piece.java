package entities;

import enums.PieceType;

public class Piece {
	
	private PieceType pieceType;
	private Player player;

	private Integer number_moves = 0;
	public Piece() {
		
	}

	public Piece(PieceType pieceType, Player player) {
		super();
		this.pieceType = pieceType;
		this.player = player;
	}

	public PieceType getPieceType() {
		return this.pieceType;
	}

	public void setPieceType(PieceType pieceType) {
		this.pieceType = pieceType;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Integer getNumber_moves() {
		return number_moves;
	}

	public void setNumber_moves(Integer number_moves) {
		this.number_moves = number_moves;
	}
	
}
