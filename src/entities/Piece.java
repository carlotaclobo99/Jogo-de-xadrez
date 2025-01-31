package entities;

public class Piece {
	
	private String value;
	private Player player;

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
	
	

}
