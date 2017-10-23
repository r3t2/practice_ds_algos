/*
Dijstra implementation
*/
import java.util.LinkedList;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.File;

public class DijkstraGraphPrac
{
    private int [] parent;
    private double [] distTo;
    private PriorityQueue<Node> vertices;

    public DijkstraGraphPrac(EdgeWeightedDiGraph ewg, int s)
    {
        int V = ewg.V();
        vertices = new PriorityQueue<Node> ();
        parent = new int[V];
        distTo = new double[V];

        for(int i=0; i<V; i++)
        {
            parent[i] = i;
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0;


        dijkstra(ewg, s);

    }

    private void dijkstra(EdgeWeightedDiGraph ewg, int s)
    {
        vertices.add(new Node(s, distTo[s]));
        int v;

        while(!vertices.isEmpty())
        {
            v = vertices.remove().vertex;
            for(DirectedEdge e: ewg.adj(v))
            {
                relaxEdge(e);
            }
        }
    }

    private void relaxEdge(DirectedEdge e)
    {
        int v = e.from();
        int w = e.to();
        double weight = e.weight();

        if(distTo[w] > distTo[v] + weight)
        {
            distTo[w] = distTo[v] + weight;
            parent[w] = v;
            vertices.remove(new Node(w, 0.0));
            vertices.add(new Node(w, distTo[w]));
        }

    }

    public boolean hasPathTo(int w)
    {
        return parent[w] != w;
    }

    public double distTo(int w)
    {
        return distTo[w];
    }

    public Iterable<Integer> pathTo(int w)
    {
        Deque<Integer> path = new LinkedList<Integer>();
        path.addFirst(w);

        while(w != parent[w])
        {
            w = parent[w];
            path.addFirst(w);
        }
        return path;
    }

    public static void main(String[] args)
    {
        String fname = "../../../../testing/algs4-data/tinyEWD.txt";
        try{
            EdgeWeightedDiGraph ewg = new EdgeWeightedDiGraph(new Scanner(new File(fname)));
            System.out.println(ewg);
            DijkstraGraphPrac dp = new DijkstraGraphPrac(ewg, 0);
            System.out.println(dp.distTo(6));
            System.out.println(dp.pathTo(6));

        }
            catch(Exception e){ e.printStackTrace(); }
    }


    private static class Node implements Comparable<Node>
    {
        private int vertex;
        private double distance;

        public Node(int v, double dist)
        {
            this.vertex = v;
            this.distance = dist;
        }

        public int compareTo(Node that)
        {
            if(this.distance < that.distance) return -1;
            else if(this.distance > that.distance) return +1;
            else return 0;
        }

        public boolean equals(Node that)
        {
            return this.vertex == that.vertex;
        }
    }
}
