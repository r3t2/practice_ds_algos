import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;

public class RandomLinkedListNode
{

  public static Node random(Node head)
  {
    /* two pass algo -- find the length, gen a rand idx between 0 and len-1 and return the node at that idx */
    
    /* Single pass algo -- use reservoir sampling*/

    Node rand = head;
    Node i = head;
    int cnt = 0;
    while(i != null)
    {
      int randIdx = (int) (Math.random() * (cnt+1));
      if(randIdx == 0) rand = i;
      i = i.next;
      cnt = cnt+1;
    }
    return rand;
  }
  public static void main(String [] args)
  {
    runTest(new int[] {1,2,3,4,5});
  }

  public static void runTest(int [] arr)
  {
    Node head = null;
    int maxVal = 0;
    for(int i: arr)
    {
      head = new Node(i, head);
      if(maxVal < i) maxVal = i;
    }


    
    int [] cnt = new int[maxVal+1];
    for(int i=0; i< cnt.length; i++) cnt[i] = 0;

    System.out.println("input = " + Arrays.toString(arr));
    //System.out.println("random node = " + RandomLinkedListNode.random(head));

    Node rand;
    for(int i=0; i<10000; i++)
    {
      rand = RandomLinkedListNode.random(head);
      cnt[rand.val] += 1;
    }

    System.out.println("freq of choosing each element = " + Arrays.toString(cnt));
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

    public String toString()
    {
      return "" + val;
    }


  }  
}