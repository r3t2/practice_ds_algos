/* 62. Unique Paths */
/* A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there? */

public class RobotUniquePathsGoBottomRight
{
    public static int uniquePathsWithObstacles(int [][] obstableGrid)
    {
        if(obstableGrid == null) return -1;

        int m = obstableGrid.length;
        int n = obstableGrid[0].length;

        if (m==0 || n==0) return 0;
        
        int [][] dp = new int[m][n];
        dp[m-1][n-1] = 1;

        for(int i=m-1; i>=0; i--)
        {
            for(int j=n-1; j>=0; j--)
            {
                if(obstableGrid[i][j] == 1) {dp[i][j] =0;continue;}
                if(i==m-1 && j==n-1) continue;

                if(j<n-1) dp[i][j] += dp[i][j+1];
                if(i<m-1) dp[i][j] += dp[i+1][j];
            }
        }

        return dp[0][0];


    }
    public static int uniquePaths(int m, int n)
    {
        if (m<0 || n<0) return -1;
        if (m==0 || n==0) return 0;

        int [][] dp = new int[m][n];
        dp[m-1][n-1] = 1;

        for(int i=m-1; i>=0; i--)
        {
            for(int j=n-1; j>=0; j--)
            {
                if(i==m-1 && j==n-1) continue;

                if(j<n-1) dp[i][j] += dp[i][j+1];
                if(i<m-1) dp[i][j] += dp[i+1][j];
            }
        }

        return dp[0][0];
    }

    public static void main(String [] args)
    {
        runTest(4, 4);
        runTest(1, 4);
        runTest(1, 1);
        runTest(3, 7);
        runTest(4, 5);

        runTest(new int [][] {{0,0,0},{0,1,0},{0,0,0}});
        runTest(new int [][] {{0,0,0},{0,0,0},{0,0,0}});
        runTest(new int [][] {{0,0,0,0}});
        runTest(new int [][] {{0,0,1,0}});
        runTest(new int [][] {{0}});
        runTest(new int [][] {{1}});
    }
    private static void runTest(int m, int n)
    {
        System.out.printf("m = %d, n = %d, numPaths = %d\n", m, n, 
            RobotUniquePathsGoBottomRight.uniquePaths(m,n));
    }

    private static void runTest(int [][] grid)
    {
        System.out.printf("with obstables, m = %d, n = %d, numPaths = %d\n", grid.length, grid[0].length, 
            RobotUniquePathsGoBottomRight.uniquePathsWithObstacles(grid));
    }
}