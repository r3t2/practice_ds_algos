import java.util.Arrays;
/*
Find the smallest positive integer value that cannot be represented as sum of any subset of a given array
3.8
Given a sorted array (sorted in non-decreasing order) of positive numbers, find the smallest positive integer value that cannot be represented as sum of elements of any subset of given set. 
Expected time complexity is O(n).

Examples:

Input:  arr[] = {1, 3, 6, 10, 11, 15};
Output: 2

Input:  arr[] = {1, 1, 1, 1};
Output: 5

Input:  arr[] = {1, 1, 3, 4};
Output: 10

Input:  arr[] = {1, 2, 5, 10, 20, 40};
Output: 4

Input:  arr[] = {1, 2, 3, 4, 5, 6};
Output: 22
*/
public class SubsetSumSmallestValueNotPossible
{
    /*
    Using the same propogation lattice as subset sum but realizing that only the max element is necesarry.
    [    1,   2,   3,   4]
    0 -> 0 -> 0    
      -> 1 -> 1
           -> 2
           -> 3
                -> 4
                -> 5
                -> 6  <-- only the max element is needed to make a decision.

    */
    public static int smallestValueNot(int [] x)
    {
        int smallestValue = 0;

        int i = 0;

        while((i<x.length) && (x[i] - smallestValue <= 1))
        {
            smallestValue = smallestValue + x[i];
            i+=1;
        }

        return smallestValue+1;
    }

    public static void main(String [] args)
    {
        runTest(new int[] {1,2,3,4});

        runTest(new int[] {1,2,5});

        runTest(new int[] {1});

        runTest(new int[] {1,1,1,1});

        runTest(new int[] {});

        runTest(new int[] {1, 3, 6, 10, 11, 15});

        runTest(new int[] {1, 1, 3, 4});

        runTest(new int[] {1, 2, 3, 4, 5, 6});

    }

    public static void runTest(int [] x)
    {
        System.out.println("input = " + Arrays.toString(x));
        System.out.println("min value not represented = " + 
            SubsetSumSmallestValueNotPossible.smallestValueNot(x) + "\n");
    }
}