import java.util.ArrayList;
import java.util.List;

public class SieveOfEratosthenes
{
    // return an int array with prime numbers between 2 and n-1
    public static List<Integer> primeSieve(int n)
    {
        int sqrtN = (int)Math.sqrt(n);
        boolean [] posPrimes = new boolean[n+1];
        List<Integer> primes = new ArrayList<Integer> ();
        for(int i=0; i<=n; i++) posPrimes[i] = true; // mark all of them as true;

        for(int i=2; i<=sqrtN; i++)
        {
            if(posPrimes[i] == false) continue;
            else
            {
                for(int j=i*i; j<=n; j=j+i)
                {
                    posPrimes[j] = false;
                }
            }
        }

        for(int i=2; i<=n; i++)
        {
            if(posPrimes[i] == true) primes.add(i);
        }

        return primes;
    }

    public static void main(String [] args)
    {
        runTest(2); // smallest prime
        runTest(4); 
        runTest(9); // perfect square
        runTest(11); // ending in prime
        runTest(10); // ending between primes
        runTest(100);
        runTest(200);
    }

    private static void runTest(int n)
    {
        List<Integer> sieve = SieveOfEratosthenes.primeSieve(n);
        System.out.println("n = " + n + 
                            ", num primes = " + sieve.size() +
                            ", sieve = " + sieve);
    }
}