public class DirectedEdge
{
    private int v;
    private int w;
    private double weight;

    public DirectedEdge(int v, int w, double weight)
    {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int from()
    {
        return v;
    }

    public int to()
    {
        return w;
    }

    public double weight()
    {
        return weight;
    }

    public String toString()
    {
        return String.format("%d, %d, %.02f", v, w, weight);
    }


    public static void main(String[] args)
    {
        DirectedEdge d = new DirectedEdge(0, 1, 0.5);
        System.out.println(d);
        System.out.println(d.from());
        System.out.println(d.to());
        System.out.println(d.weight());
    }
}
