public class StudentAttendanceRecordII
{
    private static final int MOD = 1000*1000*1000 + 7;
    private static final int [][] transitionMatrix = new int [][] {
        {1, 1, 1, 0, 0, 0},
        {1, 0, 0, 0, 0, 0},
        {0, 1, 0, 0, 0, 0},
        {1, 1, 1, 1, 1, 1},
        {0, 0, 0, 1, 0, 0},
        {0, 0, 0, 0, 1, 0}
    };

    public static int checkRecord(int n)
    {
        if(n<1) return 0;
        if(n==1) return 3;

        int [][] matPowN = matrixModPow(transitionMatrix, n-1);
        int [] sol = new int[] {1, 1, 0, 1, 0, 0};

        sol = matrixMultMod(matPowN, sol);
        int sum = 0;
        for(int i=0; i<sol.length; i++)
        {
            sum = (sum + sol[i]) % MOD;
            if(sum <= 0) sum = (sum + MOD) % MOD;
        }

        return sum;

    }
    private static int[][] matrixModPow(int [][] mat, int n)
    {
        if(n==1) return mat;

        int [][] matNby2 = matrixModPow(mat, n/2);
        int [][] matN = matrixMultMod(matNby2, matNby2);

        //if n is odd
        if(n%2 == 1)
        {
            matN = matrixMultMod(matN, mat);
        }

        return matN;
    }
    private static int [][] matrixMultMod(int [][] mat1, int [][] mat2)
    {
        int nR1 = mat1.length, nC1 = mat1[0].length;
        int nR2 = mat2.length, nC2 = mat2[0].length;

        if(nC1 != nR2) throw new IllegalArgumentException();


        int [][] res = new int[nR1][nC2];
        for(int iC2=0; iC2<nC2; iC2++)
        {
            for(int iR = 0; iR<nR1; iR++)
            {
                long sum = 0;
                for(int iC1=0; iC1<nC1; iC1++)
                {
                    long mul = ((mat1[iR][iC1])*(mat2[iC1][iC2])) % MOD;
                    if(mul <= 0) mul = (mul + MOD) % MOD;
                    sum = (sum + mul) % MOD;
                    if(sum<=0) sum = (sum + MOD) % MOD;
                }
                res[iR][iC2] = (int)sum;
            }
        }

        return res;
    }

    private static int [] matrixMultMod(int [][] mat, int [] col)
    {
        int nR = mat.length, nC = mat[0].length;
        if(nC != col.length) throw new IllegalArgumentException();

        int [] ret = new int [nR];

        for(int iR =0; iR<nR; iR++)
        {
            int sum=0;
            for(int iC=0; iC<nC; iC++)
            {
                // long mul = (mat[iR][iC]*col[iC])%MOD;
                // if(mul <=0) mul = (mul + MOD) % MOD;
                sum = (sum + mat[iR][iC]*col[iC]) % MOD;
                // sum = (sum + ((int)mul)) % MOD;
                // if(sum <=0) sum = (sum + MOD)%MOD;
            }
            ret[iR] = sum;
        }

        return ret;
    }



    public static int checkRecordLinear(int n)
    {
        // for n=1:
        // dpCol: d_A_L_LL
        // d_0_0_0 = 1 // P
        // d_0_1_0 = 1 // L
        // d_0_0_1 = 0 // LL not possible
        // d_1_0_0 = 1 // A
        // d_1_1_0 = 0 // AL not possible
        // d_1_0_1 = 0 // ALL not possible
        int [] dpCol = new int[] {1, 1, 0, 1, 0, 0};
        for(int i=1; i<n; i++)
        {
           dpCol = matrixMultMod(transitionMatrix, dpCol);
        }

        int sum = 0;
        for(int i=0; i<dpCol.length; i++)
        {
            sum = (sum + dpCol[i]) % MOD;
        }

        return sum;
    }
    public static void main(String [] args)
    {
        runTest(1);
        runTest(2);
        runTest(3);
        runTest(4);
        runTest(25);
        runTest(50);
        runTest(128);
        runTest(100);
    }

    private static void runTest(int n)
    {
        System.out.printf("n = %5d, numWays = %10d, numWaysLin = %10d\n", n, checkRecord(n), checkRecordLinear(n));
    }
}