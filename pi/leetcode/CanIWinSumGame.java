import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Arrays;

public class CanIWinSumGame
{
    public static boolean canIWin(int n, int t)
    {
        int [] sel = new int[n+1];
        for(int i=0; i<=n; i++) sel[i] = 0;

        if(t > n*(n+1)/2) return false;
        if(t<=0) return true;

        HashMap<Integer, Integer> h = new HashMap<Integer, Integer> ();

        int ret = bestScore(sel, t, h, 0);

        System.out.printf("hashmap size = %d\n", h.size());

        return ret == 1;
    }

    private static int bestScore(int [] sel, int t, HashMap<Integer, Integer> h, int player)
    {
        int key = Arrays.hashCode(sel);
        int n = sel.length-1;
        if(h.containsKey(key)) return h.get(key);

        int maxScore = -2;
        int minScore = +2;

        for(int r=1; r<=n; r++)
        {
            if(sel[r] != 0) continue;

            sel[r] = 1;
            sel[0] += r;

            if(player == 0 && sel[0] >= t){ sel[r] = 0; sel[0]-= r; maxScore = +1; break; }
            if(player == 1 && sel[0] >= t){ sel[r] = 0; sel[0]-= r; minScore = -1; break; }

            int res = bestScore(sel, t, h, 1-player);

            if(player == 0)
            {
                if(res > maxScore) maxScore = res;
                if(maxScore == 1) {sel[r] = 0; sel[0]-= r; break;}
            }
            else
            {
                if(res < minScore) minScore = res;
                if(minScore == -1) {sel[r] = 0; sel[0]-= r; break;}
            }

            
            sel[0] -= r;
            sel[r] = 0;
            
        }


        if(player == 0)
        {
            h.put(Arrays.hashCode(sel), maxScore);
            return maxScore;
        }
        else
        {
            h.put(Arrays.hashCode(sel), minScore);
            return minScore;
        }
    }
    public static boolean canIWin2(int n, int t)
    {
        State s = new State();
        for(int i=1; i<=n; i++) s.remaining.add(i);
        s.player = 0;
        HashMap<State, Integer> h = new HashMap<State, Integer>();

        s.updateHashCode();
        int best = bestScore(s, t, h, -2);
        System.out.printf("hashmap size = %d\n", h.size());
        return best == 1;
    }

    private static int bestScore(State s, int t, HashMap<State, Integer> h, int alpha)
    {
        if(h.containsKey(s)) return h.get(s).intValue();
        int maxScore = alpha;
        int minScore = +2;
        for(int r: s.remaining)
        {
            State sNew = new State(s);
            sNew.remaining.remove(r);
            sNew.player = 1 - s.player;

            sNew.total += r;


            if(s.player == 0 && sNew.total >= t) {maxScore = +1; break;}
            if(s.player == 1 && sNew.total >= t) {minScore = -1; break;}

            sNew.updateHashCode();
            int ret = bestScore(sNew, t, h, alpha);

            if(s.player == 0)
            {
                if(ret > maxScore)
                {
                    maxScore = ret;
                    alpha = maxScore;
                }
                if(maxScore == +1) break;
            }
            else
            {
                minScore = Math.min(minScore, ret);
                if(minScore == -1) break;
                if(minScore <= alpha) break;
            }
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
        runTest(4, 15);
        runTest(4, 10);
        runTest(8, 35);
        runTest(8, 64);
        runTest(16, 79);
        runTest(16, 35);
    }

    private static void runTest(int n, int t)
    {
        long st = System.currentTimeMillis();
        System.out.printf("n = %2d, t = %3d, can win 2= %-5b, time taken = %d\n", 
            n, t, CanIWinSumGame.canIWin2(n, t), System.currentTimeMillis()-st);

        st = System.currentTimeMillis();
        System.out.printf("n = %2d, t = %3d, can win  = %-5b, time taken = %d\n\n", 
            n, t, CanIWinSumGame.canIWin(n, t), System.currentTimeMillis()-st);
    }
}