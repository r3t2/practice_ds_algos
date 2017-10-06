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
      this.blocks[n-1][n-1] = 0;

      computeHammingDist();
      computeManhattanDist();
    }

    private void computeHammingDist()
    {
      
    }

    private void computeManhattanDist()
    {

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

    // a board that is obtained by exchanging any pair of blocks
    public Board twin()
    {
      int [][] twinBlocks = new int[n][n];
      for(int i=0; i<n; i++)
      {
        for(int j=0; j<n; j++)
        {
          twinBlocks[i][j] = blocks[i][j];
        }
      }

      int tmp;
      if(twinBlocks[0][0] != 0 && twinBlocks[0][1] != 0)
      {
        tmp = twinBlocks[0][0];
        twinBlocks[0][0] = twinBlocks[0][1];
        twinBlocks[0][1] = tmp;
      }
      else
      {
        tmp = twinBlocks[n-1][n-1];
        twinBlocks[n-1][n-1] = twinBlocks[n-1][n-2];
        twinBlocks[n-1][n-2] = tmp; 
      }

      return new Board(twinBlocks);
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
      return null;
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

    }
}