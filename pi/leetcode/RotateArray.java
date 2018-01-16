import java.util.Arrays;
public class RotateArray
{
/*189. Rotate Array
Rotate an array of n elements to the right by k steps.

For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].

Note:
Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.

[show hint]

Related problem: Reverse Words in a String II*/

public static void rotate(int [] x, int k)
{
    int N = x.length;
    if(N<=1) return;
    if(k%N==0) return;

    int start = 0;
    int moves = 0;

    while(moves < N)
    {
        int i = start;
        int j = (i+k)%N;
        int ti = x[i], tj;
        do
        {
            tj = x[j];
            x[j] = ti;
            ti = tj;
            i = (i+k)%N;
            j = (j+k)%N;
            moves += 1;
        }while(i != start);
        start+=1;
    }
}

public static void main(String[] args)
{
    runTest(new int []{1,2,3,4,5,6,7}, 3);
    runTest(new int []{1,2,3,4,5,6}, 3);
    runTest(new int []{1,2,3,4,5,6}, 6);
    runTest(new int []{}, 6);
    runTest(new int []{1}, 6);
    runTest(new int []{1,2}, 3);
}

private static void runTest(int [] x, int k)
{
    System.out.printf("input = %s, k = %d\n", Arrays.toString(x), k);
    RotateArray.rotate(x,k);
    System.out.printf("output = %s\n\n", Arrays.toString(x));
}

}