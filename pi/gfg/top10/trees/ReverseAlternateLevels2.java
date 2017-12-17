/*

Reverse alternate levels of a perfect binary tree
3.5

Given a Perfect Binary Tree, reverse the alternate level nodes of the binary tree.

  
Given tree: 
               a
            /     \
           b       c
         /  \     /  \
        d    e    f    g
       / \  / \  / \  / \
       h  i j  k l  m  n  o 

Modified tree:
               a
            /     \
           c       b
         /  \     /  \
        d    e    f    g
       / \  / \  / \  / \
      o  n m  l k  j  i  h 
*/

import java.util.LinkedList;
import java.util.Deque;

public class ReverseAlternateLevels2
{


  private Node root;

  public void reverseAlternate()
  {
    Deque<Node> nodeQ = new LinkedList<Node> ();
    Deque<Integer> revQ = new LinkedList<Integer> ();

    nodeQ.addLast(root);

    while(true)
    {
      for(Node n: nodeQ)
      {
        if(n.left == null) return;

        revQ.addLast(n.left.key);
        revQ.addLast(n.right.key);
      }

      for(Node n: nodeQ)
      {
        n.left.key = revQ.removeLast();
        n.right.key = revQ.removeLast();
      }

      Deque<Node> tempQ = new LinkedList<Node> ();
      for(Node n: nodeQ)
      {
        if(n.left == null) return;
        tempQ.addLast(n.left);
        tempQ.addLast(n.right);
      }
      nodeQ.clear();

      for(Node n: tempQ)
      {
        if(n.left == null) return;
        nodeQ.addLast(n.left);
        nodeQ.addLast(n.right);
      }
    }
  }


  public static class Node
  {
    int key;
    int val;

    Node left;
    Node right;
  }
}