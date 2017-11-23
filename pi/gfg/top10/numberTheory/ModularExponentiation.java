import java.util.LinkedList;
import java.util.Deque;

public class ModularExponentiation
{
    /* calculate x ** y.
    */
    public static int pow(int x, int y)
    {
        int res1 = powRecur(x, y);
        int res2 = powIter(x, y);
        if(res1 != res2) System.out.printf("res1 = %d, res2 = %d\n", res1, res2);
        return res2;
    }

    private static int powRecur(int x, int y)
    {
        if(y == 0) return 1;
        if(y == 1) return x;

        int t = powRecur(x, y/2);
        if(y%2 == 0) return (t * t);
        else return (t * t) * x;
    }

    private static int powIter(int x, int y)
    {
        Deque<Integer> stack = new LinkedList<Integer>();
        while(y!=0)
        {
            stack.addFirst(y);
            y = y/2;
        }

        int prod = 1;
        while(!stack.isEmpty())
        {
            y = stack.removeFirst();

            if(y == 0) prod = 1; /* will not happen unless input y = 0*/
            else if (y == 1) prod = x;
            else if (y%2 == 0) prod = prod * prod;
            else if (y%2 == 1) prod = prod * prod * x;
        }

        return prod;
    }

    public static void main(String [] args)
    {
        runTest(2, 2);
        runTest(2, 5);
        runTest(2, 0);
        runTest(2, 9);
        runTest(2, 10);
    }

    private static void runTest(int x, int y)
    {
        System.out.printf("x = %d, y = %d, x ** y = %d \n", x, y, ModularExponentiation.pow(x, y));
    }
}