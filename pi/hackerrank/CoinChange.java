import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
/*
The Coin Change Problem

You have  types of coins available in infinite quantities where the value of each coin is given in the array . Can you determine the number of ways of making change for  units using the given types of coins? For example, if , and , we can make change for  units in three ways: , , and .

Given , , and , print the number of ways to make change for  units using any number of coins having the values given in .

Input Format

The first line contains two space-separated integers describing the respective values of  and . 
The second line contains  space-separated integers describing the respective values of  (the list of distinct coins available in infinite amounts).

Constraints

Each  is guaranteed to be distinct.
Hints

Solve overlapping subproblems using Dynamic Programming (DP): 
You can solve this problem recursively but will not pass all the test cases without optimizing to eliminate the overlapping subproblems. Think of a way to store and reference previously computed solutions to avoid solving the same subproblem multiple times.
Consider the degenerate cases: 
How many ways can you make change for  cents?
How many ways can you make change for  cents if you have no coins?
If you're having trouble defining your solutions store, then think about it in terms of the base case .
The answer may be larger than a -bit integer.
Output Format

Print a long integer denoting the number of ways we can get a sum of  from the given infinite supply of  types of coins.

Sample Input 0

4 3
1 2 3
Sample Output 0

4
Explanation 0

There are four ways to make change for  using coins with values given by :

Thus, we print  as our answer.

Sample Input 1

10 4
2 5 3 6
Sample Output 1

5
Explanation 1

There are five ways to make change for  units using coins with values given by :

Thus, we print  as our answer.
*/
public class CoinChange {

    static long getWays(long N, long[] coins){
        if(coins == null) throw new NullPointerException();
        if(coins.length == 0) return 0;

        HashMap<Node, Long> dp = new HashMap<Node, Long> ();
        
        Arrays.sort(coins);
        for(long coin: coins) dp.put(new Node(N, coin), 1L);

        return getWays(coins, 0, N, dp, Long.MIN_VALUE);

    }

    private static long getWays(long[] coins, long N, long T, HashMap<Node, Long> dp, long lastCoin)
    {
        Node node = new Node(N, lastCoin);
        if(dp.containsKey(node)) return dp.get(node);

        long cnt = 0;
        for(int i=coins.length-1; i>=0; i--)
        {
            long coin = coins[i];
            if(coin < lastCoin) continue;
            if(N + coin <= T)
            {
                cnt+=getWays(coins, N+coin, T, dp, coin);
            }
            dp.put(new Node(N, coin), cnt);
        }
        return cnt;
    }

    private static class Node
    {
        private long val;
        private long lastCoin;
        private Node(long val, long lastCoin)
        {
            this.val = val;
            this.lastCoin = lastCoin;
        }
        public int hashCode()
        {
            return (int) (31*val + lastCoin);
        }
        public boolean equals(Object o)
        {
            if(o == null) return false;
            if(this.getClass() != o.getClass()) return false;
            
            Node that = (Node) o;
            if(this.val != that.val) return false;
            if(this.lastCoin != that.lastCoin) return false;

            return true;
        }
    }

    public static void main(String[] args) {
        // Scanner in = new Scanner(System.in);
        // int n = in.nextInt();
        // int m = in.nextInt();
        // long[] c = new long[m];
        // for(int c_i=0; c_i < m; c_i++){
        //     c[c_i] = in.nextLong();
        // }
        // // Print the number of ways of making change for 'n' units using coins having the values given by 'c'
        // long ways = getWays(n, c);
        // System.out.printf("%d\n", ways);
        runTest(5, new long[] {1,2,3});
        runTest(50, new long[] {20,10,5,1});
    }

    private static void runTest(long N, long [] coins)
    {
        System.out.printf("N = %d, coins = %s\n", N, Arrays.toString(coins));
        System.out.printf("number of ways = %d\n", CoinChange.getWays(N, coins));
    }
}