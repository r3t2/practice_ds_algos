import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class JumpGameII
{
    /*
        Given an array of non-negative integers, you are initially positioned at the first index of the array.

        Each element in the array represents your maximum jump length at that position.

        Your goal is to reach the last index in the minimum number of jumps.

        For example:
        Given array A = [2,3,1,1,4]

        The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)

        Note:
        You can assume that you can always reach the last index.
    */
    public static int jump(int [] jumps)
    {
        if(jumps == null) throw new NullPointerException();

        int [] cnt = new int [jumps.length];
        for(int i=0; i<cnt.length; i++) cnt[i] = -1;
        cnt[0] = 0;

        Deque<Integer> q = new LinkedList<Integer> ();

        q.addLast(0);

        while(!q.isEmpty())
        {
            int v = q.removeFirst();
            for(int i=1; i<=jumps[v]; i++)
            {
                if(v+i < cnt.length && cnt[v+i] == -1)
                {
                    cnt[v+i] = cnt[v] + 1;
                    q.addLast(v+i);
                }
            }
        }

        return cnt[cnt.length -1];
    }
    // O(N2) implementation
    public static int jumpON2(int [] jumps)
    {
        if(jumps == null) throw new NullPointerException();

        int [] cnt = new int [jumps.length];

        for(int i=0; i<cnt.length; i++) cnt[i] = Integer.MAX_VALUE;
        cnt[cnt.length-1] = 0;

        for(int i=cnt.length-2; i>=0; i--)
        {
            int minVal = Integer.MAX_VALUE;
            for(int j=i+1; j<=i+jumps[i] && j<cnt.length; j++)
            {
                int jumpVal = cnt[j] == Integer.MAX_VALUE ? cnt[j] : cnt[j]+1;
                if(minVal > jumpVal) minVal = jumpVal;
            }
            cnt[i] = minVal;
        }

        return cnt[0];
    }

    public static void main(String [] args)
    {
        runTest(new int [] {2,3,1,1,4});
        runTest(new int [] {5,5});
        runTest(new int [] {5});
        runTest(new int [] {5,5,5,5,5,5});
        runTest(new int [] {1,1,1,1,1});
        runTest(new int [] {1,1,0,1,1});
        runTest(new int [] {1,2,0,1});
    }

    private static void runTest(int [] arr)
    {
        System.out.printf("input = %s \n", Arrays.toString(arr));
        System.out.printf("min jumps = %d\n", JumpGameII.jumpON2(arr));
        System.out.printf("min jumps = %d\n\n", JumpGameII.jump(arr));
    }
}