package tiktaktoe.kleinefynn.com.github;

import java.util.function.Function;

public class TikTakToe
{
    private final static int DEFAULT_GAME_SIZE = 2;

    private TikTakToeField field;
    private ITikTakToeDisplay display;
    private TikTakToePlayer[] players;

    //Creates field with default size and 2 players
    private TikTakToe()
    {
        field = new TikTakToeField(DEFAULT_GAME_SIZE);

        players = new TikTakToePlayer[2];
        players[0] = new TikTakToePlayer(1);
        players[1] = new TikTakToePlayer(-1);
    }

    //Same as default constructors and sets the display
    public TikTakToe(ITikTakToeDisplay display)
    {
        this();

        if(display == null)
            throw new IllegalArgumentException("Display can't be null");

        this.display = display;
    }

    //Sets display and creates game field with specific size
    public TikTakToe(ITikTakToeDisplay display, int size)
    {
        if(display == null)
            throw new IllegalArgumentException("Display can't be null");

        field = new TikTakToeField(size);
        this.display = display;

        players = new TikTakToePlayer[2];
        players[0] = new TikTakToePlayer(1);
        players[1] = new TikTakToePlayer(-1);
    }

    //Runs the game until someone has won
    //Returns which player has won
    public void start()
    {
        int round = 0;  //Counts the amount of rounds
        int[] input;    //Saves the input from the player
        boolean isWon = false;

        do {

            display.showField(field);

            do {
                input = display.getInput();
            }while(!place(
                    input[0],
                    input[1],
                    players[round % 2]) ); //Get input until player placed

            //Check if won
            if(won(input[0], input[1]))
                isWon = true;
            else
                round++;

        }while(!isWon && !isDraw(round));

        display.showField(field);
        display.showWinner(round % 2 + 1);

    }

    public void reset()
    {
        int size = field.getSize();
        field = new TikTakToeField(size);
    }


    //Method to call if player wants to place
    private boolean place(int x, int y, TikTakToePlayer p)
    {
        if(field.canPlace(x,y)) {
            field.setPlace(x, y, p.getSymbol());
            return true;
        }
        return false;
    }

    //Checks if the move at x,y is a winning move
    private boolean won(int x, int y)
    {
        int[] sumHorVer = field.getHorVerValue(x,y);
        int[] sumDiagonals = null;

        if(Math.abs(sumHorVer[0]) == field.getSize() ||
                Math.abs(sumHorVer[1]) == field.getSize())
            return true;

        //Check if diagonals can be ignored
        //If not calculate diagonals value
        if(!canDiagonalBeIgnored(x,y))
        {
            sumDiagonals = field.getDiagonalValue();
            return  Math.abs(sumDiagonals[0]) == field.getSize() ||
                    Math.abs(sumDiagonals[1]) == field.getSize();
        }

        return false;
    }

    //Checks for a draw
    private boolean isDraw(int round){
        return round == (field.getSize() * field.getSize());
    }

    //Checks if the diagonals of the field need to be checked
    private boolean canDiagonalBeIgnored(int x, int y)
    {
        //Check for diagonal Left Top (0 0) to Right bottom (Size Size)
        if( x == y)
            return false;

        //Check for diagonal Left Bottom (0 Size) to Right Top (Size 0)
        return x != Math.abs(field.getSize() - 1 - y);
    }

}
