public class PieceFactory {

        public Piece createPawn(Color color, String ID, int startX, int startY) {
                return new Pawn(color, ID, startX, startY);
        }

        public Piece createRook(Color color, String ID, int startX, int startY) {
                return new Rook(color, ID, startX, startY);
        }

        public Piece createKnight(Color color, String ID, int startX, int startY) {
                return new Knight(color, ID, startX, startY);
        }

        public Piece createBishop(Color color, String ID, int startX, int startY) {
                return new Bishop(color, ID, startX, startY);
        }

        public Piece createQueen(Color color, String ID, int startX, int startY) {
                return new Queen(color, ID, startX, startY);
        }

        public Piece createKing(Color color, String ID, int startX, int startY) {
                return new King(color, ID, startX, startY);
        }
}