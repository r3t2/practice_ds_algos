/*
Dynamic Programming | Set 10 ( 0-1 Knapsack Problem)
Given weights and values of n items, put these items in a knapsack of capacity W to get the maximum total value in the knapsack. In other words, given two integer arrays val[0..n-1] and wt[0..n-1] which represent values and weights associated with n items respectively. Also given an integer W which represents knapsack capacity, find out the maximum value subset of val[] such that sum of the weights of this subset is smaller than or equal to W. You cannot break an item, either pick the complete item, or don’t pick it (0-1 property).

*/

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class KnapsackDPPrac
{   
  /* 
  int [] v array of value of items
  int [] w -- array of weights of items
  int W -- weight limit
  */

  public static Integer [] knapsackSol(int [] v, int [] w, int W)
  {
    if(v == null || w == null) throw new IllegalArgumentException();
    if(v.length != w.length) throw new IllegalArgumentException();
    if(W < 0) throw new IllegalArgumentException();
    // check(v) check(w)

    int N = v.length;

    int [][] dp = new int [N+1][W+1];
    int [][] parent = new int [N+1][W+1];

    for(int r = 0; r < N+1; r++)
    {
      for(int c = 0; c < W+1; c++)
      {
        dp[r][c] = -1;
        parent[r][c] = -1;
      }
    }

    dp[0][W] = 0; //empty set, all W is available, current val = 0
    parent[0][W] = -1;

    for(int r = 0; r < N; r++)
    {
      for(int c = 0; c <= W; c++)
      {
        if(dp[r][c] != -1) //updateFrom(r, c, dp, parent);
        {
          if(dp[r][c] > dp[r+1][c])
          {
              dp[r+1][c] = dp[r][c] + 0; // don’t choose to include item r
              parent[r+1][c] = c; 
          }
          if(c-w[r] >= 0 && (dp[r][c] + v[r] > dp[r+1][c-w[r]]))
          {
              dp[r+1][c-w[r]] = dp[r][c] + v[r];
              parent[r+1][c-w[r]] = c;
          }
        }
      }
    }
  
    int max = -1, maxIdx = -1;
    for(int c = 0; c <= W; c++)
    {
      if(max < dp[N][c])
      {
        max = dp[N][c];
        maxIdx = c;
      }
    }


    // for(int r = 0; r<=N; r++)
    // {
    //   for(int c = 0; c<=W; c++)
    //   {
    //     System.out.printf("%d ", dp[r][c]);
    //   }
    //   System.out.printf("\n");
    // }
    Integer [] knapsack = getItemIdxs(parent, maxIdx);
    return knapsack;
  }

  private static Integer [] getItemIdxs(int [][] parent, int idx)
  {
    Deque<Integer> dq = new LinkedList<Integer>();
    int N = parent.length -1; // num items
    int cnt = N;
    while(cnt > 0)
    {
      if(idx != parent[cnt][idx])
      { 
        dq.addFirst(cnt-1);
      }
      idx = parent[cnt][idx];
      cnt--;
    }

    return dq.toArray(new Integer[dq.size()]);

  }


  public static void main(String [] args)
  {
    runTest(new int [] {1, 3, 15, 5}, new int [] {1, 2, 3, 4}, 10);
    runTest(new int [] {15, 5, 1, 18}, new int [] {2, 10, 1, 10}, 20);

  }

  private static void runTest(int [] v, int [] w, int W)
  {
      System.out.printf("v = %s\n", Arrays.toString(v));
      System.out.printf("w = %s\n", Arrays.toString(w));
      System.out.printf("W = %d\n", W);
      System.out.printf("knapsack = %s\n\n", Arrays.toString(KnapsackDPPrac.knapsackSol(v, w, W)));
  }

}



