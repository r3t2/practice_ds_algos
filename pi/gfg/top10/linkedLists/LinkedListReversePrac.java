

public class LinkedListReversePrac
{
  private Node root = null;
  private int size;


  public LinkedListReversePrac()
  {
    // empty constructor
  }

  /* add at the end */
  public void add(int v)
  {
    Node n = new Node(v, null);

    if(root == null) { root = n; return; }

    Node i = root; 

    while(i.next != null) i = i.next;

    i.next = n;
    size++;

    return;
  }

  public void reverse(int k)
  {
    if(k <= 0) throw new IllegalArgumentException();

    Node revHead = new Node(0,null);
    Node revAddFirst = revHead;
    Node revAddFirstNextItr = null;

    Node i = root;
    Node n;
    int cnt = 0;
    while( i != null)
    {
      n = i;
      i = i.next;
      n.next = null;

      revAddFirst.next = addFirstHelper(revAddFirst.next, n);

      if(cnt == 0)
      {
        revAddFirstNextItr = revAddFirst.next;
      }

      cnt += 1;

      if(cnt == k)
      {
        revAddFirst = revAddFirstNextItr;
        cnt = 0;
      }


    }
    root = revHead.next;
  }

  public String toString()
  {
    StringBuffer sb = new StringBuffer();
    Node i = root;
    sb.append("[");
    while(i != null)
    {
      sb.append(i.val + ", ");
      i = i.next;
    }
    sb.append("]");

    return sb.toString();
  }


  private Node addFirstHelper(Node head, Node n)
  {
    n.next = head;
    return n;
  }

  public static void main(String [] args)
  {
    runTest(new int[] {1,2,3,4,5}, 3);
    runTest(new int[] {1,2,3,4,5}, 1);
    runTest(new int[] {1,2,3,4,5}, 5);
    runTest(new int[] {1,2,3,4,5}, 6);
  }

  private static void runTest(int [] x, int k)
  {
    LinkedListReversePrac l = new LinkedListReversePrac();
    for(int i: x) l.add(i);
    System.out.println("input = " + l);
    l.reverse(k);
    System.out.println("k = " + k + "\noutput = " + l);
    System.out.println("\n");
  }

  private static class Node
  {
    private int val;
    private Node next;
    private Node(int val, Node next)
    {
      this.val = val;
      this.next = next;
    }
  }
}
