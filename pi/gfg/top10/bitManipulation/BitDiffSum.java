/*
http://www.geeksforgeeks.org/sum-of-bit-differences-among-all-pairs/
Sum of bit differences among all pairs
3.7
Given an integer array of n integers, find sum of bit differences in all pairs that can be formed from array elements. Bit difference of a pair (x, y) is count of different bits at same positions in binary representations of x and y. 
For example, bit difference for 2 and 7 is 2. Binary representation of 2 is 010 and 7 is 111 ( first and last bits differ in two numbers).

Examples:

Input: arr[] = {1, 2}
Output: 4
All pairs in array are (1, 1), (1, 2)
                       (2, 1), (2, 2)
Sum of bit differences = 0 + 2 +
                         2 + 0
                      = 4

Input:  arr[] = {1, 3, 5}
Output: 8
All pairs in array are (1, 1), (1, 3), (1, 5)
                       (3, 1), (3, 3) (3, 5),
                       (5, 1), (5, 3), (5, 5)
Sum of bit differences =  0 + 1 + 1 +
                          1 + 0 + 2 +
                          1 + 2 + 0 
                       = 8
*/
import java.util.Arrays;
public class BitDiffSum
{
    private static final int NUM_BITS = 4;

    public static int bitDiffSum(int [] x)
    {
        int [][] cnts = new int [2][NUM_BITS];

        for(int i=0; i<2; i++)
        {
            for(int j=0; j<NUM_BITS; j++)
            {
                cnts[i][j] = 0;
            }
        }

        for(int i=0; i<x.length; i++)
        {
            for(int j=0; j<NUM_BITS; j++)
            {
                cnts[bit(x[i], j)][j] = cnts[bit(x[i], j)][j] + 1;
            }
        }

        int sum = 0;
        for(int i=0; i<x.length;i++)
        {
            for(int j=0; j<NUM_BITS; j++)
            {
                int bp = 1-bit(x[i],j);
                sum = sum + cnts[bp][j];
            }
        }

        return sum;
    }

    private static int bit(int x, int j)
    {
        return (x & (1<<j)) >> j;
    }

    public static void main(String [] args)
    {
        runTest(new int [] {1,2});
        runTest(new int [] {1,3,5});
        runTest(new int [] {1,3,7});
        runTest(new int [] {1});
        runTest(new int [] {1,1,1,1});
        runTest(new int [] {});
    }

    public static void runTest(int [] x)
    {
        System.out.println("imput array = " + Arrays.toString(x));
        System.out.println("sum of bit differences = " + BitDiffSum.bitDiffSum(x));
    }
}