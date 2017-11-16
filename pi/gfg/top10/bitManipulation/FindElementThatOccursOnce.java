/*
Find the element that appears once
4.2
Given an array where every element occurs three times, except one element which occurs only once. Find the element that occurs once. Expected time complexity is O(n) and O(1) extra space.
Examples:

Input: arr[] = {12, 1, 12, 3, 12, 1, 1, 2, 3, 3}
Output: 2
*/
import java.util.Arrays;
public class FindElementThatOccursOnce
{
    private static final int NUM_BITS = 8;
    public static int findElementOccursOnce(int [] x, int N)
    {
        int [] cnt = new int[NUM_BITS];

        for(int j=0; j<NUM_BITS; j++) cnt[j] = 0;

        for(int n: x)
        {
            for(int j=0; j<NUM_BITS; j++)
            {
                cnt[j] += bit(n, j);
            }
        }
        for(int j=0; j<NUM_BITS; j++) cnt[j] = cnt[j] % N;

        return convertToInt(cnt);
    }

    private static int convertToInt(int [] bits)
    {
        int acc = 0;
        for(int i=0; i < NUM_BITS; i++)
        {
            acc += bits[i]*Math.pow(2,i);
        }

        return acc;
    }

    private static int bit(int x, int pos)
    {
        return (x & (1<<pos)) >> pos;
    }

    public static void main(String [] args)
    {
        runTest(new int [] {12,12,4,4,8,12,4,8,8,1}, 3);
        runTest(new int [] {12,12,12,12}, 3);
        runTest(new int [] {12,12,12}, 3);
        runTest(new int [] {0}, 3);
    }

    private static void runTest(int [] x, int N)
    {
        System.out.println("input = " + Arrays.toString(x));
        System.out.println("N = " + N);
        System.out.println("Element that occurs once = " + FindElementThatOccursOnce.findElementOccursOnce(x, N) + "\n");
    }
}