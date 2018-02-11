public class Ch4p5CheckBinTreeIsBST
{

    public static boolean isBST(Node root)
    {
        if(root == null) throw new NullPointerException();

        return isBST(root, null, null);
    }
    private static boolean isBST(Node n, Node min, Node max)
    {
        if(n == null) return true;

        if(min != null && !(min.key <= n.key)) return false;
        if(max != null && !(max.key > n.key)) return false;

        boolean rL = isBST(n.left, min, n);
        boolean rR = isBST(n.right, n, max);

        return rL & rR;
    }
    public static boolean isBSTDoesntWork(Node root)
    {
        if(root == null) throw new NullPointerException();

        return isBST2DoesntWork(root);
    }
    private static boolean isBST2DoesntWork(Node n)
    {
        if(n == null) return true;

        if(n.left != null && !(n.key >= n.left.key)) return false;
        if(n.right != null && !(n.key < n.right.key)) return false;

        boolean retLeft = isBST2DoesntWork(n.left);
        boolean retRight = isBST2DoesntWork(n.right);

        return retLeft & retRight;

    }

    public static void main(String [] args)
    {
        runTest(new int [] {0,1,2,3,4});
        runTest(new int [] {0,2,1,4,3});
        runTest(new int [] {0,10,25,20,30});
    }
    private static void runTest(int [] x)
    {
        if(x.length != 5) return;

        Node x0 = new Node(x[0], null, null);
        Node x2 = new Node(x[2], null, null);
        Node x1 = new Node(x[1], x0, x2);
        Node x4 = new Node(x[4], null, null);
        Node x3 = new Node(x[3], x1, x4);

        System.out.printf("isBST              = %b\n", isBST(x3));
        System.out.printf("isBST Doesn't Work = %b\n\n", isBSTDoesntWork(x3));
    }

    private static class Node
    {
        private int key;
        private int val;
        private Node left;
        private Node right;

        private Node(int k, Node left, Node right)
        {
            this.key = k;
            this.left = left;
            this.right = right;
        }
    }
}