public class MagicNumberPow5
{
    public static long getMagicNumberPow5(int k)
    {
        long sum = 0;
        int b = 0;
        int i = 1;

        while(k != 0)
        {
            b = k % 2;
            k = k >> 1;

            sum += Math.pow(b*5, i);
            i++;
        }

        return sum;
    }

    public static void main(String [] args)
    {
        int k;
        k = 4; System.out.printf("k=%d magic number = %d\n", k, MagicNumberPow5.getMagicNumberPow5(k));
        k = 1; System.out.printf("k=%d magic number = %d\n", k, MagicNumberPow5.getMagicNumberPow5(k));
        k = 5; System.out.printf("k=%d magic number = %d\n", k, MagicNumberPow5.getMagicNumberPow5(k));
        k = 2; System.out.printf("k=%d magic number = %d\n", k, MagicNumberPow5.getMagicNumberPow5(k));
        k = 0; System.out.printf("k=%d magic number = %d\n", k, MagicNumberPow5.getMagicNumberPow5(k));
    }
}