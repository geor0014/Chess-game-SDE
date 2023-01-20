
public class Knight extends Piece {

	public Knight(Color color, String ID, int startX, int startY) {
		super(color, ID, startX, startY);
		this.setMoveStrategy(new KnightMoveStrategy());
	}

	@Override
	public String toString() {
		if (this.getColor() == Color.WHITE) {
			return "♘";
		}
		return "♞";
	}
}
