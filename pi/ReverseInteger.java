public class ReverseInteger
{
  public static int reverse(int in)
  {
    int sign = 1 - 2*((in<0 ? 1: 0));

    in = Math.abs(in);
    int out = 0, in_10 = 0;
    while(in != 0)
    {
      in_10 = in % 10;
      if( (Integer.MAX_VALUE - in_10)/10 < out)
        return 0;
      out = out*10 + (in % 10);
      in = in / 10;
    }

    return sign*out;

  }

  public static void main(String[] args)
  {
    System.out.println( reverse(Integer.parseInt(args[0])));
  }
}