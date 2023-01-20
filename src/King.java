
public class King extends Piece {

	public King(Color color, String ID, int startX, int startY) {
		super(color, ID, startX, startY);
		this.setMoveStrategy(new KingMoveStrategy());
	}

	@Override
	public String toString() {
		if (this.getColor() == Color.WHITE) {
			return "♔";
		}
		return "♚";
	}

}
