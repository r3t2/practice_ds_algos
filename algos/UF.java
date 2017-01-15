/*
* Class UF implements weighted quick union with path compression.
* 
* Constructor:
* UF(int N) -- N is the number of objects
*
* void union(int v, int w) -- connect v with w
* boolean find(int v, int w) -- is v connected to w
*/

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
    
    int sv = size(rv);
    int sw = size(rw);
    
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
}
