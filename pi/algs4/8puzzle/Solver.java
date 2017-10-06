import edu.princeton.cs.algs4.MinPQ;
import java.util.Deque;
import java.util.LinkedList;

public class Solver
{
  private Iterable<Board> solutionPath;
  private int numMoves;

  // find a solution to the initial board (using the A* algorithm)
  public Solver(Board initial)
  {
    if(initial == null) throw new IllegalArgumentException();
    
    Board twin = initial.twin();
    SolverState actualProb = new SolverState(initial);
    SolverState twinProb = new SolverState(twin);

    // System.out.println("initial = " + initial);
    // System.out.println("twin = " + twin);

    int cnt = 0;

    while(actualProb.solved == false && twinProb.solved == false)
    {
      actualProb.step();
      twinProb.step();
      //cnt++;
      // System.out.println("------------------");
    }


    if(actualProb.solved == true)
    {
      solutionPath = actualProb.path;
      numMoves = actualProb.numMovesPath;
    }
    else
    {
      solutionPath = null;
      numMoves = -1;
    }
  }

    // is the initial board solvable?
  public boolean isSolvable()
  {
    return (moves() != -1);
  }

    // min number of moves to solve initial board; -1 if unsolvable
  public int moves()
  {
    return numMoves;
  }

  // sequence of boards in a shortest solution; null if unsolvable
  public Iterable<Board> solution()      
  {
    return solutionPath;
  }



  private class SolverState
  {
    private MinPQ<SolverBoardStep> pq = new MinPQ<SolverBoardStep> ();
    private boolean solved = false;
    private Deque<Board> path;
    private int numMovesPath;

    private SolverState(Board init)
    {
      // System.out.println("board = " + init);
      pq.insert(new SolverBoardStep(0, init, null));
    }

    private void step()
    {
      //printpq(pq);

      SolverBoardStep min = pq.delMin();
      Board minBoard = min.board;
      Board prevBoard = min.prev == null ? null : min.prev.board;

      // System.out.println("path.size = " + path.size() + ", enq moves = " + min.moves);
      // while(!path.isEmpty() && path.size() > min.moves)
      // {
      //   path.removeLast();
      // }

      // path.addLast(minBoard);
      // numMovesPath = path.size();

      if(minBoard.isGoal())
      {
        solved = true;
        path = getPath(min);
        numMovesPath = path.size() - 1;
      }
      
      else
      {
        for(Board b: minBoard.neighbors())
        {
          if(b.equals(prevBoard)) continue;
          
          pq.insert(new SolverBoardStep(min.moves+1, b, min));
        }
      }
    }

    private void printpq(MinPQ<SolverBoardStep> pq)
    {
      for(SolverBoardStep s: pq)
      {
        System.out.printf("%s",s.toString());
      }
    }

    private Deque<Board> getPath(SolverBoardStep s)
    {
      Deque<Board> path = new LinkedList<Board>();
      while(s != null)
      {
        path.addFirst(s.board);
        s = s.prev;
      }

      return path;
    }
  }



  private static class SolverBoardStep implements Comparable<SolverBoardStep>
  {
    private int dist;
    private Board board;
    private SolverBoardStep prev;
    private int moves;
    private SolverBoardStep(int moves, Board board, SolverBoardStep prev)
    {
      this.moves = moves;
      this.dist = board.manhattan() + moves;
      this.board = board;
      this.prev = prev;
    }

    public int compareTo(SolverBoardStep that)
    {
      if(this.dist < that.dist) return -1;
      else if(this.dist > that.dist) return +1;
      else return 0;
    }

    public String toString()
    {
      return "[dist = " + dist + "board = " + board.toString() + "]";
    }
  }


  // solve a slider puzzle (given below)
  public static void main(String[] args) 
  {
    // create initial board from file
    In in = new In(args[0]);
    int n = in.readInt();
    int[][] blocks = new int[n][n];
    for (int i = 0; i < n; i++)
    {
      for (int j = 0; j < n; j++)
      {
        blocks[i][j] = in.readInt();
      }
    }
    // blocks = new int [][]{{2,1,3},{4,6,5},{7,8,0}};    
    Board initial = new Board(blocks);

    // solve the puzzle
    Solver solver = new Solver(initial);

    // print solution to standard output
    if (!solver.isSolvable())
      StdOut.println("No solution possible");
    else 
    {
      StdOut.println("Minimum number of moves = " + solver.moves());
      for (Board board : solver.solution())
        StdOut.println(board);
    }
  }
}