public class PawnMoveStrategy implements MoveStrategy {
    Board board = Board.getInstance();

    @Override
    public boolean possibleMove(int x, int y, Piece piece) {
        if (piece.getColor() == Color.WHITE) {

            // 2 spaces forward
            if (piece.isFirstMove == true && piece.getY() - y == 2 && piece.getX() - x == 0
                    && board.isPathClear(piece.getX(), piece.getY(), x, y)
                    && board.getPiece(x, y) == null) {
                return true;
            }
            // 1 space forward
            if (piece.getY() - y == 1 && piece.getX() - x == 0 && board.getPiece(x, y) == null) {
                return true;
            }

            // diagonal
            if (piece.getY() - y == 1 && Math.abs(piece.getX() - x) == 1 && board.getPiece(x, y) != null
                    && piece.sameColor(board.getPiece(x, y)) == false) {
                return true;
            }
        }

        if (piece.getColor() == Color.BLACK) {
            // 2 spaces forward
            if (piece.isFirstMove == true && piece.getY() - y == -2 && piece.getX() - x == 0
                    && board.isPathClear(piece.getX(), piece.getY(), x, y)
                    && board.getPiece(x, y) == null) {
                return true;
            }
            // 1 space forward
            if (piece.getY() - y == -1 && piece.getX() - x == 0 && board.getPiece(x, y) == null) {
                return true;
            }

            // diagonal
            if (piece.getY() - y == -1 && Math.abs(piece.getX() - x) == 1 && board.getPiece(x, y) != null
                    && piece.sameColor(board.getPiece(x, y)) == false) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canMove(Piece piece) {
        int x = piece.getX();
        int y = piece.getY();

        if (piece.getColor() == Color.WHITE) {

            if (piece.testMove(x, y - 1)) {
                return true;
            }

            if (piece.testMove(x, y - 2)) {
                return true;
            }

            if (piece.testMove(x - 1, y - 1)) {
                return true;
            }

            if (piece.testMove(x + 1, y - 1)) {
                return true;
            }

        }
        if (piece.getColor() == Color.BLACK) {

            if (piece.testMove(x, y + 1)) {
                return true;
            }

            if (piece.testMove(x, y + 2)) {
                return true;
            }

            if (piece.testMove(x - 1, y - 1)) {
                return true;
            }

            if (piece.testMove(x + 1, y + 1)) {
                return true;
            }
        }

        return false;
    }

}
