public class BinaryTree<K extends Comparable<K>, V>
{

  private Node root;

  public Iterable<Node> inorder()
  {
    List<Node> l = new LinkedList<Node> ();
    inorder(root);

    return l;
  }

  private void inorder(Node x, List<Node> l)
  {
    if(x == null)
      return;

    inorder(x.left, l);
    l.add(x); // append
    inorder(x.right, l);
  }

  public Iterable<Node> preorder()
  {
    List<Node> l = new LinkedList<Node>();
    preorder(root, l);
    return l;
  }

  private void preorder(Node x, List<Node> l)
  {
    if(x == null)
      return;

    l.add(x);
    preorder(x.left,l);
    preorder(x.right,l);
  }


  public void restore(List<Node> l)
  {
    
  }
  


  private class Node
  {
    K key;
    V val;
    Node left;
    Node right;
  }
}