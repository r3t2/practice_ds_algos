/*
* Class to define an weighted edge in a graph.
* fields: 
* weight of the edge between vertices v and w
* * double weight; 
* * int v, w; 
* methods:
* public int either() // return either v or w
* public int other(v) // vertex on the other side of the this edge at v
* public int compareTo(Edge that) // compare the weight of this edge with that.
*/

/* imports */


public class Edge implements Comparable<Edge>
{

  private Double weight;

  private int v;

  private int w;

  public Edge(int v, int w, double weight)
  {
    this.v = v;
    this.w = w;
    this.weight = weight;
  }

  public int either()
  {
    return v;
  }

// this.v = 6, this.w = 0, u = 0, retVal = 0
// this.v = 4, this.w = 7, u = 7, retVal = 7
  public int other(int u)
  {
    int retVal = (u == this.v) ? this.w : this.v;
    return retVal ;
  }

  public double weight()
  {
    return weight;
  } 

  public String toString()
  {
    return v + ", " + w + ", " + weight;
  }

  public int compareTo(Edge that)
  {
    if(this.weight < that.weight)
      return -1;
    else if(this.weight > that.weight)
      return 1;
    else
      return 0;
  }

  public boolean equals(Object o)
  {
    boolean retValue = false;
    
    if (o instanceof Edge)
    {
      int ov, ow;
      ov = ((Edge)o).either(); ow = ((Edge)o).other(ov);
      if ( weight.equals(((Edge)o).weight()) && ((v == ov && w == ow) || (v == ow && w == ov)) )
      {
        retValue = true;
      }

    }

    return retValue;

  }


}
