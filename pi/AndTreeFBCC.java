/*
Given a binary tree that complies to the following rule: 
The parent node value is always the the result of the AND operator of its children values. 

You modify one of the LEAF nodes value (e.g. if it was 1 it is now 0). Write a function that fixes the tree 
so it complies with the above rule.
//
//        0                                 1
//      /   \                             /   \
//    1      0            =====>         1     1
//   / \    / \                         / \   / \
//  1   1  0   1                       1   1 1   1
//
// The parent node value is always children value's LOGICAL AND
//   &
//
https://careercup.com/question?id=5765256088387584
*/

import java.util.Deque;
import java.util.LinkedList;


public class AndTreeFBCC
{
  private Node root = null;

  public AndTreeFBCC(boolean [] b)
  {
    insertLevelOrder(b);
    populateSize(root);
  }

  private void insertLevelOrder(boolean[] b)
  {
    if(b==null) throw new NullPointerException();
    if(b.length == 0) return;

    Deque<Node> dq = new LinkedList<Node> ();

    root = new Node(b[0]);
    dq.offer(root);

    Node n;
    int cnt = 1;
    while(!dq.isEmpty() && (cnt < b.length))
    {
      n = dq.remove();
      if(cnt < b.length)
      {
        n.left = new Node(b[cnt++]);
        dq.offer(n.left);
      }
      if(cnt < b.length)
      {
        n.right = new Node(b[cnt++]);
        dq.offer(n.right);
      }

    }
  }

  private int populateSize(Node n)
  {
    if(n == null) return 0;    

    int sl = populateSize(n.left);
    int sr = populateSize(n.right);

    n.size = sl + sr + 1;

    return n.size;
  }

  public void insert(boolean b)
  {
    root = insert(root, b);
  }

  private Node insert(Node n, boolean b)
  {
    if(n==null) return new Node(b);

    int sl = size(n.left), sr = size(n.right);

    if(sr < sl) n.right = insert(n.right, b);
    else n.left = insert(n.left, b);

    n.size++;

    return n;
  }

  private int size(Node n)
  {
    if(n == null) return 0;
    else return n.size;
  }


  public void fixAndTree()
  {
    fixAndTree(root);
  }

  private void fixAndTree(Node n)
  {
    if(n == null) return;

    if(n.left==null && n.right!=null) throw new IllegalArgumentException();
    if(n.left!=null && n.right==null) throw new IllegalArgumentException();

    fixAndTree(n.left);
    fixAndTree(n.right);

    if(n.left !=null && n.right != null)
      n.val = n.left.val & n.right.val;
  }

  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    
    Deque<Node> dq = new LinkedList<Node> ();
    if(root != null) dq.offer(root);

    Node n;
    while(!dq.isEmpty())
    {
      n = dq.remove();
      sb.append(n.val);
      sb.append(", ");
      if(n.left != null) dq.offer(n.left);
      if(n.right != null) dq.offer(n.right);
    }

    sb.append("]");

    return sb.toString();
  }

  public static void main(String [] args)
  {
    runTest(new boolean[]{false, true, false, true, false});
    runTest(new boolean[]{true, true, true, true, true});
  }

  private static void runTest(boolean [] b)
  {
    AndTreeFBCC at = new AndTreeFBCC(b);
    System.out.println(at);
    at.fixAndTree();
    System.out.println(at);
  }



  private class Node
  {

    private Node(boolean b)
    {
      this.size = 1;
      this.val = b;
      this.left = null;
      this.right = null;
    }

    private int size;
    private boolean val;
    private Node left;
    private Node right;
  }

}
