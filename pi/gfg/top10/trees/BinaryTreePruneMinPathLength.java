public class BinaryTreePruneMinPathLength
{
    private Node root;

    public void pruneMinPathLength(int k)
    {
        root = pruneMinPathLength(root, k, 1);
    }

    private Node pruneMinPathLength(Node n, int k, int p)
    {
        if(p > k) return n;

        if(n == null && p <= k) return null;

        n.left = prune(n.left, k, p+1);
        n.right = prune(n.right, k, p+1);

        if(p<k && n.left == null && n.right == null) return null;
        else return n;
    }

    private static class Node
    {
        private int key;
        private int val;
        private Node left;
        private Node right;
    }
}