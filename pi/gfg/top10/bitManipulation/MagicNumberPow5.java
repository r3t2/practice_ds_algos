/*
http://www.geeksforgeeks.org/find-nth-magic-number/

Find nth Magic Number
difficulty : 2.7
A magic number is defined as a number which can be expressed as a power of 5 or sum of unique powers of 5. First few magic numbers are 5, 25, 30(5 + 25), 125, 130(125 + 5), ….

Write a function to find the nth Magic number.

Example:

Input: n = 2
Output: 25

Input: n = 5
Output: 130 
*/


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