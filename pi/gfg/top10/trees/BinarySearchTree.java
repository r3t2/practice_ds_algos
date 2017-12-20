import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

public class BinarySearchTree
{
    private static Random rand = new Random();
    private BinaryTreeNode root;

    public void insert(int key, int val)
    {
        root = insert(root, key, val);
    }

    private BinaryTreeNode insert(BinaryTreeNode n, int key, int val)
    {
        if(n == null) return new Node(key, val);

        if(n.key == key) n.val = val; // replace val
        else if(key < n.key) n.left = insert(n.left, key, val);
        else n.right = insert(n.right, key, val);

        return n;
    }

    public int lowestCommonAncestor(int v, int w)
    {
        if(root == null) throw new IllegalArgumentException();// correct exception?

        Deque<Integer> pathV = getPath(v);
        Deque<Integer> pathW = getPath(w);

        int prev = root.key;
        while(!pathV.isEmpty() && !pathW.isEmpty())
        {
            v = pathV.removeFirst();
            w = pathW.removeFirst();
            if(v != w) return prev;
            else prev = v;
        }

        return prev;
    }

    public Deque<Integer> getPath(int v)
    {
        Deque<Integer> path = new LinkedList<Integer> ();
        getPath(root, v, path);
        return path;
    }

    private void getPath(BinaryTreeNode n, int v, Deque<Integer> path)
    {
        if(n == null) return;

        path.addLast(n.key);

        if(v == n.key) return;
        else if(v < n.key) getPath(n.left, v, path);
        else getPath(n.right, v, path);
    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("level order = " + PrintBinaryTree.levelOrder(root).toString());
        sb.append("\nin order = " + PrintBinaryTree.inOrder(root).toString());

        return sb.toString();
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
        BinarySearchTree tree = new BinarySearchTree();
        int [] keys = new int[N];
        for(int i=0; i<N; i++)
        {
            int key = rand.nextInt(100)-50;
            keys[i] = key;
            tree.insert(key, 0);
        }
        int v = keys[rand.nextInt(N)];
        int w = keys[rand.nextInt(N)];


        System.out.println("tree = " + tree.toString());
        System.out.printf("lowest common ancestor of %d, %d = %d\n\n", v, w, tree.lowestCommonAncestor(v, w));
    }

    private static class Node extends BinaryTreeNode
    {
        // private int key;
        // private int val;

        // private Node left = null;
        // private Node right = null;

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