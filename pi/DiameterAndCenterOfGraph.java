//imports
import java.util.LinkedList;
import java.util.Deque;
import java.util.Collection;
import java.util.ArrayList;

public class DiameterAndCenterOfGraph
{
	private Integer [] distToF;
	private Integer [] distToR;

	private Integer [] edgeToF;
	private Integer [] edgeToR;

	private int V;
	private int E;

	private UndirectedGraph g; 

	private int diameter;
	private Iterable<Integer> diameterPath;
	private int center;

	public DiameterAndCenterOfGraph(UndirectedGraph g)
	{
		this.g = g;

		this.V = g.V();
		this.E = g.E();

		
		init();

		int maxDistF = runBFS(0, distToF, edgeToF);
		
		distToR[maxDistF] = 0;
		int maxDistR = runBFS(maxDistF, distToR, edgeToR);

		this.diameterPath = pathToSource(edgeToR, maxDistF, maxDistR);
		this.diameter = distToR[maxDistR];
	}

	private void init()
	{
		initArr(distToF, -1);
		distToF[0] = 0;

		initArr(distToR, -1);

		initArr(edgeToF, -1);
		initArr(edgeToR, -1);
	}

	private <T> void initArr(T[] arr, T val)
	{
		for(int i=0; i<arr.length; i++)
		{
			arr[i] = val;
		}
	}

	private int runBFS(int s, Integer [] distTo, Integer[] edgeTo)
	{
		Boolean [] marked = new Boolean[V];
		int u;
		int maxDistVer = s;

		initArr(marked, false);

		Deque<Integer> dq = new LinkedList<Integer> ();

		dq.add(s);

		while(!dq.isEmpty())
		{
			u = dq.remove();

			for (int v: g.adj(u))
			{
				if(!marked[v])
				{
					dq.add(v);
					distTo[v] = distTo[u] + 1;
					edgeTo[v] = u;

					if(distTo[v] > distTo[maxDistVer])
					{
						maxDistVer = v;
					}
				}
			}
		}

		return maxDistVer;

	}

	private Iterable<Integer> pathToSource(Integer [] edgeTo, int s, int d)
	{
		Deque<Integer> dq = new LinkedList<Integer> ();

		int i = d;

		while(edgeTo[i] != -1)
		{
			dq.add(i);
			i = edgeTo[i];
		}
		dq.add(i);

		return dq;
	}




	/* for the purpose of practice, use inner a Graph inner class
	Questions:
	Dense / sparse graph? Sparse.
	Directed / Undirected ? Undirected.
	*/

	public class UndirectedGraph
	{
		/* use adjacency list representation*/
		private Collection<Integer> adj[];

		private int V;

		private int E;

		public UndirectedGraph(int V)
		{
			adj = (ArrayList<Integer> []) new ArrayList[V];
			for(int i=0; i<adj.length; i++)
			{
				adj[i] = new ArrayList<Integer> ();
			}
			this.V = V;
		}

		public void addEdge(int u, int v)
		{
			adj[u].add(v);
			adj[v].add(u);
			this.E++;
		}

		/* adjacent vertices as an Iterable */
		public Iterable<Integer> adj(int v)
		{
			return adj[v];		
		}

		public int V()
		{
			return V;
		}

		public int E()
		{
			return E;
		}



	}
}