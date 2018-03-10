/*
Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note: The solution set must not contain duplicate triplets.

For example, given array S = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
*/
import java.util.*;

public class ThreeSumToZero
{
    public static List<List<Integer>> threeSum(int[] nums)
    {
        List<List<Integer>> res = new ArrayList<>();
        if(nums.length<=2) return res;

        // assuming we can change the input array
        Arrays.sort(nums);

        int i = 0;
        while(i<nums.length-2)
        {
            res.addAll(findTwoSum(nums, i));
            int curr_num = nums[i];
            while(i<nums.length-2 && curr_num == nums[i]) i++;
        }

        return res;
    }

    // assumption for this method: nums is already sorted.
    // i --> check from i+1 to end
    private static List<List<Integer>> findTwoSum(int [] nums, int i)
    {
        List<List<Integer>> res = new ArrayList<>();
        int lt = i+1, rt = nums.length-1;
        int t = -nums[i];
        while(rt>lt)
        {
            int s = nums[lt] + nums[rt];
            if(s == t)
            {
                List<Integer> l = new ArrayList<>();
                l.add(nums[i]);
                l.add(nums[lt]);
                l.add(nums[rt]);
                res.add(l);
            }
            if(s <= t)
            {
                int curr_num_lt = nums[lt];
                while(lt<rt && (nums[lt]==curr_num_lt)) lt++; //skip duplicates
            }
            else // s>t
            {
                int curr_num_rt = nums[rt];
                while(rt>lt && (nums[rt] == curr_num_rt)) rt--; //skip duplicates
            }
        }

        return res;
    }

    public static void main(String [] args)
    {
        runTest(new int[] {-1, 0, 1, 2, -1, 4});
        runTest(new int[] {-1, 0, 0, 0, 1, 1, -2});
        runTest(new int[] {});
        runTest(new int[] {2, 3});
        runTest(new int[] {-1, 2, -1});
        runTest(new int[] {-1, 1});
        runTest(new int[] {0,0,0});
    }
    private static void runTest(int [] in)
    {
        System.out.printf("input = %s\n", Arrays.toString(in));
        System.out.println("3 sum sets = " + threeSum(in) + "\n");
    }
}