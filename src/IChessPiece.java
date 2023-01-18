public interface IChessPiece {
    public String getID();

    public boolean matchID(String ID);

    public Color getColor();

    public boolean sameColor(Piece otherPiece);

    public int getX();

    public int getY();

    public void setX(int newX);

    public void setY(int newY);

    public boolean possibleMove(int x, int y);

    public int move(int x, int y, Piece other);

    public boolean testMove(int x, int y);

    public boolean canMove();
}
