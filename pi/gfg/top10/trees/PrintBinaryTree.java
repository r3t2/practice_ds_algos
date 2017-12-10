import java.util.Deque;
import java.util.LinkedList;

public class PrintBinaryTree
{
    public static Iterable<Integer> inOrderKeys(BinaryTreeNode root)
    {
        Deque<Integer> keys = new LinkedList<Integer> ();
        inOrderKeys(root, keys);

        return keys;
    }

    private static void inOrderKeys(BinaryTreeNode n, Deque<Integer> keys)
    {
        if (n==null) return;

        inOrderKeys(n.left, keys);
        keys.addLast(n.key);
        inOrderKeys(n.right, keys);
    }

    public static Iterable<Integer> levelOrder(BinaryTreeNode root)
    {
        Deque<BinaryTreeNode> q = new LinkedList<BinaryTreeNode> ();
        Deque<Integer> keys = new LinkedList<Integer> ();

        q.addLast(root);
        while(!q.isEmpty())
        {
            BinaryTreeNode c = q.removeFirst();
            keys.addLast(c.key);
            if(c.left != null) q.addLast(c.left);
            if(c.right != null) q.addLast(c.right);
        }
        return keys;
    }

    public static Iterable<PrintOrderNode> inOrder(BinaryTreeNode root)
    {
        Deque<PrintOrderNode> keys = new LinkedList<PrintOrderNode> ();
        inOrder(root, keys, 0);
        return keys;
    }
    private static void inOrder(BinaryTreeNode n, Deque<PrintOrderNode> keys, int lvl)
    {
        if(n == null) return;

        inOrder(n.left, keys, lvl+1);
        keys.addLast(new PrintOrderNode(n.key, lvl));
        inOrder(n.right, keys, lvl+1);
    }

    public static Iterable<PrintOrderNode> preOrder(BinaryTreeNode root)
    {
        Deque<PrintOrderNode> keys = new LinkedList<PrintOrderNode> ();
        preOrder(root, keys, 0);
        return keys;
    }
    private static void preOrder(BinaryTreeNode n, Deque<PrintOrderNode> keys, int lvl)
    {
        if(n == null) return;

        keys.addLast(new PrintOrderNode(n.key, lvl+1));

        preOrder(n.left, keys, lvl+1);
        preOrder(n.right, keys, lvl+1);
    }

    public static Iterable<PrintOrderNode> postOrder(BinaryTreeNode root)
    {
        Deque<PrintOrderNode> keys = new LinkedList<PrintOrderNode> ();
        postOrder(root, keys, 0);
        return keys;
    }

    private static void postOrder(BinaryTreeNode n, Deque<PrintOrderNode> keys, int lvl)
    {
        if(n == null) return;

        postOrder(n.left, keys, lvl+1);
        postOrder(n.right, keys, lvl+1);

        keys.addLast(new PrintOrderNode(n.key, lvl));
    }
    
}