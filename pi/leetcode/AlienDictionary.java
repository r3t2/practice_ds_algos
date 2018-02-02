import edu.princeton.cs.algs4.*;
import java.util.*;
public class AlienDictionary
{
    private static final int NUM_CHARS = 128;

    private Digraph g;
    private String [] words;
    public AlienDictionary(String [] sortedWords)
    {
        g = new Digraph(NUM_CHARS);
        this.words = sortedWords;

        createGraph(0, 0, sortedWords.length-1);

        //System.out.println(g.toString());
    }

    private void createGraph(int pos, int low, int high)
    {
        if(high - low == 0) return;
        int l = low;

        while(l < high)
        {
            while(words[l].length() <= pos) l++;
            int h = l;
            while(h <= l && words[l].charAt(pos) == words[h].charAt(pos)) h++;

            createGraph(pos+1, l, h-1);
            if(h > high) return;
            g.addEdge((int) words[l].charAt(pos), (int) words[h].charAt(pos));
            l=h;
        }
    }

    public Iterable<Character> charOrder()
    {
        Deque<Integer> topoOrder = new LinkedList<Integer> ();

        boolean[] marked = new boolean[g.V()];
        Arrays.fill(marked, false);

        for(int i = (int)'a'; i<=(int)'d'; i++)
        {
            if(!marked[i])
            {
                topoDfs(i, marked, topoOrder);
            }
        }

        List<Character> charOrder = new ArrayList<Character> ();
        for(int i: topoOrder) charOrder.add((char) i);

        return charOrder;
    }

    private void topoDfs(int s, boolean [] marked, Deque<Integer> topoOrder)
    {
        // System.out.println("s = " + s);

        marked[s] = true;
        for(int i: g.adj(s))
        {
            // System.out.println("i = " + i);
            if(!marked[i])
            {
                topoDfs(i, marked, topoOrder);
            }
        }
        // System.out.println((char) s);   
        topoOrder.addFirst(s);
    }

    public static void main(String [] args)
    {
        String [] words = new String [] {"abc",   "bbd", "cab", "dab"};
        AlienDictionary ad = new AlienDictionary(words);
        System.out.println(ad.charOrder());

        words = new String [] {"dab", "bbd", "cab", "abc"};
        ad = new AlienDictionary(words);
        System.out.println(ad.charOrder());

        words = new String [] {"da", "dab", "dac", "cbc", "b", "a"};
        ad = new AlienDictionary(words);
        System.out.println(ad.charOrder());
    }
}