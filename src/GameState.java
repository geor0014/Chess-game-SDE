
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



