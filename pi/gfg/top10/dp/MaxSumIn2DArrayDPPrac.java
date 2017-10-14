/*A table composed of N x M cells, each having a certain quantity of apples, is
/*given. You start from the upper-left corner. At each step you can go down or
/*right one cell. Find the maximum number of apples you can collect.*/

import java.util.Arrays;

public class MaxSumIn2DArrayDPPrac
{
    public static int maxSum(int[][] x)
    {
        int numRow = x.length, numCol = x[0].length;

        int [][] maxCount = new int [numRow][numCol];

        System.out.println(numRow + " " + numCol);

        for(int r = 0; r<numRow; r++)
        {
            for(int c = 0; c<numCol; c++)
            {
                maxCount[r][c] = 0;
            }
        }

        // initialize(maxCount, 0);

        maxCount[numRow-1][numCol-1] = x[numRow-1][numCol-1];

        for(int c = numCol - 1; c>=0; c--)
        {
            for(int r = numRow - 1; r>=0; r--)
            {
                if((r-1)>=0 && maxCount[r-1][c] < maxCount[r][c] + x[r-1][c])
                {
                    maxCount[r-1][c] = maxCount[r][c] + x[r-1][c];
                }
                if((c-1)>=0 && maxCount[r][c-1] < maxCount[r][c] + x[r][c-1])
                {
                    maxCount[r][c-1] = maxCount[r][c] + x[r][c-1];
                }
            }
        }

        return maxCount[0][0];

    }

    private static <T> void initialize(T[][] x, T val)
    {
        int numRows = x.length;
        int numCols = x[0].length;

        for(int r = 0; r<numRows; r++)
        {
            for(int c = 0; c<numCols; c++)
            {
                x[r][c] = val;
            }
        }
    }

    public static void main(String [] args)
    {
        runTest(new int[][]{{0, 1, 2, 3},
                            {1, 0, 5, 0},
                            {2, 0, 6, 7}});

        runTest(new int[][]{{0, 1, 2, 3}});

        runTest(new int[][]{{1}});        

    }

    private static void runTest(int[][] x)
    {
        System.out.println("x = ");
        for(int [] row : x)
        {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("max sum = " + MaxSumIn2DArrayDPPrac.maxSum(x));
    }

}
