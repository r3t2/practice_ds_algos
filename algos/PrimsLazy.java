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
  private boolean [] marked;
  
  
  
  public PrimsLazy(EdgeWeightedGraph ewg)
  {
  
    V = ewg.V();
    
    marked = new boolean[V];
    for (int i=0; i<V; i++)
    {
      marked[i] = false;
    }
    
    uf = new UF(V);
    pq = new PriorityQueue<Edge> ();
    
    mstEdges = new ArrayList<Edge> ();
    
    for(int i=0; i<V; i++)
    {
      if(!marked[i])
      {
        mst(ewg, i);
      }
    }
    
  }
  
  
  private void mst(EdgeWeightedGraph ewg, int v)
  {
    int w;

    Edge minE;
    
    marked[v] = true;
    addAdjEdgesToPQ(ewg, v);

    
    while (!pq.isEmpty())
    {
      minE = pq.remove();
      v = minE.either(); w = minE.other(v);
      if(!uf.connected(v, w))
      {
        mstEdges.add(minE);
        uf.union(v, w);

        if(!marked[v])
        {
          marked[v] = true;
          addAdjEdgesToPQ(ewg, v);
        }

        if(!marked[w])
        {
          marked[w] = true;
          addAdjEdgesToPQ(ewg, w);
        }

      }
    }
  }

  private void addAdjEdgesToPQ(EdgeWeightedGraph ewg, int v)
  {
    int w;

    for(Edge e: ewg.adj(v))
    {
      w = e.other(v);

      if(!uf.connected(v, w))
      {
        pq.add(e);
      }
    }
    
  }
  
  
  public Iterable<Edge> edges()
  {
    return mstEdges;  
  }

}
