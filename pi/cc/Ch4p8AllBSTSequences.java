import java.util.*;

public class Ch4p8AllBSTSequences
{
    private Node root;

    public List<Deque<Integer>> bstSeqs()
    {
        return bstSeqs(root);
    }

    private List<Deque<Integer>> bstSeqs(Node n)
    {
        if(n == null) return new ArrayList<Deque<Integer>>();

        List<Deque<Integer>> posL = bstSeqs(n.left);
        List<Deque<Integer>> posR = bstSeqs(n.right);


        List<Deque<Integer>> pos = new ArrayList<Deque<Integer>>();

        for(int i=0; i<posL.size(); i++)
        {
            for(int j=0; j<posR.size(); j++)
            {
                pos.addAll(generatePos(posL.get(i), posR.get(j)));
            }
        }

        return pos;
    }

    public List<Deque<Integer>> generatePos(Deque<Integer> s0, Deque<Integer> s1)
    {
        List<Deque<Integer>> pos = new ArrayList<Deque<Integer>>();
        if(s0.isEmpty())
        {
            Deque<Integer> s1Dup = new LinkedList<Integer> (s1);
            pos.add(s1Dup);
            return pos;
        }
        if(s1.isEmpty())
        {
            Deque<Integer> s0Dup = new LinkedList<Integer> (s0);
            pos.add(s0Dup);
            return pos;
        }

        int s0First = s0.removeFirst();
        List<Deque<Integer>> pos0 = generatePos(s0, s1);
        prepend(s0First, pos0);
        pos.addAll(pos0);
        s0.addFirst(s0First);

        int s1First = s1.removeFirst();
        List<Deque<Integer>> pos1 = generatePos(s0, s1);
        prepend(s1First, pos1);
        pos.addAll(pos1);
        s1.addFirst(s1First);

        return pos;

    }

    private void prepend(int x, List<Deque<Integer>> listPos)
    {
        for(Deque<Integer> pos : listPos)
        {
            pos.addFirst(x);
        }
    }


    public long numPos()
    {
        RetNodeNumPos ret = numPos(root);
        if(ret.size == 0L) return 0L;
        else return ret.numPos;
    }

    private RetNodeNumPos numPos(Node n)
    {
        if(n == null) return new RetNodeNumPos(0L, 1L);

        RetNodeNumPos numPosL = numPos(n.left);
        RetNodeNumPos numPosR = numPos(n.right);

        long size = numPosL.size + numPosR.size + 1;
        long num = nChooseK(numPosL.size + numPosR.size, numPosL.size) 
                    * numPosL.numPos 
                    * numPosR.numPos;

        return new RetNodeNumPos(size, num);

    }

    private long nChooseK(long n, long k)
    {
        // not implemented.
        return 1L;
    }

    private static class RetNodeNumPos
    {
        private long size;
        private long numPos;
        private RetNodeNumPos(long size, long numPos)
        {
            this.size = size;
            this.numPos = numPos;
        }
    }

    private static class Node
    {
        private int key;
        private Node left;
        private Node right;
        private Node(int key, Node left, Node right)
        {
            this.key = key;
            this.left = left;
            this.right = right;
        }
    }
}