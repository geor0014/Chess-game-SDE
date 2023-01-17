
import java.util.Scanner;

public class Game {
	public static void main(String[] args) {
		Scanner moveChoice = new Scanner(System.in);

		while (true) {
			Board.getInstance().startGame();
			;

			int turns = 0;
			Color color;

			while (true) {
				Board.getInstance().printBoard();
				// check for check
				if (turns % 2 == 0) {
					color = Color.WHITE;
				} else
					color = Color.BLACK;

				if (Board.getInstance().staleMate(color) == true) {
					System.out.println("game over, stalemate");
					break;
				}
				if (Board.getInstance().checkForCheck(color) == true) {
					if (Board.getInstance().mate(color) == true) {

						System.out.printf("Checkmate, %s wins \n", color == Color.WHITE ? "Black" : "White");
						break;
					}
					System.out.printf("%s is in Check! \n", color == Color.WHITE ? "White" : "Black");
				}

				// move choice
				System.out.printf("%s's turn \n", color == Color.WHITE ? "White" : "Black");

				String move = moveChoice.nextLine();
				// process move
				if (Board.getInstance().processMove(move, color) == 0) {
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
