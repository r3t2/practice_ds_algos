import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.InputStream;
import java.util.Comparator;

public class MedianInStream
{

  private PriorityQueue<Double> lhsHeap = new PriorityQueue<Double> (11, new DecsendingComparator());
  private PriorityQueue<Double> rhsHeap = new PriorityQueue<Double> (11, new AscendingComparator());

  private Double median = Double.NaN;
  

  public void calcMedian(InputStream inStream)
  {
    Scanner sc = new Scanner(inStream);

    while(sc.hasNextDouble())
    {
      Double t = sc.nextDouble();

      if(median.compareTo(t) < 0)
      {
        rhsHeap.offer(t);
      }
      else
      {
        lhsHeap.offer(t);
      }
      
      if(lhsHeap.size() - rhsHeap.size() == 2)
      {
        rhsHeap.offer( lhsHeap.remove() );
      }
      else if(rhsHeap.size() - lhsHeap.size() == 2)
      {
        lhsHeap.offer( rhsHeap.remove() );
      }

      if (lhsHeap.size() == rhsHeap.size())
      {
        median = (lhsHeap.peek() + rhsHeap.peek()) / 2.0;
      }
      else if (lhsHeap.size() > rhsHeap.size())
      {
        median = lhsHeap.peek();
      }
      else
      {
        median = rhsHeap.peek();
      }

      System.out.println(String.format("median = %f, lhs.peek = %f, rhs.peek =%f", median, lhsHeap.peek(), rhsHeap.peek()));

    }
  }

  public static void main(String [] args)
  {
    MedianInStream m = new MedianInStream();
    m.calcMedian(System.in);
  }

  public class AscendingComparator implements Comparator<Double>
  {
    public int compare(Double lhs, Double rhs)
    {
      return lhs.compareTo(rhs);
    }
  }
  public class DecsendingComparator implements Comparator<Double>
  {
    public int compare(Double lhs, Double rhs)
    {
      return -1*lhs.compareTo(rhs);
    }
  }
}