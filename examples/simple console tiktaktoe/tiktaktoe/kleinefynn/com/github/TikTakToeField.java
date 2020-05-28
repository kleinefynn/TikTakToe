package tiktaktoe.kleinefynn.com.github;

public class TikTakToeField
{
    //Game field
    private int[][] field;

    //The size of the field
    private final int size;

    //Constructors
    public TikTakToeField()
    {
        size = 3;
        field = new int[size][size];
    }

    public TikTakToeField(int size)
    {
        if(size <= 0)
            throw new IllegalArgumentException("TikTakToe field size can't be less or equal zero!");

        this.size = size;
        field = new int[size][size];
    }

    //Get Methods for members
    public int[][] getField() {
        return field;
    }
    public int getSize() { return size; }

    //--------------------

    //Calculates the value of the horizontal and vertical
    //Always returns in the format int[] { hor, ver }
    public int[] getHorVerValue(int x, int y){
        int sumvertical = 0, sumhorizontal = 0;

        for(int i=0; i < size; i++)
        {
            sumvertical += field[i][x];
            sumhorizontal += field[y][i];
        }

        return new int[] { sumhorizontal, sumvertical };
    }

    //Calculates the value of the diagonals
    //Always returns in the format int[] {
    //                                      TopLeftToBottomRight,
    //                                      BottomLeftToTopRight
    //                                   }
    public int[] getDiagonalValue()
    {
        int sumDiagonalTopLeftToBottomRight = 0;
        int sumDiagonalBottomLeftToTopRight = 0;

        for(int i = 0; i < size; i++){
            //Check for 0 0 to Size Size
            sumDiagonalTopLeftToBottomRight += field[i][i];

            //Check for Size 0 to 0 Size
            sumDiagonalBottomLeftToTopRight += field[(size - 1) - i][i];
        }

        return new int[] {
                sumDiagonalTopLeftToBottomRight,
                sumDiagonalBottomLeftToTopRight
        };
    }

    //Sets at field x,y
    public void setPlace(int x, int y, int value){
        field[y][x] = value;
    }

    //Checks if the coords x,y are correct and
    //       if field x,y is occupied
    public boolean canPlace(int x, int y){
        if(x < 0 || y < 0)
            return false;

        if(x >= size || y >= size)
            return false;

        return field[y][x] == 0;
    }
}
