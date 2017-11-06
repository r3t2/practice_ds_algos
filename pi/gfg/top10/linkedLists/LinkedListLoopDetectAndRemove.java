public class LinkedListLoopDetectAndRemove
{
  
  public static boolean detectLoop(Node head)
  {
    Node slow = head, fast = head;

    while(slow != null && fast != null && fast.next !=null)
    {
      slow = slow.next;
      fast = fast.next.next;
      if(slow == fast) return true;
    }

    return false;
  }

  public static void main(String [] args)
  {
    runTest(new int[] {0, 1, 2, 3, 4, 5}, true);
    runTest(new int[] {0, 1, 2, 3, 4, 5}, false);
  }

  private static void runTest(int [] arr, boolean addCycle)
  {
    int len = arr.length;
    Node head = null, last = null, temp = null;
    for(int i=0; i<len;i++)
    {
      head = new Node(i, head);
      if(i==0) last = head;
      if(i==len/2) temp = head;
    }
    
    if(addCycle == true)
    {
      // create loop
      last.next = temp;
    }

    temp = head;
    for(int i=0; i<2*len && temp!=null ; i++)
    {
      System.out.printf("%d, ", temp.val);
      temp = temp.next;
    }
    System.out.printf("\n");

    System.out.println("has cycle = " + LinkedListLoopDetectAndRemove.detectLoop(head));
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