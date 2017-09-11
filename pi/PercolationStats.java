import java.util.Random;

public class PercolationStats
{
	private Random rand = new Random();

	private int trialNum = 0;

	private double mean = 0, stdDev = 0, confLo = 0, confHi = 0;
	// perform trials independent experiments on an n-by-n grid
	private double[] fracOpenSites;

	public PercolationStats(int n, int trials)
	{
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
			p.open(rand.nextInt(n), rand.nextInt(n));
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

	public double stdDev()
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
		System.out.println("stdDev = " + ps.stdDev());
		System.out.println(String.format("95pct confidence interval = [%f, %f]", ps.confidenceLo(), ps.confidenceHi()));
	}
}