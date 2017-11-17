/*
Rotate bits of a number
2.5
Bit Rotation: A rotation (or circular shift) is an operation similar to shift except that the bits that fall off at one end are put back to the other end.

In left rotation, the bits that fall off at left end are put back at right end.

In right rotation, the bits that fall off at right end are put back at left end.
*/
public class RotatedShift
{
    public static final int NUM_BITS = 8;
    public static final int MASK = 0xFF;
    public static int rotateLeft(int a, int n)
    {

        return ((a << n) | ((a & MASK) >> (NUM_BITS -n)));

    }

    public static int rotateRight(int a, int n)
    {

        return ((a >> n) | ((a & MASK) << (NUM_BITS -n)));

    }

    public static void main(String [] args)
    {
        runTest(1, 1);
        runTest(3, 2);
    }

    private static void runTest(int x, int n)
    {
        System.out.printf("x = %02x, n = %d\n", x, n);
        System.out.printf("rotateRight = %02x, rotateLeft = %02x\n", RotatedShift.rotateRight(x, n)
                                                                    , RotatedShift.rotateLeft(x, n));
    }
}