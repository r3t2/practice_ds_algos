/*Writing programming interview questions hasn't made me rich. Maybe trading Apple stocks will.
Suppose we could access yesterday's stock prices as a list, where:
The indices are the time in minutes past trade opening time, which was 9:30am local time.
The values are the price in dollars of Apple stock at that time.
So if the stock cost $500 at 10:30am, stock_prices_yesterday[60] = 500.
Write an efficient function that takes stock_prices_yesterday and returns the best profit I could have made from 1 purchase and 1 sale of 1 Apple stock yesterday.
For example:
 stock_prices_yesterday = [10, 7, 5, 8, 11, 9]

get_max_profit(stock_prices_yesterday)
# returns 6 (buying for $5 and selling for $11)


PythonRubyJavaJavaScriptCC++C#Objective-C (beta)PHPSwift
No "shorting"—you must buy before you sell. You may not buy and sell in the same time step (at least 1 minute must pass).
*/


import java.util.Arrays;

public class MaxDiffBetweenTwoPointsStockPriceDPPrac
{
    public static int[] maxDiff(int [] prices)
    {
        int N = prices.length;

        int [] gains = new int [N];

        if(N == 0) return null;

        gains[N-1] = 0;

        int maxGain = 0;
        int maxGainSt = N-1, maxGainEnd = N-1;

        for(int i=N-2; i>=0; i--)
        {
            if(gains[i+1] + prices[i+1] - prices[i] > 0)
            {
                gains[i] = gains[i+1] + prices[i+1] - prices[i];
                if(gains[i] > maxGain)
                {
                    maxGain = gains[i];
                    maxGainSt = i;
                }
            }
            else
            {
                gains[i] = 0;
            }
        }



        int i = maxGainSt;
        while(i<N && gains[i] != 0)
        {
            i++;
        }

        maxGainEnd = i;

        return new int [] {maxGain, maxGainSt, maxGainEnd};

    }

    public static void main(String [] args)
    {
        runTest(new int [] {5, 8, 10, 12, 6, 9, 13});
        runTest(new int [] {12, 10, 8, 3, 2});
        runTest(new int [] {});
        runTest(new int [] {2, 3, 8, 10, 12});
        runTest(new int [] {12 , 10, 6, 5, 3, 4, 10, 12});
        runTest(new int [] {5, 8, 10, 14, 6, 9, 13});
        runTest(new int [] {15, 5, 8, 10, 14, 6, 9, 13});

    }

    private static void runTest(int [] x)
    {
        System.out.println("input array = " + Arrays.toString(x));
        int [] maxGain = MaxDiffBetweenTwoPointsStockPriceDPPrac.maxDiff(x);
        if(maxGain != null) System.out.printf("maxGain = %d, buyPrice = %d, sellPrice = %d\n\n", maxGain[0], x[maxGain[1]], x[maxGain[2]]);
        else System.out.println("return val is null\n");
    }


}


