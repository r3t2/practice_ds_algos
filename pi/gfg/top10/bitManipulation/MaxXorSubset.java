import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

/* Problem statement: Given an array of integers with possible duplicates, find a subset whose XOR between elements is maximum. Return the value*/

public class MaxXorSubset
{
    public static int maxXor(int [] x)
    {
        int max = 0;
        Set<Integer> s = new HashSet<Integer> ();
        s.add(0);
        int t;
        for(int v: x)
        {
            for(int w: new HashSet<Integer>(s))
            {
                t = v ^ w;
                if(!s.contains(t)) s.add(t);
                if(max < t) max = t;
            }
        }

        System.out.println("set size = " + s.size());
        return max;
    }

    public static void main(String [] args)
    {
        runTest(new int[] {1,2,3,4});
        runTest(new int[] {1,2,4});
        runTest(new int[] {1,2,4,8,16,32,64,128,256,512,1024});
        runTest(new int[] {1,2,4,8,10,16,31,32,62,64,100,128,200,256,400,512,750,1024});
    }

    private static void runTest(int [] x)
    {
        System.out.println("input = " + Arrays.toString(x));
        System.out.println("max xor val = " + MaxXorSubset.maxXor(x) + "\n");
    }
}