/*
[1,1,1,2,3,1,1] -> [1,1,1,2,3,1,1]

[3,3,3,3] -> [1,1,1,1]

[-100, 0, 100] -> [1,2,3]
[100,0,50] -> [1, 0, 1]
[1,2,3,4,0,5] -> [1,2,3,4,3,4] ->[1,2,3,4,1,2]
[100,0,-100] -> [0,-1,-2]

[100, 200, 50, 300, 250] --> [1, 2, 1, 2, 1]

[ 5, 6, 7, 8, 7, 6, 5] -> 
[-1,-1,-1,-1,-1,-1,-1]
[ 1, 2, 3, 4, 3, 2, 1]

[-100, 0, 100]
[-1,-1,-1]
[1, 2, 3]

[ 1, 1, 1, 2, 3, 1, 1] ->
[ 1, 1, 1, 2, 3, 1, 1]

if(x[n] < x[n-1] && x[n] < x[n+1]) x[n] = 1
if(x[n] > one of x[n-1] and < another) x[n] = min(x[n+1], x[n-1]) +1
if(x[n] > both x[n-1] and x[n+1]) x[n] = max(x[n-1], x[n+1]) + 1


[1,2,3,4,5,4,3,2,1]
[1,2,3,4,5,1,1,1,1]
[1,2,3,4,5,4,3,2,1]

*/
import java.util.Arrays;

public class CandyNChildren
{
    public static int candy(int[] ratings)
    {
        int [] cnt = new int[ratings.length];
        for(int i=0; i<cnt.length; i++) cnt[i] = 1;

        
    }
    public static int candy2(int[] ratings)
    {
        Node [] vals = new Node[ratings.length];
        for(int i=0; i<ratings.length; i++)
        {
            vals[i] = new Node(ratings[i], i);
        }
        Arrays.sort(vals);

        int [] cnt = new int[ratings.length];
        for(int i=0; i<cnt.length; i++) cnt[i] = 1;

        for(Node val: vals)
        {
            int idx = val.idx;
            if(idx > 0 && ratings[idx] > ratings[idx-1])
                cnt[idx] = Math.max(cnt[idx-1]+1, cnt[idx]);

            if(idx < cnt.length-1 && ratings[idx] > ratings[idx+1])
                cnt[idx] = Math.max(cnt[idx+1]+1, cnt[idx]);
        }

        int res = 0;
        for(int i=0; i<cnt.length; i++)
        {
            res += cnt[i];
        }

        return res;
    }
    private static class Node implements Comparable<Node>
    {
        private int idx;
        private int val;

        private Node(int val, int idx)
        {
            this.val = val;
            this.idx = idx;
        }

        public int compareTo(Node that)
        {
            if(this.val < that.val) return -1;
            if(this.val > that.val) return +1;

            return 0;
        }
    }


    public static void main(String [] args)
    {
        runTest(new int [] {1,1,1,1,1});
        runTest(new int [] {1,2,3,4,3,2,1});
        runTest(new int [] {1,2,3,4});
        runTest(new int [] {1,2,1,4});
        runTest(new int [] {1,2,1,2,1,2});
    }

    private static void runTest(int [] x)
    {
        System.out.println("input = " + Arrays.toString(x));
        System.out.println("numCandy = " + CandyNChildren.candy2(x) + "\n");
    }
}