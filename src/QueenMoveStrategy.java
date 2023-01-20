public class QueenMoveStrategy implements MoveStrategy {
    Board board = Board.getInstance();

    @Override
    public boolean possibleMove(int x, int y, Piece piece) {
        // cannot capture own piece
        if (piece.sameColor(board.getPiece(x, y)) == true) {
            return false;
        }
        // obstruction
        if (board.isPathClear(piece.getX(), piece.getY(), x, y) != true) {
            return false;
        }
        // bishop
        if (Math.abs(piece.getX() - x) == Math.abs(piece.getY() - y)) { // bishop
            return true;
        }
        // rook
        if (Math.abs(piece.getX() - x) != 0 && Math.abs(piece.getY() - y) == 0
                || Math.abs(piece.getX() - x) == 0 && Math.abs(piece.getY() - y) != 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean canMove(Piece piece) {

        int x = piece.getX();
        int y = piece.getY();

        // bishop
        // top left
        while ((--x) >= 0 && (--y) >= 0) {
            if (piece.testMove(x, y)) {
                return true;
            }
        }

        x = piece.getX();
        y = piece.getY();
        // top right
        while ((++x) <= 7 && (--y) >= 0) {
            if (piece.testMove(x, y)) {
                return true;
            }
        }

        x = piece.getX();
        y = piece.getY();
        // bottom left
        while ((--x) >= 0 && (++y) <= 7) {
            if (piece.testMove(x, y)) {
                return true;
            }
        }

        x = piece.getX();
        y = piece.getY();
        // bottom right
        while ((++x) <= 7 && (++y) <= 7) {
            if (piece.testMove(x, y)) {
                return true;
            }
        }

        x = piece.getX();
        y = piece.getY();
        // rook
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
