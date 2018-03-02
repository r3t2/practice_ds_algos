/*
in sorted array A, find i such that A[i] = i
1. distinct elements (soln below)
2. duplicates possible.
*/
import java.util.*;
public class Ch08p03MagicIndexAiEqI
{
    public static int magicIndex(int [] sortedArr)
    {
        if(sortedArr.length == 0) return -1;

        return magicIndex(sortedArr, 0, sortedArr.length-1);
    }

    private static int magicIndex(int [] a, int st, int end)
    {
        if(end < st) return -1;

        int mid = (st + end)/2;
        if(a[mid] == mid) return mid;
        else if(a[mid] > mid) return magicIndex(a, st, mid-1);
        else return magicIndex(a, mid+1, end);
    }

    public static void main(String [] args)
    {
        runTest(new int [] {0,1,2,3,4,5});
        runTest(new int [] {0,1,2,3,5,7,8,9});
        runTest(new int [] {2,3,5,7,8,9});
    }

    private static void runTest(int [] arr)
    {
        System.out.printf("input = %s\n, magicIndex = %d\n\n",
            Arrays.toString(arr), magicIndex(arr));
    }
}