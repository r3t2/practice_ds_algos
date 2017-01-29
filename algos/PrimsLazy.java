/*
* Implementation of Prim's algorithm -- lazy.
* 
* Constructor:
* PrimsLazy(EdgeWeightedGraph ewg) -- constructor accepts a edgeweighted graph and computes MST
* Iterable<Edges> edges() -- iterate over Edges
*/

/* imports */
import java.util.PriorityQueue;
import java.util.ArrayList;


public class PrimsLazy
{
  private int V;
  private int E;
  
  private UF uf;
  private PriorityQueue<Edge> pq;
  private ArrayList<Edge> mstEdges;
  
  
  
  public PrimsLazy(EdgeWeightedGraph ewg)
  {
  
    V = ewg.V();
    E = ewg.E();
    
    uf = new UF(V);
    pq = new PriorityQueue<Edge> ();
    
    mstEdges = new ArrayList<Edge> ();
    
  }
  
  private void mst(int )
  {
    
  }
  
  public Iterable<Edge> edges()
  {
  
  }

}
