import java.util.Arrays;
/*
Smallest subarray with sum greater than a given value
3.1
Given an array of integers and a number x, find the smallest subarray with sum greater than the given value.

Examples:
arr[] = {1, 4, 45, 6, 0, 19}
   x  =  51
Output: 3
Minimum length subarray is {4, 45, 6}

arr[] = {1, 10, 5, 2, 7}
   x  = 9
Output: 1
Minimum length subarray is {10}

arr[] = {1, 11, 100, 1, 0, 200, 3, 2, 1, 250}
    x = 280
Output: 4
Minimum length subarray is {100, 1, 0, 200}

arr[] = {1, 2, 4}
    x = 8
Output : Not Possible
Whole array sum is smaller than 8.
*/
public class SmallestSubArraySumGtX
{
    public static int smallestSubArraySumGtXPositive(int [] arr, int x)
    {
        int lt = 0, rt = 0;

        int sum = 0;
        int iEnd = 0, iStart = 0;

        int cnt = Integer.MAX_VALUE;

        for(iEnd=0; iEnd<arr.length; iEnd++)
        {
            sum += arr[iEnd];

            if(sum > x)
            {
                while(sum - arr[iStart] > x) 
                {
                    sum -= arr[iStart];
                    iStart++;
                    if(iEnd - iStart + 1 < cnt) { cnt = iEnd - iStart + 1; lt = iStart; rt = iEnd; }
                }
            }
        }

        return (cnt == Integer.MAX_VALUE)? 0:cnt;
    }

    public static void main(String [] args)
    {
        runTest(new int [] {1, 4, 45, 6, 0, 19}, 51);
        runTest(new int [] {1, 4, 45, 0, 6, 46}, 51);
        runTest(new int [] {1, 10, 5, 2, 7}, 9);
        runTest(new int [] {1, 11, 100, 1, 0, 200, 3, 2, 1, 250}, 280);
        runTest(new int [] {1, 2, 4}, 8);
    }

    private static void runTest(int [] arr, int x)
    {
        System.out.println("arr = " + Arrays.toString(arr) + ", x = " + x);
        System.out.println("output = " + SmallestSubArraySumGtX.smallestSubArraySumGtXPositive(arr, x));
    }
}