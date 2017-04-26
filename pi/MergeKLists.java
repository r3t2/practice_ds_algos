import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.List;
import java.util.Collection;
import java.util.Arrays;

public class MergeKLists
{
	LinkedList<Integer> mergedList = new LinkedList<Integer> ();
	PriorityQueue<Entry> minPQ = new PriorityQueue<Entry> ();


	public ListNode mergeKLists(ListNode [] lists)
	{
		int N = lists.length;
		LinkedList<Integer> [] lls = (LinkedList<Integer> []) new LinkedList[N];

		for (int i=0; i<N; i++)
		{
			if(lists[i] == null)
				return null;
			lls[i] = ListNodeList2LinkedList( lists[i] );			
		}

		LinkedList<Integer> llSorted = mergeKLists(lls);
		return LinkedList2ListNode(llSorted);
	}

	public LinkedList<Integer> mergeKLists(LinkedList<Integer> [] lists)
	{
		Entry t = null;
		for (LinkedList<Integer> list : lists)
		{
			if(list != null)
			{
				minPQ.offer(new Entry(list.removeFirst(), list));
			}
		}

		while(!minPQ.isEmpty())
		{
			t = minPQ.peek();
			minPQ.remove(t);
			mergedList.addLast(t.key);
			if(!t.val.isEmpty())
			{
				minPQ.offer(new Entry(t.val.removeFirst(), t.val));
			}
		}
		return mergedList;
	}

	private static LinkedList<Integer> ListNodeList2LinkedList (ListNode head)
	{
		ListNode i = head;
		LinkedList<Integer> list = new LinkedList<Integer> ();

		while(i != null)
		{
			list.addLast(i.val);
			i = i.next;
		}

		return list;
	}

	private static ListNode LinkedList2ListNode (LinkedList<Integer> l)
	{
		ListNode sentinel = new ListNode(0);
		ListNode t = sentinel;

		for (int i=0; i< l.size(); i++)
		{
			t.next = new ListNode( l.get(i));
			t = t.next;
		}

		return sentinel.next;
	}


	public static void main(String [] args)
	{
		int N = 4;
		LinkedList<Integer> [] lists = (LinkedList<Integer> []) new LinkedList[N];
		lists[0] = new LinkedList<Integer> ();
		lists[1] = new LinkedList<Integer> ();
		lists[2] = new LinkedList<Integer> ();
		lists[3] = new LinkedList<Integer> ();

		lists[0].addLast(1);lists[0].addLast(2);
		lists[1].addLast(3);lists[1].addLast(8);
		lists[2].addLast(4);lists[2].addLast(5);lists[2].addLast(6);
		lists[3].addLast(7);lists[3].addLast(9);

		MergeKLists m = new MergeKLists();
		//System.out.println(m.mergeKLists(lists));

		ListNode [] LNlists = new ListNode [4];
		LNlists[0] = LinkedList2ListNode (lists[0]);
		LNlists[1] = LinkedList2ListNode (lists[1]);
		LNlists[2] = LinkedList2ListNode (lists[2]);
		LNlists[3] = LinkedList2ListNode (lists[3]);

		ListNode ret = m.mergeKLists(LNlists);
		while(ret != null)
		{
			System.out.println(ret.val);
			ret = ret.next;
		}

	}

	public static class ListNode
	{
		private int val;
		private ListNode next;
		public ListNode(int x)
		{
			this.val = x;
		}
	}

	public class Entry implements Comparable<Entry>
	{
		Integer key;
		LinkedList<Integer> val;

		public Entry(Integer key, LinkedList<Integer> val)
		{
			this.key = key;
			this.val = val;
		}

		public int compareTo(Entry that)
		{
			return this.key.compareTo(that.key);
		}
	}
}