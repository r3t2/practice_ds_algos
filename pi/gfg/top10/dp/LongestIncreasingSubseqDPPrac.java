/*
The Longest Increasing Subsequence (LIS) problem is to find the length of the longest subsequence of a given sequence such that all elements of the subsequence are sorted in increasing order. For example, the length of LIS for {10, 22, 9, 33, 21, 50, 41, 60, 80} is 6 and LIS is {10, 22, 33, 50, 60, 80}.
*/
import java.util.LinkedList;
import java.util.Deque;
import java.util.Arrays;

public class LongestIncreasingSubseqDPPrac
{
    private static final int PARENT_TERM = -1;

    public static int [] lis(int [] x)
    {
        if (x == null) throw new IllegalArgumentException();

        int N = x.length;

        int [] max = new int [N];
        int [] parent = new int [N];

        int [] maxAtI;

        int maxGlb = 0;
        int maxGlbEndIdx = PARENT_TERM;

        for(int i=0; i<N; i++)
        {
            maxAtI = getMax(max, i, x);
            putMax(max, maxAtI[0]+1, i);
            putParent(parent, maxAtI[1], i);
            if(maxGlb < maxAtI[0]+1)
            {
                maxGlb = maxAtI[0]+1;
                maxGlbEndIdx = i;
            }
        }
        return getSubSeq(parent, x, maxGlbEndIdx);

    }

    private static int [] getSubSeq(int [] parent, int [] x, int idx)
    {
        if(idx == PARENT_TERM) return new int[] {};
        
        Deque<Integer> lisList = new LinkedList<Integer>();

        lisList.addFirst(x[idx]);

        while(parent[idx] != PARENT_TERM)
        {
            idx = parent[idx];
            lisList.addFirst(x[idx]);
        }

        //return lisList.toArray(new int[lisList.size()]);
        int [] ret = new int[lisList.size()];
        int cnt = 0;
        for(Integer i : lisList)
        {
            ret[cnt++] = i;
        }

        return ret;

    }

    private static int [] getMax(int [] max, int idx, int [] x)
    {
        int maxVal = 0;
        int maxValIdx = PARENT_TERM;

        for(int j=0; j<idx; j++)
        {
            if(x[j] <= x[idx] && maxVal < max[j])
            {
                maxVal = max[j];
                maxValIdx = j;
            }
        }

        return new int [] {maxVal, maxValIdx};
    }
    private static void putMax(int [] max, int val, int idx)
    {
        max[idx] = val;
    }
    private static void putParent(int [] parent, int val, int idx)
    {
        parent[idx] = val;
    }

    public static void main(String [] args)
    {
        runTest(new int[] {1,2,3,4});
        runTest(new int[] {4,3,2,1});
        runTest(new int[] {1,1,1,1});
        runTest(new int[] {10,22,9,33,21,50,41,60,80});
        runTest(new int[] {1});
        runTest(new int[] {});
        runTest(null);
    }

    private static void runTest(int [] x)
    {
        System.out.println("\ninput = " + Arrays.toString(x));
        System.out.println("Longest Non-Decreasing Subsequence = " 
            + Arrays.toString( LongestIncreasingSubseqDPPrac.lis(x)) );
    }

}
