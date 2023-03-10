public class PieceWithCastling extends DecoratedPiece {

    Board board = Board.getInstance();

    public PieceWithCastling(Piece piece) {
        super(piece);
    }

    public int castle(String side) {
        Rook rook = (Rook) board.getPiece("rook" + side, this.getColor());
        int originX = this.getX();
        int originY = this.getY();

        if (this.isFirstMove != true || rook.isFirstMove != true) {
            System.out.println("Cannot castle if king or rook has already moved");
            return -1;
        }
        if (board.isPathClear(this.getX(), this.getY(), rook.getX(), rook.getY()) != true) {
            System.out.println("Cannot castle across a line of check");
            return -1;
        }

        if (this.getColor() == Color.WHITE) {

            if (side.equals("K")) {
                // cant castle accross a line of check
                if (this.move(5, 7, null) == 0 && this.move(6, 7, null) == 0) {
                    board.setPiece(rook.getX(), rook.getY(), null);
                    board.setPiece(5, 7, rook);
                    return 0;
                } else {
                    board.setPiece(this.getX(), this.getY(), null);
                    board.setPiece(originX, originY, this);
                    return -1;
                }
            }

            else if (side.equals("Q")) {
                if (this.move(3, 7, null) == 0 && this.move(2, 7, null) == 0) {
                    board.setPiece(rook.getX(), rook.getY(), null);
                    board.setPiece(3, 7, rook);
                    return 0;
                } else {
                    board.setPiece(this.getX(), this.getY(), null);
                    board.setPiece(originX, originY, this);
                    return -1;
                }
            }
        }

        if (this.getColor() == Color.BLACK) {
            if (side.equals("K")) {
                if (this.move(5, 0, null) == 0 && this.move(6, 0, null) == 0) {
                    board.setPiece(rook.getX(), rook.getY(), null);
                    board.setPiece(5, 0, rook);
                    return 0;
                } else {
                    board.setPiece(this.getX(), this.getY(), null);
                    board.setPiece(originX, originY, this);
                    return -1;
                }
            }

            else if (side.equals("Q")) {
                if (this.move(3, 0, null) == 0 && this.move(2, 0, null) == 0) {
                    board.setPiece(rook.getX(), rook.getY(), null);
                    board.setPiece(3, 0, rook);
                    return 0;
                } else {
                    board.setPiece(this.getX(), this.getY(), null);
                    board.setPiece(originX, originY, this);
                    return -1;
                }
            }
        }
        return -1;
    }
}
