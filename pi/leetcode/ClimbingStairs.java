/* 70. Climbing Stairs DescriptionHintsSubmissionsDiscussSolution Pick One You are climbing a stair
case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Note: Given n will be a positive integer.


Example 1:

Input: 2 Output:  2 Explanation:  There are two ways to climb to the top.

1. 1 step + 1 step 2. 2 steps Example 2:

Input: 3 Output:  3 Explanation:  There are three ways to climb to the top.

1. 1 step + 1 step + 1 step 2. 1 step + 2 steps 3. 2 steps + 1 step */

public class ClimbingStairs
{
    public static int [][] fibMat = new int [][] {{1, 1}, {1, 0}};

    // finding nth fib number is 
    // [x_n_plus_1; x_n] = fibMat**n * [x1; x0]
    // [x2; x1] = fibMat * [x1; x0]
    // [x3; x2] = fibmat**2 * [x1; x0]
    public static int climbStairs(int n)
    {
        if(n==0) return 0;
        int [][] fibMatn = matPow(fibMat, n);

        //fibMatn[1][0] will xn
        return fibMatn[0][0];
    }

    private static int [][] matPow(int [][] mat, int n)
    {
        if(n == 1) return mat;

        int [][] mat_n_by_2 = matPow(mat, n/2);
        int [][] matn = matMult(mat_n_by_2, mat_n_by_2);

        if(n%2 == 1) matn = matMult(matn, mat);

        return matn;
    }

    private static int [][] matMult(int [][] mat1, int [][] mat2)
    {
        int nR1 = mat1.length, nC1 = mat1[0].length;
        int nR2 = mat2.length, nC2 = mat2[0].length;

        int [][] res = new int [nR1][nC2];

        for(int iR1 = 0; iR1<nR1; iR1++)
        {
            for(int iC2 = 0; iC2<nC2; iC2++)
            {
                int sum = 0;
                for(int iC1=0; iC1<nC1; iC1++)
                {
                    sum += mat1[iR1][iC1] * mat2[iC1][iC2];
                }
                res[iR1][iC2] = sum;
            }
        }

        return res;
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
        System.out.printf("n = %d, numWays = %d\n", n, climbStairs(n));
    }
}