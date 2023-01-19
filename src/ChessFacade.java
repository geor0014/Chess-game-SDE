import java.util.Scanner;

// the facade for the game
public class ChessFacade {
    // instace of the Board class, used for state of the game board
    Board board = Board.getInstance();
    // reads input from the user when user makes move
    Scanner moveChoice = new Scanner(System.in);
    // used to store information about state of the game
    GameState currentGameState;

    public ChessFacade() {
    }

    public void startGame() {
        while (true) {
            // initialize the board
            board.startGame();
            int turns = 0;

            while (true) {
                // print current state of the board
                board.printBoard();

                // determine whose turn it is based on number of turns
                if (turns % 2 == 0) {
                    currentGameState = new WhiteTurnState();
                } else
                    currentGameState = new BlackTurnState();

                // check for stalemate
                if (currentGameState.staleMate(currentGameState.getColor()) == true) {
                    System.out.println("Game over, it is a stalemate.");
                    break;
                }
                // check for check
                if (currentGameState.checkForCheck(currentGameState.getColor()) == true) {
                    // check for checkmate
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
            // ask if players want to play again
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