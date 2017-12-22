/*
You have 4 cards each containing a number from 1 to 9. You need to judge whether they could operated through *, /, +, -, (, ) to get the value of 24.

Example 1:
Input: [4, 1, 8, 7]
Output: True
Explanation: (8-4) * (7-1) = 24
Example 2:
Input: [1, 2, 1, 2]
Output: False
*/
import java.util.Arrays;
public class Game24ArithmeticCombination
{
    private static final double eps = 0.000001;

    public static boolean judgePoint24(int [] nums)
    {
        double [] dNums = new double[nums.length];
        for(int i=0; i<nums.length; i++) dNums[i] = nums[i];

        return judgePoint24(dNums, 24.0);
    }

    public static boolean judgePoint24(double [] nums, double target)
    {
        if(nums.length == 1)
        {
            if(Math.abs(nums[0] - target) < eps) return true;
            else return false;
        }

        for(int i=0; i<nums.length-1; i++)
        {
            for(int j=i+1; j<nums.length; j++)
            {
                double x = nums[i];
                double y = nums[j];

                double [] temp = new double[] {x+y, x-y, x*y, x/y, y-x, y/x};

                for(double t: temp)
                {
                    double [] newArray = new double[nums.length-1];

                    newArray[0] = t;
                    int idx = 1;

                    for(int k=0; k<nums.length; k++)
                    {
                        if(k != i && k != j) newArray[idx++] = nums[k];
                    }

                    boolean ret = judgePoint24(newArray, target);
                    if(ret == true) return true;
                }
            }
        }
        return false;
    }

    public static void main(String [] args)
    {
        runTest(new double [] {4, 8, 1, 7}, 24);
        runTest(new double [] {1, 2, 1, 2}, 24);
        runTest(new double [] {1, 1, 1, 21}, 24);
        runTest(new double [] {1, 1, 1, 1}, 24);
        runTest(new double [] {4, 8, 1, 7}, 25);
    }

    public static void runTest(double [] nums, double target)
    {
        System.out.println("input = " + Arrays.toString(nums));
        System.out.printf("target = %f, reachable = %b\n\n", 
            target, Game24ArithmeticCombination.judgePoint24(nums, target));

    }
}