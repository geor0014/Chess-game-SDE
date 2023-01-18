
import java.util.Scanner;

public class Game {
	public static void main(String[] args) {
		Scanner moveChoice = new Scanner(System.in);

		Board board = Board.getInstance();

		while (true) {
			board.startGame();

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

				// move choice
				System.out.printf("%s's turn \n", color == Color.WHITE ? "White" : "Black");

				String move = moveChoice.nextLine();
				// process move
				if (board.processMove(move, color) == 0) {
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
