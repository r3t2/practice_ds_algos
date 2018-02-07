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
                sum = (sum + mat[iR][iC]*col[iC]) % MOD;
            }
            ret[iR] = sum;
        }

        return ret;
    }

    public static void main(String [] args)
    {
        runTest(1);
        runTest(2);
        runTest(3);
        runTest(4);
        runTest(100);
    }

    private static void runTest(int n)
    {
        System.out.printf("n = %5d, numWays = %5d\n", n, checkRecord(n));
    }
}