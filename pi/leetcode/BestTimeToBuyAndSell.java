/*
121. Best Time to Buy and Sell Stock

Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Example 1:
Input: [7, 1, 5, 3, 6, 4]
Output: 5

max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
Example 2:
Input: [7, 6, 4, 3, 1]
Output: 0

In this case, no transaction is done, i.e. max profit = 0.
*/
import java.util.Arrays;
public class BestTimeToBuyAndSell
{
    public static int maxProfit(int [] prices)
    {
        if(prices == null) throw new NullPointerException();
        if(prices.length == 0) return 0;

        int maxProfit = 0;
        int minSoFar = prices[0];
        for(int i=0; i<prices.length; i++)
        {
            if(prices[i] < minSoFar) minSoFar = prices[i];
            if(prices[i] - minSoFar > maxProfit) maxProfit = prices[i] - minSoFar;
        }

        return maxProfit;
    }

    public static void main(String [] args)
    {
        runTest(new int [] {7,6,5,4,3,2,1});
        runTest(new int [] {7, 1, 5, 3, 6, 4});
    }

    private static void runTest(int [] prices)
    {
        System.out.println("input = " + Arrays.toString(prices));
        System.out.println("max profit = " + BestTimeToBuyAndSell.maxProfit(prices));
    }
}