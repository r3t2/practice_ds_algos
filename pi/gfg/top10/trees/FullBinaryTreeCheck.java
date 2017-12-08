import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

public class FullBinaryTreeCheck
{

    private static Random rand = new Random();
    private Node root;

    public void randomButLevelInsert(int k, int v)
    {
        root = randomButLevelInsert(root, k, v);
    }

    private Node randomButLevelInsert(Node n, int k, int v)
    {
        if(n == null) return new Node(k, v);

        if((n.left == null) == (n.right == null))
        {
            if(rand.nextInt(2) == 0) n.left = randomButLevelInsert(n.left, k, v);
            else n.right = randomButLevelInsert(n.right, k, v);
        }
        else
        {
            if(n.left == null) n.left = randomButLevelInsert(n.left, k, v);
            else n.right = randomButLevelInsert(n.right, k, v);
        }

        return n;
    }

    public boolean isFullTree()
    {
        if(root == null) throw new NullPointerException();

        return isFullTree(root);
    }

    private boolean isFullTree(Node n)
    {
    
        if((n.left == null) != (n.right == null)) return false;

        boolean ret = true;
        if(n.left != null) ret = ret & isFullTree(n.left);
        if(n.right != null) ret = ret & isFullTree(n.right);

        return ret;
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

    public Iterable<PrintOrderNode> inOrder()
    {
        Deque<PrintOrderNode> keys = new LinkedList<PrintOrderNode> ();
        inOrder(root, keys, 0);
        return keys;
    }
    private void inOrder(Node n, Deque<PrintOrderNode> keys, int lvl)
    {
        if(n == null) return;

        inOrder(n.left, keys, lvl+1);
        keys.addLast(new PrintOrderNode(n.key, lvl));
        inOrder(n.right, keys, lvl+1);
    }

    public Iterable<PrintOrderNode> preOrder()
    {
        Deque<PrintOrderNode> keys = new LinkedList<PrintOrderNode> ();
        preOrder(root, keys, 0);
        return keys;
    }
    private void preOrder(Node n, Deque<PrintOrderNode> keys, int lvl)
    {
        if(n == null) return;

        keys.addLast(new PrintOrderNode(n.key, lvl+1));

        preOrder(n.left, keys, lvl+1);
        preOrder(n.right, keys, lvl+1);
    }

    public Iterable<PrintOrderNode> postOrder()
    {
        Deque<PrintOrderNode> keys = new LinkedList<PrintOrderNode> ();
        postOrder(root, keys, 0);
        return keys;
    }

    private void postOrder(Node n, Deque<PrintOrderNode> keys, int lvl)
    {
        if(n == null) return;

        postOrder(n.left, keys, lvl+1);
        postOrder(n.right, keys, lvl+1);

        keys.addLast(new PrintOrderNode(n.key, lvl));
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
        FullBinaryTreeCheck tree = new FullBinaryTreeCheck();
        for(int i=0; i<N; i++)
        {
            tree.randomButLevelInsert(rand.nextInt(100)-50, 0);
        }

        System.out.println("lvl  order = " + tree.levelOrder());
        System.out.println("in   order = " + tree.inOrder());
        System.out.println("pre  order = " + tree.preOrder());
        System.out.println("post order = " + tree.postOrder());
        System.out.println("");
        System.out.println("isFullTree  = " + tree.isFullTree());
        System.out.println("");
    }

    private static class Node
    {
        private int key;
        private int val;
        private Node left;
        private Node right;

        private Node(int key, int val)
        {
            this.key = key;
            this.val = val;
            left = null; right = null;
        }

        private Node(int key, int val, Node left, Node right)
        {   
            this(key, val);
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