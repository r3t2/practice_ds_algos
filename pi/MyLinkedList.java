import java.util.Arrays;

public class MyLinkedList<E>
{

	private LLNode<E> head;
	private LLNode<E> tail;

	private long count;

	public MyLinkedList()
	{
		head = null;
		tail = null;
		count = 0;
	}

	public void addFirst(E e)
	{
		LLNode<E> newNode = new LLNode<E>(e, head);


		if(tail == null)
		{
			tail = newNode;
		}
		
		head = newNode;
		count++;

	}

	public void addLast(E e)
	{
		LLNode<E> newNode = new LLNode<E>(e, null);

		if(tail == null)
		{
			head = newNode;
			tail = newNode;
		}

		else
		{
			tail.next = newNode;
			tail = tail.next;
		}

		count++;

	}

	public long size()
	{
		return count;
	}

	public String toString()
	{
		LLNode<E> itr = head;
		StringBuffer sb = new StringBuffer();
		while(itr != null)
		{
			sb.append(itr.val.toString());
			sb.append(", ");
			itr = itr.next;
		}

		return sb.toString();
	}

	public boolean isPalindrome()
	{
		if (head == null)
		{
			return false;
			// Should exception be thrown??
		}

		//LLNode<E>[] forward = (LLNode<E> []) new Object[1];
		LLNode<E>[] forward = new LLNode<E>[1];

		forward[0] = head;
		return isPalindrome_recursive(head, forward);
	}

	private boolean isPalindrome_recursive(LLNode<E> itr, LLNode<E>[] forward)
	{
		return false;
	}

	public static void main(String[] args)
	{
		/* Test insert
		*/

		MyLinkedList<Integer> slist1 = new MyLinkedList<Integer>();
		int[] intList = {1,2,3,4,5};

		for (int i=0; i < intList.length; i++)
		{
			slist1.addLast( intList[i] );
		}

		for (int i=0; i < intList.length; i++)
		{
			slist1.addFirst( intList[i] );
		}


		System.out.println(slist1.toString());
		System.out.println("size = " + slist1.size());






	}

	
}