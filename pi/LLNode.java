public class LLNode<E>
{
	E val;
	LLNode<E> next;

	public LLNode(E val, LLNode<E> next)
	{
		this.val = val;
		this.next = next;
	}

	public LLNode()
	{
		this.val = null;
		this.next = null;
	}

	/**
	* Override equals method.
	* Does it make sense to compare only the values???
	*/	
	/* public boolean equals(LLNode thatNode) throws Exception
	{
		if (thatNode == null || thatNode.val == null)
		{
			//throw Nullpointer Exception
		}

		return this.val.equals(thatNode.val);
	}
	*/	
}
