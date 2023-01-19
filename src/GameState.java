public interface Gamestate() {
    public boolean staleMate(Color color);
    
    public boolean checkForCheck(Color color);

    public boolean mate(Color color);

    public int processMove(String move, Color color);

    public void printBoard();

    public Color getColor();
}



