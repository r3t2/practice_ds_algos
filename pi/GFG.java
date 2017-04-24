import java.util.Scanner;

class GFG {

  public static long gFib(int a, int b, int c, int n)
  {
    long g_n_1 = 1;
    long g_n_2 = 1;
    long g_n = 1;

    for (int i=3; i<=n; i++)
    {
      g_n = a*g_n_1 + b*g_n_2 + c;
      g_n_2 = g_n_1;
      g_n_1 = g_n;
    }

    return g_n;
  }

  public static void main (String[] args) {
    Scanner sc = new Scanner (System.in);
    int N = sc.nextInt();
    int a, b, c, n, m;

    for(int i=0; i<N; i++)
    {
      a = sc.nextInt();
      b = sc.nextInt();
      c = sc.nextInt();
      n = sc.nextInt();
      m = sc.nextInt();

      long g_n = gFib(a, b, c, n);
      System.out.println(g_n % m);
    }
  }
}