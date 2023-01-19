
import java.util.Scanner;

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
