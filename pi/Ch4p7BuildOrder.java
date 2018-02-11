import java.util.*;
import edu.princeton.cs.algs4.Digraph;

public class Ch4p7BuildOrder
{
    private Map<String, Integer> proj2NodeIdx = new HashMap<String, Integer> ();
    private Map<Integer, String> nodeIdx2Proj = new HashMap<Integer, String> ();
    private Digraph g;

    public Ch4p7BuildOrder(String [] projs, String[][] deps)
    {
        g = new Digraph(projs.length);
        for(int i=0; i<projs.length; i++)
        {
            proj2NodeIdx.put(projs[i], i);
            nodeIdx2Proj.put(i, projs[i]);
        }

        for(String [] dep : deps)
        {
            g.addEdge(  proj2NodeIdx.get(dep[0]),
                        proj2NodeIdx.get(dep[1]));
        }
    }
    public Iterable<String> buildOrder()
    {
        Iterable<Integer> order = topoOrder();
        Deque<String> ret = new LinkedList<String>();

        for(int i: order)
        {
            ret.addLast(nodeIdx2Proj.get(i));
        }

        return ret;
    }

    private Iterable<Integer> topoOrder()
    {
        Deque<Integer> order = new LinkedList<Integer> ();
        boolean [] marked = new boolean[g.V()];
        Arrays.fill(marked, false);
        Set<Integer> callStack = new HashSet<Integer> ();

        for(int i=0; i<g.V(); i++)
        {
            if(marked[i] == false)
            {
                callStack.add(i);
                dfs(i, marked, order, callStack);
                callStack.remove(i);
            }
        }

        return order;
    }

    private void dfs(int v, boolean[] marked, Deque<Integer> order, Set<Integer> callStack)
    {
        marked[v] = true;

        for(int w: g.adj(v))
        {
            if(callStack.contains(w)) throw new IllegalArgumentException("cycle detected. build order not possible.");

            if(marked[w] == true) continue;

            callStack.add(w);
            dfs(w, marked, order, callStack);
            callStack.remove(w);
        }

        // done with this node
        order.addLast(v);
    }

    /*input:
    projects: a b c d e f
    dependencies: (a,d), (f,b), (b,d), (f,a), (d,c)
    */
    public static void main(String [] args)
    {
        runTest(new String[] {"a", "b", "c", "d", "e", "f"},
            new String[][] {{"d", "a"},{"f", "b"},{"b", "d"},{"f", "a"},{"d", "c"}});
        runTest(new String[] {"a", "b", "c", "d", "e", "f"},
            new String[][] {{"d", "a"},{"f", "b"},{"b", "d"},{"f", "a"},{"d", "c"}, {"c", "b"}});
    }

    private static void runTest(String [] projs, String [][] deps)
    {
        Ch4p7BuildOrder o = new Ch4p7BuildOrder(projs, deps);

        System.out.println(o.buildOrder());
    }
}