/*
Implementation of Prim’s algo
*/

import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import edu.princeton.cs.algs4.UF;
import java.util.Scanner;
import java.io.File;
import java.util.Arrays;

public class PrimsMST
{
  private int V;
  private PriorityQueue<Node> pq;
  private UF conn;
  private boolean [] marked;

  private List<Edge> mst = new ArrayList<Edge>();
  private double mstWeight = 0.0;

  public PrimsMST(EdgeWeightedGraph ewg)
  {
    this.V = ewg.V();
    pq = new PriorityQueue<Node> ();
    conn = new UF(V);
    marked = new boolean[V];

    for(int i=0; i<V; i++)
    { 
      marked[i] = false;
    }

    for(int i=0; i<V; i++)
    {
      if(marked[i] == false)
      {
        mst(ewg, i);
      }
    }

  }

  private void mst(EdgeWeightedGraph ewg, int s)
  {
    marked[s] = true;
    for(Edge e: ewg.adj(s))
    {
      pq.add(new Node(s, e));
    }
    Node min;
    int v, w;
    while(!pq.isEmpty() && mst.size() < V-1)
    {
      min = pq.remove();
      v = min.v; w = min.e.other(v);
      if(!conn.connected(v, w))
      {
        marked[w] = true;
        conn.union(v, w);
        mst.add(min.e);
        mstWeight += min.e.weight();
        for(Edge e: ewg.adj(w))
        {
          pq.add(new Node(w, e));
        }
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

    PrimsMST pmst = new PrimsMST(ewg);

    System.out.println("MST::");
    for (Edge e: pmst.mst())
    {
      System.out.println(e);
    }
  }

  private static class Node implements Comparable<Node>
  {
    private int v;
    private Edge e;

    public Node(int v, Edge e)
    {
      this.v = v;
      this.e = e;
    }

    public int compareTo(Node that)
    {
      return this.e.compareTo(that.e);
    }

    public boolean equals(Node that)
    {
      if(this.getClass() != that.getClass()) return false;

      Node t = (Node) that;
      if(this.v == that.v && this.e.equals(that.e)) return true;
      else return false;
    }
  }

}
