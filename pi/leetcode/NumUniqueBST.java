public class NumUniqueBST
{
    public static int numTrees(int n)
    {
        if(n < 0) return -1;
        if(n == 0) return 0;

        int [] memo = new int [n+1];
        for(int i=0; i<=n; i++) memo[i] = -1;
        memo[0]=1; memo[1] = 1;

        return numTrees(n, memo);
    }

    private static int numTrees(int n, int [] memo)
    {
        if(memo[n] != -1) return memo[n];

        int sum = 0;

        for(int i=1; i<=n; i++)
        {
            int lt = numTrees(i-1, memo);
            int rt = numTrees(n-i, memo);
            sum += lt*rt;
        }

        memo[n] = sum;
        return sum;
    }

    public static void main(String [] args)
    {
        runTest(1);
        runTest(2);
        runTest(3);
        runTest(4);
        runTest(5);
    }

    private static void runTest(int n)
    {
        System.out.printf("n = %d, num unique bst = %d\n", n, NumUniqueBST.numTrees(n));
    }
}