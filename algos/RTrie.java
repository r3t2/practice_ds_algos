import java.util.LinkedList;
import java.util.Queue;

public class RTrie<V>
{
  private static int R;
  private Node root;

  public RTrie(int R)
  {
    this.R = R;
  }

  public Iterable<String> keys()
  {
    Queue<String> keysQ = new LinkedList<String> ();
    StringBuilder sb = new StringBuilder();
    keys(root, keysQ, sb, 0);

    return keysQ;

  }

  private void keys(Node n, Queue<String> q, StringBuilder sb, int d)
  {
    if(n == null) return;

    if(n.val != null) q.add(sb.toString());

    for(int i=0; i<R; i++)
    {
      if(n.nodes[i] != null)
      {
        sb.append((char) i);
        
        keys(n.nodes[i], q, sb, d+1);

        sb.deleteCharAt(d);
      }
    }
  }

  public Iterable<String> prefixMatch(String prefix)
  {
    Node prefixNode = findNodeEndingAtPrefix(root, prefix, 0);
    Queue<String> prefixes = new LinkedList<String> ();
    StringBuilder sb = new StringBuilder(prefix.substring(0, prefix.length()));

    if(prefixNode != null)
    {
      keys(prefixNode, prefixes, sb, prefix.length());
    }

    return prefixes;

  }

  private Node findNodeEndingAtPrefix(Node n, String prefix, int d)
  {
    if(n == null) return null;

    if(prefix.length() == d) return n;

    char c = prefix.charAt(d);
    return findNodeEndingAtPrefix(n.nodes[c], prefix, d+1);
  }

  public void delete(String key)
  {
    delete(root, key, 0);
  }

  private Node delete(Node n, String key, int d)
  {
    if(n == null) return null;

    /*arrived at the node that could contain the value for the associated key*/
    if(key.length() == d)
    {
      /*set the value to null*/
      n.val = null;

      /*if the key is part of a longer key, then return the node as is otherwise return null*/
      for(int i=0; i<R; i++)
      {
        if(n.nodes[i] != null)
          return n;
      }
      return null;
    }

    char c = key.charAt(d);
    n.nodes[c] = delete(n.nodes[c], key, d+1);

    return n;

  }

  public void put(String key, V val)
  {
    root = put(root, key, val, 0);
  }

  private Node put(Node n, String key, V val, int d)
  {
    if(n == null)
    {
      n = new Node();
    }
    
    if(key.length() == d) 
    {
      n.val = val;
    }
    else
    {
      char c = key.charAt(d);
      n.nodes[c] = put(n.nodes[c], key, val, d+1);
    }

    return n;
  }

  public boolean contains(String key)
  {
    return get(key) != null;
  }

  public V get(String key)
  {
    return get(root, key, 0);
  }

  private V get(Node n, String key, int d)
  {
    if(n == null) return null;

    if(key.length() == d) return (V) n.val;

    else return get(n.nodes[key.charAt(d)], key, d+1);
  }


  private static class Node
  {
    private Object val = null;
    private Node [] nodes =  new Node[R];
  }


  public static void main(String[] args)
  {

    Integer val; 
    String key;

    RTrie<Integer> r = new RTrie<Integer>(256);
    r.put("a", 1);
    r.put("b", 2);
    r.put("ab", 3);
    r.put("cab", 4);

    System.out.println(r.get("a"));
    System.out.println(r.get("b"));
    System.out.println(r.get("ab"));
    System.out.println(r.get("cab"));
    System.out.println(r.get("abb"));
    System.out.println(r.get("can"));
    System.out.println(r.get("cans"));
    System.out.println(r.get("cats"));
    System.out.println(r.get("cat"));

    System.out.println(r.keys());

    r.delete("a");
    System.out.println(r.keys());
    r.delete("cab");
    System.out.println(r.keys());
    r.delete("ab");
    System.out.println(r.keys());
    r.delete("ab");
    System.out.println(r.keys());
    r.delete("b");
    System.out.println(r.keys());

    r.put("abc", 1);
    r.put("def", 2);
    r.put("abt", 3);
    r.put("d", 4);
    r.put("c", 4);
    r.put("cats", 4);
    r.put("cat", 4);
    r.put("cans", 4);
    r.put("can", 4);

    System.out.println(r.keys());
    System.out.println(r.keys());

    System.out.println("keys with prefix ca = " + r.prefixMatch("ca"));

    System.out.println(r.keys());
  }
}