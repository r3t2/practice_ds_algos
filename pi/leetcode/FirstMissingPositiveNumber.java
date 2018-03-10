/*
Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.
*/
import java.util.*;
public class FirstMissingPositiveNumber
{
    public static int firstMissingPositive(int[] nums) {
        
        if(nums.length == 0) return 1;

        int i=0;
        while(i<nums.length)
        {
            if(nums[i] <=0 || nums[i] > nums.length || ((nums[i]-1) == i)) 
            { i++; continue;}

            int t = nums[nums[i]-1];
            if(t == nums[i]) {i++; continue;}
            nums[nums[i]-1] = nums[i];
            nums[i] = t;


        }

        i=0;
        while(i<nums.length && ((nums[i]-1) == i))
        {
            i++;
        }

        return (i+1);
    }

    public static void main(String [] args)
    {
        runTest(new int [] {1,2,3,4});
        runTest(new int [] {0,1,2,3});
        runTest(new int [] {0,1,2,4});
        runTest(new int [] {1,2,-1,4});
        runTest(new int [] {-1,-2,-3,4});
        runTest(new int [] {-1,-2,-3,1});
        runTest(new int [] {3,4,-1,1});
        runTest(new int [] {});
        runTest(new int [] {1,1});
    }

    private static void runTest(int [] x)
    {
        System.out.printf("input = %s\nfirst missing positive number = %d\n\n", 
            Arrays.toString(x), firstMissingPositive(x));
    }
}