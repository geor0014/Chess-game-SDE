import java.util.ArrayList;

public class Board {
	private static Board instance = null;

	private ArrayList<Piece> black = new ArrayList<Piece>();
	private ArrayList<Piece> white = new ArrayList<Piece>();

	private Piece board[][] = new Piece[8][8];

	private Piece blackRookQ;
	private Piece blackKnightQ;
	private Piece blackBishopQ;
	private Piece blackQueen;
	private Piece blackKing;
	//
	private PieceWithCastling blackCastlingKing;
	//
	private Piece blackBishopK;
	private Piece blackknightK;
	private Piece blackRookK;

	private Piece blackPawnA;
	private Piece blackPawnB;
	private Piece blackPawnC;
	private Piece blackPawnD;
	private Piece blackPawnE;
	private Piece blackPawnF;
	private Piece blackPawnG;
	private Piece blackPawnH;

	// white
	private Piece whiteRookQ;
	private Piece whiteKnightQ;
	private Piece whiteBishopQ;
	private Piece whiteQueen;
	private Piece whiteKing;
	//
	private PieceWithCastling whiteCastlingKing;
	//
	private Piece whiteBishopK;
	private Piece whiteKnightK;
	private Piece whiteRookK;

	private Piece whitePawnA;
	private Piece whitePawnB;
	private Piece whitePawnC;
	private Piece whitePawnD;
	private Piece whitePawnE;
	private Piece whitePawnF;
	private Piece whitePawnG;
	private Piece whitePawnH;

	private Board() {
	}

	public static Board getInstance() {
		if (instance == null) {
			instance = new Board();
		}
		return instance;
	}

	public void printBoard() {
		System.out.println("    a   b   c   d   e   f   g   h");

		System.out.println("  ---------------------------------");
		int count = 8;
		for (int i = 0; i < 8; i++) {
			System.out.print(count + " ");
			System.out.print("| ");
			for (int j = 0; j < 8; j++) {
				if (board[i][j] == null) {
					System.out.print("  | ");
				} else {
					System.out.print(board[i][j] + " | ");
				}
			}
			System.out.print(count);
			count--;
			System.out.println();
			System.out.println("  ---------------------------------");
		}
		System.out.println("    a   b   c   d   e   f   g   h");
		System.out.println();
	}

	public void startGame() {
		System.out.println("How to play:");
		System.out.println("For pawns, type in \"pawn\" followed by the file letter. For example, \"pawnA\"");
		System.out.println("For bishops, knights and rooks, put \"Q\" or \"K\" to specify Queen's or King's side");
		System.out.println("provide a space and then enter a valid tile. For example, \"bishopK c4\"");
		System.out.println(
				"Pawns auto-promote to queens. The new queens are referenced by what the pawns file was. \"queenH\"");
		System.out.println(
				"To castle, type castle, followed by a space and then a capital K or Q to specify a side. \"castle Q\"\n");
		// black
		this.blackRookQ = new Rook(Color.BLACK, "rookQ", 0, 0);
		this.blackKnightQ = new Knight(Color.BLACK, "knightQ", 1, 0);
		this.blackBishopQ = new Bishop(Color.BLACK, "bishopQ", 2, 0);
		this.blackQueen = new Queen(Color.BLACK, "queen", 3, 0);
		this.blackKing = new King(Color.BLACK, "king", 4, 0);
		//
		this.blackCastlingKing = new PieceWithCastling(blackKing);
		//
		this.blackBishopK = new Bishop(Color.BLACK, "bishopK", 5, 0);
		this.blackknightK = new Knight(Color.BLACK, "knightK", 6, 0);
		this.blackRookK = new Rook(Color.BLACK, "rookK", 7, 0);

		this.blackPawnA = new Pawn(Color.BLACK, "pawnA", 0, 1);
		this.blackPawnB = new Pawn(Color.BLACK, "pawnB", 1, 1);
		this.blackPawnC = new Pawn(Color.BLACK, "pawnC", 2, 1);
		this.blackPawnD = new Pawn(Color.BLACK, "pawnD", 3, 1);
		this.blackPawnE = new Pawn(Color.BLACK, "pawnE", 4, 1);
		this.blackPawnF = new Pawn(Color.BLACK, "pawnF", 5, 1);
		this.blackPawnG = new Pawn(Color.BLACK, "pawnG", 6, 1);
		this.blackPawnH = new Pawn(Color.BLACK, "pawnH", 7, 1);

		// white
		this.whiteRookQ = new Rook(Color.WHITE, "rookQ", 0, 7);
		this.whiteKnightQ = new Knight(Color.WHITE, "knightQ", 1, 7);
		this.whiteBishopQ = new Bishop(Color.WHITE, "bishopQ", 2, 7);
		this.whiteQueen = new Queen(Color.WHITE, "queen", 3, 7);
		this.whiteKing = new King(Color.WHITE, "king", 4, 7);
		//
		this.whiteCastlingKing = new PieceWithCastling(whiteKing);
		//
		this.whiteBishopK = new Bishop(Color.WHITE, "bishopK", 5, 7);
		this.whiteKnightK = new Knight(Color.WHITE, "knightK", 6, 7);
		this.whiteRookK = new Rook(Color.WHITE, "rookK", 7, 7);

		this.whitePawnA = new Pawn(Color.WHITE, "pawnA", 0, 6);
		this.whitePawnB = new Pawn(Color.WHITE, "pawnB", 1, 6);
		this.whitePawnC = new Pawn(Color.WHITE, "pawnC", 2, 6);
		this.whitePawnD = new Pawn(Color.WHITE, "pawnD", 3, 6);
		this.whitePawnE = new Pawn(Color.WHITE, "pawnE", 4, 6);
		this.whitePawnF = new Pawn(Color.WHITE, "pawnF", 5, 6);
		this.whitePawnG = new Pawn(Color.WHITE, "pawnG", 6, 6);
		this.whitePawnH = new Pawn(Color.WHITE, "pawnH", 7, 6);
	}

	// set piece to provided coordinates
	public void setPiece(int x, int y, IChessPiece piece) {
		if (piece != null) {
			piece.setX(x);
			piece.setY(y);
		}
		board[y][x] = (Piece) piece;
	}

	// check spot on board
	public Piece getPiece(int x, int y) {
		return board[y][x];
	}

	// match String piece from user with Piece on board
	public Piece getPiece(String piece, Color color) {

		if (color == Color.WHITE) {

			for (int i = 0; i < white.size(); i++) {
				Piece p = white.get(i);
				if (p.matchID(piece)) {
					return p;
				}
			}
		}

		else if (color == Color.BLACK) {

			for (int i = 0; i < black.size(); i++) {
				Piece p = black.get(i);
				if (p.matchID(piece)) {
					return p;
				}
			}
		}

		return null;

	}

	public boolean isPathClear(int x1, int y1, int x2, int y2) {

		int xDistance = x2 - x1;
		int yDistance = y2 - y1;
		int xDir = 0;
		int yDir = 0;
		int size = 0;

		if (xDistance < 0) {
			xDir = -1;
		} else if (xDistance > 0) {
			xDir = 1;
		}

		if (yDistance < 0) {
			yDir = -1;
		} else if (yDistance > 0) {
			yDir = 1;
		}

		if (xDistance != 0) {
			size = Math.abs(xDistance) - 1;
		} else {
			size = Math.abs(yDistance) - 1;
		}
		// change on x and y

		for (int i = 0; i < size; i++) {
			x1 += xDir;
			y1 += yDir;

			if (getPiece(x1, y1) != null) {
				return false;
			}
		}
		return true;

	}

	public int processMove(String move, Color color) {

		String[] splitStr = move.split(" ");
		String piece = splitStr[0];

		if (piece.equals("castle")) {
			if (color == Color.BLACK) {
				this.blackCastlingKing.castle(splitStr[1]);
			} else {
				this.whiteCastlingKing.castle(splitStr[1]);
			}
		}

		// piece selected to move
		Piece p = getPiece(piece, color);
		if (p == null) {
			System.out.println("invalid piece, please type in piece to move it.");
			return -1;
		}

		String coordinates = splitStr[1];
		if (coordinates.length() != 2) {
			System.out.println("Invalid Tile please try again");
			return -1;
		}

		int file = coordinates.charAt(0) - 'a'; // y
		int rank = 7 - (coordinates.charAt(1) - '1'); // x

		if (rank < 0 || rank > 7 || file < 0 || file > 7) {
			System.out.println("Invalid Tile please try again");
			return -1;
		}

		// piece at destination
		Piece other = getPiece(file, rank);

		return p.move(file, rank, other);

	}

	public boolean checkForCheck(Color color) {

		Piece king = getPiece("king", color);

		if (color == Color.WHITE) {
			for (int i = 0; i < black.size(); i++) {
				Piece p = black.get(i);
				if (p.possibleMove(king.getX(), king.getY())) {
					return true;
				}
			}
		}

		else if (color == Color.BLACK) {
			for (int i = 0; i < white.size(); i++) {
				Piece p = white.get(i);
				if (p.possibleMove(king.getX(), king.getY())) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean mate(Color color) {

		if (color == Color.WHITE) {
			for (int i = 0; i < white.size(); i++) {
				Piece p = white.get(i);
				if (p.canMove()) {
					return false;
				}
			}
		} else if (color == Color.BLACK) {
			for (int i = 0; i < black.size(); i++) {
				Piece p = black.get(i);
				if (p.canMove()) {
					return false;
				}
			}
		}

		return true;
	}

	public boolean staleMate(Color color) {

		// insufficient material stalemate
		Piece knightK = getPiece("knightK", color);
		Piece knightQ = getPiece("knightQ", color);
		Piece bishopK = getPiece("bishopK", color);
		Piece bishopQ = getPiece("bishopQ", color);

		if (white.size() == 2 && black.size() == 2) {
			if (white.contains(bishopK) || white.contains(bishopQ) || white.contains(knightK)
					|| white.contains(knightQ)) {
				return true;
			}
			if (black.contains(bishopK) || black.contains(bishopQ) || black.contains(knightK)
					|| white.contains(knightQ)) {
				return true;
			}

		}
		if (white.size() == 1 && white.get(0) instanceof King && black.size() == 1 && black.get(0) instanceof King) {
			return true;
		}

		// no legal moves stalemate
		if (mate(color) == true && checkForCheck(color) == false) {
			return true;
		}

		return false;

	}

	public ArrayList<Piece> getWhite() {
		return white;
	}

	public ArrayList<Piece> getBlack() {
		return black;
	}

}
