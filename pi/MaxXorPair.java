import java.util.Arrays;

/*Problem statement: Given an array of integers, find a pair of numbers that will give the max xor value */

public class MaxXorPair
{
    public static final int NUM_BITS = 4;
    private int [] x;

    private TrieNode root;
    public MaxXorPair(int [] x)
    {
        this.x = x;
        for(int i=0; i<x.length; i++)
        {
            root = insert(root, x[i], i, NUM_BITS-1);
        }
    }

    public int maxXor(int k)
    {
        return x[getMax(root, k, NUM_BITS-1)];
    }

    private TrieNode insert(TrieNode n, int key, int val, int pos)
    {
        if(pos == -1) return null;

        int b = (key & (1<<pos)) >> pos;

        if(n==null) n = new TrieNode();

        if(pos == 0) n.val = val;

        n.next[b] = insert(n.next[b], key, val, pos-1);

        return n;
    }

    private int getMax(TrieNode n, int k, int pos)
    {
        if(pos == 0) return n.val;

        int b = (k & (1<<pos)) >> pos;
        int bp = 1-b;

        if(n.next[bp] != null) return getMax(n.next[bp], k, pos-1);
        else return getMax(n.next[b], k, pos-1);
    }

    public static void main(String [] args)
    {
        runTest(new int [] {1,2,3,4}, 4);
        runTest(new int [] {1,2,3,4}, 3);
        runTest(new int [] {1,2,3,4}, 6);
        runTest(new int [] {1,1,1,1}, 6);
        runTest(new int [] {}, 6);
    }

    private static void runTest(int [] x, int k)
    {
        System.out.println("input = " + Arrays.toString(x));
        System.out.println("query = " + k);
        MaxXorPair m = new MaxXorPair(x);
        System.out.println("max xor pair = " + m.maxXor(k));
    }

    private static class TrieNode
    {
        private int val;
        private TrieNode [] next = new TrieNode [2];
    }
}