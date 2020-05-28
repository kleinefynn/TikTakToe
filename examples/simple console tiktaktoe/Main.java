import tiktaktoe.kleinefynn.com.github.TikTakToe;

public class Main {

    public static void main(String[] args)
    {
        TikTakToe game = new TikTakToe(new TikTakToeConsoleDisplay(), 3);
        game.start();
    }
}
