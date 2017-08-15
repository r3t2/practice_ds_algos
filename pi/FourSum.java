/* imports */
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

public class FourSum
{
  public FourSum()
  {

  }

  public Iterable<Collection<IdxPair>> fourSum(int [] a)
  {
    if(a == null)
      return null;

    HashMap<Integer, Collection<IdxPair>> map = new HashMap<Integer, Collection<IdxPair>> ();


    int sum;
    Collection<IdxPair> temp;

    for(int i=0; i < a.length; i++)
    {
      for(int j = i+1; j < a.length; j++)
      {
        sum = a[i] + a[j];
        temp = map.remove(sum);
        if(temp == null)
        {
          temp = new ArrayList<IdxPair>();
        }

        temp.add(new IdxPair(i, j));
        map.put(sum, temp);
      }
    }

    //System.out.println(map);

    ArrayList<Collection<IdxPair>> ret = new ArrayList<Collection<IdxPair>> ();
    /* For each key, if the collection has more than one entry, add it to the Iterable that is being returned */
    for (int i : map.keySet())
    {
      temp = map.get(i);
      if(temp.size() > 1)
      {
        ret.add(temp);
      }
    }

    return ret;

  }

  public static void main(String [] args)
  {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();

    int [] a = new int[N];
    for (int i=0; i<N; i++)
    {
      a[i] = sc.nextInt();
    }

    FourSum f = new FourSum();

    for(Collection<IdxPair> c : f.fourSum(a))
    {
      System.out.println(c);
    }
  }


  private class IdxPair
  {
    private int i, j;

    public IdxPair(int i, int j)
    {
      this.i = i;
      this.j = j;
    }

    public String toString()
    {
      return "(i = " + i + ", j = " + j + ")";
    }
  }
}