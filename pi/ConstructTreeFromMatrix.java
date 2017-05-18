import java.util.List;
import java.util.LinkedList;
import java.util.Deque;

public class ConstructTreeFromMatrix
{
	private Node root;


	public ConstructTreeFromMatrix(boolean[][] mat)
	{
		int N = mat[0].length;
		Node [] adjList = new Node[N];

		for(int i=0; i<N; i++)
		{
			for(int j=0; j<N; j++)
			{
				if(mat[i][j] == true)
				{
					addEdge(i, j, adjList);
				}
			}
		}

		root = findRoot(adjList);

	}

	private Node findRoot(Node [] adjList)
	{
		int N = adjList.length;
		/*find root by doing a topological sort*/

		Boolean [] marked = new Boolean[N];
		init(marked, false);

		Deque<Node> topoSorted = new LinkedList<Node> ();
		for(int i=0; i<N; i++)
		{
			topoSort(adjList, marked, i, topoSorted);
		}

		return topoSorted.getLast();

	}

	private void topoSort(Node[] adjList, Boolean[] marked, int v, Deque<Node> topoSorted)
	{
		marked[v] = true;


		for (Node n: adjList[v].siblings)
		{
			if(marked[n.key] != true)
			{
				topoSort(adjList, marked, n.key, topoSorted);
			}
		}

		topoSorted.addLast(adjList[v]);
	}

	private void addEdge(int i, int j, Node[] adjList)
	{
		if(adjList[i] == null)
		{
			adjList[i] = new Node(i);
		}

		if(adjList[j] == null)
		{
			adjList[j] = new Node(j);
		}

		adjList[i].siblings.add(adjList[j]);
	}

	private <T> void init(T[] arr, T val)
	{
		for(int i=0; i<arr.length; i++)
		{
			arr[i] = val;
		}
	}

	private class Node
	{
		private int key;
		private List<Node> siblings = new LinkedList<Node>();

		public Node()
		{

		}

		public Node(int key)
		{
			this.key = key;
		}
	}
}