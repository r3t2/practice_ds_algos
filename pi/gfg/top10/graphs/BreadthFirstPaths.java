/*
Breadth First Traversal or BFS for a Graph
2.3
Breadth First Traversal (or Search) for a graph is similar to Breadth First Traversal of a tree (See method 2 of this post). The only catch here is, unlike trees, graphs may contain cycles, so we may come to the same node again. To avoid processing a node more than once, we use a boolean visited array. For simplicity, it is assumed that all vertices are reachable from the starting vertex.
For example, in the following graph, we start traversal from vertex 2. When we come to vertex 0, we look for all adjacent vertices of it. 2 is also an adjacent vertex of 0. If we don’t mark visited vertices, then 2 will be processed again and it will become a non-terminating process. A Breadth First Traversal of the following graph is 2, 0, 3, 1.
*/


public class BreadthFirstPaths
{
    private int [] parent;
    private boolean marked [];

    public BreadthFirstPaths(DiGraph dg, int s)
    {
        int V = dg.V();
        parent = new int [V];
        marked = new boolean[V];

        for(int i=0; i<V; i++)
        {
            parent[i] = i;
            marked[i] = false;
        }


        runBFS(dg, s);


    }

    private void runBFS(DiGraph dg, int s)
    {
        Deque<Integer> q = new LinkedList<Integer> ();
        q.add(s);
        marked[s] = true;

        int v;

        while(!q.isEmpty())
        {
            v = q.remove();
            for(int w : dg.adj(v))
            {
                if(marked[w] == false)
                {
                    q.add(w);
                    marked[w] = true;
                    parent[w] = v; 
                }

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


    }
}

