import java.util.Arrays;
public class InterpolationSearch
{

    public static int search(int [] x, int key)
    {
        return search(x, key, 0, x.length-1);
    }

    private static int search(int [] x, int key, int lo, int hi)
    {

        if(lo > hi) return -1;

        int mid;
        
        if(x[hi] == x[lo]) mid = hi;
        else mid = lo + (key - x[lo])*(hi-lo)/(x[hi] - x[lo]);

        if(mid < lo) mid = lo;
        if(mid > hi) mid = hi;

        if(key == x[mid]) return mid;
        else if(key < x[mid]) return search(x, key, lo, mid-1);
        else return search(x, key, mid+1, hi);
    }

    public static void main(String [] args)
    {
        runTest(new int [] {10, 11, 12, 13, 14, 15, 16}, 0);
        runTest(new int [] {10, 11, 12, 13, 14, 15, 16}, 11);
        runTest(new int [] {10, 11, 12, 13, 14, 15, 16}, 15);
        runTest(new int [] {10, 11, 12, 13, 14, 15, 16}, 20);
        runTest(new int [] {}, 0);
        runTest(new int [] {0}, 0);
        runTest(new int [] {0}, -1);
        runTest(new int [] {0}, 1);
    }

    private static void runTest(int [] arr, int key)
    {
        System.out.println("input = " + Arrays.toString(arr));
        System.out.println("key = " + key);
        System.out.println("index of key = " + InterpolationSearch.search(arr, key));
    }
}