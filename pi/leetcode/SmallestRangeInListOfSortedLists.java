import java.util.PriorityQueue;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;
import java.util.HashSet;
/*
632. Smallest Range
You have k lists of sorted integers in ascending order. Find the smallest range that includes at least one number from each of the k lists.

We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.

Example 1:
Input:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
Output: [20,24]
Explanation: 
List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
List 2: [0, 9, 12, 20], 20 is in range [20,24].
List 3: [5, 18, 22, 30], 22 is in range [20,24].
Note:
The given list may contain duplicates, so ascending order means >= here.
1 <= k <= 3500
-105 <= value of elements <= 105.
For Java users, please note that the input type has been changed to List<List<Integer>>. And after you reset the code template, you'll see this point.
*/
public class SmallestRangeInListOfSortedLists
{
    public static int[] smallestRange(List<List<Integer>> lists)
    {
        int numLists = lists.size();
        int [] listCnts = new int [numLists];
        int listIdx = 0;
        PriorityQueue<Node> minPQ = new PriorityQueue<Node> ();
        for(List<Integer> list: lists)
        {
            if(list.size() == 0) return new int [] {-1, -1};

            for(int i: list)
            {
                minPQ.add(new Node(i, listIdx));
            }
            listIdx++;
        }

        int minRange = Integer.MAX_VALUE;
        int L, R;
        int minL =-1, minR=-1;
        Node p, pl;
        Deque<Node> lVals = new LinkedList<Node> ();
        HashSet<Integer> zeroCnts = new HashSet<Integer>();
        for(int i=0; i<numLists; i++) zeroCnts.add(i);

        while(!minPQ.isEmpty())
        {
            p = minPQ.poll();
            lVals.addFirst(p);
            listCnts[p.listNum] += 1;
            if(zeroCnts.contains(p.listNum)) zeroCnts.remove(p.listNum);

            // while(validRange(listCnts))
            while(zeroCnts.size() == 0)
            {
                R = p.val;
                pl = lVals.removeLast();
                L = pl.val;
                if(R-L < minRange)
                {
                    minRange = R-L;
                    minL = L;
                    minR = R;
                }

                listCnts[pl.listNum] -= 1;
                if(listCnts[pl.listNum] == 0) zeroCnts.add(pl.listNum);
            }
        }

        return new int [] {minL, minR};
    }

    private static boolean validRange(int[] listCnts)
    {
        for(int i=0; i<listCnts.length; i++)
        {
            if(listCnts[i] == 0) return false;
        }

        return true;
    }

    private static class Node implements Comparable<Node>
    {
        private int val;
        private int listNum;

        private Node(int val, int listNum)
        {
            this.val = val;
            this.listNum = listNum;
        }

        public int compareTo(Node that)
        {
            if(this.val < that.val) return -1;
            if(this.val > that.val) return +1;
            return 0;
        }
    }

    public static void main(String [] args)
    {
        List<List<Integer>> l = new LinkedList<List<Integer>>();
        l.add(Arrays.asList(1,3,6,8));
        l.add(Arrays.asList(0,2,5,9));
        l.add(Arrays.asList(4,7,10));
        System.out.println(Arrays.toString(SmallestRangeInListOfSortedLists.smallestRange(l)));

        l = new LinkedList<List<Integer>>();
        l.add(Arrays.asList(1,3,6,9));
        l.add(Arrays.asList(0,2,5,9));
        l.add(Arrays.asList(4,7,10));
        System.out.println(Arrays.toString(SmallestRangeInListOfSortedLists.smallestRange(l)));
    }
}