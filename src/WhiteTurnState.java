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