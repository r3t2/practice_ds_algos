import java.util.*;
public class Ch2p6CheckPalindromeLL
{
    public static boolean isPalindrome(Node n)
    {
        if(n==null) return false;
        Deque<Integer> stack = new LinkedList<> ();
        int l = 0;
        Node head = n;

        while(n!=null)
        {
            stack.addFirst(n.k);
            l++;
            n = n.next;
        }

        int i=0;
        n = head;
        while(i < l/2)
        {
            int kp = stack.removeFirst();
            if(kp != n.k) return false;
            n = n.next;
            i++;
        }
        return true;
    }

    private static class Node
    {
        int k;
        Node next;
        private Node(int k, Node next)
        {
            this.k = k;
            this.next = next;
        }
    }


    public static void main(String [] args)
    {
        runTest(new int [] {1,2,3,4,3,2,1});
        runTest(new int [] {1,2,3,4,4,2,1});
        runTest(new int [] {2,2,3,4,3,2,1});
        runTest(new int [] {1,2,3,4,3,2,2});
        runTest(new int [] {});
    }

    private static void runTest(int [] x)
    {
        Node n = null;
        for(int i=x.length-1; i>=0; i--)
        {
            n = new Node(x[i], n);
        }
        System.out.printf("input = %s, palindrome = %b\n", 
            Arrays.toString(x), Ch2p6CheckPalindromeLL.isPalindrome(n));
    }
}