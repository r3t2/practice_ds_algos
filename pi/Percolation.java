import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation
{
	private static final boolean CLOSE = false;
	private static final boolean OPEN = true;

	private int n; /*size of the grid*/

	private boolean [][] grid; /*n x n grid*/

	private WeightedQuickUnionUF uf; /*reference to the union find datastructure*/

	private int numOpen = 0;

	public Percolation(int n)
	{
		uf = new WeightedQuickUnionUF(n*n+2);

		grid = new boolean[n][n];

		for(int i=0;i<n;i++)
		{
			for(int j=0; j<n; j++)
			{
				grid[i][j] = CLOSE;
			}
		}

		/*last two entries will be unioned with first and last rows respectively*/
		for(int j=0; j<n; j++)
		{
			uf.union(grid2LinIdx(0,j), n*n);
			uf.union(grid2LinIdx(n-1,j), n*n+1);
		}

		System.out.println("end of constructor @ " + uf.connected(n*n, n*n+1));
	}

	private int grid2LinIdx(int i, int j)
	{
		return n*i+j;
	}

	/*open site (row, col) if it is not open already*/
	public void open(int row, int col)
	{
		System.out.println(uf.connected(n*n, n*n+1));
		if(grid[row][col] == CLOSE)
		{
			grid[row][col] = OPEN;
			numOpen++;
		}

		if((row-1) >= 0 && grid[row-1][col] == OPEN) uf.union(grid2LinIdx(row-1, col), grid2LinIdx(row, col));
		if((row+1) < n && grid[row+1][col] == OPEN) uf.union(grid2LinIdx(row+1, col), grid2LinIdx(row, col));
		if((col-1) >= 0 && grid[row][col-1] == OPEN) uf.union(grid2LinIdx(row, col-1), grid2LinIdx(row, col));
		if((col+1) < n && grid[row][col+1] == OPEN) uf.union(grid2LinIdx(row, col+1), grid2LinIdx(row, col));
		System.out.println(uf.connected(n*n, n*n+1));
	}

	/*is site (row, col) open?*/
	public boolean isOpen(int row, int col)
	{
		return grid[row][col]==OPEN;
	}
	
	/* is site (row, col) full? */
	public boolean isFull(int row, int col)
	{
		return grid[row][col] == CLOSE;
	}

	// number of open sites
	public int numberOfOpenSites()
	{
		return numOpen;
	}

	// does the system percolate?
	public boolean percolates()
	{
		return uf.connected(n*n, n*n+1);
	}







	public static void main(String[] args)
	{
		Percolation p = new Percolation(5);
		System.out.println(p.percolates());
		p.open(0,0);
		System.out.println(p.percolates());
		System.out.println(p.numberOfOpenSites());
	}
}