import java.io.*;
import java.util.*;
/*
Given a matrix of 1s and 0s, 1 is land, 0 is water, find the number of islands.
*/
/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class NumIslandsMatrix {
  public static int numIslands(int [][] grid)
  {
    int nR = grid.length;
    if(nR == 0) return 0;
    int nC = grid[0].length;
    
    boolean [][] marked = new boolean[nR][nC];
    for(int r=0; r<nR; r++){
      Arrays.fill(marked[r], false);
    }
    
    int islands = 0;
    for(int r=0; r<nR; r++)
    {
      for(int c=0; c<nC; c++)
      {
        if(grid[r][c] !=0 && marked[r][c] == false)
        {
          islands++;
          dfs(r,c,marked,grid);
        }
      }
    }
    
    return islands;
  }
  private static void dfs(int r, int c, boolean[][] marked, int [][] grid)
  {
    int nR = marked.length;
    int nC = marked[0].length;
    
    marked[r][c] = true;
    //may be we don't have to check
    if(r-1>=0 && marked[r-1][c] == false && grid[r-1][c]==1) dfs(r-1,c,marked, grid);
    //may be we don't have to check
    if(c-1>=0 && marked[r][c-1] == false && grid[r][c-1]==1) dfs(r,c-1,marked, grid);
    
    if(r+1<nR && marked[r+1][c] == false && grid[r+1][c]==1) dfs(r+1,c,marked, grid);
    if(c+1<nC && marked[r][c+1] == false && grid[r][c+1]==1) dfs(r,c+1,marked, grid);
  }
  public static void main(String[] args) {
    runTest(new int [][] {{0,0,0},{0,1,0},{1,1,1}});
    runTest(new int [][] {{1,0,1},{0,1,0},{1,1,1}});
    runTest(new int [][] {{0,0,0},{0,0,0},{0,0,0}});
    runTest(new int [][] {{1,1,1},{1,1,1},{1,1,1}});
  }
  
  private static void runTest(int [][] grid)
  {
    System.out.printf("num Islands = %d\n", numIslands(grid));
  }
}
