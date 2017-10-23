/*
Implementation of EdgeWeightedDiGraph
*/
import java.util.Collection;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class EdgeWeightedDiGraph
{
    private Collection<DirectedEdge> [] adj;
    private int V;
    private int E;

    public EdgeWeightedDiGraph(int V)
    {
        this.V = V;
        init();
    }

    public EdgeWeightedDiGraph(Scanner sc)
    {
        V = sc.nextInt();
        int E_exp = sc.nextInt();
        
        init();

        for(int i=0; i<E_exp; i++)
        {
            addEdge(sc.nextInt(), sc.nextInt(), sc.nextDouble());
        }
    }

    private void init()
    {
        adj = (ArrayList<DirectedEdge> []) new ArrayList[V];
        for(int i=0; i<V; i++)
        {
            adj[i] = new ArrayList<DirectedEdge>();
        }
    }

    public void addEdge(int v, int w, double weight)
    {
        adj[v].add(new DirectedEdge(v, w, weight));
        E++;
    }

    public Iterable<DirectedEdge> adj(int v)
    {
        return adj[v];
    }

    public int V()
    {
        return V;
    }

    public int E()
    {
        return E;
    }

  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    sb.append("V = " + V + ", E = " + E + "\n");

    for(int i=0; i<V; i++)
    {
      for(DirectedEdge e: adj[i]) 
      {
        sb.append(e.toString() + "\n");
      }
    }
    return sb.toString();
  }

  public static void main(String[] args)
  {
    String fname = "../../../../testing/algs4-data/tinyEWD.txt";
    try{
    EdgeWeightedDiGraph ewg = new EdgeWeightedDiGraph(new Scanner(new File(fname)));
    System.out.println(ewg);}
    catch(Exception e){ e.printStackTrace();}
  }

}
