import java.util.HashSet;

public class LinkedListUnionIntr
{
  private Node root;
  public static Node [] unionIntersect(Node l1, Node l2)
  {
    Node union = null;
    Node inter = null;

    HashSet<Integer> l1Hash = new HashSet<Integer> ();
    HashSet<Integer> l2Hash = new HashSet<Integer> ();

    Node i1 = l1;
    while(i1 != null) { l1Hash.add(i1.val); i1 = i1.next;}

    Node i2 = l2;
    while(i2 != null)
    {
      if(l1Hash.contains(i2.val) && !l2Hash.contains(i2.val))
      {
        Node n = new Node(i2.val, inter);
        inter = n;
        l2Hash.add(i2.val);
      }
      i2 = i2.next;
    }

    for(int i: l1Hash)
    {
      Node n = new Node(i, union);
      union = n;
    }
    for(int i: l2Hash)
    {
      Node n = new Node(i, union);
      union = n;
    }

    return new Node[] {union, inter};
  }

  public static Node add(Node f, int x)
  {
    return new Node(x, f);
  }

  private static String toString(Node n)
  {
    StringBuffer sb = new StringBuffer();
    
    sb.append("[");
    while(n != null)
    {
      sb.append(n.val + ", ");
      n = n.next;
    }
    sb.append("]");

    return sb.toString();
  }
  public static void main(String [] args)
  {
    runTest(new int [] {1,2,3,4}, new int [] {3,4,5});
  }
  private static void runTest(int [] a1, int [] a2)
  {
    Node l1 = null, l2 = null;
    for(int i : a1) l1 = add(l1, i);
    for(int i : a2) l2 = add(l2, i);
    Node [] ret = LinkedListUnionIntr.unionIntersect(l1, l2);

    System.out.println("input:: l1 = " + toString(l1));
    System.out.println("input:: l2 = " + toString(l2));
    System.out.println("input:: union = " + toString(ret[0]));
    System.out.println("input:: intersection = " + toString(ret[1]));
  }
  private static class Node
  {
    private Node(int val, Node next)
    {
      this.val = val;
      this.next = next;
    }

    private int val;
    private Node next;

  }
}
