import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;


public class BusLinesFewestTransfers
{
    private HashMap<String, List<String>> line2Sta = new HashMap<String, List<String>> ();
    private HashMap<String, List<Integer>> sta2vertices = new HashMap<String, List<Integer>> ();

    private EdgeWeightedGraph g;

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
                prevStaIdx = currStaIdx;
                currStaIdx++;
            }
        }

    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer();

        sb.append("Line to Stations =\n" + line2Sta.toString() + "\n\n");
        sb.append("Station to Vertices = \n" + sta2vertices.toString() + "\n\n");
        sb.append("System Graph = \n" + g.toString() + "\n\n");

        return sb.toString();
    }

    public static void main(String [] args) throws Exception
    {
        Scanner sc = new Scanner(new File(args[0]));
        BusLinesFewestTransfers b = new BusLinesFewestTransfers(sc);
        System.out.println(b);
    }
}