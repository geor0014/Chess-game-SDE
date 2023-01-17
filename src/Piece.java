
public abstract class Piece {

	private final Color color;

	// set the move strategy for each piece
	private MoveStrategy moveStrategy;

	private final String ID;

	private int x, y;

	public boolean isFirstMove;

	public Piece(Color color, String ID, int startX, int startY) {
		this.color = color;
		this.ID = ID;
		this.x = startX;
		this.y = startY;

		if (this.getColor() == Color.WHITE) {
			Board.getInstance().white.add(this);
		} else if (this.getColor() == Color.BLACK) {
			Board.getInstance().black.add(this);
		}
		Board.getInstance().setPiece(x, y, this);
	}

	public String getID() {
		return this.ID;
	}

	public boolean matchID(String ID) {
		return this.ID.equals(ID);
	}

	public Color getColor() {
		return this.color;
	}

	public boolean sameColor(Piece otherPiece) {
		if (otherPiece == null) {
			return false;
		}
		return (this.color == otherPiece.getColor());
	}

	public int getX() {
		return this.x;
	}

	void setX(int newX) {
		this.x = newX;
	}

	public int getY() {
		return this.y;
	}

	void setY(int newY) {
		this.y = newY;
	}

	public void setMoveStrategy(MoveStrategy moveStrategy) {
		this.moveStrategy = moveStrategy;
	}

	public boolean possibleMove(int x, int y) {
		return this.moveStrategy.possibleMove(x, y, this);
	};

	public int move(int x, int y, Piece other) {
		if (this.possibleMove(x, y) != true) {
			return -1;
		}

		Color color = this.getColor();
		int originX = this.getX();
		int originY = this.getY();

		if (this.getColor() == Color.WHITE) {
			Board.getInstance().black.remove(other);
		} else {
			Board.getInstance().white.remove(other);
		}

		Board.getInstance().setPiece(originX, originY, null);
		Board.getInstance().setPiece(x, y, this);

		boolean isFirstMoveOG = this.isFirstMove;
		this.isFirstMove = false;

		if (Board.getInstance().checkForCheck(color) == true) {
			if (other != null) {
				if (this.getColor() == Color.WHITE) {
					Board.getInstance().black.add(other);
				} else {
					Board.getInstance().white.add(other);
				}
			}
			Board.getInstance().setPiece(originX, originY, this);
			Board.getInstance().setPiece(x, y, other);
			this.isFirstMove = isFirstMoveOG;

			return -1;
		}

		if (this instanceof Pawn) {
			char file = this.getID().charAt(4);
			if (this.getColor() == Color.WHITE && y == 0) {
				Board.getInstance().setPiece(x, y, null);
				Board.getInstance().white.remove(this);
				Queen queen = new Queen(Color.WHITE, "queen" + file, x, y);
				System.out.println("Pawn promoted!");
			} else if (this.getColor() == Color.BLACK && y == 7) {
				Board.getInstance().setPiece(x, y, null);
				Board.getInstance().black.remove(this);
				Queen queen = new Queen(Color.BLACK, "queen" + file, x, y);
				System.out.println("Pawn promoted!");
			}
		}

		return 0;
	}

	public boolean testMove(int x, int y) {
		int originX = this.getX();
		int originY = this.getY();
		Piece other;
		boolean isFirst = this.isFirstMove;

		if (x >= 0 && y >= 0 && x <= 7 && y <= 7) {
			other = Board.getInstance().getPiece(x, y);
			if (this.move(x, y, other) == 0) {
				// captured piece set to original position
				Board.getInstance().setPiece(x, y, other);
				// selected piece set to original position
				Board.getInstance().setPiece(originX, originY, this);
				isFirstMove = isFirst;
				if (other != null) {
					if (other.getColor() == Color.WHITE) {
						Board.getInstance().white.add(other);
					} else
						Board.getInstance().black.add(other);
				}
				return true;
			}
		}
		return false;
	}

	public String nullToString() {
		return "   ";
	}

	public abstract String toString();

	public boolean canMove() {
		return this.moveStrategy.canMove(this);
	};
}
