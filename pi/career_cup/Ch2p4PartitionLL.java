public class Ch2p4PartitionLL
{
    public static Node partition(Node head, int p)
    {
        if(head == null) return null;
        if(head.next == null) return head;

        /*we have at least two nodes*/
        Node leftPtr = null, leftPtrW = null, rightPtr = null, rightPtrw = null;
        Node i = head;
        Node iNext;

        while(i != null)
        {
            iNext = i.next;
            if(i.k < p)
            {
                leftPtrW = addAfter(leftPtrW, i);
                if(leftPtr == null) leftPtr = leftPtrW;
            }
            else
            {
                rightPtrw = addAfter(rightPtrw, i);;
                if(rightPtr == null) rightPtr = rightPtrw;
            }
            i = iNext;
        }

        if(leftPtrW == null) return rightPtr;
        
        leftPtrW.next = rightPtr;
        return leftPtr;
    }

    private static Node addAfter(Node n, Node i)
    {
        if(n == null)
        {
            i.next = null;
        }
        else
        {
            i.next = n.next;
            n.next = i;
        }
        return i;
        
    }

    private static class Node
    {
        private int k;
        private Node next;
        private Node() {};
        private Node(int k, Node next)
        {
            this.k = k;
            this.next = next;
        }
    }


    public static void main(String [] args)
    {
        runTest(new int[]{5,4,4,6,2,1,7}, 4);
        runTest(new int[]{7,6,5,4,3,2,1}, 4);
        runTest(new int[]{}, 4);
        runTest(new int[]{7,6,5,4,3,2,1}, 8);
        runTest(new int[]{7,6,5,4,3,2,1}, 0);
    }

    private static String toString(Node n)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while(n!=null)
        {
            sb.append(n.k);
            sb.append(", ");
            n = n.next;
        }
        sb.append("]");
        return sb.toString();

    }

    private static void runTest(int [] x, int p)
    {
        Node n = null, head = null;

        for(int i: x)
        {
            if(n == null)
            {
                n = new Node(i, null);
                head = n;
            }
            else
            {
                n.next = new Node(i, null);
                n = n.next;
            }
        }

        System.out.printf("input = %s, partition = %d\n", toString(head), p);
        System.out.printf("partitioned output = %s\n", toString(Ch2p4PartitionLL.partition(head, p)));
    }
}