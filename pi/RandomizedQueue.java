import java.util.Random;
import java.util.Iterator;
import java.util.NoSuchElementException;
// import java.util.Scanner;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdIn;

public class RandomizedQueue<Item> implements Iterable<Item>
{

	private Item [] arr;
	private int size = 0;
	private Random rand = new Random();
	private static final int INIT_SIZE = 4;


	public RandomizedQueue()
	{
		arr = (Item[]) new Object[INIT_SIZE];
	}

	public int size()
	{
		return size;
	}

	public boolean isEmpty()
	{
		return size==0;
	}

	public void enqueue(Item e)
	{
		if(e == null) throw new IllegalArgumentException();
		
		arr[size++] = e;
		resize();
	}

	public Item dequeue()
	{
		if(size == 0) throw new NoSuchElementException();

		int randIdx = rand.nextInt(size);
		
		Item e = arr[randIdx];
		arr[randIdx] = arr[size-1];
		size--;
		
		resize();

		return e;
	}

	private void resize()
	{
		Item [] newArr;
		if(size == arr.length) newArr = (Item[]) new Object[2*arr.length];

		else if(size < arr.length/4) newArr = (Item[]) new Object[arr.length/4];

		else return;

		for(int i=0; i<size; i++)
		{
			newArr[i] = arr[i];
		}

		arr = newArr;
		return;
	}

	public Item sample()
	{
		if(size == 0) throw new NoSuchElementException();

		int randIdx = rand.nextInt(size);
		return arr[randIdx];
	}

	public Iterator<Item> iterator()
	{
		return new RandomIterator();
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();

		sb.append("[");

		for(int i=0; i < size; i++)
		{
			sb.append(arr[i].toString());
			sb.append(", ");
		}

		sb.append("]");

		return sb.toString();
	}

	public static void main(String[] args)
	{
		String [] strs = StdIn.readAllStrings();

		RandomizedQueue<String> r = new RandomizedQueue<String>();

		for(String str: strs)
		{
			r.enqueue(str);
		}

		System.out.println("Printing in sequence:");
		System.out.println(r);
		System.out.println("size = " + r.size());

		System.out.println("Iterating first time:");
		for(String e: r) System.out.print(e.toString() + " ");

		System.out.println("");
		System.out.println("Iterating second time:");
		for(String e: r) System.out.print(e.toString() + " ");

		System.out.println("");
		System.out.println("dequeue till empty:");
		while(!r.isEmpty()) System.out.print(r.dequeue() + " ");

		System.out.println("");
		System.out.println("size = " + r.size());

	}

	private class RandomIterator implements Iterator<Item>
	{
		private Item [] itArr;
		private int nextIdx = 0;

		private RandomIterator()
		{
			itArr = (Item[]) new Object[size];

			for(int i=0; i < size; i++)
			{
				itArr[i] = arr[i];
			}

			shuffle(itArr);
		}

		private void shuffle(Item[] x)
		{
			int randIdx;
			for(int i=0; i<x.length; i++)
			{
				randIdx = StdRandom.uniform(i, x.length);
				exch(x, i, randIdx);
			}
		}

		private void exch(Item[] x, int i, int j)
		{
			Item temp = x[i];
			x[i] = x[j];
			x[j] = temp;
		}

		public boolean hasNext()
		{
			return nextIdx < itArr.length;
		}

		public Item next()
		{
			if(!hasNext()) throw new NoSuchElementException();

			return itArr[nextIdx++];
		}

		public void remove()
		{
			throw new java.lang.UnsupportedOperationException();
		}
	}
}