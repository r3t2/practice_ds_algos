/*
* Class UF implements weighted quick union with path compression.
* 
* Constructor:
* UF(int N) -- N is the number of objects
*
* void union(int v, int w) -- connect v with w
* boolean find(int v, int w) -- is v connected to w
*/

import java.util.Arrays;
import java.util.Scanner;
import java.io.File;


public class UF
{
  private int [] id;
  private int [] size;
  
  public UF(int N)
  {
    
    /* check N */
    
    id = new int[N];
    size = new int[N];
    
    /* initialize */
    for (int i=0; i<N; i++)
    {
      id[i] = i;
      size[i] = 1;
    }
    
  }
  
  public void union(int v, int w)
  {
    int rv = root(v);
    int rw = root(w);

    if (rv == rw)
    {
      return;
    }
    
    int sv = size[rv];
    int sw = size[rw];
    
    if(sv > sw)
    {
      id[rw] = rv;
      size[rv] = sv + sw;
    }
    else
    {
      id[rv] = rw;
      size[rw] = sv + sw;
    }    
  }
  
  public boolean find(int v, int w)
  {
    return (root(v) == root(w));
  }
  /* returns the root of v */
  private int root(int v)
  {
    /* check(v) */
    
    while(v != id[v])
    {
      id[v] = id[id[v]];
      v = id[v];
    }
    
    return v;
  }

  public String toString()
  {
    StringBuffer sb = new StringBuffer();

    sb.append(Arrays.toString(id) + "\n");
    sb.append(Arrays.toString(size) );
    return sb.toString();
  }

  public static void main(String [] args) throws Exception
  {
    System.out.println("args = " + Arrays.toString(args));

    Scanner sc = new Scanner(new File(args[0]));
    int N = sc.nextInt();
    UF uf = new UF(N);

    for (int i=0; i<N; i++)
    {
      uf.union(sc.nextInt(), sc.nextInt());
    }

    System.out.println(uf);

    System.out.println(uf.find(0,2));
    System.out.println(uf.find(0,4));
    System.out.println(uf.find(9,3));
    System.out.println(uf.find(7,4));
    
  }
}
