import java.util.LinkedList;
import java.util.Deque;

public class ModularExponentiation
{
    /* (a%p * b%p) %p = (a*b) % p*/
    public static int modPow(int x, int y, int p)
    {
        if(y == 0) return 1;

        int xModp = x%p;

        if(y == 1) return xModp;

        int xPowY2Modp = modPow(x, y/2, p);
        int xPowYModp = ((xPowY2Modp % p) * (xPowY2Modp % p)) % p;
        if(y % 2 == 0) return xPowYModp;
        else return (xPowYModp * xModp)%p;
    }

    /* calculate x ** y.
    */
    public static int pow(int x, int y)
    {
        int res1 = powRecur(x, y);
        int res2 = powIter2(x, y);
        if(res1 != res2) System.out.printf("FAIL:: Recur res1 = %d, Iter2 res2 = %d\n", res1, res2);
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

    private static int powIter2(int x, int y)
    {
        // get binary representation of y.
        // y = yB[0]*2^0 + yB[1]*2^1 + yB[2]*2^2 + ....
        // x ^ y = x ^ (yB[0]*2^0 + yB[1]*2^1 + yB[2]*2^2 + ....)
        // x ^ y = x^(yB[0]*2^0) * x^(yB[1]*2^1) * x^(yB[2]*2^2) * ...
        // for example: y = 6, yB = [0,1,1,0]
        // x^y = x^(0*1) * x^(1*2) * x^(1*4) * x^(0*8)
        int [] yB = getBinaryRepArr(y);

        int xPowI = x;
        int xPowY = 1;

        for(int i=0; i<yB.length; i++)
        {
            if(yB[i] == 1) xPowY = xPowY * xPowI;
            xPowI = xPowI * xPowI;
        }

        return xPowY;

    }

    private static int [] getBinaryRepArr(int n)
    {
        int [] binRep = new int[8];
        for(int i=0; i<binRep.length; i++) binRep[i] = 0;

        int i = 0;
        while(n!=0)
        {
            binRep[i] = n % 2;
            n = n >> 1;
            i = i+1;
        }
        return binRep;
    }

    public static void main(String [] args)
    {
        runTest(2, 2, 2);
        runTest(2, 5, 3);
        runTest(2, 0, 4);
        runTest(2, 9, 10);
        runTest(2, 10, -5);
    }

    private static void runTest(int x, int y, int p)
    {
        System.out.printf("x = %d, y = %d, p = %d, x ** y = %d, (x ** y) mod p = %d \n", 
            x, y, p,
            ModularExponentiation.pow(x, y),
            ModularExponentiation.modPow(x, y, p));
    }
}