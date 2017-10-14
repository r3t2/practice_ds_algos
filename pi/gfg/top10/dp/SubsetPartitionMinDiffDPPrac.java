/* Partition a set into two subsets such that the difference of subset sums is
minimum Given a set of integers, the task is to divide it into two sets S1 and
S2 such that the absolute difference between their sums is minimum. If there is
a set S with n elements, then if we assume Subset1 has m elements, Subset2 must
have n-m elements and the value of abs(sum(Subset1) – sum(Subset2)) should be
minimum. 
Example: 
Input:  arr[] = {1, 6, 11, 5}  
Output: 1 
Explanation: 
Subset1 = {1, 5, 6}, sum of Subset1 = 12  
Subset2 = {11}, sum of Subset2 = 11 */

import java.util.Arrays;

public class SubsetPartitionMinDiffDPPrac
{
    public static int subsetPartition(int [] arr)
    {
        int N = arr.length;
        int sum = 0;
        for(int i=0; i<N; i++) sum+= arr[i];

        /* int[0][*] corresponds to empty set. extra space but more clear*/

        int [][] sol = new int[N+1][sum+1];
        sol[0][0] = 1; // mark that empty set with sum 0 is possible.

        for(int iA = 0; iA < N; iA++)
        {
            for(int iS = 0; iS <= sum; iS++)
            {
                updateNext(sol, iA, iS, arr[iA]);
            }
        }

        int minDiff = Integer.MAX_VALUE;
        int minSum = -1;
        for(int i=0; i<=sum; i++)
        {
            if(sol[N][i] == 1 && (Math.abs(2*i-sum) < minDiff))
            {
                minDiff = Math.abs(2*i - sum);
                minSum = i;
            }
        }

        return minDiff;
    }

    public static void updateNext(int [][] sol, int iA, int iS, int x)
    {
        int sum = sol[0].length;
        if(sol[iA][iS] == 0) return;
        sol[iA+1][iS] = 1; // update the case where iA+1 is not selected.
        if(iS + x <= sum && iS + x >=0)sol[iA+1][iS+x] = 1;

    }

    public static void main(String [] args)
    {
        runTest(new int [] {1, 5, 2});
        runTest(new int [] {1, 6, 11, 5, 7});
        runTest(new int [] {1, 6, 11, 5});

    }

    private static void runTest(int [] x)
    {
        System.out.println("input = " + Arrays.toString(x));
        System.out.println("minDiff = " + SubsetPartitionMinDiffDPPrac.subsetPartition(x));
    }

}
