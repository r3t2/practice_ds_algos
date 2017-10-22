/*
Dynamic Programming | Set 37 (Boolean Parenthesization Problem)
Given a boolean expression with following symbols.
Symbols
    'T' ---> true 
    'F' ---> false
And following operators filled between symbols
Operators
    &   ---> boolean AND
    |   ---> boolean OR
    ^   ---> boolean XOR
Count the number of ways we can parenthesize the expression so that the value of expression evaluates to true.
Let the input be in form of two arrays one contains the symbols (T and F) in order and other contains operators (&, | and ^}
Examples:
Input: symbol[]    = {T, F, T}
       operator[]  = {^, &}
Output: 2
The given expression is "T ^ F & T", it evaluates true
in two ways "((T ^ F) & T)" and "(T ^ (F & T))"

Input: symbol[]    = {T, F, F}
       operator[]  = {^, |}
Output: 2
The given expression is "T ^ F | F", it evaluates true
in two ways "( (T ^ F) | F )" and "( T ^ (F | F) )". 

Input: symbol[]    = {T, T, F, T}
       operator[]  = {|, &, ^}
Output: 4
The given expression is "T | T & F ^ T", it evaluates true
in 4 ways ((T|T)&(F^T)), (T|(T&(F^T))), (((T|T)&F)^T) 
and (T|((T&F)^T)).


*/
import java.util.Arrays;

public class BooleanParanthesizationDPPrac
{
  public static final int TRUE = 1;
  public static final int FALSE = 0;

  public static int numWays(boolean [] b, char [] op)
  {
    if(b == null || op == null) throw new IllegalArgumentException();

    if(b.length != op.length + 1) throw new IllegalArgumentException();

    int [][][] dp = new int [b.length][b.length][];
    numWays(b, op, dp, 0, b.length - 1);

    return dp[0][b.length-1][TRUE];
  }

  private static int [] numWays(boolean [] b, char [] op, int [][][] dp, int st, int end)
  {
    if(st > end) throw new IllegalArgumentException();

    if(dp[st][end] != null) return Arrays.copyOf(dp[st][end], 2);

    if(st == end)
    {
      int [] sol = new int [2];
      sol[TRUE] = (b[st] == true) ? 1 : 0;
      sol[FALSE] = (b[st] == false) ? 1 : 0;
      dp[st][end] = sol;
      return Arrays.copyOf(sol, 2);
    }

    int [] solIntLt, solIntRt, solInt;
    int [] solAcc = new int[] {0, 0};

    for(int i = st; i<end; i++)
    {
      solIntLt = numWays(b, op, dp, st, i);
      solIntRt = numWays(b, op, dp, i+1, end);
      solInt = combine(solIntLt, solIntRt, op[i]);

      solAcc[TRUE] += solInt[TRUE];
      solAcc[FALSE] += solInt[FALSE];
    }

    dp[st][end] = solAcc;

    return Arrays.copyOf(solAcc, 2);

  }

  private static int [] combine(int [] lt, int [] rt, char op)
  {
    int [] sol;
    //char AND = '&', OR = '|', XOR = '^';

    switch(op)
    {
      case '&':
        sol = combineAND(lt, rt);
        break;
      case '|':
        sol = combineOR(lt, rt);
        break;
      case '^':
        sol = combineXOR(lt, rt);
        break;
      default:
        throw new IllegalArgumentException();
        
    }

    return sol;
  }

  private static int [] combineXOR(int [] lt, int [] rt)
  {
    int [] sol = new int[2];
    int numTrue = lt[TRUE] * rt[FALSE]
                + lt[FALSE] * rt[TRUE];
    
    int numFalse = lt[TRUE] * rt[TRUE]
                 + lt[FALSE] * rt[FALSE];
    
    sol[TRUE] = numTrue;
    sol[FALSE] = numFalse;

    return sol;
  }

  private static int [] combineOR(int [] lt, int [] rt)
  {
    // System.out.println(Arrays.toString(lt));
    // System.out.println(Arrays.toString(rt));

    int [] sol = new int[2];
    int numTrue = lt[TRUE] * rt[TRUE] 
                + lt[FALSE] * rt[TRUE]
                + lt[TRUE] * rt[FALSE];

    int numFalse = lt[FALSE] * rt[FALSE];

    sol[TRUE] = numTrue;
    sol[FALSE] = numFalse;

    return sol;
  }

  private static int [] combineAND(int [] lt, int [] rt)
  {
    int [] sol = new int[2];
    int numFalse = lt[FALSE] * rt[FALSE] 
                + lt[FALSE] * rt[TRUE]
                + lt[TRUE] * rt[FALSE];

    int numTrue = lt[TRUE] * rt[TRUE];

    sol[TRUE] = numTrue;
    sol[FALSE] = numFalse;

    return sol;
  }

  public static void main(String [] args)
  {
    runTest(new boolean[] {true, false}, new char[] {'|'});
    runTest(new boolean[] {false, false}, new char[] {'|'});

    runTest(new boolean[] {true, true}, new char[] {'&'});
    runTest(new boolean[] {true, false}, new char[] {'&'});

    runTest(new boolean[] {true, false}, new char[] {'^'});
    runTest(new boolean[] {true, true}, new char[] {'^'});

    runTest(new boolean[] {true, true, false, true}, new char[] {'|', '&','^'});
  }

  private static void runTest(boolean [] b, char [] op)
  {
    System.out.println("b = " + Arrays.toString(b));
    System.out.println("op = " + Arrays.toString(op));
    System.out.println("numWays to evaluate to True = " + BooleanParanthesizationDPPrac.numWays(b, op) + "\n");
  }

}
