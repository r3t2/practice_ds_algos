import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

public class MinDepthBinaryTree
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

    public int minHeight()
    {
        return minHeight(root);
    }

    private int minHeight(Node n)
    {
        if(n == null) return 0;
        else return Math.min(minHeight(n.left), minHeight(n.right)) + 1;
    }

    public int maxHeight()
    {
        return maxHeight(root);
    }

    private int maxHeight(Node n)
    {
        if(n == null) return 0;
        else return Math.max(maxHeight(n.left), maxHeight(n.right)) + 1;
    }

    public int minHeight2()
    {
        if(root == null) return 0;

        Deque<MinHeightQNode> q = new LinkedList<MinHeightQNode> ();
        q.addLast(new MinHeightQNode(root, 0));
        while(!q.isEmpty())
        {
            MinHeightQNode m = q.removeFirst();
            if(m.node == null) return m.h;
            else
            {
                q.addLast(new MinHeightQNode(m.node.left, m.h+1)); 
                q.addLast(new MinHeightQNode(m.node.right, m.h+1));
            }
        }
        return -1;
    }



    public Iterable<Integer> levelOrder()
    {
        Deque<Node> q = new LinkedList<Node> ();
        Deque<Integer> keys = new LinkedList<Integer> ();

        q.addLast(root);
        while(!q.isEmpty())
        {
            Node c = q.removeFirst();
            keys.addLast(c.key);
            if(c.left != null) q.addLast(c.left);
            if(c.right != null) q.addLast(c.right);
        }
        return keys;
    }

    public Iterable<Integer> inOrder()
    {
        Deque<Integer> keys = new LinkedList<Integer> ();
        inOrder(root, keys);
        return keys;
    }
    private void inOrder(Node n, Deque<Integer> keys)
    {
        if(n == null) return;

        inOrder(n.left, keys);
        keys.addLast(n.key);
        inOrder(n.right, keys);
    }

    public Iterable<Integer> preOrder()
    {
        Deque<Integer> keys = new LinkedList<Integer> ();
        preOrder(root, keys);
        return keys;
    }
    private void preOrder(Node n, Deque<Integer> keys)
    {
        if(n == null) return;

        keys.addLast(n.key);

        preOrder(n.left, keys);
        preOrder(n.right, keys);
    }

    public Iterable<Integer> postOrder()
    {
        Deque<Integer> keys = new LinkedList<Integer> ();
        postOrder(root, keys);
        return keys;
    }

    private void postOrder(Node n, Deque<Integer> keys)
    {
        if(n == null) return;

        postOrder(n.left, keys);
        postOrder(n.right, keys);

        keys.addLast(n.key);
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
        MinDepthBinaryTree tree = new MinDepthBinaryTree();
        for(int i=0; i<N; i++)
        {
            tree.randomInsert(rand.nextInt(100), 0);
        }

        System.out.println("lvl  order = " + tree.levelOrder());
        System.out.println("in   order = " + tree.inOrder());
        System.out.println("pre  order = " + tree.preOrder());
        System.out.println("post order = " + tree.postOrder());
        System.out.println("minHeight recursive = " + tree.minHeight());
        System.out.println("minHeight iterative = " + tree.minHeight2());
        System.out.println("maxHeight recursive = " + tree.maxHeight());
        System.out.println("");
    }

    private static class MinHeightQNode
    {
        private Node node;
        private int h;
        private MinHeightQNode(Node n, int h)
        {
            this.node = n;
            this.h = h;
        }
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
}