
public class Pawn extends Piece {
	public Pawn(Color color, String ID, int startX, int startY) {
		super(color, ID, startX, startY);
		this.isFirstMove = true;
		this.setMoveStrategy(new PawnMoveStrategy());
	}

	@Override
	public String toString() {
		if (this.getColor() == Color.WHITE) {
			return "♙";
		}
		return "♟";
	}
}
