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
## How to build and run
```sh
$ cd Chess/src
$ javac Game.java
$ java Game
```
