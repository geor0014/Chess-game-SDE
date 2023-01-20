# Viktoria: State Design Pattern

For this design pattern, I first created an interface named GameState.java. Then I created classes WhiteTurnState.java and BlackTurnState.java that implement the GameState.java file. Lastly, I updated the Game.java file

## GameState.java
The GameState interface declares the state-specific methods.
```java
// This interface has several methods that are used to achieve the state design pattern
public interface Gamestate() {
    //checks if the game is in stale mate and for what color
    public boolean staleMate(Color color);
    //checks if the king of the given color is in check
    public boolean checkForCheck(Color color);
    
    //checks if king w/b is in a checkmate
    public boolean mate(Color color);
    //processes a move for a color and returns a status of move
    public int processMove(String move, Color color);
    //prints the current state of board
    public void printBoard();
    // returns a turn color
    public Color getColor();
}
```
## WhiteTurnState.java

```java
// handles game logic for turns of the white player
public class WhiteTurnState implements GameState {
    // stores white color of the current turn
    private Color color = Color.WHITE;
    // represents the game board
    private Board board = Board.getInstance();
    // checks if it is a stalemate for W/B
    public boolean staleMate(Color color) {
        if (board.staleMate(color) == true) {
            System.out.println("game over, stalemate");
            return true;
        }
        return false;
    }
    //checks if the king W/B is in check
    public boolean checkForCheck(Color color) {
        if (board.checkForCheck(color) == true) {
            return true;
        }
        return false;
    }
    // checks if W/B is in mate
    public boolean mate(Color color) {
        if (board.mate(color) == true) {
            return true;
        }
        return false;
    }
    // processes move W/B and returns a success or failure of move
    public int processMove(String move, Color color) {
        if (board.processMove(move, color) == 0) {
            return 0;
        } else {
            return 1;
        }
    }
}
```
In addition to this class, I also have a class which is the same as class WhiteTurnState expect it is for the color black (BlackTurnState).

## Game.java
The currentGameState variable is being used to keep track of the current state of the game, which can be either a "WhiteTurnState" or a "BlackTurnState."The code uses the current state to determine if the game is in a stalemate, check,  or checkmate, and to determine which player's turn it is.

```java
public class ChessFacade {
    // instace of the Board class, used for state of the game board
    Board board = Board.getInstance();
    // reads input from the user when user makes move
    Scanner moveChoice = new Scanner(System.in);
    // used to store information about state of the game
    GameState currentGameState;
    public ChessFacade() {
        public void startGame() {
            while (true) {
                //initialize the board
                board.startGame();
                int turns = 0;
                while (true) {
                    //print current state of the board
                    board.printBoard();
                    //determine whose turn it is based on number of turns
                    if (turns % 2 == 0) {
                        currentGameState = new WhiteTurnState();
                    } else
                        currentGameState = new BlackTurnState();
                    //check for stalemate
                    if (currentGameState.staleMate(currentGameState.getColor()) == true) {
                        System.out.println("Game over, it is a stalemate.");
                        break;
                    }
                    //check for check
                    if (currentGameState.checkForCheck(currentGameState.getColor()) == true) {
                        //check for checkmate
                        if (currentGameState.mate(currentGameState.getColor()) == true) {
                            System.out.printf("Checkmate, %s wins \n!",
                                    currentGameState.getColor() == Color.WHITE ? "Black" : "White");
                            break;
                        }
                        System.out.printf("%s is in Check! \n",
                                currentGameState.getColor() == Color.WHITE ? "White" : "Black");
                    }
                    // move choice
                    System.out.printf("%s's turn \n", currentGameState.getColor() == Color.WHITE ? "White" : "Black");
                    String move = moveChoice.nextLine();
                    // process move
                    if (currentGameState.processMove(move, currentGameState.getColor()) == 0) {
                        turns++;
                    } else {
                        System.out.println("That is an illegal move, sorry.");
                    }
                }
                //ask if players want to play again
                playAgain();
            }
        }
        // method to prompt the player to play again
        private void playAgain() {
            System.out.println("Would you like to play again? Type: y/n");
            if (moveChoice.next().equals("y")) {
                return;
            } else
                System.exit(0);
        }
    }
}
}
```
Overall, the state design pattern is being used to separate the different states of the game and encapsulate the behavior of the game in a way that makes the code more organized and maintainable.

# Viktoria: Facade design pattern
The Facade design pattern is used in this code to provide a simplified and unified interface to the complex functionality of a chess game. The ChessFacade class serves as the facade, hiding the complexity of the different game states, the game board and user input.
## Game.java
```java
public class Game {
    public static void main(String[] args) {
            //instantiate a new instance of ChessFacade
            ChessFacade chess = new ChessFacade();
            //start the game
             chess.startGame();
    }
}
```
This class is the entry point of the game, it contains the main method that is executed when the program starts. It creates a new instance of the ChessFacade class and calls the startGame() method on it, which begins the chess game.

## ChessFacade.java
To achieve the correct facade structure, I extracted the main functionality of the game and moved it to class ChessFacade.
```java
public class ChessFacade {
    // instace of the Board class, used for state of the game board
    Board board = Board.getInstance();
    // reads input from the user when user makes move
    Scanner moveChoice = new Scanner(System.in);
    // used to store information about state of the game
    GameState currentGameState;
    public ChessFacade() {
        public void startGame() {
            while (true) {
                //initialize the board
                board.startGame();
                int turns = 0;
                while (true) {
                    //print current state of the board
                    board.printBoard();
                    //determine whose turn it is based on number of turns
                    if (turns % 2 == 0) {
                        currentGameState = new WhiteTurnState();
                    } else
                        currentGameState = new BlackTurnState();
                    //check for stalemate
                    if (currentGameState.staleMate(currentGameState.getColor()) == true) {
                        System.out.println("Game over, it is a stalemate.");
                        break;
                    }
                    //check for check
                    if (currentGameState.checkForCheck(currentGameState.getColor()) == true) {
                        //check for checkmate
                        if (currentGameState.mate(currentGameState.getColor()) == true) {
                            System.out.printf("Checkmate, %s wins \n!",
                                    currentGameState.getColor() == Color.WHITE ? "Black" : "White");
                            break;
                        }
                        System.out.printf("%s is in Check! \n",
                                currentGameState.getColor() == Color.WHITE ? "White" : "Black");
                    }
                    // move choice
                    System.out.printf("%s's turn \n", currentGameState.getColor() == Color.WHITE ? "White" : "Black");
                    String move = moveChoice.nextLine();
                    // process move
                    if (currentGameState.processMove(move, currentGameState.getColor()) == 0) {
                        turns++;
                    } else {
                        System.out.println("That is an illegal move, sorry.");
                    }
                }
                //ask if players want to play again
                playAgain();
            }
        }
        // method to prompt the player to play again
        private void playAgain() {
            System.out.println("Would you like to play again? Type: y/n");
            if (moveChoice.next().equals("y")) {
                return;
            } else
                System.exit(0);
        }
    }
}
}
```

# Viktoria: Factory method design pattern
In a factory method design pattern, a factory class is defined that has a set of methods for creating objects of different types.

## PieceFactory.java
I implemented the PieceFactory class for creating all chess piece objects (Pawn, Rook, Knight, Bishop, Queen, King). The factory methods take in the same set of parameters (color, ID, startX, startY) and return an instance of the corresponding chess piece class. The factory pattern is used to create objects without specifying the exact class of object that will be created. This allows for a level of abstraction and flexibility in the code, as the client code can simply request an object from the factory, without needing to know the specific class that will be instantiated.
```java
public class PieceFactory() {
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
```

## Board.java
The startGame method first prints out instructions on how to play the game, and then creates the chess pieces using the factory methods from the PieceFactory class. The chess pieces are then given specific names and positions on the board.
```java
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
        this.blackRookQ = this.pieceFactory.createRook(Color.BLACK, "rookQ", 0, 0);
        this.blackKnightQ = this.pieceFactory.createKnight(Color.BLACK, "knightQ", 1, 0);
        this.blackBishopQ = this.pieceFactory.createBishop(Color.BLACK, "bishopQ", 2, 0);
        this.blackQueen = this.pieceFactory.createQueen(Color.BLACK, "queen", 3, 0);
        this.blackKing = this.pieceFactory.createKing(Color.BLACK, "king", 4, 0);
        //
        this.blackCastlingKing = new PieceWithCastling(blackKing);
        //
        this.blackBishopK = this.pieceFactory.createBishop(Color.BLACK, "bishopK", 5, 0);
        this.blackknightK = this.pieceFactory.createKnight(Color.BLACK, "knightK", 6, 0);
        this.blackRookK = this.pieceFactory.createRook(Color.BLACK, "rookK", 7, 0);
        this.blackPawnA = this.pieceFactory.createPawn(Color.BLACK, "pawnA", 0, 1);
        this.blackPawnB = this.pieceFactory.createPawn(Color.BLACK, "pawnB", 1, 1);
        this.blackPawnC = this.pieceFactory.createPawn(Color.BLACK, "pawnC", 2, 1);
        this.blackPawnD = this.pieceFactory.createPawn(Color.BLACK, "pawnD", 3, 1);
        this.blackPawnE = this.pieceFactory.createPawn(Color.BLACK, "pawnE", 4, 1);
        this.blackPawnF = this.pieceFactory.createPawn(Color.BLACK, "pawnF", 5, 1);
        this.blackPawnG = this.pieceFactory.createPawn(Color.BLACK, "pawnG", 6, 1);
        this.blackPawnH = this.pieceFactory.createPawn(Color.BLACK, "pawnH", 7, 1);
        // white
        this.whiteRookQ = this.pieceFactory.createRook(Color.WHITE, "rookQ", 0, 7);
        this.whiteKnightQ = this.pieceFactory.createKnight(Color.WHITE, "knightQ", 1, 7);
        this.whiteBishopQ = this.pieceFactory.createBishop(Color.WHITE, "bishopQ", 2, 7);
        this.whiteQueen = this.pieceFactory.createQueen(Color.WHITE, "queen", 3, 7);
        this.whiteKing = this.pieceFactory.createKing(Color.WHITE, "king", 4, 7);
        //
        this.whiteCastlingKing = new PieceWithCastling(whiteKing);
        //
        this.whiteBishopK = this.pieceFactory.createBishop(Color.WHITE, "bishopK", 5, 7);
        this.whiteKnightK = this.pieceFactory.createKnight(Color.WHITE, "knightK", 6, 7);
        this.whiteRookK = this.pieceFactory.createRook(Color.WHITE, "rookK", 7, 7);
        this.whitePawnA = this.pieceFactory.createPawn(Color.WHITE, "pawnA", 0, 6);
        this.whitePawnB = this.pieceFactory.createPawn(Color.WHITE, "pawnB", 1, 6);
        this.whitePawnC = this.pieceFactory.createPawn(Color.WHITE, "pawnC", 2, 6);
        this.whitePawnD = this.pieceFactory.createPawn(Color.WHITE, "pawnD", 3, 6);
        this.whitePawnE = this.pieceFactory.createPawn(Color.WHITE, "pawnE", 4, 6);
        this.whitePawnF = this.pieceFactory.createPawn(Color.WHITE, "pawnF", 5, 6);
        this.whitePawnG = this.pieceFactory.createPawn(Color.WHITE, "pawnG", 6, 6);
        this.whitePawnH = this.pieceFactory.createPawn(Color.WHITE, "pawnH", 7, 6);
    }
```

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

public class ChessFacade {

    Board board = Board.getInstance();
    Scanner moveChoice = new Scanner(System.in);
    IGameState currentGameState;

    public ChessFacade() {
    }

    public void startGame() {
        while (true) {
            board.startGame();

            int turns = 0;

            while (true) {
                board.printBoard();
                // check for check
                if (turns % 2 == 0) {
                    currentGameState = new WhiteTurnState();
                } else
                    currentGameState = new BlackTurnState();

                if (currentGameState.staleMate(currentGameState.getColor()) == true) {
                    System.out.println("game over, stalemate");
                    break;
                }
                if (currentGameState.checkForCheck(currentGameState.getColor()) == true) {
                    if (currentGameState.mate(currentGameState.getColor()) == true) {

                        System.out.printf("Checkmate, %s wins \n",
                                currentGameState.getColor() == Color.WHITE ? "Black" : "White");
                        break;
                    }
                    System.out.printf("%s is in Check! \n",
                            currentGameState.getColor() == Color.WHITE ? "White" : "Black");
                }

                // move choice
                System.out.printf("%s's turn \n", currentGameState.getColor() == Color.WHITE ? "White" : "Black");

                String move = moveChoice.nextLine();
                // process move
                if (currentGameState.processMove(move, currentGameState.getColor()) == 0) {
                    turns++;
                } else {
                    System.out.println("illegal move");
                }

            }
            playagain();
        }
    }

    private void playagain() {
        System.out.println("would you like to play again? y/n");
        if (moveChoice.next().equals("y")) {
            return;
        } else
            System.exit(0);
    }
}

```

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
