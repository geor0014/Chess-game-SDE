// this class is used to check if a knight can move to a certain position on the board and if it can move at all
public class KnightMoveStrategy implements MoveStrategy {

    @Override
    public boolean possibleMove(int x, int y, Piece piece) {
        // cannot capture own piece
        if (piece.sameColor(Board.getPiece(x, y)) == true) {
            return false;
        }

        if (Math.abs(piece.getY() - y) == 2 && Math.abs(piece.getX() - x) == 1
                || Math.abs(piece.getY() - y) == 1 && Math.abs(piece.getX() - x) == 2) {
            return true;
        }

        return false;
    }

    @Override
    public boolean canMove(Piece piece) {
        int x = piece.getX();
        int y = piece.getY();

        // left & up
        if (piece.testMove(x - 2, y - 1)) {
            return true;
        }
        if (piece.testMove(x - 1, y - 2)) {
            return true;
        }

        // right & up
        if (piece.testMove(x + 2, y - 1)) {
            return true;
        }
        if (piece.testMove(x + 1, y - 2)) {
            return true;
        }

        // left & down
        if (piece.testMove(x - 2, y + 1)) {
            return true;
        }
        if (piece.testMove(x - 1, y + 2)) {
            return true;
        }

        // right & down
        if (piece.testMove(x + 2, y + 1)) {
            return true;
        }
        if (piece.testMove(x + 1, y + 2)) {
            return true;
        }

        return false;
    }

}
