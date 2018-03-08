import java.util.*;

public class BoyerMooreMajority
{
    // returns true only if there is a number that is strict majority
    public static MajorityReturnVal majority(int [] in)
    {
        if(in.length == 0) throw new IllegalArgumentException();
        if(in.length == 1) return new MajorityReturnVal(true, in[0]);
        int cand = majorityCandidate(in);
        int cnt = 0;
        for(int x: in)
        {
            if(x == cand) cnt++;
        }
        if(in.length%2 == 0 && cnt > in.length/2) return new MajorityReturnVal(true, cand);
        else if(in.length%2 == 1 && cnt >= (in.length+1)/2) return new MajorityReturnVal(true, cand);
        else return new MajorityReturnVal(false, 0);

    }

    private static int majorityCandidate(int [] in)
    {
        int mIdx = 0, i = 1;
        int cnt = 1;
        
        for(; i<in.length; i++)
        {
            if(in[mIdx] == in[i]) cnt++;
            else cnt--;
            if(cnt == 0)
            {
                mIdx = i;
                cnt = 1;
            }
        }

        return in[mIdx];
    }

    public static class MajorityReturnVal
    {
        public boolean isMajority;
        public int majority;

        private MajorityReturnVal(boolean isMajority, int cand)
        {
            this.isMajority = isMajority;
            this.majority = cand;
        }

        public String toString()
        {
            return "isMajority = " + isMajority + ", majority = " + majority;
        }
    }

    public static void main(String [] args)
    {
        runTest(new int [] {1,2,3,4});
        runTest(new int [] {1,2,1,1});
        runTest(new int [] {1,2,2,1});
        runTest(new int [] {1,2,1});
        runTest(new int [] {1,2,2,2});
        runTest(new int [] {1,2,2,2,1});
        runTest(new int [] {1,2,1,1,3});
        runTest(new int [] {1,2,1,1,3,4});
    }
    private static void runTest(int [] x)
    {
        System.out.println("input = " + Arrays.toString(x) + "\n" + majority(x) +"\n");
    }
}