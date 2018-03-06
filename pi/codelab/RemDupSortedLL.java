/*Given a sorted linked list, delete all duplicates such that each element appear only once.

For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.*/

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     public int val;
 *     public ListNode next;
 *     ListNode(int x) { val = x; next = null; }
 * }
 */
public class RemDupSortedLL {
    public ListNode deleteDuplicates(ListNode a) {
        if(a == null) return null;
        
        ListNode head = a;
        ListNode wPtr = a;
        ListNode rPtr = a.next;
        while(rPtr != null)
        {
            if(wPtr.val != rPtr.val)
            {
                wPtr.next = rPtr;
                wPtr = wPtr.next;
            }
            rPtr = rPtr.next;
        }
        wPtr.next = null;
        
        return head;
    }
    private static class ListNode
    {

      public int val;
      public ListNode next;
      ListNode(int x) { val = x; next = null; }
    }
}