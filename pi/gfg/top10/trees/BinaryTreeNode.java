private static class BinaryTreeNode // not making this generic for now
{
    private int key;
    private int val;

    private BinaryTreeNode left;
    private BinaryTreeNode right;

    private BinaryTreeNode(int key, int val)
    {
        this.key = key;
        this.val = val;
    }

    private BinaryTreeNode(int key, int val, BinaryTreeNode left, BinaryTreeNode right)
    {
        this(key, val);
        this.left = left;
        this.right = right;
    }        
}