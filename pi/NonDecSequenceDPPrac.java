/*Given a sequence of N numbers – A[1] , A[2] , …, A[N] . Find the length of the longest non-decreasing sequence.*/

import java.util.Arrays;

public class NonDecSequenceDPPrac
{
    public static int nonDecreasingSeq(int [] x)
    {
        int [] maxLen = new int [x.length];

        for(int i=0; i<x.length; i++) maxLen[i] = 0;

            maxLen[0] = 1;

        int max = Integer.MIN_VALUE;

        for(int i=1; i<x.length; i++)
        {
            maxLen[i] = (x[i] >= x[i-1]) ? maxLen[i-1] + 1 : 1;
            if(maxLen[i] > max) max = maxLen[i];
        }

        return max;
    }

    public static void main(String [] args)
    {
        runTest(new int[] {0,1,2,3,4});

        runTest(new int[] {0});

        runTest(new int[] {0, 1, 5, 4, 0, 1, 2, 3, 4, 0});

        runTest(new int[] {5, 4, 3, 2, 1});
    }

    private static void runTest(int [] x)
    {
        System.out.println("x[] = " + Arrays.toString(x));
        System.out.println("max = " + NonDecSequenceDPPrac.nonDecreasingSeq(x));
    }
}
