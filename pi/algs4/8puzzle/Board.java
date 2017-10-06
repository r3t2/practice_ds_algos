import java.util.Deque;
import java.util.LinkedList;

public class Board
{
    private int[][] blocks; //
    private int n; // dimension of the board n x n
    private int hDist; // hamming distance
    private int mDist; // manhattan distance

    private int zx;
    private int zy;

    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks)                                       
    {
      this.n = blocks.length;

      this.blocks = new int[n][n];

      for(int i=0; i<n; i++)
      {
        for(int j=0; j<n; j++)
        {
          this.blocks[i][j] = blocks[i][j];
          if(blocks[i][j] == 0)
          {
            zx = i;
            zy = j;
          }
        }
      }

      computeHammingDist();
      computeManhattanDist();
    }

    private void computeHammingDist()
    {
      int cnt = 0;

      for(int i=0; i<n; i++)
      {
        for(int j=0; j<n; j++)
        {
          if((blocks[i][j] !=0) && (blocks[i][j] != target(i,j))) cnt++;
        }
      }

      this.hDist = cnt;
    }

    private void computeManhattanDist()
    {
      int cnt = 0, val, tx, ty;

      for(int i=0; i<n; i++)
      {
        for(int j=0; j<n; j++)
        {
          val = blocks[i][j];
          if(val == 0) continue;

          tx = (val-1) / n;
          ty = (val-1) % n;

          cnt += ( Math.abs(tx - i) + Math.abs(ty - j) );

        }
      }

      this.mDist = cnt;
    }

    // board dimension n
    public int dimension()
    {
      return this.n;
    }
    
    // number of blocks out of place
    public int hamming() 
    {
      return this.hDist;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan()
    {
      return this.mDist;
    }
    
    // is this board the goal board?
    public boolean isGoal()
    {
      for(int i=0; i<n; i++)
      {
        for(int j=0; j<n; j++)
        {
          if(this.blocks[i][j] != target(i,j))
            return false;
        }
      }

      return true;
    }

    private int target(int r, int c)
    {
      if(r==n-1 && c==n-1) return 0;
      return r*n + c + 1;
    }

    private int[][] copy(int [][] in)
    {
      int numRows = in.length;
      int numCols = in[0].length;
      int [][] out = new int [numRows][numCols];

      for(int i=0; i<numRows; i++)
      {
        for(int j=0; j<numCols; j++)
        {
          out[i][j] = in[i][j];
        }
      }

      return out;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin()
    {
      if(blocks[0][0] != 0 && blocks[0][1] != 0)
      {
        return twin(0, 0, 0, 1);
      }
      else
      {
        return twin(n-1, n-1, n-1, n-2);
      }
    }

    // twin board obtained by exchanging tile at (x0, y0) with (x1, y1)
    private Board twin(int x0, int y0, int x1, int y1)
    {
      if(x0 < 0 || x0 >=n) return null;
      if(y0 < 0 || y0 >=n) return null;
      if(x1 < 0 || x1 >=n) return null;
      if(y1 < 0 || y1 >=n) return null;

      int [][] twinBlocks = copy(blocks);
      exch(twinBlocks, x0, y0, x1, y1);
      return new Board(twinBlocks);
    }

    private void exch(int[][] arr, int x0, int y0, int x1, int y1)
    {
      int tmp = arr[x1][y1];
      arr[x1][y1] = arr[x0][y0];
      arr[x0][y0] = tmp;
    }

    // does this board equal y?
    public boolean equals(Object y)
    {
      if(y == this) return true;
      if(y == null) return false;

      if(y.getClass() != this.getClass()) return false;

      Board that = (Board) y;

      if(that.n != this.n) return false;

      for(int i=0; i<n; i++)
      {
        for(int j=0; j<n; j++)
        {
          if(this.blocks[i][j] != that.blocks[i][j]) return false;
        }
      }

      return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors()
    {
      Deque<Board> n = new LinkedList<Board>();
      Board b;

      b = twin(zx, zy, zx-1, zy); if(b != null) n.addFirst(b);
      b = twin(zx, zy, zx+1, zy); if(b != null) n.addFirst(b);
      b = twin(zx, zy, zx, zy-1); if(b != null) n.addFirst(b);
      b = twin(zx, zy, zx, zy+1); if(b != null) n.addFirst(b);

      return n;
    }

    // string representation of this board (in the output format specified below)
    public String toString()
    {
      StringBuilder s = new StringBuilder();
      s.append(n + "\n");
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          s.append(String.format("%2d ", this.blocks[i][j]));
        }
        s.append("\n");
      }
      return s.toString();
    }

    // unit tests (not graded)
    public static void main(String[] args)
    {
      runTest(new int[][] { {8,1,3}, {4,0,2}, {7,6,5} });
    }

    private static void runTest(int[][] blocks)
    {
      Board b = new Board(blocks);
      System.out.println("input = \n" + b);
      System.out.println("\nneighbors = \n" + b.neighbors());
      System.out.println("hamming = " + b.hamming());
      System.out.println("manhattan = " + b.manhattan());
      System.out.println("isGoal = " + b.isGoal());
      System.out.println("\ntwin = \n" + b.twin());
      System.out.println("\n");
    }
}