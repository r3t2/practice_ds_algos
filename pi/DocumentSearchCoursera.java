import java.util.HashMap;

public class DocumentSearchCoursera 
{   

  /*   Document search. Design an algorithm that takes a sequence of
n document words and a sequence of m query words and find the shortest interval in which the m query
words appear in the document in the order given. The length of an interval is the number of words in
that interval.   */ 

  private String[] words;

  private HashMap<Node, Integer> dpSolution;

  public DocumentSearchCoursera(String [] words)
  {
    this.words = words;
  }


  public void smallestRange(String[] query)
  {
    dpSolution = new HashMap<Node, Integer> ();
    int sol = -1, minRange = Integer.MAX_VALUE, minRangeStart = -1, minRangeEnd = -1;

    for(int i=0; i<words.length; i++)
    {
      if(words[i].equals(query[0]))
      {
        sol = smallestRange(query, i, 0);
        if(( sol != -1) && ((sol - i) < minRange))
        {
          minRange = sol-i;
          minRangeStart = i;
          minRangeEnd = sol;
        }
      }

    }

    System.out.println(String.format("minRange = %d, start = %d, end = %d", minRange, minRangeStart, minRangeEnd));
  }

  private int smallestRange(String [] query, int i, int j)
  {
    if((j >= query.length) || (i>= words.length)) return -1;



    Node n = new Node(i,j);
    Integer sol = dpSolution.get(n);
    if(sol != null) return sol;


    if(words[i].equals(query[j]))
    {
      if((query.length-1) == j) return i;

      sol = smallestRange(query, i+1, j+1);
      n = new Node(i+1, j+1);
    }
    else 
    {
      sol = smallestRange(query, i+1, j);
      n = new Node(i+1, j);
    }

    dpSolution.put(n, sol);

    return sol;
  }



  public static void main(String[] args)
  {

    HashMap<Node, Integer> test = new HashMap<Node, Integer> ();
    test.put(new Node(0,0), 0);
    test.put(new Node(1,0), 1);

    System.out.println(test.get(new Node(0,0)));
    System.out.println(test.get(new Node(1,0)));


    String [] words = new String [] {"A", "B", "Z", "C", "D", "A", "C", "D"};
    String [] query = new String [] {"A", "D"};

    DocumentSearchCoursera dSearch = new DocumentSearchCoursera(words);
    dSearch.smallestRange(query);
  }



  private static class Node
  {
    private int i;
    private int j;

    public Node(int i, int j)
    {
      this.i = i;
      this.j = j;
    }

    public int hashCode()
    {
      return i*31 + j + 17;
    }

    public boolean equals(Object y)
    {
      if(!(y instanceof Node)) return false;
      Node yNode = (Node) y;
      
      if(yNode.i == i && yNode.j == j) return true;
      else return false;
    }
  }



}
