import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Arrays;

public class BoggleBrute
{
    private DiGraph g;
    private HashMap<Integer, Character> v2c = new HashMap<Integer, Character>();
    private HashSet<String> boggleWords = new HashSet<String> ();
    private Set<Integer> cPath = new HashSet<Integer> ();
    private Set<String> dict;
    private int nR;
    private int nC;

    public BoggleBrute(char[][] board, Set<String> dict)
    {
        this.dict = dict;
        /* creates a graph and constructs a map */
        charArr2Graph(board);

        

        StringBuffer sb = new StringBuffer();

        for(int i=0; i<g.V(); i++)
        {
            findWords(i, sb, 0);
        }

        // System.out.println(g.adj(gIdx(2,0)));
        // System.out.println(g.adj(gIdx(1,1)));

    }

    public void findWords(int v, StringBuffer sb, int pos)
    {
        sb.append(v2c.get(v));
        if(dict.contains(sb.toString()))
        {
            boggleWords.add(sb.toString());
        }

        cPath.add(v);
        for(int w: g.adj(v))
        {
            if(!cPath.contains(w))
            {
                findWords(w, sb, pos+1);
            }
        }
        cPath.remove(v);
        sb.deleteCharAt(pos);
    }

    public Iterable<String> words()
    {
        return boggleWords;
    }

    private void charArr2Graph(char[][] arr)
    {
        this.nR = arr.length;
        this.nC = arr[0].length;
        g = new DiGraph(nR * nC);

        for(int r=0; r<nR; r++)
        {
            for(int c=0; c<nC; c++)
            {
                process(r, c, arr);
            }
        }
    }

    private void process(int r, int c, char[][] arr)
    {
        v2c.put(gIdx(r, c), arr[r][c]);

        if((r-1)>=0 && (c-1)>=0)    g.addEdge(gIdx(r, c), gIdx(r-1, c-1));
        if((r-1)>=0 && (c+1)<nC)    g.addEdge(gIdx(r, c), gIdx(r-1, c+1));
        if((r-1)>=0 )               g.addEdge(gIdx(r, c), gIdx(r-1, c));
        if((c-1)>=0 )               g.addEdge(gIdx(r, c), gIdx(r, c-1));
        if((r+1)<nR && (c-1)>=0)    g.addEdge(gIdx(r, c), gIdx(r+1, c-1));
        if((r+1)<nR && (c+1)<nC)    g.addEdge(gIdx(r, c), gIdx(r+1, c+1));
        if((r+1)<nC )               g.addEdge(gIdx(r, c), gIdx(r+1, c));
        if((c+1)<nC )               g.addEdge(gIdx(r, c), gIdx(r, c+1));
    }

    private int gIdx(int r, int c) { return r*nC + c ;}

    public static void main(String [] args)
    {
        runTest(new char[][]{{'G', 'I', 'Z'}, 
                            {'U', 'E', 'K'}, 
                            {'Q', 'X', 'E'}}, 
                            new HashSet<String> (Arrays.asList("GEEK", "QUIZ", "ABCD")));
    }

    private static void runTest(char[][] arr, HashSet<String> dict)
    {
        int nR = arr.length, nC = arr[0].length;
        System.out.println("Board:");
        for(int i=0; i<nR; i++) System.out.println(Arrays.toString(arr[i]));

        System.out.println("dict:" + dict.toString());

        BoggleBrute b = new BoggleBrute(arr, dict);
        System.out.println("words on board:" + b.words());
    }
}