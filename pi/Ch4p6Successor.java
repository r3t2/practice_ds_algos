public class Ch4p6Successor
{
    public static int successor(Node root, int t)
    {
        RetResult res = successor2(root, t);
        if(res != null && res.found == true) return res.key;
        else return -1; // need to define a better return value. Perhaps return a RetResult obj.
    }

    private static RetResult successor2(Node n, int t)
    {
        if(n == null) return new RetResult(false, -1);

        RetResult ret;
        if(n.key == t)
        {
            ret = new RetResult(false, -1);
            ret.found = n.right != null;
            if(n.right != null) ret.key = findMin(n.right); // find min in right subtree.

            return ret;
        }
        else if(t > n.key) ret = successor2(n.right, t);
        else //go left. last Node to go left on will be successor.
        {
            ret = successor2(n.left, t);
            if(ret.found == false)
            {
                ret.found = true;
                ret.key = n.key;
            }
        }
        return ret;
    }

    public static int findMin(Node n)
    {
        if(n == null) return -1;

        while(n.left != null) n = n.left;

        return n.key;
    }

    private static class RetResult
    {
        private boolean found;
        private int key;
        private RetResult(boolean found, int key)
        {
            this.found = found;
            this.key = key;
        }
    }
    private static class Node
    {
        private int key;
        private Node left;
        private Node right;
    }
}