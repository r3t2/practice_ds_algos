import java.util.Arrays;

class NumArraySumRange {
/*
    Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

    Example:
    Given nums = [-2, 0, 3, -5, 2, -1]

    sumRange(0, 2) -> 1
    sumRange(2, 5) -> -1
    sumRange(0, 5) -> -3
    Note:
    You may assume that the array does not change.
    There are many calls to sumRange function.
*/

    /*
    1. many calls to sumRange?
    2. will the array change between the many calls?
    3. is there a concern of overflow? 
    4. any constraints on run-time/ space complexity? 
    */

    private int [] cumSum;
    public NumArraySumRange(int[] nums) {
        if(nums == null) throw new NullPointerException();

        cumSum = new int[nums.length];
        if(nums.length >= 1) cumSum[0] = nums[0];

        for(int i=1; i<nums.length; i++)
        {
            cumSum[i] = nums[i] + cumSum[i-1];
        }
    }
    
    public int sumRange(int i, int j) {
        if(j < i) throw new IllegalArgumentException();
        if(i < 0 || j < 0) throw new IllegalArgumentException();
        if(i >= cumSum.length || j >= cumSum.length) throw new IllegalArgumentException();

        if(i == 0) return cumSum[j];
        else return cumSum[j] - cumSum[i-1];
    }

    public static void main(String [] args)
    {
        runTest(new int[] {1,1,1,1,1});
        runTest(new int[] {1,2,3,4,5});
        runTest(new int[] {});
    }
    private static void runTest(int [] nums)
    {
        System.out.println("input = " + Arrays.toString(nums));
        NumArraySumRange n = new NumArraySumRange(nums);
        int i, j;
        i =  2; j = 4; System.out.printf("i = %d, j= %d, sumRange= %d\n\n", i, j, n.sumRange(i,j));
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */