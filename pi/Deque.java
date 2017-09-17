import java.util.Iterator;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class Deque<E> implements Iterable<E>
{
	private Node head;
	private Node tail;
	private int size;

	// construct empty deque.
	public Deque()
	{
		size = 0;
	}

	public int size()
	{
		return size;
	}

	// is the deque empty
	private boolean isEmpty()
	{
		return size==0;
	}

	// Add the element to the start of the deque
	public void addFirst(E e)
	{
		if(e == null) throw new IllegalArgumentException();

		if(head == null)
		{
			head = new Node(e);
			tail = head;
		}
		else
		{
			head = new Node(e, head, null);
			head.next.prev = head;
		}

		size++;
	}

	// Add the element to the end of the deque
	public void addLast(E e)
	{
		if(e == null) throw new IllegalArgumentException();

		if(tail == null)
		{
			tail = new Node(e);
			head = tail;
		}
		else
		{
			tail = new Node(e, null, tail);
			tail.prev.next = tail;
		}

		size++;
	}

	// Remove the element from the start of the queue
	public E removeFirst()
	{
		if(size == 0) throw new NoSuchElementException();

		Node prevHead = head;

		head = head.next;
		if(head!=null) head.prev = null;

		// clean remove
		prevHead.next = null;

		// If there was only one element in Deque, then set tail to null
		if(tail == prevHead) tail = null;

		// decrement size
		size--;

		return prevHead.e;
	}

	// Remove the element from the tail of the queue
	public E removeLast()
	{
		if(size == 0) throw new NoSuchElementException();

		Node prevTail = tail;

		tail = tail.prev;
		if(tail != null) tail.next = null;

		prevTail.prev = null;

		if(head == prevTail) head = null;

		size--;

		return prevTail.e;
	}

	// returns an iterator in order from front to last.
	public Iterator<E> iterator()
	{
		return new ForwardIterator();
	}

	public String toString()
	{
		Node i = head;
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		while(i!=null)
		{
			sb.append(i.e.toString());
			sb.append(", ");
			i = i.next;
		}
		sb.append("]");
		return sb.toString();
	}

	public static void main(String[] args)
	{
		Scanner sc = new Scanner (System.in);

		Deque<String> d = new Deque<String> ();

		while(sc.hasNext())
		{
			d.addLast(sc.next());
		}

		System.out.println(d.toString());

		System.out.println("size = " + d.size());

		while(!d.isEmpty()) System.out.println(d.removeFirst());
	}

	private class Node
	{
		private E e;
		private Node next = null;
		private Node prev = null;

		private Node(E e)
		{
			this.e = e;
		}

		private Node(E e, Node next, Node prev)
		{
			this.e = e;
			this.next = next;
			this.prev = prev;
		}
	}

	private class ForwardIterator implements Iterator<E>
	{
		private Node i = head;

		public boolean hasNext()
		{
			return i != null;
		}

		public E next()
		{
			if(!hasNext()) throw new NoSuchElementException();

			E e = i.e;
			i = i.next;
			return e;
		}

		public void remove()
		{
			throw new java.lang.UnsupportedOperationException();
		}
	}
}