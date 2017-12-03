
/*Implement n_C_k computation as a DP algorithm*/
/* Use n_C_k = n-1_C_k-1 + n-1_C_k */

public class NChooseKDP
{
    /* returns int. simplistic. should be OK for small values of n and k*/
    public static int nChooseK(int n, int k)
    {
        int [][] nck = new int [n+1][k+1];
        for(int ni = 0; ni <= n; ni++) nck[ni][0] = 1; // n_C_0 = 1
        for(int ni = 0; ni <= k; ni++) nck[ni][ni] = 1; // n_C_n = 1

        for(int ni = 2; ni <= n; ni++)
        {
            for(int ki = 1; ki < ni && ki <= k; ki++)
            {
                nck[ni][ki] = nck[ni-1][ki-1] + nck[ni-1][ki];
            }
        }

        return nck[n][k];
    }

    public static int nChooseKModp(int n, int k, int p)
    {
        int [][] nck = new int [n+1][k+1];
        for(int ni = 0; ni <= n; ni++) nck[ni][0] = 1; // n_C_0 = 1
        for(int ni = 0; ni <= k; ni++) nck[ni][ni] = 1; // n_C_n = 1

        for(int ni = 2; ni <= n; ni++)
        {
            for(int ki = 1; ki < ni && ki <= k; ki++)
            {
                nck[ni][ki] = ( (nck[ni-1][ki-1] % p) + (nck[ni-1][ki] % p) ) % p;
            }
        }

        return nck[n][k];
    }

    public static void main(String [] args)
    {
        runTest(5, 3, 9);
        runTest(0, 0, 9);
        runTest(4, 0, 9);
        runTest(100, 80, 8);
        runTest(10, 8, 8);
    }

    private static void runTest(int n, int k, int p)
    {
        System.out.printf("n = %d, k = %d, p = %d \n", n, k ,p);
        System.out.printf("nCk = %d, nCk mod p = %d \n\n",
            NChooseKDP.nChooseK(n, k), 
            NChooseKDP.nChooseKModp(n, k, p));
    }
}