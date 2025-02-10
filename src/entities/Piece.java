package entities;

public class Piece {
	
	private String value;
	private Player player;

	private Integer number_moves= 0;
	public Piece() {
		
	}

	public Piece(String value, Player player) {
		super();
		this.value = value;
		this.player = player;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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
