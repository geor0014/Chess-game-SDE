public class RookMoveStrategy implements MoveStrategy {

    @Override
    public boolean possibleMove(int x, int y, Piece piece) {
        // cannot capture own piece
        if (piece.sameColor(Board.getPiece(x, y)) == true) {
            return false;
        }
        // invalid move for rook
        if (Math.abs(piece.getX() - x) != 0 && Math.abs(piece.getY() - y) != 0) {
            return false;
        }

        if (Board.isPathClear(piece.getX(), piece.getY(), x, y)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean canMove(Piece piece) {
        int x = piece.getX();
        int y = piece.getY();

        // left
        while ((--x) >= 0 && y >= 0) {
            if (piece.testMove(x, y)) {
                return true;
            }
        }
        x = piece.getX();
        y = piece.getY();
        // right
        while ((++x) <= 7 && y >= 0) {
            if (piece.testMove(x, y)) {
                return true;
            }
        }
        x = piece.getX();
        y = piece.getY();
        // down
        while (x >= 0 && (++y) <= 7) {
            if (piece.testMove(x, y)) {
                return true;
            }
        }
        x = piece.getX();
        y = piece.getY();
        // up
        while (x <= 7 && (--y) >= 0) {
            if (piece.testMove(x, y)) {
                return true;
            }
        }

        return false;
    }

}
