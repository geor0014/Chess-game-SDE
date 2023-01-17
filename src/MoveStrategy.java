
// interface for the different move strategies of the pieces
public interface MoveStrategy {
    public boolean possibleMove(int x, int y, Piece piece);

    public boolean canMove(Piece piece);
}
