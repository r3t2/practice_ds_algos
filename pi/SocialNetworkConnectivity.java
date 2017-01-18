/*
* Social network connectivity. Given a social network containing n members and a log file containing m timestamps at which times pairs of members formed friendships, design an algorithm to determine the earliest time at which all members are connected (i.e., every member is a friend of a friend of a friend ... of a friend). Assume that the log file is sorted by timestamp and that friendship is an equivalence relation. The running time of your algorithm should be mlogn or better and use extra space proportional to n.
* 
* Constructor: 
* SocialNetworkConnectivity(fname) -- filename of the logFile.
* 
* LogFile format --
* n
* m 
* i j t (m times)
*/

import java.util.Scanner;
import java.io.File;


public class SocialNetworkConnectivity
{
  private UF uf;
  private int n;
  private int m;
  private int minConnectTime;
  
  public SocialNetworkConnectivity(String logFile) throws Exception
  {
    Scanner sc = new Scanner(new File(logFile));
    
    n = sc.nextInt();
    m = sc.nextInt();
    
    uf = new UF(n + 2);
    int start = n, end = n+1;
    
    uf.union(n, 0);
    uf.union(n+1, n-1);
    
    int i, j, t = -1;
    
    while( (uf.connected(n, n+1) == false) & sc.hasNextInt())
    {
      i = sc.nextInt(); j = sc.nextInt(); t = sc.nextInt();
      
      uf.union(i ,j);
    }
    
    if(uf.connected(n, n+1))
    {
      minConnectTime = t;
    }
    else
    {
      minConnectTime = -1;
    }
    
  }
  
  public int connectTime()
  {
    return minConnectTime;
  }
  
  /* only for practice. Ideally use the one from algos dir */
  public class UF
  {
    private int[] id;
    private int[] size;
    
    public UF(int N)
    {
      id = new int[N];
      size = new int[N];
      
      for(int i=0; i<N; i++)
      {
        id[i] = i;
        size[i] = 1;
      }
    }
    
    
    public void union(int i, int j)
    {
      int ri = root(i), rj = root(j);
      int si = size[i], sj = size[j];
      
      if(si > sj)
      {
        id[rj] = ri;
        size[rj] = si + sj;
      }
      else
      {
        id[ri] = rj;
        size[ri] = si + sj;
      }
    }
    
    private int root(int i)
    {
      while(id[i] != i)
      {
        id[i] = id[id[i]];
        i = id[i];
      }

      return i;
    }
    
    private boolean connected(int i, int j)
    {
      return (root(i) == root(j)); 
    }
  }
}
