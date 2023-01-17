
public class Queen extends Piece {

	public Queen(Color color, String ID, int startX, int startY) {
		super(color, ID, startX, startY);

		this.setMoveStrategy(new QueenMoveStrategy());
	}

	@Override
	public String toString() {
		if (this.getColor() == Color.WHITE) {
			return "♕";
		}
		return "♛";
	}
}
