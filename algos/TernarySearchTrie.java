public class TernarySearchTrie<V>
{

  private Node root;

  public void put(String key, V val)
  {
    root = put(root, key, val, 0);
  }

  public Node put(Node n, String key, V val, int d)
  {
    char c = key.charAt(d);

    if(n == null)
    {
      n = new Node();
      n.c = c;
    }

    if(c == n.c)
    {
      if(key.length() == (d+1)) 
      {
        n.val = val;
      }
      else
      {
        n.middle = put(n.middle, key, val, d+1);
      }
    }
    else if(c < n.c)
    {
      n.left = put(n.left, key, val, d);
    }
    else
    {
      n.right = put(n.right, key, val, d);
    }

    return n;
  }

  public V get(String key)
  {
    return get(root, key, 0);
  }

  private V get(Node n, String key, int d)
  {
    char c = key.charAt(d);

    if(n == null) return null;

    if(c == n.c)
    {
      if(key.length() == (d+1))
      {
        return n.val;
      }
      else
      {
        return get(n.middle, key, d+1);
      }
    }
    else if(c < n.c)
    {
      return get(n.left, key, d);
    }
    else
    {
      return get(n.right, key, d);
    }

  }



  private class Node
  {
    V val;
    char c;
    Node left, middle, right;
  }

  public static void main(String[] args)
  {
    TernarySearchTrie<Integer> t = new TernarySearchTrie<Integer>();

    t.put("c", 1);
    t.put("cat", 2);
    t.put("bat", 3);
    t.put("apple", 4);
    t.put("apple", 5);

    System.out.println(t.get("c"));
    System.out.println(t.get("cat"));
    System.out.println(t.get("bat"));
    System.out.println(t.get("apple"));
  }
}