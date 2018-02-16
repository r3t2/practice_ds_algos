public class Ch5p01InsertMintoN
{
    // insert M into N such that M starts at bit j and ends i (inclusive assumed)
    public static int insertBits(int M, int N, int j, int i)
    {
        System.out.printf("-1 <<  j+1 =%02d = %s\n", j+1, intToBin((-1<<j+1)));
        System.out.printf("-1 >>> 32-i=%02d = %s\n", 32-i, intToBin((-1>>>(32-i))));
        int clear_mask_low = i==0?0:(-1 >>> (32-i)); // this is because the shift amount is truncated to 5 bits (or modulo 32).
        int clear_mask_high = j==31?0:(-1 << (j+1));
        int clear_mask = clear_mask_high | clear_mask_low;
        N = N & clear_mask;
        N = N | (M << i);
        return N;
    }

    public static void main(String [] args)
    {
        runTest(0b00001110, 0b10111101, 3, 0);
        runTest(0b00001110, 0b10111101, 7, 4);
        runTest(0b00001110, 0b10111101, 31, 28);
    }
    private static void runTest(int M, int N, int j, int i)
    {
        System.out.printf("j = %d,      i = %d\n", j, i);
        System.out.printf("N              = %s\n", intToBin(N));
        System.out.printf("M              = %s\n", intToBin(M));
        System.out.printf("MinsertN       = %s\n\n", intToBin(Ch5p01InsertMintoN.insertBits(M, N, j, i)));
    }

    private static String intToBin(int x)
    {
        String s = String.format("%32s",Integer.toBinaryString(x)).replace(" ", "0");
        // return s.substring(s.length()-16, s.length());
        return s;
    }
}