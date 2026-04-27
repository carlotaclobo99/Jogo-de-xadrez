package enums;

public enum PieceType {

	EMPTY(0, " "),
	PAWN(1, "P"),
	KNIGHT(3, "N"),
	BISHOP(4, "B"),
	ROOK(6, "R"),
	QUEEN(8, "Q"),
	KING(100, "K");
	
	private final Integer value;
	private final String label;
	
	private PieceType(Integer value, String label) {
		this.value=value;
		this.label=label;
	}

	public String getLabel() {
		return label;
	}

	public Integer getValue() {
		return value;
	}

	
}
