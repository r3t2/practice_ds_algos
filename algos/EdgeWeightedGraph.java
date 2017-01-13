/*
* Class implements EdgeWeightedGraph
* 
* Constructor: 
* public EdgeWeightedGraph(int V) // Similar to Un/Directed Graph class.
*
* Methods:
* public addEdge(int v, int w, double weight) // add edge from v to w with weight
*
* public Iterable<Edge> adj(v) // An iterable of edges from v
*
* int V()
*
* int E()
* 
*/

/* imports */
import java.util.ArrayList;

public class EdgeWeightedGraph
{
  /* fields */
  private int V;
  
  private int E;
  
  private Collection<Edge> [] adj;
  
  public EdgeWeightedGraph(int V)
  {
    /* error checking */
    
    this.V = V;
    
    adj = (ArrayList<Edge>) ArrayList[V];
    for (int i=0; i<V; i++)
    {
      adj[i] = new ArrayList<Edge>();
    }
    
    this.E = 0;    
    
  }
  
  public EdgeWeightedGraph(Scanner sc)
  {
    /* error checking */
    
    V = sc.nextInt();
    E = sc.nextInt();
    
    EdgeWeightedGraph g = new EdgeWeightedGraph();
    
    for (int i=0; i<E; i++)
    {
      g.addEdge(sc.nextInt(), sc.nextInt(), sc.nextDouble());
    }
    
    /* if not EOF, then throw Exception*/
    
  }
  
  public addEdge(int v, int w, double weight)
  {
    Edge e = new Edge(v, w, weight);
    
    adj[v].add(e);
    addj[w].add(e);
    
    E++;
    
  }
  
  public Iterable<Edge> adj(int v)
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
}
