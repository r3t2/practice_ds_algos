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
 private double weight;
 
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
 
 public int other(int v)
 {
  return (v==this.v ? w : v) ;
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


}
