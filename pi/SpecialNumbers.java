public class SpecialNumbers
{
	/**
	* The following method computes the number of numbers starting and ending with same digit within the range [0, N]
	*/
	public static int numNumbersStartEndSameDigit(int N)
	{
		int k = (int) Math.floor(Math.log10(N));
		int k10 = (int) Math.pow(10, k);

		int MSD = N/k10;
		int LSD = N % 10;

		int sum = 9 + (int) Math.pow(10, k-1);
		sum = sum + (int) ((MSD -1) * Math.pow(10, k-1));

		N = N - MSD*k10;
		if (N>10)
		{
			N = N/10;
		}

		if(LSD<MSD)
		{
			N = N-1;
		}

		sum = sum + N;
		return sum;
	}

	public static long nthNum1121231234seq(long N)
	{
		long k = (long) ((Math.sqrt(1+8*(N-1)) -1) / 2.0);
		long k_base = k * (k+1)/2;
		return N - k_base;
	}

	public static void main(String[] args)
	{
		int N;
		N = 9; System.out.println(String.format("N = %d, result = %d", N, numNumbersStartEndSameDigit(N) ));
		N = 7236; System.out.println(String.format("N = %d, result = %d", N, numNumbersStartEndSameDigit(N) ));
		N = 10; System.out.println(String.format("N = %d, result = %d", N, numNumbersStartEndSameDigit(N) ));
		N = 100; System.out.println(String.format("N = %d, result = %d", N, numNumbersStartEndSameDigit(N) ));
		N = 102; System.out.println(String.format("N = %d, result = %d", N, numNumbersStartEndSameDigit(N) ));

		long N1;
		N1 = 9; System.out.println(String.format("N = %d, result = %d", N1, nthNum1121231234seq(N1) ));
		N1 = 7236; System.out.println(String.format("N = %d, result = %d", N1, nthNum1121231234seq(N1) ));
		N1 = 10; System.out.println(String.format("N = %d, result = %d", N1, nthNum1121231234seq(N1) ));
		N1 = 100; System.out.println(String.format("N = %d, result = %d", N1, nthNum1121231234seq(N1) ));
		N1 = 102; System.out.println(String.format("N = %d, result = %d", N1, nthNum1121231234seq(N1) ));
	}

}