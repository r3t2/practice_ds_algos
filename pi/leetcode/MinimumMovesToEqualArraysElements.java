import java.util.Arrays;
public class MinimumMovesToEqualArraysElements
{
    public static int minMoves2(int [] x)
    {
        int median = quickSelect(x, x.length/2, 0, x.length-1);

        int cnt = 0;
        for(int i=0; i<x.length; i++)
        {
            cnt+= Math.abs(x[i] - median);
        }

        return cnt;
    }
    public static int quickSelect(int [] x, int tIdx, int st, int end)
    {
        // System.out.printf("tIdx = %d, st = %d, end = %d\n", tIdx, st, end);
        if(st == end) return x[st];
        if(st < 0 || end > x.length || st > end) throw new IllegalArgumentException();

        // int pos = partition(x, st, end);
        /* start 3 way partition */
        int pivot = x[st];
        int scan = st;
        int lt = st, rt = end;

        while(scan <= rt)
        {
            // System.out.printf("x = %s, scan = %d, lt = %d, rt = %d\n", Arrays.toString(x), scan, lt, rt);
            if(x[scan] < x[lt])
            {
                swap(x, scan, lt);
                scan++;
                lt++;
            }
            else if(x[scan] == pivot)
            {
                scan++;
            }
            else if(x[scan] > pivot)
            {
                swap(x, scan, rt);
                rt--;
            }
        }

        //if(pos == tIdx) return x[pos];
        if (tIdx >= lt && tIdx <= rt) return x[tIdx];
        else if(tIdx < lt) return quickSelect(x, tIdx, st, lt-1);
        else return quickSelect(x, tIdx, rt+1, end);
    }

    private static void swap(int [] x, int i, int j)
    {
        int t = x[i];
        x[i] = x[j];
        x[j] = t;
    }
    public static int minMoves2Incorrect(int[] nums) {
        float avg = 0;
        for(int i:nums)
        {
            avg += i;
        }
        avg = avg/nums.length;

        int mean = Math.round(avg);

        int moves = 0;
        for(int i:nums)
        {
            moves += Math.abs(mean - i);
        }

        return moves;
    }

    public static void main(String [] args)
    {
        runTest(new int [] {1,2,3});
        runTest(new int [] {1,100});
        runTest(new int [] {1,2,4,6});
        runTest(new int [] {1,2,4,5,6});
        runTest(new int [] {1,0,0,8,6});
        runTest(new int [] {1,0,0,0,8,6});
    }

    private static void runTest(int [] arr)
    {
        System.out.printf("input = %s, num moves = %d\n",Arrays.toString(arr), 
            MinimumMovesToEqualArraysElements.minMoves2(arr));
    }
}