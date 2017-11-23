import java.util.Arrays;
import java.util.HashMap;
/*
Find subarray with given sum | Set 2 (Handles Negative Numbers)
3.6
Given an unsorted array of integers, find a subarray which adds to a given number. If there are more than one subarrays with sum as the given number, print any of them.

Examples:

Input: arr[] = {1, 4, 20, 3, 10, 5}, sum = 33
Ouptut: Sum found between indexes 2 and 4

Input: arr[] = {10, 2, -2, -20, 10}, sum = -10
Ouptut: Sum found between indexes 0 to 3

Input: arr[] = {-10, 0, 2, -2, -20, 10}, sum = 20
Ouptut: No subarray with given sum exists

*/
public class SubarrayWithGivenSum
{
    /*
    sum of subarray from i:j = cum sum from 0:j - cum sum from 0:i
    if x is the target sum, then x = c[0:j] - c[0:i]
    c[0:i] = c[0:j] - x
    j is a running variable. keep computing the cum sum. 
    At every step check if there is a prefix cumsum if when removed yields subarray sum = x.
    */
    public static boolean subarrayWithGivenSum(int [] arr, int x)
    {
        HashMap<Integer, Integer> sum2IdxMap = new HashMap<Integer, Integer> ();

        int cumSum = 0;

        for(int i=0; i < arr.length; i++)
        {
            cumSum += arr[i];
            if(sum2IdxMap.containsKey(cumSum -x)) return true;
            sum2IdxMap.put(cumSum, i);
        }

        return false;
    }

    public static void main(String [] args)
    {
        runTest(new int [] {1, 4, 45, 6, 0, 19}, 51);
        runTest(new int [] {-1, -4, 45, 0, 6, 46}, 51);
        runTest(new int [] {1, -4, 45, 1, 6, 46}, 51);
        runTest(new int [] {1, 10, -5, 2, 7}, 9);
        runTest(new int [] {-1, 10, -5, 3, 7}, 9);
        runTest(new int [] {1, 11, -100, 1, 0, 200, 3, 2, 1, 250}, 280);
        runTest(new int [] {1, 2, 4}, 8);
        runTest(new int [] {}, 8);
    }
    private static void runTest(int [] arr, int x)
    {
        System.out.println("arr = " + Arrays.toString(arr) + ", x = " + x);
        System.out.println("output = " + SubarrayWithGivenSum.subarrayWithGivenSum(arr, x) + "\n");
    }
}