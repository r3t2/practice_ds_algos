/*
* Compute minimum spanning tree using Kruskal's algorithm.
* Constructor accepts an EdgeWeightedGraph and considers edges in ascending order of weight.
* An edge is added to MST if adding the edge does not create a cycle.
* Union-Find datastructure is used to test connectivity between two vertices. 
*
* public KruskalMST(EdgeWeightedGraph ewg)
* public KruskalMST(EdgeWeightedDiGraph ewdg)
*
* public Iterable<Edge> edges() //returns the edges in the MST
* 
*/

/* imports */
import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;

public class KruskalMST
{

  // Union Find data structure to determine if two vertices are already connected
  UF isConn;
  
  ArrayList<Edge> allEdges;
  
  ArrayList<Edge> mst;
  
  
  public KruskalMST(EdgeWeightedGraph ewg)
  {
    int V = ewg.V();
    int E = ewg.E();
    
    int i, v, w;
    
    allEdges = new ArrayList<Edge> ();
    mst = new ArrayList<Edge> ();
    
    isConn = new UF(V);
    
    
    for (i=0; i<V; i++)
    {
      for (Edge e: ewg.adj(i))
      {
        allEdges.add(e);
      }
    }
    
    Collections.sort(allEdges);
    
    
    for (Edge e: allEdges)
    {
      v = e.either(); w = e.other(v);
      
      if(!isConn.connected(v, w))
      {
        isConn.union(v,w);
        mst.add(e);
      }
    }
    
  }
  
  public Iterable<Edge> edges()
  {
    return mst;
  }
  
  public static void main(String[] args) throws Exception
  {
    System.out.println("args = " + Arrays.toString(args));
    EdgeWeightedGraph ewg = new EdgeWeightedGraph(new Scanner(new File(args[0])));

    System.out.println(ewg);

    KruskalMST kmst = new KruskalMST(ewg);

    System.out.println("MST::");
    for (Edge e: kmst.edges())
    {
      System.out.println(e);
    }
  }
}
