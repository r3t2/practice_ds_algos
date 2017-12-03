#include <stdio.h>
/*
Brian Kernighan’s Algorithm:
Subtraction of 1 from a number toggles all the bits (from right to left) till the rightmost set bit(including the righmost set bit). So if we subtract a number by 1 and do bitwise & with itself (n & (n-1)), we unset the righmost set bit. If we do n & (n-1) in a loop and count the no of times loop executes we get the set bit count.
Beauty of the this solution is number of times it loops is equal to the number of set bits in a given integer.
*/

int count_set_bits(int x)
{
    int cnt = 0;
    while(x!=0)
    {
        x = x & (x-1);
        cnt++;
    }
    return cnt;
}

void run_test(int x)
{
    printf("input = %04x\n", x);
    printf("set bits = %d\n\n", count_set_bits(x));
}
void main()
{
    run_test(4);
    run_test(7);
    run_test(15);
}