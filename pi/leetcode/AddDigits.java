/*
258. Add Digits
DescriptionHintsSubmissionsDiscussSolution
DiscussPick One
Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

For example:

Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.

Follow up:
Could you do it without any loop/recursion in O(1) runtime?
*/
class AddDigits {
    /*complexity is log*(n) */
    /* log* is also called iterative log */
    /* original val --> x, num digits --> log(x)*/
    /* max val after addition--> 9*log(x), num digits --> log(9*log(x))*/
    /* max val after addition--> 9*log(log(x)), num digits --> log(log(9*log(x)))*/
    /*  ..... log*(x) times */
    public int addDigits(int num) {
        if(num < 10) return num;
        int S = 0;
        
        while(num != 0)
        {
            S += num % 10;
            num = num/10;
        }
        
        return addDigits(S);
    }
}