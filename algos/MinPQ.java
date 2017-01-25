/*
* Min priority queue using a binary heap implementation.
* 
* Constructor
* MinPQ(int capacity) //initial capacity
* MinPQ(Key[] keys) // construct a min PQ given an array of keys
* 
* Methods:
* Key min() // returns min key if minPQ is not empty. 
* void insert(Key key) // insert key into the PQ if minPQ is not empty
* Key delMin() // deletes the minimum and returns the min Key if it exists
* boolean isEmpty() // returns true if PQ is empty, false if not.
*/

/* imports */
import java.util.Arrays;

public class MinPQ<Key extends Comparable<Key>>
{
  /*fields*/

  private Key [] keys;
  private int size;

  public MinPQ(int capacity)
  {
    /* error checking */
    
    /* init */
    keys = (Key []) new Comparable[capacity+1];
    size = 0;
  }

  public void insert(Key key) throws Exception
  {
    if(size == (keys.length-1))
      resize();

    size++;
    keys[size] = key;
    
    
    swim(size);
    
  }

  public Key min()
  {
    if(size==0)
      return null;
    else
      return keys[1];
  }

  public Key delMin()
  {
    Key min = null;

    if(size == 0)
    {
      min = null;
    }
    else
    {
      min = keys[1];
      keys[1] = keys[size];
      keys[size] = null;
      size--;
    }
    
    if(size>0)
    {
      sink(1);
    }

    return min;

  }

  private void swim(int i)
  {
    if (i==1)
    {
      return;
    }
    
    int p = i/2;
    if(keys[p].compareTo(keys[i]) > 0)
    {
      exch(p, i);
      swim(p);
    }
    
  }

  public boolean isEmpty()
  {
    return (size==0);
  }

  private void sink(int p)
  {
    if(p>size)
      return;

    int c0 = 2*p, c1 = 2*p+1;
    int c = -1;
    if (c1 < size)
    {
      c = (keys[c0].compareTo(keys[c1]) < 0) ? c0 : c1;
    }
    else if (c0 < size)
    {
      c = c0;
    }
    
    if(c > 1)
    {
      if(keys[p].compareTo(keys[c]) > 0) // parent is larger than the child
      {
        exch(p, c);
        sink(c);
      }

    }
  }

  private void exch(int i, int j)
  {
    Key temp = keys[i];
    keys[i] = keys[j];
    keys[j] = temp;
  }



  private void resize()
  {
    throw new UnsupportedOperationException();
  }

  public String toString()
  {
    return "size = " + size + "\n" + Arrays.toString(keys);
  }

  public static void main(String[] args) throws Exception
  {
    MinPQ<Integer> pq = new MinPQ<Integer>(10);
    pq.insert(5);
    pq.insert(4);
    pq.insert(2);
    pq.insert(7);
    pq.insert(1);
    pq.insert(9);

    System.out.println("pq = " + pq);
    System.out.println("min = " + pq.delMin());
    System.out.println("min = " + pq.delMin());
    System.out.println("min = " + pq.delMin());
    System.out.println("min = " + pq.delMin());
    System.out.println("pq = " + pq);
  }

}