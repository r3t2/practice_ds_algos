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
    public String longestCommonSubstringDP(String X, String Y)
    {
        int nX = X.size();
        int nY = Y.size();

        int max=0, maxX=-1, maxY=-1;

        int[][] sol = new int[nX][nY];
        for(iX=0; iX<nX; iX++)
        {
            if(X.charAt[iX] == Y.charAt[0]) sol[iX][0] = 1;
            else    sol[iX][0] = 0;
            if(max < sol[iX][0])
            {
                max = sol[iX][0]; maxX = iX; maxY = 0;
            }
        }
        for(iY = 0; iY<nY; iY++)
        {
            if(X.charAt[0] == Y.charAt[iY]) sol[0][iY] = 1;
            sol[0][iY] = 0;
            if(max < sol[0][iY])
            {
                max = sol[0][iY]; maxX = 0; maxY = iY;
            }
        }

        for(iX = 1; iX<nX; iX++)
        {
            for(iY = 1; iY<nY; iY++)
            {
                if(X.charAt[iX] == Y.charAt[iY]) sol[iX][iY] = sol[iX-1][iY-1] + 1;
                else sol[iX][iY] = 0;
                if(max < sol[iX][iY])
                {
                    max = sol[iX][iY]; maxX = iX; maxY = iY;
                }
            }
        }

        if(maxX == -1) return null;
        else
        {
            return X.subString(maxX - max + 1, maxX);
        }

    }

    public static void main(String [] args)
    {
        
    }

}
