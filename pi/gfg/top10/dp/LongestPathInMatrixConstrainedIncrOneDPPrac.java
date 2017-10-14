/*
Find the longest path in a matrix with given constraints
Given a n*n matrix where all numbers are distinct, find the maximum length path (starting from any cell) such that all cells along the path are in increasing order with a difference of 1.
We can move in 4 directions from a given cell (i, j), i.e., we can move to (i+1, j) or (i, j+1) or (i-1, j) or (i, j-1) with the condition that the adjacent cells have a difference of 1.
Example:
Input:  mat[][] = {{1, 2, 9}
                   {5, 3, 8}
                   {4, 6, 7}}
Output: 4
The longest path is 6-7-8-9.
*/

import java.util.Arrays;


public class LongestPathInMatrixConstrainedIncrOneDPPrac
{
    /**/
    
    public static int longestPath(int[][] mat)
    {
        int numRows = mat.length;
        int numCols = mat[0].length;

        int [][] sol = new int [numRows][numCols];
        for(int iR = 0; iR < numRows; iR++)
        {
            for(int iC = 0; iC < numCols; iC++)
            {
                sol[iR][iC] = -1;
            }
        }

        int max = 0;
        for(int iR = 0; iR < numRows; iR++)
        {
            for(int iC = 0; iC < numCols; iC++)
            {
                if(sol[iR][iC] == -1)
                {

                    dfs(mat, sol, iR, iC);
                    if(sol[iR][iC] > max) max = sol[iR][iC];
                }

            }
        }

        return max;
    }
    private static void dfs(int [][] mat, int [][] sol, int iR, int iC)
    {
        if(sol[iR][iC] != -1) return;

        int numRows = sol.length;
        int numCols = sol[0].length;

        int temp = 0;

        if((iR+1 < numRows) && (mat[iR+1][iC] - mat[iR][iC] == 1))
        {
            dfs(mat, sol, iR+1, iC);
            temp = sol[iR+1][iC];
            if((temp+1) > sol[iR][iC]) sol[iR][iC] = temp+1;
        }
        if((iC+1 < numCols) && (mat[iR][iC+1] - mat[iR][iC] == 1))
        {
            dfs(mat, sol, iR, iC+1);
            temp = sol[iR][iC+1];
            if((temp+1) > sol[iR][iC]) sol[iR][iC] = temp+1;
        }
        if((iR-1 >= 0) && (mat[iR-1][iC] - mat[iR][iC] == 1))
        {
            dfs(mat, sol, iR-1, iC);
            temp = sol[iR-1][iC];
            if((temp+1) > sol[iR][iC]) sol[iR][iC] = temp+1;
        }
        if((iC-1 >= 0) && (mat[iR][iC-1] - mat[iR][iC] == 1))
        {
            dfs(mat, sol, iR, iC-1);
            temp = sol[iR][iC-1];
            if((temp+1) > sol[iR][iC]) sol[iR][iC] = temp+1;
        }
        // System.out.printf("iR = %d, iC = %d, temp = %d, sol= %d\n", iR, iC, temp, sol[iR][iC]);
        if(temp == 0) sol[iR][iC] = 1;

    }


    public static void main(String [] args)
    {
            runTest(new int [][] {{1,2,9}, {5,3,8}, {4,6,7}});
            runTest(new int [][] {{1,2,3}, {6,5,4}, {7,8,9}});
            runTest(new int [][] {{6,3,2}, {8,9,1}, {4,3,2}});
    }

    private static void runTest(int [][] mat)
    {

        System.out.println("input = ");
        print2dint(mat);
        System.out.println("longest path = " +
            LongestPathInMatrixConstrainedIncrOneDPPrac.longestPath(mat));
    }

    private static void print2dint(int [][] mat)
    {
        int numR = mat.length;
        int numC = mat[0].length;
        for(int i=0; i<numR; i++)
        {
            System.out.println(Arrays.toString(mat[i]));
        }
    }

}
