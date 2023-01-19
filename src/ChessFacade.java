import java.util.Scanner;


// extracted funcniilatiy of game, you have chess, facade
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