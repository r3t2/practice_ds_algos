public class BinaryTreePruneMinPathLength
{
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
}