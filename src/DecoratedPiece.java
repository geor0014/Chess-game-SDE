public class DecoratedPiece implements IChessPiece {
    private Piece piece;
    protected boolean isFirstMove;

    public DecoratedPiece(Piece piece) {
        this.piece = piece;
        this.isFirstMove = piece.getIsFirstMove();
    }

    @Override
    public String getID() {
        return piece.getID();
    }

    @Override
    public boolean matchID(String ID) {
        return piece.matchID(ID);
    }

    @Override
    public Color getColor() {
        return piece.getColor();
    }

    @Override
    public boolean sameColor(Piece otherPiece) {
        return piece.sameColor(otherPiece);
    }

    @Override
    public int getX() {
        return piece.getX();
    }

    @Override
    public int getY() {
        return piece.getY();
    }

    @Override
    public void setX(int newX) {
        piece.setX(newX);
    }

    @Override
    public void setY(int newY) {
        piece.setY(newY);
    }

    @Override
    public boolean possibleMove(int x, int y) {
        return piece.possibleMove(x, y);
    }

    @Override
    public int move(int x, int y, Piece other) {
        return piece.move(x, y, other);

    }

    @Override
    public boolean testMove(int x, int y) {
        return piece.testMove(x, y);
    }

    @Override
    public boolean canMove() {
        return piece.canMove();
    }

}
