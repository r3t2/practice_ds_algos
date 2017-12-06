import java.util.Deque;
import java.util.LinkedList;

public class DieHardStateMachineDP
{
  public static Iterable<Pair> solve(int M, int N, int T)
  {
    // check for M and N values
    // create a dp table
    Pair[][] dp = new Pair[M+1][N+1];
    Deque<Pair> q = new LinkedList<Pair> ();

    dp[0][0] = new Pair(0, 0); 
    q.addLast(dp[0][0]);

    while(!q.isEmpty())
    {
      Pair v = q.removeFirst();
      for(Pair w: adj(v, M, N))
      {
        if(dp[w.x][w.y] == null)
        {
          dp[w.x][w.y] = v;
          q.addLast(w);
        }
        if(isTarget(w, T)) return getPath(dp, w);
      }
    }

    return null;
  }

  private static Iterable<Pair> adj(Pair p, int M, int N)
  {
    Deque<Pair> pos = new LinkedList<Pair> ();

    if(p.x != 0) pos.addLast(new Pair(0, p.y));
    if(p.y != 0) pos.addLast(new Pair(p.x, 0));
    if(p.x != M) pos.addLast(new Pair(M, p.y));
    if(p.y != N) pos.addLast(new Pair(p.x, N));

    if(p.x + p.y != 0)
    {
      if(p.x + p.y <= M) pos.addLast(new Pair(p.x + p.y, 0));
      else pos.addLast(new Pair(M, p.y - (M - p.x)));

      if(p.x + p.y <= N) pos.addLast(new Pair(0, p.x + p.y));
      else pos.addLast(new Pair(p.x - (N - p.y), N));
    }

    return pos;
  }

  private static Iterable<Pair> getPath(Pair [][] dp, Pair p)
  {
    Deque<Pair> path = new LinkedList<Pair> ();
    path.addFirst(p);
    while(! dp[p.x][p.y].equals(p))
    {
      p = dp[p.x][p.y];
      path.addFirst(p);
    }

    return path;
  }

  private static boolean isTarget(Pair p, int T)
  {
    if(p.x == T || p.y == T) return true;
    else return false;
  }

  public static void main(String [] args)
  {
    runTest(3, 5, 4);
    runTest(3, 7, 5);
    runTest(2, 8, 5);
    runTest(3, 8, 5);
    runTest(191, 97, 3);
    runTest(191, 97, 5);
    runTest(191, 97, 1);
  }

  private static void runTest(int M, int N, int T)
  {
    System.out.printf("M = %d, N = %d, T = %d\n", M, N, T);
    System.out.println("solution = " + DieHardStateMachineDP.solve(M, N, T));
    System.out.println("");
  }

  public static class Pair 
  {
    private int x, y;

    public Pair(int x, int y)
    {
      this.x = x;
      this.y = y;
    }

    public boolean equals(Object o)
    {
      if(this == o) return true;
      if(o == null) return false;

      if(o.getClass() != this.getClass()) return false;

      Pair that = (Pair) o;

      if(this.x == that.x && this.y == that.y) return true;
      else return false;
    }

    public String toString()
    {
      return "(" + x + ", " + y + ")";
    }
  }
}