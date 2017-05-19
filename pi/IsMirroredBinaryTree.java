
// 	find the given Binary tree is mirrored tree or not 
// should be like 


// 60 
// / \ 
// 30 30 
// / \ / \ 
// 20 50 50 20

public class IsMirroredBinaryTree<K>
{
	private Node root;

	public boolean isMirror()
	{
		return isMirror(root.left, root.right);
	}
	private isMirror(Node x, Node y)
	{
		if(x == null && y == null)
			return true;
		else if ((x == null && y != null) || (x != null && y == null))
			return false;
		else if (! x.key.equals(y.key))
			return false;

		/* x.key equals y.key */
		boolean ret1 = isMirror(x.left, y.right);
		boolean ret2 = isMirror(x.right, y.left);

		return ret1 & ret2;
	}

	private class Node
	{
		K key;
		private Node left;
		private Node right;
	}


}