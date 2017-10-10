/*
Count number of ways to cover a distance
Given a distance ‘dist, count total number of ways to cover the distance with 1, 2 and 3 steps.
Examples:
Input:  n = 3
Output: 4
Below are the four ways
 1 step + 1 step + 1 step
 1 step + 2 step
 2 step + 1 step
 3 step

Input:  n = 4
Output: 7


*/
import java.util.Arrays;


public class CountWaysToSplitANumber
{
    /*Does not handle negative numbers -- can cause negative weight cycle*/
    
    public static int numSplits(int num, int [] den)
    {
        int [] sol = new int[num+1];

        for(int i=0; i<=num; i++) sol[i] = 0;

        for(int i=0; i<den.length; i++)
        {
            if(den[i] <= num)
                sol[den[i]] = 1;
        }

        for(int i=1; i < num; i++)
        {
            updateFrom(sol, i, den);
        }

        return sol[num];
    }

    public static void updateFrom(int [] sol, int i, int [] den)
    {
        for(int j=0; j<den.length; j++)
        {
            if( i + den[j] < sol.length)
                sol[i+den[j]] = sol[i] + sol[i+den[j]];

        }
    }

    public static void main(String [] args)
    {
        runTest(4, new int [] {1,2,3});
        runTest(5, new int [] {2,4});
    }

    private static void runTest(int N, int [] den)
    {
        System.out.println("N = " + N + " den = " + Arrays.toString(den));
        System.out.println("num ways = " + CountWaysToSplitANumber.numSplits(N, den));
    }

}
