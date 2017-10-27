/*
Implementation of Kruskal’s MST algo.
*/
import java.util.Scanner;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
import edu.princeton.cs.algs4.UF;
import java.util.Arrays;

public class KruskalsMST
{
  private int V;
  private UF conn;
  private List<Edge> mst = new ArrayList<Edge> ();
  private double mstWeight = 0.0;

  public KruskalsMST(EdgeWeightedGraph ewg)
  {
    this.V = ewg.V();
    conn = new UF(V);

    mst(ewg);
  }

  private void mst(EdgeWeightedGraph ewg)
  {
    PriorityQueue<Edge> edgeSorted = new PriorityQueue<Edge> ();

    for(int i=0; i<V; i++)
    {
      for(Edge e: ewg.adj(i))
      {
        edgeSorted.add(e);
      }
    }
    Edge e;
    int v, w;
    while(!edgeSorted.isEmpty() && mst.size() < (V-1))
    {
      e = edgeSorted.remove();
      v = e.either(); w = e.other(v);
      if(! conn.connected(v, w))
      {
        conn.union(v, w);
        mst.add(e);
      }
    }

  }

  public Iterable<Edge> mst()
  {
    return mst;
  }


  public static void main(String[] args) throws Exception
  {
    System.out.println("args = " + Arrays.toString(args));
    EdgeWeightedGraph ewg = new EdgeWeightedGraph(new Scanner(new File(args[0])));

    System.out.println(ewg);

    KruskalsMST kmst = new KruskalsMST(ewg);

    System.out.println("MST::");
    for (Edge e: kmst.mst())
    {
      System.out.println(e);
    }
  }

}
