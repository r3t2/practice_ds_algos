import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;

public class CanIWinSumGame
{
    public static boolean canIWin(int n, int t)
    {
        State s = new State();
        for(int i=1; i<=n; i++) s.remaining.add(i);
        s.player = 0;
        HashMap<State, Integer> h = new HashMap<State, Integer>();

        s.updateHashCode();
        int best = bestScore(s, t, h);
        return best == 1;
    }

    private static int bestScore(State s, int t, HashMap<State, Integer> h)
    {
        if(h.containsKey(s)) return h.get(s).intValue();
        int maxScore = -2;
        int minScore = +2;
        for(int r: s.remaining)
        {
            State sNew = new State(s);
            sNew.remaining.remove(r);
            sNew.player = 1 - s.player;

            sNew.total += r;


            if(s.player == 0 && sNew.total >= t) return +1;
            if(s.player == 1 && sNew.total >= t) return -1;

            sNew.updateHashCode();
            int ret = bestScore(sNew, t, h);

            if(s.player == 0)   maxScore = Math.max(maxScore, ret);
            else                minScore = Math.min(minScore, ret);
        }

        if(s.player == 0)
        {
            h.put(s, maxScore);
            return maxScore;
        }
        else
        {
            h.put(s, minScore);
            return minScore;
        }
    }

    private static class State
    {
        private int total = 0;

        private Set<Integer> remaining = new HashSet<Integer> ();

        private int player = 0;

        private int hashcode = 0;

        private State() {};

        private State(State s)
        {
            this.total = s.total;
            this.remaining = new HashSet<Integer>(s.remaining);
            this.player = s.player;
            this.hashcode = s.hashcode;
        }

        private void updateHashCode()
        {
            hashcode = 31*total + player;
            hashcode += 31*hashcode + remaining.hashCode();
        }
        public int hashCode()
        {
            return hashcode;
        }

        public boolean equals(Object o)
        {
            if(this == o) return true;
            if(o == null) return false;

            if(this.getClass() != o.getClass()) return false;

            State s = (State) o;

            if(this.total != s.total) return false;
            if(! this.remaining.equals(s.remaining)) return false;
            if(this.player != s.player) return false;

            return true;
        }
    }


    public static void main(String [] args)
    {
        runTest(4, 4);
        runTest(4, 5);
        runTest(10, 11);
        runTest(10, 15);
        runTest(18, 79);
    }

    private static void runTest(int n, int t)
    {
        long st = System.currentTimeMillis();
        System.out.printf("n = %d, t = %d, can win = %b, time taken = %d\n", 
            n, t, CanIWinSumGame.canIWin(n, t), System.currentTimeMillis()-st);
    }
}