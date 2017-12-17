/*

Reverse alternate levels of a perfect binary tree
3.5

Given a Perfect Binary Tree, reverse the alternate level nodes of the binary tree.

  
Given tree: 
               a
            /     \
           b       c
         /  \     /  \
        d    e    f    g
       / \  / \  / \  / \
       h  i j  k l  m  n  o 

Modified tree:
               a
            /     \
           c       b
         /  \     /  \
        d    e    f    g
       / \  / \  / \  / \
      o  n m  l k  j  i  h 
*/

import java.util.ArrayList;

public class ReverseAlternateLevels
{
    private ArrayList<Integer> tree = new ArrayList<Integer> ();
    private int size = 0;

    public ReverseAlternateLevels()
    {
        size = 0;
        tree.add(-1);
    }

    public void insert(int i)
    {
        tree.add(i);
        size++;
    }
    
    public void reverseAlternate()
    {
        int p = 0;
        int start, end;

        while(true)
        {
            start = 1 << p+1;
            end = (1 << p+2)-1;
            
            if(start > size) break;

            reverseAlternate(start, end);

            p += 2;
        }
    }

    private void reverseAlternate(int start, int end)
    {
        int temp;
        while(end > start)
        {
            temp = tree.get(end);
            tree.set(end, tree.get(start));
            tree.set(start, temp);
            end -= 1; start += 1;
        }
    }

    public String toString()
    {
        return "size = " + size + ", " + tree.toString();
    }
    public static void main(String [] args)
    {
        runTest(3);
        runTest(7);
        runTest(15);
    }

    private static void runTest(int N)
    {
        ReverseAlternateLevels tree = new ReverseAlternateLevels();

        for(int i=1; i<=N; i++)
        {
            tree.insert(i);
        }

        System.out.println(tree);
        tree.reverseAlternate();
        System.out.println(tree);
        System.out.println();
    }
}