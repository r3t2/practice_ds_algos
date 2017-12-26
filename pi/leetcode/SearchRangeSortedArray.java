import java.util.Arrays;

public class SearchRangeSortedArray
{    
    public static int [] searchRange(int[] nums, int target)
    {
        if(nums == null) throw new NullPointerException();
        if(nums.length == 0) return (new int [] {});

        int [] range = new int [2];
        range[0] = findRangeStart(nums, target, 0, nums.length-1);
        range[1] = findRangeEnd(nums, target, 0, nums.length-1);

        return range;
    }

    private static int findRangeStart(int [] x, int t, int st, int end)
    {
        if(end < st) return -1; // no element found

        int mid = (st + end)/2;
        if(mid == st && x[mid] == t) return mid;
        if(x[mid]==t && x[mid-1]<t) return mid;

        if(t <= x[mid]) return findRangeStart(x, t, st, mid-1);
        else return findRangeStart(x, t, mid+1, end);
    }

    private static int findRangeEnd(int [] x, int t, int st, int end)
    {
        if(end < st) return -1; // no element found

        int mid = (st + end)/2;
        if(mid == (x.length-1) && x[mid] == t) return mid;
        if(x[mid]==t && x[mid+1]>t) return mid;

        if(t < x[mid]) return findRangeEnd(x, t, st, mid-1);
        else return findRangeEnd(x, t, mid+1, end);
    }
    
    public static void main(String [] args)
    {
        runTest(new int [] {0,1,2,3,4,5,5,5,5,9}, 5);
        runTest(new int [] {0,1,2,3,4,5,5,5,5,9}, 6);
        runTest(new int [] {0,1,2,2,2,2,5,5,5,9}, 2);
        runTest(new int [] {0,1,2,2,2,2,2,2,2,9}, 2);
        runTest(new int [] {0,1,2,2,2,3,4,5,6,7,8,9}, 2);
        runTest(new int [] {0,1,2,3,4,5,6,7,8,8,8,8,8,9}, 8);
        runTest(new int [] {0,1}, 1);
        runTest(new int [] {1,1,1,1,1,1}, 1);
        runTest(new int [] {}, 0);
    }

    private static void runTest(int [] x, int t)
    {
        System.out.println("input = " + Arrays.toString(x));
        System.out.printf("range of t = %d is %s \n\n", 
            t, Arrays.toString(SearchRangeSortedArray.searchRange(x, t)));
    }

}