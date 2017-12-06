public class MinDepthBinaryTree
{
    private Node root;

    public int minHeight()
    {
        return minHeightRec(root);
    }

    private int minHeight(Node n)
    {
        if(n == null) return 0;
        else return Math.min(minHeight(n.left), minHeight(n.right)) + 1;
    }

    public static class Node
    {
        private int key;
        private int val;

        private Node left = null;
        private Node right = null;

        public Node(int key, int val)
        {
            this.key = key;
            this.val = val;
        }

        public Node(int key, int val, Node left, Node right)
        {
            this.key = key;
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}