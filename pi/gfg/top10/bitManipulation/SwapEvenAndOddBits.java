/*
wap all odd and even bits
3.1
Given an unsigned integer, swap all odd bits with even bits. 
For example, if the given number is 23 (00010111), it should be converted to 43 (00101011). 
Every even position bit is swapped with adjacent bit on right side (even position bits are 
highlighted in binary representation of 23), and every odd position bit is swapped with adjacent on left side.
*/
public class SwapEvenAndOddBits
{
    private static final int NUM_BITS = 8;
    private static final int EVEN = 0xAA;
    private static final int ODD = 0x55;

    public static int swapEvenOddBits(int x)
    {
        int xOdd = x & ODD;
        int xEven = x & EVEN;

        return (xOdd << 1) | (xEven >> 1);
    }

    public static void main(String [] args)
    {
        int x = 0;
        x = 0; System.out.printf("x = %02x, evenOddSwapped = %02x \n", x, SwapEvenAndOddBits.swapEvenOddBits(x));
        x = 1; System.out.printf("x = %02x, evenOddSwapped = %02x \n", x, SwapEvenAndOddBits.swapEvenOddBits(x));
        x = 16; System.out.printf("x = %02x, evenOddSwapped = %02x \n", x, SwapEvenAndOddBits.swapEvenOddBits(x));
        x = 0b10101010; System.out.printf("x = %02x, evenOddSwapped = %02x \n", x, SwapEvenAndOddBits.swapEvenOddBits(x));
        x = 0b01010101; System.out.printf("x = %02x, evenOddSwapped = %02x \n", x, SwapEvenAndOddBits.swapEvenOddBits(x));
    }
}