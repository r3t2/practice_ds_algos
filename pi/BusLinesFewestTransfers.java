import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;


public class BusLinesFewestTransfers
{
    private HashMap<String, List<String>> line2Sta = new HashMap<String, List<String>> ();
    private HashMap<String, List<String>> sta2Line = new HashMap<String, List<String>> ();
    private HashMap<String, List<Integer>> sta2vertices = new HashMap<String, List<Integer>> ();
    private HashMap<Integer, StaLinePair> vertex2StaLine = new HashMap<Integer, StaLinePair> ();

    private EdgeWeightedGraph g;

    private double [] distTo;
    private int [] edgeTo;

    private PriorityQueue<PQNode> dijkstraPQ;

    public BusLinesFewestTransfers(Scanner sc)
    {
        if(sc == null) throw new IllegalArgumentException();

        int numLines = sc.nextInt();
        int numGVertices = 0; // number of graph vertices



        for(int n=0; n<numLines; n++)
        {
            String lineName = sc.next();
            int numSta = sc.nextInt();
            ArrayList<String> stations = new ArrayList<String> ();
            for(int s=0; s<numSta; s++)
            {
                String staName = sc.next();
                stations.add(staName);
                numGVertices++;

                List<String> linesAtSta = sta2Line.get(staName);
                if(linesAtSta == null)
                {
                    linesAtSta = new ArrayList<String> ();
                    sta2Line.put(staName, linesAtSta);
                }
                linesAtSta.add(lineName);

            }
            line2Sta.put(lineName, stations);
        }

        g = new EdgeWeightedGraph(numGVertices);

        int currStaIdx = 0;
        // for each line
        for(String line : line2Sta.keySet())
        {
            int prevStaIdx = -1;
            // for each station on the line
            for(String sta: line2Sta.get(line))
            {
                // get a list of all graph vertices associated with that station so far.
                List<Integer> staVertices = sta2vertices.get(sta);
                if(staVertices == null)
                {
                    staVertices = new ArrayList<Integer>();
                    sta2vertices.put(sta, staVertices);
                }

                for(int w: staVertices)
                {
                    /* add edge from one line to all other lines at a given station indicating a transfer is possible 
                    and costs a unit of 1 to transfer*/
                    g.addEdge(new Edge(currStaIdx, w, 1));
                }
                
                /* add edge from prev station to current station with weight of 0 indicating no transfer*/
                if(prevStaIdx != -1) g.addEdge(new Edge(currStaIdx, prevStaIdx, 0));

                staVertices.add(currStaIdx);
                vertex2StaLine.put(currStaIdx, new StaLinePair(sta, line));
                prevStaIdx = currStaIdx;
                currStaIdx++;
            }
        }

    }

    public Iterable<String> routeFewestTransfers(String source, String dest)
    {
        Iterable<Integer> pathInternal = routeFewestTransfers(sta2vertices.get(source), sta2vertices.get(dest));
        Deque<String> path = new LinkedList<String> ();

        for (int i: pathInternal)
        {
            StaLinePair s = vertex2StaLine.get(i);
            path.addLast(s.toString());
        }

        return path;

    }

    private Iterable<Integer> routeFewestTransfers(List<Integer> source, List<Integer> dest)
    {
        distTo = new double [g.V()];
        edgeTo = new int [g.V()];

        for(int i=0; i<g.V(); i++)
        {
            distTo[i] = Double.POSITIVE_INFINITY;
            edgeTo[i] = i;
        }

        dijkstraPQ = new PriorityQueue<PQNode> ();
        for(int i: source)
        {
            distTo[i] = 0;
            dijkstraPQ.add(new PQNode(0, i));
        }

        runDijkstra();

        return getPath(source, dest);
    }

    private Iterable<Integer> getPath(List<Integer> source, List<Integer> dest)
    {
        double minDist = Double.POSITIVE_INFINITY;
        int minIdx = -1;
        for(int d: dest)
        {
            if(minDist > distTo[d])
            {
                minDist = distTo[d];
                minIdx = d;
            }
        }

        Deque<Integer> path = new LinkedList<Integer> ();
        int v = minIdx;
        path.addFirst(v);

        while(v != edgeTo[v])
        {
            v = edgeTo[v];
            path.addFirst(v);
        }

        return path;
    }

    private void runDijkstra()
    {
        /* 
        while priority queue is not empty
        get min entry
        relax all edges 
        */

        while(!dijkstraPQ.isEmpty())
        {
            PQNode minNode = dijkstraPQ.poll();
            int v = minNode.v;
            for(Edge e: g.adj(v))
            {
                relaxEdge(e, v);
            }
        }

    }

    private void relaxEdge(Edge e, int v)
    {
        int w = e.other(v);
        if(distTo[w] > distTo[v] + e.weight())
        {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = v;
            dijkstraPQ.add(new PQNode(distTo[w], w));
        }
    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer();

        sb.append("Line to Stations =\n" + line2Sta.toString() + "\n\n");
        sb.append("Stations to Lines = \n" + sta2Line.toString() + "\n\n");
        sb.append("Station to Vertices = \n" + sta2vertices.toString() + "\n\n");
        sb.append("System Graph = \n" + g.toString() + "\n\n");

        return sb.toString();
    }

    public static void main(String [] args) throws Exception
    {
        Scanner sc = new Scanner(new File(args[0]));
        BusLinesFewestTransfers b = new BusLinesFewestTransfers(sc);
        System.out.println(b);
        runTest(b, "A", "F");
        runTest(b, "A", "G");
        runTest(b, "A", "D");
    }
    private static void runTest(BusLinesFewestTransfers b, String src, String dest)
    {
        System.out.printf("src = %s, dest = %s, path = %s\n\n",src, dest, b.routeFewestTransfers(src, dest));
    }

    private static class StaLinePair
    {
        String staName;
        String lineName;
        private StaLinePair(String sta, String line)
        {
            this.staName = sta;
            this.lineName = line;
        }
        public String toString()
        {
            return "(" + lineName + ", " + staName + ")";
        }
    }

    private static class PQNode implements Comparable<PQNode>
    {
        private double dist;
        private int v;
        private PQNode(double dist, int v)
        {
            this.dist = dist;
            this.v = v;
        }

        public int compareTo(PQNode that)
        {
            if(this.dist < that.dist) return -1;
            else if(this.dist > that.dist) return +1;
            else return 0;
        }
    }
}