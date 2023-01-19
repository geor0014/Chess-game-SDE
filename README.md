# Daniel: Strategy Design Pattern

I implemented the "Strategy" design pattern in the ```Piece.java``` file to handle the moving of the pieces. I created ```MoveStrategy.java``` interface and a different strategy for each piece, for example ```KnightMoveStrategy.java```

## Implementation
### MoveStrategy.java 
  ```java
  
 // interface for the different move strategies of the pieces
public interface MoveStrategy {
    public boolean possibleMove(int x, int y, Piece piece);
    public boolean canMove(Piece piece);
}
  ```

### KnightMoveStrategy.java
```java
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
```

### Knight.java
```java

public class Knight extends Piece {

	public Knight(Color color, String ID, int startX, int startY) {
		super(color, ID, startX, startY);
		this.setMoveStrategy(new KnightMoveStrategy());
	}

	@Override
	public String toString() {
		if (this.getColor() == Color.WHITE) {
			return "♘";
		}
		return "♞";
	}
}
```
### Piece.java
```java
public abstract class Piece {

	private final Color color;
	
    // set the move strategy for each piece
	private MoveStrategy moveStrategy;

	private final String ID;

	private int x, y;

	public boolean isFirstMove;

	public Piece(Color color, String ID, int startX, int startY) {
		this.color = color;
		this.ID = ID;
		this.x = startX;
		this.y = startY;

		if (this.getColor() == Color.WHITE) {
			Board.white.add(this);
		} else if (this.getColor() == Color.BLACK) {
			Board.black.add(this);
		}
		Board.setPiece(x, y, this);
	}

	public void setMoveStrategy(MoveStrategy moveStrategy) {
		this.moveStrategy = moveStrategy;
	}

	public boolean possibleMove(int x, int y) {
		return this.moveStrategy.possibleMove(x, y, this);
	};
	
    ....other methods

	public boolean canMove() {
		return this.moveStrategy.canMove(this);
	};
}
```
# Daniel: Singleton Design Pattern
I implemented the Singleton design pattern to the ```Board.java``` class, making sure that there is only one instance of that class, providing a global access point to it.
## Implementation
### Board.java
```java
private static Board instance = null;

	private ArrayList<Piece> black = new ArrayList<Piece>();
	private ArrayList<Piece> white = new ArrayList<Piece>();

	private Piece board[][] = new Piece[8][8];

	private Board() {
	}

	public static Board getInstance() {
		if (instance == null) {
			instance = new Board();
		}
		return instance;
	}
	
	public void printBoard(){
	    ...print board code
	}
	
	public void startGame() {
	    ... start game code
	}
	
	public void setPiece (){
	    ..
	}
	
	public boolean mate(Color color){
	    ..check for mate logic.
	}
	
	public ArrayList<Piece> getWhite() {
		return white;
	}

	public ArrayList<Piece> getBlack() {
		return black;
	}
```
### Game.java
Other classes can get an instance of the Board class by doing this:
```Board board = Board.getInstance();```
like in the example below
```java
import java.util.Scanner;

public class Game {
	public static void main(String[] args) {
		Scanner moveChoice = new Scanner(System.in);

		Board board = Board.getInstance();

		while (true) {
			board.startGame();
			;

			int turns = 0;
			Color color;

			while (true) {
				board.printBoard();
				// check for check
				if (turns % 2 == 0) {
					color = Color.WHITE;
				} else
					color = Color.BLACK;

				if (board.staleMate(color) == true) {
					System.out.println("game over, stalemate");
					break;
				}
				if (board.checkForCheck(color) == true) {
					if (board.mate(color) == true) {

						System.out.printf("Checkmate, %s wins \n", color == Color.WHITE ? "Black" : "White");
						break;
					}
					System.out.printf("%s is in Check! \n", color == Color.WHITE ? "White" : "Black");
				}
		}
}

# Daniel: Decorator Design pattern
I implemented the "Decorator" design pattern in ```PiecewithCastling.java``` file in order to add additional functionality to the ```Piece.java``` class for pieces who are able to perform a castle, like ```King.java```
## Implementation
### IChessPiece.java
this is the ```interface``` that both ```Piece.java``` and ```DecoratedPiece.java``` implement
```java
public interface IChessPiece {
    public String getID();

    public boolean matchID(String ID);

    public Color getColor();

    public boolean sameColor(Piece otherPiece);

    public int getX();

    public int getY();

    public void setX(int newX);

    public void setY(int newY);

    public boolean possibleMove(int x, int y);

    public int move(int x, int y, Piece other);

    public boolean testMove(int x, int y);

    public boolean canMove();
}
```
### DecoratedPiece.java
This is the base decorator
```java 
public class DecoratedPiece implements IChessPiece {
    private Piece piece;
    protected boolean isFirstMove;

    public DecoratedPiece(Piece piece) {
        this.piece = piece;
        this.isFirstMove = piece.getIsFirstMove();
    }

    @Override
    public String getID() {
        return piece.getID();
    }

    @Override
    public boolean matchID(String ID) {
        return piece.matchID(ID);
    }

    @Override
    public Color getColor() {
        return piece.getColor();
    }

    @Override
    public boolean sameColor(Piece otherPiece) {
        return piece.sameColor(otherPiece);
    }

    @Override
    public int getX() {
        return piece.getX();
    }

    @Override
    public int getY() {
        return piece.getY();
    }

    @Override
    public void setX(int newX) {
        piece.setX(newX);
    }

    @Override
    public void setY(int newY) {
        piece.setY(newY);
    }

    @Override
    public boolean possibleMove(int x, int y) {
        return piece.possibleMove(x, y);
    }

    @Override
    public int move(int x, int y, Piece other) {
        return piece.move(x, y, other);

    }

    @Override
    public boolean testMove(int x, int y) {
        return piece.testMove(x, y);
    }

    @Override
    public boolean canMove() {
        return piece.canMove();
    }

}
```
### PieceWithCastling.java
this is the concrete decorator
```java 
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
```
### Usage in ```Board.java```
```java
import java.util.ArrayList;

public class Board {
	private static Board instance = null;
	private Piece board[][] = new Piece[8][8];

    .....other fields
	private Board() {
	}

	public static Board getInstance() {
		if (instance == null) {
			instance = new Board();
		}
		return instance;
	}

	public void printBoard() {
	... board printing logic
	}

	public void startGame() {
        ... instructions to play
		// black
		this.blackRookQ = new Rook(Color.BLACK, "rookQ", 0, 0);
		this.blackKnightQ = new Knight(Color.BLACK, "knightQ", 1, 0);
		this.blackBishopQ = new Bishop(Color.BLACK, "bishopQ", 2, 0);
		this.blackQueen = new Queen(Color.BLACK, "queen", 3, 0);
		this.blackKing = new King(Color.BLACK, "king", 4, 0);
		// Decorator implementation
		this.blackCastlingKing = new PieceWithCastling(blackKing);
		//
		this.blackBishopK = new Bishop(Color.BLACK, "bishopK", 5, 0);
		this.blackknightK = new Knight(Color.BLACK, "knightK", 6, 0);
		this.blackRookK = new Rook(Color.BLACK, "rookK", 7, 0);

		this.blackPawnA = new Pawn(Color.BLACK, "pawnA", 0, 1);
        .. other pawns

		// white 
	    ... white pieces
	}

    ... other methods

	public int processMove(String move, Color color) {

		String[] splitStr = move.split(" ");
		String piece = splitStr[0];

		if (piece.equals("castle")) {
			if (color == Color.BLACK) {
			// calling the castle method
				this.blackCastlingKing.castle(splitStr[1]);
			} else {
			// calling the castle method
				this.whiteCastlingKing.castle(splitStr[1]);
			}
		}
        ....

	}
    .. getters and setters
]


```

## How to build and run
```sh
$ cd Chess/src
$ javac Game.java
$ java Game
```
