# Viktoria: State Design Pattern

For this design pattern, I first created an interface named GameState.java. Then I created classes WhiteTurnState.java and BlackTurnState.java that implement the GameState.java file. Lastly, I updated the Game.java file

# Implementation

## GameState.java
```java
public interface Gamestate() {
    public boolean staleMate(Color color);
    
    public boolean checkForCheck(Color color);

    public boolean mate(Color color);

    public int processMove(String move, Color color);

    public void printBoard();

    public Color getColor();
}
```
## WhiteTurnState.java
The BlackTurnState is the same as this file just that the color is black.
```java

public class WhiteTurnState implements GameState {
    private Color color = Color.WHITE;
    private Board board = Board.getInstance();

    public boolean staleMate(Color color) {

        // check for stalemate
        if (board.staleMate(color) == true) {
            System.out.println("game over, stalemate");
            return true;
        }
        return false;
    }
    
    public boolean checkForCheck(Color color) {
        // check for check
        if (board.checkForCheck(color) == true) {
            return true;
        }
        return false;
    }
    
    public boolean mate(Color color) {
        // check for mate
        if (board.mate(color) == true) {
            return true;
        }
        return false;
    }
    
    public int processMove(String move, Color color) {
        // process move
        if (board.processMove(move, color) == 0) {
            return 0;
        } else {
            return 1;
        }
    }
} 
```
## Game.java

```java
public class Game {
	public static void main(String[] args) {
		Scanner moveChoice = new Scanner(System.in);
		GameState currentGameState;

		Board board = Board.getInstance();

		while (true) {
			board.startGame();

			int turns = 0;
			Color color;

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

						System.out.printf("Checkmate, %s wins \n", currentGameState.getColor() == color.WHITE ? "Black" : "White");
						break;
					}
					System.out.printf("%s is in Check! \n", currentGameState.getColor() == color.WHITE ? "White" : "Black");
				}

				// move choice
				System.out.printf("%s's turn \n", currentGameState.getColor() == color.WHITE ? "White" : "Black");

				String move = moveChoice.nextLine();
				// process move
				if (currentGameState.processMove(move, currentGameState.getColor()) == 0) {
					turns++;
				} else {
					System.out.println("illegal move");
				}

			}
			System.out.println("would you like to play again? y/n");
			if (moveChoice.next().equals("y")) {
				continue;
			} else
				System.exit(0);
		}
	}

}
```