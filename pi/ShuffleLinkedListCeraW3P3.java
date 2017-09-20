import java.util.Random; 
/** 
3.  Shuffling a linked list. Given a singly-linked list containing n
items, rearrange the items uniformly at random. Your algorithm should consume a logarithmic (or
constant) amount of extra memory and run in time proportional to nlogn in the worst case. 
*/

public class ShuffleLinkedListCeraW3P3<Item>
{

	private Node head = null;
	private int size = 0;
	private Random rand = new Random();

	public void addFirst(Item e)
	{
		head = new Node(e, head);
		size++;
	}

	public int size() { return size;}

	public void shuffle()
	{
		head = shuffle(head, size);
	}

	private Node shuffle(Node n, int size)
	{
		if(size == 1) return n;

		Node left = n;
		for(int i=0; i<size/2; i++) n = n.next;

		Node mid = n;
		left = shuffle(left, size/2);
		mid = shuffle(mid, size-size/2); // size-size/2 calculates the reminder. for example size = 3. size/2 = 1.

		left = shuffleMerge(left, mid, size/2, size-size/2);

		return left;
	}

	private Node shuffleMerge(Node n1, Node n2, int nSize1, int nSize2)
	{
		int i1 = 0, i2 = 0;
		int randVal;
		Node newHead = null, ni = null;

		while(i1 < nSize1 && i2 < nSize2)
		{
			randVal = rand.nextInt(2);
			if(ni == null)
			{
				if(randVal == 0)
				{
					ni = n1;
					n1 = n1.next;
					i1++;
				}
				else
				{
					ni = n2;
					n2 = n2.next;
					i2++;
				}
				newHead = ni;
			}

			else if(randVal == 0)
			{
				ni.next = n1;
				n1 = n1.next;
				ni = ni.next;
				i1++;
			}
			else
			{
				ni.next = n2;
				n2 = n2.next;
				ni = ni.next;
				i2++;
			}
		}

		while(i1 < nSize1)
		{
			ni.next = n1;
			n1 = n1.next;
			ni = ni.next;
			i1++;
		}
		while(i2 < nSize2)
		{
			ni.next = n2;
			n2 = n2.next;
			ni = ni.next;
			i2++;
		}

		ni.next = null;

		return newHead;
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();

		sb.append("[");
		Node n = head;
		while(n!=null)
		{
			sb.append(n.e.toString());
			sb.append(", ");
			n = n.next;
		}
		sb.append("]");

		return sb.toString();
	}

	public static void main(String [] args)
	{
		runTest(new int[]{4, 3, 2, 1});

		runTest(new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1});
	}

	private static void runTest(int [] x)
	{
		ShuffleLinkedListCeraW3P3<Integer> s = new ShuffleLinkedListCeraW3P3<Integer>();
		for(int i: x) s.addFirst(i);

		System.out.println("before shuffle =" + s.toString());

		s.shuffle();

		System.out.println("after shuffle  =" + s);

	}

	private class Node
	{
		Item e;

		Node next;

		public Node(Item e) { this.e = e; }

		public Node(Item e, Node next) { this.e = e; this.next = next; }
	}
}