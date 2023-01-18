public class KingMoveStrategy implements MoveStrategy {
    Board board = Board.getInstance();

    @Override
    public boolean possibleMove(int x, int y, Piece piece) {
        // cannot capture own piece
        if (piece.sameColor(board.getPiece(x, y)) == true) {
            return false;
        }
        // bishop
        else if (Math.abs(piece.getX() - x) == 1 && Math.abs(piece.getY() - y) == 1) {
            return true;
        }
        // rook
        else if (Math.abs(piece.getX() - x) == 1 && Math.abs(piece.getY() - y) == 0
                || Math.abs(piece.getX() - x) == 0 && Math.abs(piece.getY() - y) == 1) {
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
        if (piece.testMove(x - 1, y - 1)) {
            return true;
        }
        // top right
        if (piece.testMove(x + 1, y - 1)) {
            return true;
        }
        // bottom left
        if (piece.testMove(x - 1, y + 1)) {
            return true;
        }
        // bottom right
        if (piece.testMove(x + 1, y + 1)) {
            return true;
        }

        // rook
        // left
        if (piece.testMove(x - 1, y)) {
            return true;
        }
        // right
        if (piece.testMove(x + 1, y)) {
            return true;
        }
        // down
        if (piece.testMove(x, y + 1)) {
            return true;
        }
        // up
        if (piece.testMove(x, y - 1)) {
            return true;
        }
        return false;
    }

}
