/*
find the last index of the last duplicate number in a sorted array 

ex 
input: 1,2,5,6,6,7,9 
output: 4(index)
https://careercup.com/question?id=5707987787186176
*/
import java.util.Arrays;
public class LastIdxLastDup
{
    public static int lastIdxLastDup(int [] x)
    {
        if(x.length == 0) return -1;
        if(x.length == 1) return 0;

        int lastDup = x[0];
        int lastDupLastIdx = 0;

        for(int i=1; i<x.length; i++)
        {
            if(x[i] == lastDup) lastDupLastIdx = i;
            else lastDup = x[i];
        }
        return lastDupLastIdx;
    }

    public static void main(String [] args)
    {
        runTest(new int [] {1,2,3});
        runTest(new int [] {1,1,1});
        runTest(new int [] {1,2,3,5,5,6});
        runTest(new int [] {});
        runTest(new int [] {1});
    }

    private static void runTest(int [] x)
    {
        System.out.println("input = " + Arrays.toString(x));
        System.out.println("last idx last duplicate = " + LastIdxLastDup.lastIdxLastDup(x) + "\n");

    }
}