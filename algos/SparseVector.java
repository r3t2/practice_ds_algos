import java.util.HashMap;


public class SparseVector
{
  private HashMap<Integer, Double> sVector = new HashMap<Integer, Double> ();
  private int length;

  public SparseVector(double [] x)
  {
    this.length = x.length;

    for(int i=0; i<length; i++)
    {
      //Double dx = Double(x[i]);
      //if(! (Double(x[i]).equals(Double(0.0)) ) )
      if(x[i] != 0.0)
      {
        sVector.put(i, x[i]);
      }
    }
  }

  public double get(int i)
  {
    if(!sVector.containsKey(i))
    {
      return 0.0;
    }

    return sVector.get(i);
  }

  public double dot(double [] x)
  {
    double acc = 0.0;
    for (int i: sVector.keySet())
    {
        acc += sVector.get(i) * x[i];
    }

    return acc;
  }

  public String toString()
  {
    /*StringBuilder sb = new StringBuilder();
    for (int i: sVector.keySet())
    {
      sb.append("(" + i + ":" + sVector.get(i) + ")");
    }*/
    return sVector.toString();

  }

  public static void main(String [] args)
  {
    SparseVector sv = new SparseVector(new double[] {0.0, 0.0, 2, 3, 0.0});
    System.out.println(sv);
    System.out.println(sv.dot(new double[] {0,1,2,3,4}));
  }

}