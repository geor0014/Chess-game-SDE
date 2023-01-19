// handles game logic for turns of the black player
public class BlackTurnState implements GameState {
    // stores black color of the current turn
    private Color color = Color.BLACK;
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