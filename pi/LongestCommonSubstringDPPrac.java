/*
Given two strings ‘X’ and ‘Y’, find the length of the longest common substring.
Examples :
Input : X = "GeeksforGeeks", y = "GeeksQuiz"
Output : 5
The longest common substring is "Geeks" and is of
length 5.

Input : X = "abcdxyz", y = "xyzabcd"
Output : 4
The longest common substring is "abcd" and is of
length 4.

Input : X = "zxabcdezy", y = "yzabcdezx"
Output : 6
The longest common substring is "abcdez" and is of
length 6.

*/


public class LongestCommonSubstringDPPrac
{
    public static String longestCommonSubstringDP(String X, String Y)
    {
        int nX = X.length();
        int nY = Y.length();

        int max=0, maxX=-1, maxY=-1;

        int[][] sol = new int[nX][nY];

        for(int iX = 0; iX<nX; iX++)
        {
            for(int iY = 0; iY<nY; iY++)
            {
                if(iX == 0 || iY == 0)
                {
                    if(X.charAt(iX) == Y.charAt(iY)) sol[iX][iY] = 1;
                    else sol[iX][iY] = 0;
                }
                else
                {
                    if(X.charAt(iX) == Y.charAt(iY)) sol[iX][iY] = sol[iX-1][iY-1] + 1;
                    else sol[iX][iY] = 0;
                }

                if(max < sol[iX][iY])
                {
                    max = sol[iX][iY]; maxX = iX; maxY = iY;
                }
            }
        }


        if(maxX == -1) return "";
        else
        {
            return X.substring(maxX - max + 1, maxX+1);
        }

    }

    public static void main(String [] args)
    {
        runTest("abcdhelloxyz", "mnhellow");

        runTest("GeeksforGeeks", "GeeksQuiz");

        runTest("abcd", "efgh");

        runTest("abcd", "abcd");

        runTest("a", "a");

        runTest("a", "b");

        runTest("", "b");

        runTest("", "");
    }

    private static void runTest(String X, String Y)
    {
        System.out.printf("str1 = %s, str2 = %s, lcSubString = %s\n",
            X, Y, LongestCommonSubstringDPPrac.longestCommonSubstringDP(X,Y));
    }

}
