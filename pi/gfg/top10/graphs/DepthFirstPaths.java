/*
Implement DFS
*/
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;

public class DepthFirstPaths
{
    private boolean [] marked;
    private int [] parent;

    public DepthFirstPaths(DiGraph dg, int s)
    {
        int V = dg.V();

        marked = new boolean[V];
        parent = new int[V];

        for(int i=0; i< V; i++)
        {
            marked[i] = false;
            parent[i] = i;
        }

        marked[s] = true;
        dfs(dg, s);

    }

    private void dfs(DiGraph dg, int v)
    {
        for(int w: dg.adj(v))
        {
            if( marked[w] == false)
            {
                marked[w] = true;
                parent[w] = v;
                dfs(dg, w);
            }

        }

    }

    public boolean hasPathTo(int w)
    {
        return marked[w];
    }

    public Iterable<Integer> pathTo(int w)
    {
        Deque<Integer> path = new LinkedList<Integer> ();

        path.addFirst(w);

        while(w != parent[w])
        {
            w = parent[w];
            path.addFirst(w);
        }

        return path;
    }

    public static void main(String [] args)
    {
        runTest("../../../../testing/algs4-data/tinyDG.txt", 0, 2);
    }

    private static void runTest (String fname, int s, int d)
    {
        try{
            DiGraph dg = new DiGraph(new Scanner(new File(fname)));
            DepthFirstPaths bfs = new DepthFirstPaths(dg, s);
            System.out.println("path to "+ d + " = " + bfs.pathTo(d));}
            catch(Exception e){};
    }

}
