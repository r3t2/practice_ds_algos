import java.util.Collection;
import java.util.ArrayList;
import java.util.Scanner;

public class RoadsAndLibrariesGraphPrac
{
    /*https://www.hackerrank.com/challenges/torque-and-development/problem*/

    /*
    Input Format

    The first line contains a single integer, , denoting the number of queries. The subsequent lines describe each query in the following format:

    The first line contains four space-separated integers describing the respective values of  (the number of cities),  (the number of roads),  (the cost to build a library), and  (the cost to repair a road).
    Each line  of the  subsequent lines contains two space-separated integers,  and , describing a bidirectional road connecting cities  and .
    */

    public static long getMinCost(Graph g, long libCost, long roadCost)
    {
        if(libCost < roadCost) return libCost * g.V();

        int numCC = numConnectedComponents(g);
        return numCC*libCost + ((g.V()-1) - (numCC-1))*roadCost;
    }

    private static int numConnectedComponents(Graph g)
    {
        boolean [] marked = new boolean[g.V()];
        for(int i=0; i<marked.length; i++) marked[i] = false;

        int cc = 0;
        for(int i=0; i<g.V(); i++)
        {
            if(!marked[i])
            {
                dfs(g, marked, i);
                cc++;
            }
        }
        return cc;
    }

    private static void dfs(Graph g, boolean [] marked, int v)
    {
        marked[v] = true;
        for(int w: g.adj(v))
        {
            if(!marked[w])
            {
                dfs(g, marked, w);
            }
        }
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            int n = in.nextInt();
            Graph g = new Graph(n);

            int m = in.nextInt();
            long x = in.nextLong();
            long y = in.nextLong();
            for(int a1 = 0; a1 < m; a1++){
                int city_1 = in.nextInt();
                int city_2 = in.nextInt();
                g.addEdge(city_1-1, city_2-1);
            }
            System.out.printf("%d\n",getMinCost(g, x, y));
        }
    }

    private static class Graph
    {
        private Collection<Integer> adj [];
        private int V;
        private int E;


        private Graph(int V)
        {
            adj = (ArrayList<Integer> []) new ArrayList[V];
            for(int i=0; i<V; i++) adj[i] = new ArrayList<Integer> ();
            this.V = V;
        }

        private Collection<Integer> adj(int v)
        {
            return adj[v];
        }

        private void addEdge(int v, int w)
        {
            adj[v].add(w);
            adj[w].add(v);
            this.E++;
        }

        private int V()
        {
            return V;
        }

        private int E()
        {
            return E;
        }

    }
}