import java.util.List;
public class PrimalityTest
{
    public static boolean isPrime(int n)
    {
        if(n <= 1) throw new IllegalArgumentException("input n = " + n + " not supported");

        boolean res1 = isPrimeOpt1(n);
        boolean res2 = isPrimeOpt2(n);
        boolean res3 = isPrimeOpt3(n);

        if(res1 != res2) System.out.println("FAIL:: res1 = " + res1 + ", res2 = " + res2);
        if(res1 != res3) System.out.println("FAIL:: res1 = " + res1 + ", res3 = " + res3);
        return res2;
    }

    private static boolean isPrimeOpt1(int n)
    {
        int sqrtN = (int) Math.sqrt(n);

        // sqrt(2), sqrt(3) < 2, so n=2, 3 will work.
        for(int i=2; i<=sqrtN; i++)
        {
            if(n % i == 0) return false;
        }
        return true;
    }

    private static boolean isPrimeOpt2(int n)
    {
        int sqrtN = (int) Math.sqrt(n);

        // sqrt(2), sqrt(3) < 2, so n=2, 3 will work.
        List<Integer> primeSieve = SieveOfEratosthenes.primeSieve(sqrtN);
        for(int i: primeSieve)
        {
            if(n % i == 0) return false;
        }

        return true;
    }

    private static boolean isPrimeOpt3(int n)
    {
        int sqrtN = (int) Math.sqrt(n);
        
        if(n == 2 || n == 3) return true;

        if(n % 2 == 0) return false;
        if(n % 3 == 0) return false;

        // check 6*k +/- 1
        // 6*k +0, +2, +4 are divisible by 2
        // 6*k +3 is divisible by 3
        // remaining: 6*k +1 and +5. +5 is equivalent to -1.
        for(int i=6; i<=sqrtN; i+=6)
        {
            if(n % (i+1) == 0) return false;
            if(n % (i+5) == 0) return false;
        }
        return true;

    }

    public static boolean isProbablePrime(int n)
    {
        if(n == 2 || n == 3) return true;

        // Use Fermat's little theorem:
        // (a ** n) mod n = a
        for(int i=0; i<10; i++)
        {
            // generate a random integer between [2, n-1]
            int a = (int) Math.random()*(n-1 - 2 + 1) + 2;

            // compute a ** n mod n
            int m = ModularExponentiation.modPow(a, n, n);
            if(m != a) return false;
        }
        return true;
    }

    public static void main(String [] args)
    {
        runTest(2);
        runTest(3);
        runTest(4);
        runTest(121);
        runTest(11*13);
        runTest(11*17);
        runTest(197);
        runTest(561); //carmichael number
        runTest(8911); //carmichael number
    }

    private static void runTest(int n)
    {
        System.out.printf("n = %4d, isPrime = %5B, isProbablePrime = %5B\n\n", 
            n,
            PrimalityTest.isPrime(n),
            PrimalityTest.isProbablePrime(n));
    }
}