/*
Count number of bits to be flipped to convert A to B
1.8
Given two numbers ‘a’ and b’. Write a program to count number of bits needed to be flipped to convert ‘a’ to ‘b’.

Example :

Input : a = 10, b = 20
Output : 4
Binary representation of a is 00001010
Binary representation of b is 00010100
We need to flip highlighted four bits in a
to make it b.

Input : a = 7, b = 10
Output : 3
Binary representation of a is 00000111
Binary representation of b is 00001010
We need to flip highlighted three bits in a
to make it b.
*/
public class CountBitsFlippedA2B
{
    public static int countSetBits(int x)
    {
        int cnt = 0;
        while(x!=0)
        {
            x = x & (x-1);
            cnt++;
        }

        return cnt;
    }
    public static int countBitsToBeFlipped(int a, int b)
    {
        return countSetBits(a^b);
    }

    public static void main(String [] args)
    {
        runTest(1, 2);
        runTest(1, 3);
        runTest(4, 8);
    }
    private static void runTest(int a, int b)
    {
        System.out.printf("a = %04x, b = %04x, bits to be flipped = %d\n", a, b, CountBitsFlippedA2B.countBitsToBeFlipped(a,b));
    }
}