public class Ch2p5SumLists
{
    public static Node sumListsForward(Node n1, Node n2)
    {
        if(n1 == null || n2 == null) return null;

        int l1 = len(n1), l2 = len(n2);

        if(l1 > l2)
        {
            n2 = prependZeros(n2, l1-l2);
        }
        else
        {
            n1 = prependZeros(n1, l2-l1);
        }

        RetNode nRet = sum(n1, n2);
        Node sumNode = nRet.node;
        if(nRet.carry != 0) sumNode = new Node(nRet.carry, sumNode);
        return sumNode; 
    }

    private static RetNode sum(Node n1, Node n2)
    {
        if(n1 == null || n2 == null) return new RetNode(0, null);

        RetNode ret = sum(n1.next, n2.next);
        int carry = (ret.carry + n1.d + n2.d)/10;
        int d = (ret.carry + n1.d + n2.d)%10;
        Node sumNode = new Node(d, ret.node);
        return new RetNode(carry, sumNode); 
    }

    private static class RetNode
    {
        private int carry;
        private Node node;
        private RetNode(int c, Node n)
        {
            this.carry = c;
            this.node = n;
        }
    }

    private static Node prependZeros(Node n, int lZeros)
    {
        int i = 0;

        while(i<lZeros)
        {
            n = new Node(0, n);
            i++;
        }
        return n;
    }

    private static int len(Node n)
    {
        int l = 0;
        while(n!=null)
        {
            l++;
            n = n.next;
        }

        return l;
    }

    private static class Node
    {
        int d = 0;
        Node next = null;
        private Node(int d, Node next)
        {
            this.d = d;
            this.next = next;
        }
    }
}