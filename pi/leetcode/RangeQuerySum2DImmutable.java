/*
304. Range Sum Query 2D - Immutable
DescriptionHintsSubmissionsDiscussSolution
DiscussPick One
Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Example:
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12
Note:
You may assume that the matrix does not change.
There are many calls to sumRegion function.
You may assume that row1 ≤ row2 and col1 ≤ col2.
*/

public class RangeQuerySum2DImmutable
{
    private int [][] cumSum = null;
    private int [][] matrix = null;
    private int nR, nC;
    public RangeQuerySum2DImmutable(int [][] matrix)
    {
        this.matrix = matrix;

        this.nR = matrix.length;
        this.nC = matrix[0].length;

        cumSum = new int[nR][nC];
        computeCumSum();
    }

    private void computeCumSum()
    {
        int sum;
        for(int r=0; r<nR; r++)
        {
            sum = 0;
            for(int c=0; c<nC; c++)
            {
                sum += matrix[r][c];
                cumSum[r][c] = sum;
            }
        }
        for(int c=0; c<nC; c++)
        {
            sum = 0;
            for(int r=0; r<nR; r++)
            {
                sum += cumSum[r][c];
                cumSum[r][c] = sum;
            }
        }
    }

    private int getCumSum(int r, int c)
    {
        if(r<0 || c<0) return 0;
        return cumSum[r][c];
    }
    public int sumRegion(int row1, int col1, int row2, int col2)
    {
        int s2 = getCumSum(row2, col2);
        int sLeft = getCumSum(row2, col1-1);
        int sTop = getCumSum(row1-1, col2);
        int s1_1 = getCumSum(row1-1, col1-1);

        return s2 - sLeft - sTop + s1_1;
    }

    public static void main(String [] args)
    {
        int [][] inputMatrix = new int [][]{  {3, 0, 1, 4, 2},
                                              {5, 6, 3, 2, 1},
                                              {1, 2, 0, 1, 5},
                                              {4, 1, 0, 1, 7},
                                              {1, 0, 3, 0, 5}
                                           };
        RangeQuerySum2DImmutable r = new RangeQuerySum2DImmutable(inputMatrix);

        runTest(r, 2, 1, 4, 3);
        runTest(r, 1, 1, 2, 2);
        runTest(r, 1, 2, 2, 4);
        runTest(r, 0, 0, 2, 4);
        runTest(r, 0, 0, 0, 0);
        runTest(r, 0, 0, 1, 2);
    }

    private static void runTest(RangeQuerySum2DImmutable r, int r1, int c1, int r2, int c2)
    {
        System.out.printf("r1=%d, c1=%d, r2=%d, c2=%d, sum=%d\n", 
            r1, c1, r2, c2, r.sumRegion(r1, c1, r2, c2));
    }
}