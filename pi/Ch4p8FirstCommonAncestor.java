public class Ch4p8FirstCommonAncestor
{
    private Node root;

    public Ch4p8FirstCommonAncestor(int [] x)
    {
        if(x.length != 5) return;

        Node x0 = new Node(x[0], null, null);
        Node x2 = new Node(x[2], null, null);
        Node x1 = new Node(x[1], x0, x2);
        Node x4 = new Node(x[4], null, null);
        Node x3 = new Node(x[3], x1, x4);

        root = x3;
    }

    public int firstCommonAncestor(int t0, int t1)
    {
        RetNode ret = firstCommonAncestor(root, t0, t1);
        return ret.ancestor.key;

    }
    public RetNode firstCommonAncestor(Node n, int t0, int t1)
    {
        if(n == null) return new RetNode();

        RetNode ret = new RetNode();
        if(n.key == t0) ret.found0 = true;
        if(n.key == t1) ret.found1 = true;

        RetNode retL = firstCommonAncestor(n.left, t0, t1);
        RetNode retR = firstCommonAncestor(n.right, t0, t1);

        ret.found0 = ret.found0 | retL.found0 | retR.found0;
        ret.found1 = ret.found1 | retL.found1 | retR.found1;

        if(retL.ancestor != null) ret.ancestor = retL.ancestor;
        if(retR.ancestor != null) ret.ancestor = retR.ancestor;
        
        if(ret.found0 && ret.found1 && ret.ancestor == null) ret.ancestor = n;

        return ret;
    }

    private static class RetNode
    {
        private boolean found0 = false;
        private boolean found1 = false;
        private Node ancestor = null;
    }

    private static class Node
    {
        private int key;
        private Node left;
        private Node right;
        private Node(int k, Node left, Node right)
        {
            this.key = k;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String [] args)
    {
        runTest(new int [] {0,1,2,3,4}, 0, 3);
        runTest(new int [] {0,2,1,4,3}, 0, 2);
        runTest(new int [] {0,10,25,20,30}, 10, 25);
    }
    private static void runTest(int [] x, int t0, int t1)
    {
        Ch4p8FirstCommonAncestor tree = new Ch4p8FirstCommonAncestor(x);
        System.out.printf("common ancestor of t0 = %d, t1 = %d is %d\n", 
            t0, t1, tree.firstCommonAncestor(t0, t1));
    }

}