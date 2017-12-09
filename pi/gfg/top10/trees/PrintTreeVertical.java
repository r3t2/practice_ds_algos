import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class PrintTreeVertical
{
    private static Random rand = new Random();
    private Node root;


    public void randomInsert(int k, int v)
    {
        root = randomInsert(root, k, v);
    }

    private Node randomInsert(Node n, int k, int v)
    {
        if(n == null) return new Node(k, v);

        if(rand.nextInt(2) == 0) n.left = randomInsert(n.left, k, v);
        else n.right = randomInsert(n.right, k, v);

        return n;
    }

    public int span()
    {
        int [] span = new int[2];
        span[0] = 0; span[1] = 0;
        findSpan(root, 0, span);
        return span[1] - span[0] + 1;
    }

    private void findSpan(Node n, int pos, int [] span)
    {
        if(n == null) return;

        if(pos < span[0]) span[0] = pos;
        if(pos > span[1]) span[1] = pos;

        findSpan(n.left, pos-1, span);
        findSpan(n.right, pos+1, span);
    }
    public Iterable<Integer> topView()
    {
        int [] span = new int[2];
        span[0] = 0;
        span[1] = 0;
        findSpan(root, 0, span);

        Deque<Integer> [] nodes = (LinkedList<Integer> []) new LinkedList[span[1] - span[0] + 1];

        topView(root, -span[0], nodes);

        return null;
    }

    private void topView(Node n, int pos, Deque<Integer> [] nodes)
    {

    }

    public Iterable<Integer> bottomView()
    {
        return null;
    }

    public List<Integer> [] verticalOrder()
    {
        int [] span = new int[2];
        span[0] = 0;
        span[1] = 0;
        findSpan(root, 0, span);

        List<Integer> [] nodes = (ArrayList<Integer> []) new ArrayList[span[1] - span[0] + 1];
        for(int i=0; i<nodes.length; i++) nodes[i] = new ArrayList<Integer> ();
        verticalOrder(root, -span[0], nodes);
        return nodes;
    }

    private void verticalOrder(Node n, int pos, List<Integer> [] nodes)
    {
        if(n == null) return;

        nodes[pos].add(n.key);

        verticalOrder(n.left, pos-1, nodes);
        verticalOrder(n.right, pos+1, nodes);
    }

    public static void main(String [] args)
    {
        runTest(5);
        runTest(10);
        runTest(15);
        runTest(20);
        runTest(30);
    }

    private static void runTest(int N)
    {
        PrintTreeVertical tree = new PrintTreeVertical();
        for(int i=0; i<N; i++)
        {
            tree.randomInsert(rand.nextInt(100)-50, 0);
        }

        System.out.println("tree span = " + tree.span());
        
        List<Integer> [] nodes = tree.verticalOrder();
        StringBuffer sb = new StringBuffer();
        for(List<Integer> l : nodes)
        {
            sb.append(l.toString() + ", ");
        }
        System.out.println("vertical   order = " + sb.toString());
        System.out.println("");
    }

    private static class Node
    {
        private int key;
        private int val;

        private Node left = null;
        private Node right = null;

        private Node(int key, int val)
        {
            this.key = key;
            this.val = val;
        }

        private Node(int key, int val, Node left, Node right)
        {
            this.key = key;
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private static class PrintOrderNode
    {
        private int k;
        private int l;

        private PrintOrderNode(int k, int l)
        {
            this.k = k;
            this.l = l;
        }
        public String toString()
        {
            return "(" + k + ", " + l + ")";
        }
    }


    
}