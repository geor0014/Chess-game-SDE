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

```

## How to build and run
```sh
$ cd Chess/src
$ javac Game.java
$ java Game
```
