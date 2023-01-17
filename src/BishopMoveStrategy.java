
public class BishopMoveStrategy implements MoveStrategy {
    public boolean possibleMove(int x, int y, Piece piece) {
        // cannot capture own piece
        if (piece.sameColor(Board.getPiece(x, y)) == true) {
            return false;
        }
        // invalid move for bishop
        if (Math.abs(piece.getX() - x) != Math.abs(piece.getY() - y)) {
            return false;
        }

        if (Board.isPathClear(piece.getX(), piece.getY(), x, y)) {
            return true;
        }
        return false;
    }

    public boolean canMove(Piece piece) {
        int originX = piece.getX();
        int originY = piece.getY();

        // reset x and y to original position after each while loop
        int x = originX;
        int y = originY;

        // top left
        while ((--x) >= 0 && (--y) >= 0) {
            if (piece.testMove(x, y)) {
                return true;
            }
        }
        x = originX;
        y = originY;
        // top right
        while ((++x) <= 7 && (--y) >= 0) {
            if (piece.testMove(x, y)) {
                return true;
            }
        }
        x = originX;
        y = originY;
        // bottom left
        while ((--x) >= 0 && (++y) <= 7) {
            if (piece.testMove(x, y)) {
                return true;
            }
        }
        x = originX;
        y = originY;
        // bottom right
        while ((++x) <= 7 && (++y) <= 7) {
            if (piece.testMove(x, y)) {
                return true;
            }
        }
        return false;
    }
}
