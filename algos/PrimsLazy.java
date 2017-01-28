/*
* Implementation of Prim's algorithm -- lazy.
* 
* Constructor:
* PrimsLazy(EdgeWeightedGraph ewg) -- constructor accepts a edgeweighted graph and computes MST
* Iterable<Edges> edges() -- iterate over Edges
*/

/* imports */
import java.util.PriorityQueue;


public class PrimsLazy
{
  private int V;
  private int E;
  
  private UF uf;
  private PriorityQueue<Edge> pq;
  
  
  
  public PrimsLazy(EdgeWeightedGraph ewg)
  {
  
    V = ewg.V();
    E = ewg.E();
    
    uf = new UF(V);
    pq = new PriorityQueue<Edge> ();
    
  }
  
  public Iterable<Edge> edges()
  {
  
  }

}
