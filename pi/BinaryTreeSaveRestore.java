import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;

public class BinaryTreeSaveRestore<K extends Comparable<K>>
{
  private Node root;

  public void restore(List<K> l)
  {
    int N = l.size()/2;
    List<K> lIn = new LinkedList<K> ();
    List<K> lPre = new LinkedList<K> ();

    int i = 0;
    for (K k: l)
    {
      if(i<N)
      {
        lIn.add(k);
      }
      else
      {
        lPre.add(k);
      }

      i++;
    }

    root = restore(lIn, lPre, 0, N-1);
  }

  private Node restore(List<K> lIn, List<K> lPre, int l, int r)
  {
    if(r < l)
    {
      return null;
    }

    /* I would have used a Deque instead but staying true to problem statement*/
    K nRKey = lPre.remove(0);
    Node nRNode = new Node(nRKey);
    int nRIdx = lIn.indexOf(nRKey);

    nRNode.left = restore(lIn, lPre, l, nRIdx-1);
    nRNode.right = restore(lIn, lPre, nRIdx+1, r);

    // System.out.println("nRNode.key = " + nRNode.key + ", nRNode.left = " + nRNode.left + ", nRNode.right = " + nRNode.right);

    return nRNode;


  }

  public List<K> save()
  {
    List<K> lIn = inorder();
    List<K> lPre = preorder();

    lIn.addAll(lPre);
    return lIn;
  }

  public List<K> inorder()
  {
    List<K> l = new LinkedList<K> ();
    inorder(root, l);
    return l;
  }

  private void inorder(Node x, List<K> l)
  {
    if(x == null)
      return;

    inorder(x.left, l);
    l.add(x.key);
    inorder(x.right, l);

  }

  public List<K> preorder()
  {
    List<K> l = new LinkedList<K> ();
    preorder(root, l);
    return l;
  }

  private void preorder(Node x, List<K> l)
  {

    if(x == null)
      return;

    l.add(x.key);
    preorder(x.left, l);
    preorder(x.right, l);

  }
  
  public boolean isMirror()
  {
    return isMirror(root.left, root.right);
  }
  private boolean isMirror(Node x, Node y)
  {
    if(x == null && y == null)
      return true;
    else if ((x == null && y != null) || (x != null && y == null))
      return false;
    else if (! x.key.equals(y.key))
      return false;

    /* x.key equals y.key */
    boolean ret1 = isMirror(x.left, y.right);
    boolean ret2 = isMirror(x.right, y.left);

    return ret1 & ret2;
  }

  private class Node
  {
    private K key;
    private Node left;
    private Node right;

    public Node(K key)
    {
      this.key = key;
      this.left = null;
      this.right = null;
    }

    public Node()
    {
      this.key = null;
      this.left = null;
      this.right = null;
    }
  }


  public static void main(String [] args)
  {
    LinkedList<Integer> lIn = new LinkedList<Integer>(); lIn.add(6); lIn.add(3); lIn.add(5); lIn.add(2);
    LinkedList<Integer> lPre = new LinkedList<Integer>(); lPre.add(6); lPre.add(5); lPre.add(3); lPre.add(2);

    System.out.println("lIn:: " + Arrays.toString(lIn.toArray()));
    System.out.println("lPre:: " + Arrays.toString(lPre.toArray()));

    lIn.addAll(lPre);

    BinaryTreeSaveRestore<Integer> b = new BinaryTreeSaveRestore<Integer> ();
    b.restore(lIn);

    int N = 4;

    List<Integer> lSave = b.save();
    List<Integer> lInOut = lSave.subList(0, N);
    List<Integer> lPreOut = lSave.subList(N, 2*N);

    System.out.println("lInOut:: " + Arrays.toString(lInOut.toArray()));
    System.out.println("lPreOut:: " + Arrays.toString(lPreOut.toArray()));


    /* test mirror */
    lIn = new LinkedList<Integer>(); lIn.add(20); lIn.add(50); lIn.add(30); lIn.add(60); lIn.add(30); lIn.add(50); lIn.add(20);
    lPre = new LinkedList<Integer>(); lIn.add(60); lIn.add(50); lIn.add(20); lIn.add(30); lIn.add(50); lIn.add(30); lIn.add(20);
    lIn.addAll(lPre);

    BinaryTreeSaveRestore<Integer> b2 = new BinaryTreeSaveRestore<Integer> ();
    b2.restore(lIn);
    System.out.println(b2.isMirror());


  }
}