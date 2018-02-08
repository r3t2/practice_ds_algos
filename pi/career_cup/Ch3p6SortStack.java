import java.util.*;
public class Ch3p6SortStack
{
    // push: addFirst
    // pop: removeFirst
    // peek: getFirst
    // method1: selection sort. Find max in the stack, remove and add the max into another stack.
    // method2: quick sort but using only stacks.
    public static Deque<Integer> sort(Deque<Integer> s)
    {

        Deque<Integer> sortedStack = new LinkedList<Integer> ();
        
        if(s.size() <= 1) return s;

        sort(s, sortedStack);

        return sortedStack;
    }

    private static void sort(Deque<Integer> s, Deque<Integer> sortedStack)
    {
        if(s.size() == 0) return;
        if(s.size() == 1)
        {
            sortedStack.addFirst(s.removeFirst());
            return;
        }

        Deque<Integer> lt = new LinkedList<Integer>();
        Deque<Integer> gt = new LinkedList<Integer>();

        int pivot = s.getFirst();
        int e;
        while(!s.isEmpty())
        {
            e = s.removeFirst();
            if(e < pivot) lt.addFirst(e);
            else gt.addFirst(e);
        }

        sort(gt, sortedStack);
        sort(lt, sortedStack);
    }
    private static String toString(Deque<Integer> s)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int e: s)
        {
            sb.append(e);
            sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
    public static void main(String [] args)
    {
        runTest(new int [] {1,2,3,4,5});
        runTest(new int [] {5,4,3,2,1});
        runTest(new int [] {1,5,4,2,3});
        runTest(new int [] {1,5,4,2});
        runTest(new int [] {});
        runTest(new int [] {1});
    }
    private static void runTest(int [] s)
    {
        Deque<Integer> stack = new LinkedList<>();
        for(int e: s)
        {
            stack.addFirst(e); 
        }
        System.out.printf("input = %s\n", toString(stack));
        
        Deque<Integer> ret = sort(stack);
        
        System.out.printf("output = %s\n\n", toString(ret));   
    }
}