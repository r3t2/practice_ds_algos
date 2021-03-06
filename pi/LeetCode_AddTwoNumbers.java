public class LeetCode_AddTwoNumbers
{
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2)
    {
        ListNode retHead = new ListNode(-1);
        ListNode reti = retHead;
        int rem;
        
        AddDigits a = new AddDigits();
        
        
        /* Assume that the number zero will also be a linked list. 
        Null linked list is not a valid number.
        */
        if(l1 == null || l2 == null)
        {
            return null;
        }
        
        ListNode l1i = l1, l2i = l2;
        while(l1i != null && l2i !=null)
        {
            rem = a.add(l1i.val, l2i.val);
            reti = insertNext(reti, rem);
            
            l1i = l1i.next;
            l2i = l2i.next;
            
        }
        while(l1i !=null)
        {
            rem = a.add(l1i.val, 0);
            reti = insertNext(reti, rem);
            l1i = l1i.next;
        }
        while(l2i != null)
        {
            rem = a.add(0, l2i.val);
            reti = insertNext(reti, rem);
            l2i = l2i.next;
        }
        
        if(a.carry != 0)
        {
            reti.next = new ListNode(a.carry);
        }
            
            
        
        
        return retHead.next;        
    }
    
    public static ListNode insertNext(ListNode reti, int val)
    {
        if(reti == null)
        {
            reti = new ListNode(val);
        }
        else
        {
            reti.next = new ListNode(val);
            reti = reti.next;
        }
        
        
        return reti;
    }
    
    
    
    public static void main(String[] args)
    {
        LinkedListMine ll1, ll2;
        ListNode ret;
        LinkedListMine retL;
        
        ll1 = new LinkedListMine(); ll2 = new LinkedListMine();
        ll1.insertTail(3); ll1.insertTail(4); ll1.insertTail(5);
        System.out.println(ll1);        
        ll2.insertTail(6); ll2.insertTail(0); ll2.insertTail(0);
        System.out.println(ll2);        
        ret = addTwoNumbers(ll1.head, ll2.head);
        retL = new LinkedListMine(ret);
        System.out.println(retL);
        
        ll1 = new LinkedListMine(); ll2 = new LinkedListMine();
        ll1.insertTail(9); ll1.insertTail(9); ll1.insertTail(9);
        System.out.println(ll1);        
        ll2.insertTail(9); ll2.insertTail(9); ll2.insertTail(0);
        System.out.println(ll2);        
        ret = addTwoNumbers(ll1.head, ll2.head);
        retL = new LinkedListMine(ret);
        System.out.println(retL);
    }
    
    public static class AddDigits
    {
        /* public for convenience */
        public int carry = 0;
        
        public int add(int a, int b)
        {
            int sum = a + b + carry;
            int rem = sum % 10;
            carry = sum / 10;
            
            return rem;
        }
    }
    
    
    /* A barebones version of LinkedList implementation to facilitate the testing of addTwoNumbers function.
    */
    public static class LinkedListMine{
        ListNode head = null;
        
        public LinkedListMine ()
        {
            this.head = null;
        }
        public LinkedListMine(ListNode head)
        {
            this.head = head;
        }
        public void insertTail(int val)
        {
            if(head == null)
            {
                head = new ListNode(val);
            }
            else
            {
                ListNode i = head;
                while(i.next != null)
                {
                    i = i.next;
                }
                i.next = new ListNode(val);
                
            }
        }
        
        public String toString()
        {
            StringBuilder sb = new StringBuilder();
            ListNode l = head;
            while(l != null)
            {
                sb.append(l.val + ", ");
                l = l.next;
            }
            
            return sb.toString();
        }
        
    }
    
    public static class ListNode
    {
        int val;
        ListNode next;
        ListNode(int x) { val = x; next = null; }
    }

}