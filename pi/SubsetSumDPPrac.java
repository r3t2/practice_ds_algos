/*
Count number of ways to cover a distance
Given a set of non-negative integers, and a value sum, determine if there is a subset of the given set with sum equal to given sum.

Examples: set[] = {3, 34, 4, 12, 5, 2}, sum = 9
Output:  True  //There is a subset (4, 5) with sum 9.

*/

import java.util.Arrays;


public class SubsetSumDPPrac
{
    /**/
    
    public static boolean subsetSum(int sum, int [] set)
    {
        if(sum < 0 || set == null) throw new IllegalArgumentException();

        for(int i: set)
        {
            if(i < 0) throw new IllegalArgumentException("element in set is < 0 -- " + i);
        }

        int N = set.length;
        int [][] sol = new int [N+1][sum+1];
        for(int iR = 0; iR <= N; iR++)
        {
            for(int iC = 0; iC <= sum; iC++)
            {
                sol[iR][iC] = -1;
            }
        }

        sol[0][sum] = 1;
        for(int iR = 0; iR < N; iR++)
        {
            for(int iC = 0; iC <= sum; iC++)
            {
                if(sol[iR][iC] == 1)
                    updateFrom(sol, iR, iC, set[iR]);
            }

        }

        return (sol[N][0] == 1);
    }


    public static void updateFrom(int [][] sol, int n, int X, int d)
    {
        int sum = sol[0].length;
        sol[n+1][X] = 1;
        if((X - d >=0) && (X - d < sum)) sol[n+1][X-d] = 1;

    }

    public static void main(String [] args)
    {
        runTest(4, new int [] {1,2,3});
        runTest(5, new int [] {2,4});
        runTest(5, new int [] {});
        runTest(9, new int [] {3, 34, 4, 12, 5, 2});
    }

    private static void runTest(int sum, int [] set)
    {
        System.out.println("Sum = " + sum + " set = " + Arrays.toString(set));
        System.out.println("subset available = " + SubsetSumDPPrac.subsetSum(sum, set));
    }

}

