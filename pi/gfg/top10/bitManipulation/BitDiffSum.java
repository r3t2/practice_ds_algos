/*
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