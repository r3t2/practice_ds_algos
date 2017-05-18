import java.util.LinkedList;
import java.util.Deque;
import java.util.Scanner;

public class BST<K extends Comparable<K>, V>
{
	private BSTNode<K, V> root;

	public void insert(K key, V val)
	{
		root = insert(root, key, val);
		return;
	}

	private BSTNode<K,V> insert(BSTNode<K, V> node, K key, V val)
	{
		if(node == null)
		{
			return new BSTNode<K, V>(key, val);
		}
		else if(key.compareTo(node.key) < 0)
		{
			node.left = insert(node.left, key, val);
		}
		else
		{
			node.right = insert(node.right, key, val);			
		}
		node.size++;
		node.height = Math.max(height(node.left), height(node.right)) + 1;

		return node;
	}

	public Iterable<BSTNode<K,V>> inorder()
	{
		Deque<BSTNode<K,V>> ll = new LinkedList<BSTNode<K,V>>();
		inorder(root, ll);

		return ll;
	}

	public void inorder(BSTNode<K,V> node, Deque<BSTNode<K,V>> d)
	{
		if(node == null)
		{
			return;
		}
		inorder(node.left, d);
		d.offerLast(node);
		inorder(node.right, d);
	}

	public Iterable<BSTNode<K,V>> levelOrder()
	{
		Deque<BSTNode<K,V>> ll = new LinkedList<BSTNode<K,V>> ();

		Deque<BSTNode<K,V>> nodes = new LinkedList<BSTNode<K,V>> ();
		nodes.offerLast(root);

		BSTNode<K, V> node;

		while(!nodes.isEmpty())
		{
			node = nodes.removeFirst();
			ll.offerLast(node);
			if(node.left != null)
			{
				nodes.offerLast(node.left);
			}
			if(node.right != null)
			{
				nodes.offerLast(node.right);
			}
		}

		return ll;

	}

	public int heightTraversal()
	{
		return heightTraversal(root);
	}

	public int heightTraversal(BSTNode<K,V> node)
	{
		if (node == null)
		{
			return -1;
		}

		int lh = heightTraversal(node.left);
		int rh = heightTraversal(node.right);
		return Math.max(lh, rh) + 1;
	}

	public int height()
	{
		return height(root);
	}

	private int height(BSTNode<K,V> node)
	{
		if(node == null)
		{
			return -1;
		}
		else
		{
			return node.height;
		}
	}

	public K floor(K key)
	{
		return floor(root, key);
	}
	private K floor(BSTNode<K, V> node, K key)
	{
		K ret;

		if(node == null)
			return null;
		if(key.equals(node.key))
		{
			ret = key;
		}
		else if(key.compareTo(node.key) < 0)
		{
			ret = floor(node.left, key);
		}
		else
		{
			ret = floor(node.right, key);
			if (ret == null)
			{
				ret = node.key;
			}
		}

		return ret;

	}

	public K ceil(K key)
	{
		return ceil(root, key);
	}
	private K ceil(BSTNode<K, V> node, K key)
	{
		K ret;

		if(node == null)
			return null;
		if(key.equals(node.key))
		{
			ret = key;
		}
		else if(key.compareTo(node.key) < 0)
		{
			ret = ceil(node.left, key);
			if (ret == null)
			{
				ret = node.key;
			}
		}
		else
		{
			ret = ceil(node.right, key);
		}

		return ret;

	}

	public K rank(long rank)
	{
		return rank(root, rank);
	}

	private K rank(BSTNode<K, V> node, long rank)
	{
		K ret;

		if(node == null)
		{
			return null;
		}

		long lsize;
		if (node.left == null)
		{
			lsize = 1;
		}
		else
		{
			lsize = node.left.size + 1;
		}

		if(rank == lsize)
		{
			ret = node.key;
		}
		else if(rank < lsize)
		{
			ret = rank(node.left, rank);
		}
		else
		{
			ret = rank(node.right, rank - lsize);
		}

		return ret;

	}


	public void deleteMax()
	{
		root = deleteMax(root);
	}

	private BSTNode<K,V> deleteMax(BSTNode<K,V> node)
	{
		if(node == null)
			return null;
		else if (node.right == null)
			return node.left;
		else
		{
			node.right = deleteMax(node.right);
			node.size = size(node.left) + size(node.right) + 1;
			return node;
		}
	}

	public void deleteMin()
	{
		root = deleteMin(root);
	}

	private BSTNode<K,V> deleteMin(BSTNode<K,V> node)
	{
		if(node == null)
		{
			return null;
		}

		if(node.left == null)
		{
			return node.right;
		}

		node.left = deleteMin(node.left);
		node.size = size(node.left) + size(node.right) +1;
		return node;

	}

	private long size(BSTNode<K,V> x)
	{
		if(x == null)
			return 0;
		else
			return x.size;
	}




	private static class BSTNode<K extends Comparable<K>, V>
	{
		private K key;
		private V val;
		private long size;
		private int height;
		private BSTNode<K, V> left;
		private BSTNode<K, V> right;

		public long size()
		{
			return size;
		}

		public BSTNode(K key, V val)
		{
			this.key = key;
			this.val = val;
			this.size = 1;
			this.height = 0;
			this.left = null;
			this.right = null;
		}

		public BSTNode()
		{
			this.key = null;
			this.val = null;
			this.size = 1;
			this.height = 0;
			this.left = null;
			this.right = null;
		}

		public String toString()
		{
			return "key = " + key 
					+ ", val = " + val 
					+ ", size = " + size;
		}


	}

	public static void main(String[] args)
	{
		/*
		* format of input file:
		* num_nodes
		* key0, val0
		* key1, val1
		*/
		Scanner sc = new Scanner(System.in);
		BST<Integer, Integer> bst = new BST<Integer, Integer>();

		int num_nodes = sc.nextInt();
		int key, val;
		for (int i=0; i<num_nodes; i++)
		{
			bst.insert(sc.nextInt(), sc.nextInt());
		}

		Iterable<BSTNode<Integer, Integer>> level = bst.levelOrder();

		System.out.println("level order::");
		for (BSTNode<Integer, Integer> node : level)
		{
			System.out.println(node);
		}

		Iterable<BSTNode<Integer, Integer>> inorder = bst.inorder();

		System.out.println("inorder ::");
		for (BSTNode<Integer, Integer> node : inorder)
		{
			System.out.println(node);
		}

		System.out.println("heightTraversal = " + bst.heightTraversal());
		System.out.println("height = " + bst.height());

		int cnt = 0;
		for (int i: new int[] {5, 15, 25, 35, 45, 55, 65, 75, 85, 95, 125})
		{

			System.out.println("i = " + i 
					   + ", floor = " + bst.floor(i)
					   + ", ceil = " + bst.ceil(i)
					   + ", rank(" + cnt +") = " + bst.rank(cnt));
			cnt++;
		}
	}
}