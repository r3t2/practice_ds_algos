import java.lang.Math;

public class LinkedListMergeSort
{

	private LinkedList.Node merge_ll(LinkedList.Node lt_ptr, LinkedList.Node rt_ptr)
	{
		if (lt_ptr == null)
		{
			return rt_ptr;
		}
		if(rt_ptr == null)	
		{
			return lt_ptr;
		}

		LinkedList.Node dummy = new LinkedList.Node();
		LinkedList.Node ret_ptr_head = dummy;

		while ( (lt_ptr != null) && (rt_ptr != null))
		{
			if (lt_ptr.val.compareTo(rt_ptr.val) < 0)
			{
				dummy.next = lt_ptr;
				lt_ptr = lt_ptr.next;
			}
			else
			{
				dummy.next = rt_ptr;
				rt_ptr = rt_ptr.next;
			}
			dummy = dummy.next;
		}

		if(lt_ptr == null)
		{
			dummy.next = rt_ptr;
		}
		else
		{
			dummy.next = lt_ptr;
		}

		return ret_ptr_head.next;

	}
	public LinkedList.Node merge_sort_ll_recursive(LinkedList.Node m, int N)
	{
		if(m == null)
		{
			return null;
		}
		if(N == 1)
		{
			return m;
		}

		int N_2 = (int) Math.ceil( ((double) N) / 2);

		LinkedList.Node lt_ptr = m;
		LinkedList.Node rt_ptr = m;
		LinkedList.Node prev = null;

		for(int i = 0; i<N_2; i++)
		{
			prev = rt_ptr;
			rt_ptr = rt_ptr.next;
		}
		prev.next = null;

		lt_ptr = merge_sort_ll_recursive(lt_ptr, N_2);
		rt_ptr = merge_sort_ll_recursive(rt_ptr, N-N_2);

		m = merge_ll(lt_ptr, rt_ptr);

		return m;
	}

	public static void main(String[] args)
	{
		
	}

	public static class LinkedList <E extends Comparable<E>>
	{
		public Node head;
		public Node tail;
		public int N;

		public LinkedList()
		{
			head = null;
			tail = null;
			N = 0;
		}

		public void addLast(E val)
		{
			Node newnode = new Node(val, null);

			if (head == null)
			{
				head = newnode;
				tail = newnode;
				N = 1;
			}
			else
			{
				tail.next = newnode;
				tail = newnode;
				N += 1;
			}

		}

		public static class Node <E extends Comparable<E>>
		{
			E val;
			Node next;

			public Node()
			{
				val = null;
				next = null;
			}

			public Node (E val, Node next)
			{
				this.val = val;
				this.next = next;
			}
		}

	}
	
}