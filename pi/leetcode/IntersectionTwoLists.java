/* 160. Intersection of Two Linked Lists */
/*
Write a program to find the node at which the intersection of two singly linked lists begins.


For example, the following two linked lists:

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:     b1 → b2 → b3
begin to intersect at node c1.


Notes:

If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Your code should preferably run in O(n) time and use only O(1) memory.
*/
import java.util.HashSet;

public class IntersectionTwoLists
{
        /*based on discussions */
        /*original idea based on HashMap but takes O(N) extra space */
        /*Questions asks for O(1) space */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;

        HashSet<ListNode> set = new HashSet<ListNode> ();

        ListNode t = headA;

        while(t != null)
        {
            set.add(t);
            t = t.next;
        }

        t = headB;
        while(t != null)
        {
            if(set.contains(t)) return t;
            t = t.next;
        }

        return null;
    }
    
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB)
    {
        int lenA = length(headA), lenB = length(headB);
        
        while(lenA > lenB)
        {
            headA = headA.next;
            lenA--;
        }
        
        while(lenB > lenA)
        {
            headB = headB.next;
            lenB--;
        }
        
        while(headA != headB)
        {
            headA = headA.next;
            headB = headB.next;
        }
        
        return headA;
        
    }
    
    public int length(ListNode n)
    {
        int len = 0;
        while(n != null)
        {
            len++;
            n = n.next;
        }
        return len;
    }
    
    public class ListNode
    {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}