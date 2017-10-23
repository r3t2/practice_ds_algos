/* 
Implementation of an Edge Weighted Un Directed Graph
*/
import java.util.Scanner;
import java.util.Collection;
import java.util.ArrayList;
import java.io.File;

public class EdgeWeightedGraph
{
    private int V;
    private int E;
    private Collection<Edge> [] adj;
    public EdgeWeightedGraph(int V)
    {
        this.V = V;
        init();
    }

    public EdgeWeightedGraph(Scanner sc)
    {
        this.V = sc.nextInt();
        init();
        int E_exp = sc.nextInt();
        for(int i=0; i<E_exp; i++)
            addEdge(sc.nextInt(), sc.nextInt(), sc.nextDouble());
    }

    private void init()
    {
        adj = (ArrayList<Edge> []) new ArrayList [V];
        for(int i=0; i<V; i++)
            adj[i] = new ArrayList<Edge> ();

    }

    public void addEdge(int v, int w, double weight)
    {
        Edge e = new Edge(v, w, weight);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public int V() { return V; }

    public int E() { return E; }

    public Iterable<Edge> adj(int v)
    {
        return adj[v];
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("V = " + V + ", E = " + E + "\n");

        for(int i=0; i<V; i++)
        {
          for(Edge e: adj[i]) 
          {
            sb.append(e.toString() + "\n");
          }
        }
        return sb.toString();
    }

    public static void main(String[] args)
    {
        String fname = "../../../../testing/algs4-data/tinyEWG.txt";
        try{
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(new Scanner(new File(fname)));
        System.out.println(ewg);}
        catch(Exception e){ e.printStackTrace();}
    }


}
