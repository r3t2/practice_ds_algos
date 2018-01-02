public class IntersectionTwoLists
{
        /*based on discussions */
        /*original idea based on HashMap but takes O(N) extra space */
        /*Questions asks for O(1) space */
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
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
}