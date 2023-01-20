
public class Rook extends Piece {

	public Rook(Color color, String ID, int startX, int startY) {
		super(color, ID, startX, startY);

		this.setMoveStrategy(new RookMoveStrategy());
	}

	boolean isFirstMove = true;

	@Override
	public String toString() {
		if (this.getColor() == Color.WHITE) {
			return "♖";
		}
		return "♜";
	}
}
