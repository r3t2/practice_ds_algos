/*
Implementation of a Edge in UnDirected Weighted Graph
*/


public class Edge implements Comparable<Edge>
{
    private int v;
    private int w;
    private double weight;

    public Edge(int v, int w, double weight)
    {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int compareTo(Edge that)
    {
        if(this.weight < that.weight) return -1;
        else if(this.weight > that.weight) return +1;
        else return 0;
    }

    public int either()
    {
        return v;
    }

    public int other(int x)
    {
        if(x == v) return w;
        else if(x == w) return v;
        else return -1;
    }

    public double weight()
    {
        return weight;
    }

    public boolean equals(Object that)
    {
        if(this.getClass() !=that.getClass()) return false;
        Edge t = (Edge) that;

        if((t.v == this.v) && (t.w == this.w) && (t.weight == this.weight)) return true;
        if((t.w == this.v) && (t.v == this.w) && (t.weight == this.weight)) return true;

        return false;
    }


    public String toString()
    {
        return String.format("%d, %d, %.02f", v, w, weight);
    }

    public static void main(String [] args)
    {
        Edge e = new Edge(1,2,0.5);
        System.out.println(e);
    }

}


