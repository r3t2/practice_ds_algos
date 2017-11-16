#include <stdio.h>


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