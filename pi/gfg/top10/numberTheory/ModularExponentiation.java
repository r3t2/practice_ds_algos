public class ModularExponentiation
{
    /* calculate x ** y.
    */
    public static int pow(int x, int y)
    {
        int res1 = powRecur(x, y);

        return res1;
    }

    private static int powRecur(int x, int y)
    {
        if(y == 0) return 1;
        if(y == 1) return x;

        int t = powRecur(x, y/2);
        if(y%2 == 0) return (t * t);
        else return (t * t) * x;
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