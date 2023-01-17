
public class Bishop extends Piece {

	public Bishop(Color color, String ID, int startX, int startY) {
		super(color, ID, startX, startY);
		this.setMoveStrategy(new BishopMoveStrategy());
	}

	@Override
	public String toString() {
		if (this.getColor() == Color.WHITE) {
			return "♗";
		}
		return "♝";
	}
}
