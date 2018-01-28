import java.util.*;
/*
322. Coin Change

You are given coins of different denominations and a total amount of money
amount. Write a function to compute the fewest number of coins that you need to
make up that amount. If that amount of money cannot be made up by any
combination of the coins, return -1.

Example 1:
coins = [1, 2, 5], amount = 11
return 3 (11 = 5 + 5 + 1)

Example 2:
coins = [2], amount = 3
return -1.

Note:
You may assume that you have an infinite number of each kind of coin.
*/

public class CoinChangeFewestCoins
{
    public static int coinChange(int [] coins, int T)
    {
        if(T < 0) throw new IllegalArgumentException();
        if(T==0) return 0;
        if(coins.length == 0) return -1;

        int [] sol = new int[T+1];
        Arrays.fill(sol, -1);
        Arrays.sort(coins);
        sol[0] = 0;

        Deque<Integer> dq = new LinkedList<> ();
        dq.addLast(0);
        while(!dq.isEmpty())
        {
            int s = dq.removeFirst();
            for(int coin:coins)
            {
                if(s+coin == T) return sol[s] + 1;

                if(s+coin > T) continue;

                if(s+coin < T && sol[s+coin] == -1)
                {
                    sol[s+coin] = sol[s] + 1;
                    dq.addLast(s+coin);
                }
            }
        }
        return -1;
    }

    public static void main(String [] args)
    {
        runTest(new int []{1,2,5}, 11);
        runTest(new int []{2,4}, 11);
        runTest(new int []{1,2,3}, 11);
    }
    private static void runTest(int [] c, int T)
    {
        System.out.printf("coins = %s, T = %d, fewest coins = %d\n", 
            Arrays.toString(c), T, CoinChangeFewestCoins.coinChange(c, T));
    }
}