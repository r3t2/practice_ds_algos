/* implement a BST class that implements insert, delete, contains & getRandomNode */
import java.util.*;

public class Ch4p11RandomTreeNode
{
    private Node root = null;
    private Random rand = new Random();

    // simplifying by not using an associate value
    public void insert(int k)
    {
        root = insert(root, k);
    }

    private Node insert(Node n, int k)
    {
        if(n==null)
        {
            n = new Node(k, 1, null, null);
            return n;
        }

        if(k <= n.key)  n.left = insert(n.left, k);
        else n.right = insert(n.right, k);

        n.size = size(n.left) + size(n.right) + 1;

        return n;
    }
    private int size(Node n)
    {
        if(n == null) return 0;
        else return n.size;
    }

    public int size()
    {
        return size(root);
    }

    public int getRandomKey()
    {
        if(root == null) throw new NoSuchElementException("getRandomKey called on empty tree");
        return getRandomKey(root);
    }

    private int getRandomKey(Node n)
    {
        if(n == null) return getRandomKey(root);

        int retSelect = rand.nextInt(n.size);
        if(retSelect == 0) return n.key;

        // if left subtree is null, then this will not have effect.
        // Because: if retSelect ==0, it wouldn't reach this point.
        if(retSelect <= size(n.left)) return getRandomKey(n.left);
        else return getRandomKey(n.right);
    }

    public boolean contains(int k)
    {
        return (contains(root, k) != null);
    }

    private Node contains(Node n, int k)
    {
        if(n == null) return null;

        if(n.key == k) return n;
        else if(k <= n.key) return contains(n.left, k);
        else return contains(n.right, k);
    }

    public void delete(int k)
    {
        if(root == null) throw new IllegalStateException("tree is empty");
        root = delete(root, k);
    }

    private Node delete(Node n, int k)
    {
        if(n==null) return null;

        if(n.key == k)
        {
            if(n.left == null && n.right == null) return null;
            if(n.left == null && n.right != null) return n.right;
            if(n.left != null && n.right == null) return n.left;
            n.key = getMin(n.right);
            n.right = deleteMin(n.right);

        }
        else if(k <= n.key) n.left = delete(n.left, k);
        else n.right = delete(n.right, k);

        n.size = size(n.left) + size(n.right) + 1;
        return n;
    }

    private int getMin(Node n)
    {
        if(n == null) throw new NullPointerException();

        while(n.left!=null) n = n.left;

        return n.key;
    }

    private Node deleteMin(Node n)
    {
        if(n == null) throw new NullPointerException();

        if(n.left == null) return n.right;
        else n.left = deleteMin(n.left);

        return n;
    }


    private static class Node
    {
        private int size; // subtree size
        private int key;
        private Node left;
        private Node right;
        private Node(int key, int size, Node left, Node right)
        {
            this.key = key;
            this.size = size;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String [] args)
    {
        runTest(10);
    }

    private static void runTest(int N)
    {
        Ch4p11RandomTreeNode tree = new Ch4p11RandomTreeNode();
        List<Integer> l = new ArrayList<Integer>();
        for(int i=0; i<N; i++) l.add(i);

        Collections.shuffle(l);

        for(int i: l) tree.insert(i);

        System.out.printf("size = %d, expected = %d\n", tree.size(), N);

        testRand(tree, N);

        int r;
        r = tree.getRandomKey(); tree.delete(r); System.out.printf("delete %d\n", r);
        r = tree.getRandomKey(); tree.delete(r); System.out.printf("delete %d\n", r);
        r = tree.getRandomKey(); tree.delete(r); System.out.printf("delete %d\n", r);
        System.out.printf("size = %d, expected = %d\n", tree.size(), N-3);

        testRand(tree, N);
    }

    private static void testRand(Ch4p11RandomTreeNode tree, int N)
    {
        System.out.println("testing uniformity of random key");
        int [] cnts = new int[N];

        for(int i=0; i<N*100; i++)
        {
            cnts[tree.getRandomKey()] += 1;
        }
        System.out.println(Arrays.toString(cnts));
    }
}