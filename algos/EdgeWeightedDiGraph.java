import java.util.Collection;
import java.util.ArrayList;
import java.util.Scanner;


public class EdgeWeightedDiGraph
{
  private int V; //number of vertices in this graph
  private int E; //number of edges in this graph

  private Collection<DirectedEdge> [] adj;

  public EdgeWeightedDiGraph(int V)
  {
    //check for valid v
    this.V = V;
    this.E = 0;

    init_adj();
  }

  public EdgeWeightedDiGraph(Scanner sc)
  {
    /* error checking */
    
    V = sc.nextInt();
    int E_expected = sc.nextInt();
    
    init_adj();
    
    for (int i=0; i<E_expected; i++)
    {
      addEdge(sc.nextInt(), sc.nextInt(), sc.nextDouble());
    }
    
    /* if not EOF, then throw Exception*/

  }
  private void init_adj()
  {
    adj = (ArrayList<DirectedEdge> []) new ArrayList[V];

    for(int i=0; i<V; i++)
    {
      adj[i] = new ArrayList<DirectedEdge> ();
    }
  }

  /**
  * Return an iterable of outward edges from vertex v
  */
  public Iterable<DirectedEdge> adj(int v)
  {
    // check for valid v
    return adj[v];
  }

  public int V()
  {    
    return this.V;
  }
  public int E()
  {
    return this.E;
  }
  public void addEdge(int from, int to, double weight)
  {
    // check for valid from and to
    adj[from].add(new DirectedEdge(from, to, weight));

    this.E++;

  }
}