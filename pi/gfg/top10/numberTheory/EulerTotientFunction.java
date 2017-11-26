/*
Euler’s Totient Function
2.8
Euler’s Totient function Φ(n) for an input n is count of numbers in {1, 2, 3, …, n} that are relatively prime to n, p.e., the numbers whose GCD (Greatest Common Divisor) with n is 1.

Examples:

Φ(1) = 1  
gcd(1, 1) is 1

Φ(2) = 1
gcd(1, 2) is 1, but gcd(2, 2) is 2.

Φ(3) = 2
gcd(1, 3) is 1 and gcd(2, 3) is 1

Φ(4) = 2
gcd(1, 4) is 1 and gcd(3, 4) is 1

Φ(5) = 4
gcd(1, 5) is 1, gcd(2, 5) is 1, 
gcd(3, 5) is 1 and gcd(4, 5) is 1

Φ(6) = 2
gcd(1, 6) is 1 and gcd(5, 6) is 1, 
*/
public class EulerTotientFunction
{
    /*
    Use Euler’s product formula.
    phi(n) = n*pi_(p|n)(1-1/p)
    p|n -> for every prime p that divides n
    pi --> multiplication
    example: n = 35 = 5*7
    pi(35) = 35*(1-1/5)*(1-1/7)
    example: n = 75 = 5**2 * 3
    pi(75) = 75*(1-1/5)*(1-1/3)
    */
    public static int eulerTotientFunc(int x)
    {
        if(x == 1) return 1;

        int res = x;
        int rem = x;
        for(int p=2; p<=x;p++) // we could probably check only the 6k+/-1
        {
            if(rem % p == 0)
            {
                while(rem % p == 0) rem = rem/p;
                res = res - res/p; // n = n*(1-1/p) => n = n - n/p
            }
        }

        // if(rem > 1)
        // {
        //     // x must be prime
        //     res = res-res/rem; // res = x*(1-1/x) = x-1
        // }

        return res;
    }

    public static void main(String [] args)
    {
        runTest(1);
        runTest(2);
        runTest(3);
        runTest(4);
        runTest(5);
        runTest(6);
        runTest(25);
        runTest(35);
        runTest(75);
    }
    private static void runTest(int n)
    {
        System.out.printf("n = %5d, phi(n) = %5d\n\n", n, EulerTotientFunction.eulerTotientFunc(n));
    }
}