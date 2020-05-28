package tiktaktoe.kleinefynn.com.github;

public interface ITikTakToeDisplay {

    //Method to display the field
    void showField(TikTakToeField field);

    //Method to display who the winner is
    void showWinner(int player);

    //Method do display draw
    void showDraw();

    //Method to get the input from the player
    //Returns int[2] { x, y }
    int[] getInput();
}
