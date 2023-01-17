
public class Pawn extends Piece {

	public Pawn(Color color, String ID, int startX, int startY) {
		super(color, ID, startX, startY);
		this.setMoveStrategy(new PawnMoveStrategy());
	}

	boolean isFirstMove = true;

	@Override
	public String toString() {
		if (this.getColor() == Color.WHITE) {
			return "♙";
		}
		return "♟";
	}
}
