public class DirectedEdge implements Comparable<DirectedEdge>
{
  private double weight;

  private int from;
  private int to;

  public DirectedEdge(int from, int to, double weight)
  {
    this.from = from;
    this.to = to;
    this.weight = weight;
  }

  public DirectedEdge()
  {
  }

  public String toString()
  {
    return from + ", " + to + ", " + weight;
  }

  public int compareTo(DirectedEdge that)
  {
    Double thisWeight = new Double(weight);
    Double thatWeight = new Double(that.weight);

    return thisWeight.compareTo(thatWeight);
  }

  public int from()
  {
    return this.from;
  }

  public int to()
  {
    return this.to;
  }

  public double weight()
  {
    return this.weight;
  }


}