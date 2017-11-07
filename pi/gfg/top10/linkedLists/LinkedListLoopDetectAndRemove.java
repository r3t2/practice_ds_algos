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

  /*
  1. Hypothetically number all the nodes in Linked List starting from 0.
  2. a ptr iterating through the list, after 1 step, will reach node numbered 1.
  3. 0 -> 1 -> 2 -> 3 -> .... -> m-1 -> m -> m+1 -> m+2 -> ... -> m+n-1 
                                        ^                           |
                                        |___________________________|
  4. number of nodes in the cycle = n.
  5. the slow pointer, after i moves (i>m), will reach a node numbered  m + (i - m)%n
  6. the fast pointer, after i moves (i>m), will reach a node numbered  m + ((i - m/2)*2)%n = m + (2i-m)%n
  7. Since they will meet at some node eventually, m+(i-m/2)%n = m+(2*i-m/2)%n ==> (i-m/2)%n = (2*i-m/2)%n = k (actually node will be numbered m + k but k relative to start of the loop)
  8. 2i-m = nx + k
  9. i-m = ny + k
  10. subtracting 2*step_9 - step_8, m+k = (x-2y)*n ==> m+k is a multiple of the loop length.
  11. if m+k = n, then m = n -k . n- k is number of the remaining nodes in the loop after traversing k nodes in the loop. 
  so if a ptr starts at head and moves m nodes, a ptr starting at k nodes into the loop will meet at the start of the loop.
  12. similarly, if m+k = 2*n, then m-n = n-k, i.e., if the pointer at node k (k is relative to start of the loop) and from head of the loop moved n positions,
  then node inside the loop is at still at k and ptr started from head has m-n nodes before reaching the start of the loop. 
  13. hence n-k steps will make the two ptrs meet at the start of the loop.
  14. Similarly extend the argument to multiples of n.
  */

  public static void detectAndRemoveLoop(Node head)
  {
    Node slow = head, fast = head;

    while(slow != null && fast != null && fast.next !=null)
    {
      slow = slow.next;
      fast = fast.next.next;
      if(slow == fast) removeLoop(head, fast);
    }

  }

  private static void removeLoop(Node slow, Node fast)
  {
    while(fast.next != slow.next)
    {
      fast = fast.next;
      slow = slow.next;
    }
    fast.next = null;
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

    System.out.println("input LL = " + printLL(head, len));
    System.out.println("has cycle = " + LinkedListLoopDetectAndRemove.detectLoop(head));
    
    System.out.println("removing cycle");
    LinkedListLoopDetectAndRemove.detectAndRemoveLoop(head);
    System.out.println("After removing cycle, detect cycle = " + LinkedListLoopDetectAndRemove.detectLoop(head));
    System.out.println("Linked list after removing cycle = " + printLL(head, len));
    System.out.printf("\n");
  }

  private static String printLL(Node head, int len)
  {
    Node temp = head;
    StringBuffer sb = new StringBuffer();
    sb.append("[");
    for(int i=0; i<2*len && temp!=null ; i++)
    {
      sb.append(temp.val +", ");
      temp = temp.next;
    }
    sb.append("]");
    return sb.toString();
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