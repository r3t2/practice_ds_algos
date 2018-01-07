import java.util.Arrays;
public class MinimumMovesToEqualArraysElements
{
    public static int minMoves2(int[] nums) {
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
    }

    private static void runTest(int [] arr)
    {
        System.out.printf("input = %s, num moves = %d\n",Arrays.toString(arr), 
            MinimumMovesToEqualArraysElements.minMoves2(arr));
    }
}