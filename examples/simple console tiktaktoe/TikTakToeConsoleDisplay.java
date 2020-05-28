import tiktaktoe.kleinefynn.com.github.ITikTakToeDisplay;
import tiktaktoe.kleinefynn.com.github.TikTakToeField;

import java.util.Scanner;

public class TikTakToeConsoleDisplay implements ITikTakToeDisplay
{
    //Scanner to get input from console
    Scanner sc;

    //---OVERRIDE-METHODS---

    //Method to print the field
    @Override
    public void showField(TikTakToeField field)
    {
        int fieldSize = field.getSize();

        System.out.println();
        for(int y=0; y < fieldSize; y++){
            for(int x = 0; x < fieldSize; x++){
                System.out.print((field.getField())[y][x] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    //Method to display who the winner is
    @Override
    public void showWinner(int player)
    {
        System.out.println("Player " + player + " has won!");;
    }

    @Override
    public void showDraw() {
        System.out.println("DRAW");
    }

    //Method to get the input from the player
    //Returns int[] { x , y }
    @Override
    public int[] getInput() {
        String inputString;
        int[] inputInt;

        sc = new Scanner(System.in);

        do {
            System.out.print("-> ");
            inputString = sc.nextLine();
            inputInt = parseInput(inputString);

        }while(inputInt == null);

        sc.close();

        return inputInt;
    }

    //----------------

    //Method to parse the input String to an int[2]
    //input format: "x y"
    //returns null if invalid
    private int[] parseInput(String input)
    {
        //Stores the input split by " "
        String[] inputs;

        //Stores the parsed numbers from inputs
        int[] parsed;

        if(input == null)
            return null;

        inputs = input.split(" ");

        if(inputs.length != 2)
            return null;

        parsed = new int[2];

        try
        {
            for(int i=0; i < inputs.length; i++)
            {
                parsed[i] = Integer.parseInt(inputs[i]);
            }
        }
        catch (Exception e)
        {
            return null;
        }

        return parsed;
    }
}
