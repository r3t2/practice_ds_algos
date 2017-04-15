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
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;

public class PrimsLazy
{
  private int V;
  private int E;
  
  /* Union-Find data structure to determine connectivity between vertices */
  private UF uf;
  
  /* Priority Queue maintains the next edge to be considered for MST */
  private PriorityQueue<Edge> pq;

  /* ArrayList to maintain a list of edges in MST */
  private ArrayList<Edge> mstEdges;

  /* Maintain a array to know which vertices have already been visited */
  private boolean [] marked;
  
  
  
  public PrimsLazy(EdgeWeightedGraph ewg)
  {
  	/* Get the nubmer of vertices in the EWG */
    V = ewg.V();
    
    /*Initializations*/
    marked = new boolean[V];
    for (int i=0; i<V; i++)
    {
      marked[i] = false;
    }
    
    uf = new UF(V);
    pq = new PriorityQueue<Edge> ();
    
    mstEdges = new ArrayList<Edge> ();
    
    /* Call the MST algorithm from each of the unvisited vertices */
    for(int i=0; i<V; i++)
    {
      if(!marked[i])
      {
        mst(ewg, i);
      }
    }
    
  }
  
  
  private void mst(EdgeWeightedGraph ewg, int u)
  {
    int v, w;

    Edge minE;
    
    marked[u] = true;
    addAdjEdgesToPQ(ewg, u);

    
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
      printstr("v = " + v + ", w = "+ w);

      if((!uf.connected(v, w)) && (!pq.contains(e)))
      {
        pq.add(e);
      }
    }
    
  }
  
  
  public Iterable<Edge> edges()
  {
    return mstEdges;  
  }

  public static void main(String[] args) throws Exception
  {
    System.out.println("args = " + Arrays.toString(args));
    EdgeWeightedGraph ewg = new EdgeWeightedGraph(new Scanner(new File(args[0])));

    System.out.println(ewg);

    PrimsLazy pmst = new PrimsLazy(ewg);

    System.out.println("MST::");
    for (Edge e: pmst.edges())
    {
      System.out.println(e);
    }
  }

  private void printstr(String str)
  {
    System.out.println(str);
  }
}
