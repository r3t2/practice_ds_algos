import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats
{

	private int trialNum = 0;

	private double mean = 0, stdDev = 0, confLo = 0, confHi = 0;
	// perform trials independent experiments on an n-by-n grid
	private double[] fracOpenSites;

	public PercolationStats(int n, int trials)
	{
		if(n<=0 || trials<=0) throw new IllegalArgumentException();
		
		fracOpenSites = new double[trials];

		for(int i=0; i<trials; i++)
		{
			runTrial(n);
			trialNum++;
		}

		calcStats();
	}

	private void runTrial(int n)
	{
		Percolation p = new Percolation(n);
		while(!p.percolates())
		{
			/* valid range is 1 - n */
			p.open(StdRandom.uniform(1, n+1), StdRandom.uniform(1, n+1));
		}

		fracOpenSites[trialNum] = p.numberOfOpenSites()/((double) n*n);
		
	}

	private void calcStats()
	{
		calcMean();
		calcStdDev();
		calcConfIntv();
	}

	private void calcMean()
	{
		double acc = 0;

		for(int i=0; i<trialNum; i++)
		{
			acc += fracOpenSites[i];
		}

		mean = acc/trialNum;
	}

	private void calcStdDev()
	{
		double acc = 0;

		for(int i=0; i<trialNum; i++)
		{
			acc += Math.pow((fracOpenSites[i] - mean), 2);
		}

		stdDev = Math.sqrt(acc/(trialNum - 1));
	}

	private void calcConfIntv()
	{
		confLo = mean - 1.96*stdDev/Math.sqrt(trialNum);
		confHi = mean + 1.96*stdDev/Math.sqrt(trialNum);
	}

	public double mean()
	{
		return mean;
	}

	public double stddev()
	{
		return stdDev;
	}

	public double confidenceLo()
	{
		return confLo;
	}

	public double confidenceHi()
	{
		return confHi;
	}

	public static void main(String[] args)
	{
		int n = Integer.parseInt(args[0]);
		int trials = Integer.parseInt(args[1]);

		PercolationStats ps = new PercolationStats(n, trials);

		System.out.println("mean = " + ps.mean());
		System.out.println("stdDev = " + ps.stddev());
		System.out.println(String.format("95pct confidence interval = [%f, %f]", ps.confidenceLo(), ps.confidenceHi()));
	}
}