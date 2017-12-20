/*
Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. Return the maximum product you can get.

For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).

Note: You may assume that n is not less than 2 and not larger than 58.
*/
public class IntegerBreakMaxProd
{
    public static int integerBreak(int n) {
        if(n<1) throw new IllegalArgumentException();
        if(n==1) return 1;
        if(n==2) return 1;

        int [] dp = new int[n+1];
        for(int i=0; i<dp.length; i++) dp[i] = -1;
        dp[1] = 1;

        return integerBreak(n, dp);
    }

    private static int integerBreak(int n, int [] dp)
    {
        int dp_n;
        if(dp[n] != -1) return dp[n];
        
        int max = n, temp;
        for(int i=1; i<=n/2;i++)
        {
            temp = integerBreak(i, dp) * integerBreak(n-i, dp);
            if(temp > max) max = temp;
        }
        dp[n] = max;

        return max;
    }

    public static void main(String [] args)
    {
        runTest(1);
        runTest(2);
        runTest(5);
        runTest(10);
        runTest(20);
        runTest(30);
    }

    private static void runTest(int n)
    {
        System.out.printf("input = %d, prod = %d \n", n, IntegerBreakMaxProd.integerBreak(n));
    }
}