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
    Board twin = initial.twin();
    SolverState actualProb = new SolverState(initial);
    SolverState twinProb = new SolverState(twin);

    // System.out.println("initial = " + initial);
    // System.out.println("twin = " + twin);

    int cnt = 0;
    while(cnt < 10000000 && actualProb.solved == false && twinProb.solved == false)
    {
      actualProb.step();
      twinProb.step();
      cnt++;
    }


    if(actualProb.solved == true)
    {
      solutionPath = actualProb.path;
      numMoves = actualProb.numSteps;
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
    private int numSteps = 0;
    private boolean solved = false;
    private Deque<Board> path = new LinkedList<Board> ();

    private SolverState(Board init)
    {
      // System.out.println("board = " + init);
      pq.insert(new SolverBoardStep(init.manhattan() + numSteps, init));
    }

    private void step()
    {

      Board minBoard = pq.delMin().board;

      path.addLast(minBoard);

      if(minBoard.isGoal()) solved = true;
      
      else
      {
        numSteps++;
        for(Board b: minBoard.neighbors())
        {
          if(!b.equals(minBoard))
            pq.insert(new SolverBoardStep(b.manhattan() + numSteps, b));
        }
      }
    }
  }

  private static class SolverBoardStep implements Comparable<SolverBoardStep>
  {
    private int dist;
    private Board board;
    private SolverBoardStep(int dist, Board board)
    {
      this.dist = dist;
      this.board = board;
    }

    public int compareTo(SolverBoardStep that)
    {
      if(this.dist < that.dist) return -1;
      else if(this.dist > that.dist) return +1;
      else return 0;
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