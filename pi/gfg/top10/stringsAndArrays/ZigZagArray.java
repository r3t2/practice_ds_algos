import java.util.Arrays;
/*
onvert array into Zig-Zag fashion
2.4
Given an array of distinct elements, rearrange the elements of array in zig-zag fashion in O(n) time. The converted array should be in form a < b > c < d > e < f.

Example: 
Input:  arr[] = {4, 3, 7, 8, 6, 2, 1}
Output: arr[] = {3, 7, 4, 8, 2, 6, 1}

Input:  arr[] =  {1, 4, 3, 2}
Output: arr[] =  {1, 4, 2, 3}
*/
public class ZigZagArray
{
    public static int [] zigZagify(int [] x)
    {
        int [] z = Arrays.copyOf(x, x.length);

        for(int i=1; i<z.length; i++)
        {
            if(compare(z, i) == true) swap(z, i, i-1);
        }

        return z;
    }

    private static boolean compare(int [] z, int i)
    {
        if(i%2 == 1 && z[i] < z[i-1]) return true;
        else if(i%2 == 0 && z[i] > z[i-1]) return true;

        return false;
    }

    private static void swap(int [] z, int i, int j)
    {
        int temp = z[j];
        z[j] = z[i];
        z[i] = temp;
    }


    public static void main(String [] args)
    {
        runTest(new int [] {6, 4, 8, 9});
        runTest(new int [] {6});
        runTest(new int [] {6, 4});
        runTest(new int [] {1,2,3,4,5});
        runTest(new int [] {1,2,3,4});
        runTest(new int [] {4,3,2,1});
        runTest(new int [] {5,4,3,2,1});
        runTest(new int [] {});
    }

    private static void runTest(int [] x)
    {
        System.out.println("input = " + Arrays.toString(x));
        System.out.println("zig zag output = " + Arrays.toString(ZigZagArray.zigZagify(x)) + "\n");
    }
}