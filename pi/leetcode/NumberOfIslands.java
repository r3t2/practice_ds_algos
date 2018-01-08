/* 200. Number of islands */
/*
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

11110
11010
11000
00000
Answer: 1

Example 2:

11000
11000
00100
00011
Answer: 3
*/
import java.util.List;
import java.util.ArrayList;

public class NumberOfIslands
{
    public static int numIslands(char [][] grid)
    {
        if(grid == null) return 0;
        int nR = grid.length;
        if(nR == 0) return 0;
        int nC = grid[0].length;

        boolean [][] marked = new boolean[nR][nC];
        int cnt = 0;
        for(int r=0; r<nR; r++)
        {
            for(int c=0; c<nC; c++)
            {
                if(!marked[r][c] && grid[r][c] == '1')
                {
                    dfs(grid, marked, r, c);
                    cnt += 1;
                }
            }
        }
        return cnt;
    }

    private static void dfs(char [][] grid, boolean[][] marked, int r, int c)
    {
        marked[r][c] = true;

        int nR = grid.length;
        int nC = grid[0].length;

        // for(Node n: neighbors(grid, r, c, nR, nC))
        // {
        //     if(!marked[n.r][n.c])
        //     {
        //         dfs(grid, marked, n.r, n.c);
        //     }
        // }
        if(r+1<nR && grid[r+1][c]=='1' && !marked[r+1][c]) dfs(grid, marked, r+1, c);
        if(r-1>=0 && grid[r-1][c]=='1' && !marked[r-1][c]) dfs(grid, marked, r-1, c);
        if(c+1<nC && grid[r][c+1]=='1' && !marked[r][c+1]) dfs(grid, marked, r, c+1);
        if(c-1>=0 && grid[r][c-1]=='1' && !marked[r][c-1]) dfs(grid, marked, r, c-1);
    }

    // private static Iterable<Node> neighbors(char[][] grid, int r, int c, int nR, int nC)
    // {
    //     List<Node> l = new ArrayList<Node> ();
    //     if(r+1<nR && grid[r+1][c]=='1') l.add(new Node(r+1, c));
    //     if(r-1>=0 && grid[r-1][c]=='1') l.add(new Node(r-1, c));

    //     if(c+1<nC && grid[r][c+1]=='1') l.add(new Node(r, c+1));
    //     if(c-1>=0 && grid[r][c-1]=='1') l.add(new Node(r, c-1));

    //     return l;
    // }

    private static class Node
    {
        private int r, c;
        private Node(int r, int c)
        {
            this.r = r;
            this.c = c;
        }
    }


    public static void main(String [] args)
    {
        runTest(new char [][] {{'1','1',0},{'1','1',0},{0,0,'1'}});
        // runTest(new char [][] {{1,1,0},{1,1,1},{0,1,1}});
        // runTest(new char [][] {{1,1,0},{1,0,1},{0,1,1}});
        // runTest(new char [][] {{1,1,0,0,0,1}});
        // runTest(new char [][] {{1}});
    }
    private static void runTest(char [][] grid)
    {
        System.out.printf("num islands = %d\n", NumberOfIslands.numIslands(grid));
    }
}