public class GCD
{
    public static int gcd(int a, int b)
    {
        if(a == 0) return b;
        if(b == 0) return a;
        
        if(b > a) return gcd(b%a, a);
        else return gcd(a%b, b);
    }

    public static void main(String [] args)
    {
        runTest(2, 3);
        runTest(3, 2);
        runTest(11, 17);
        runTest(189, 17);
        runTest(11*13, 19*17);
        runTest(4*13, 4*17);
        runTest(4*3*13, 4*3*17);
    }

    private static void runTest(int a, int b)
    {
        System.out.printf("a = %3d, b = %3d, gcd = %3d\n\n", a, b, gcd(a,b));
    }
}