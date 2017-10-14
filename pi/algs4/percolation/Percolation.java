import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation
{
	private static final boolean BLOCKED = false;
	private static final boolean OPEN = true;

	private static int HEAD;
	private static int TAIL;

	private int n; /*size of the grid*/

	private boolean [][] grid; /*n x n grid*/

	private WeightedQuickUnionUF uf; /*reference to the union find datastructure*/

	private int numOpen = 0;

	public Percolation(int n)
	{
		if(n<=0) throw new java.lang.IllegalArgumentException();

		this.n = n;

		uf = new WeightedQuickUnionUF(n*n+2);
		HEAD = n*n;
		TAIL = n*n+1;

		/*by convention, use indices 1 to n. Could've saved some memory but using more memory for covenience*/
		grid = new boolean[n+1][n+1];

		for(int i=1;i<=n;i++)
		{
			for(int j=1; j<=n; j++)
			{
				grid[i][j] = BLOCKED;
			}
		}

		/*last two entries will be unioned with first and last rows respectively*/
		for(int j=1; j<=n; j++)
		{
			uf.union(grid2LinIdx(1, j), HEAD);
			uf.union(grid2LinIdx(n, j), TAIL);
		}
	}

	private int grid2LinIdx(int i, int j)
	{
		return n*(i-1)+ (j-1);
	}

	/*open site (row, col) if it is not open already*/
	public void open(int row, int col)
	{
		check(row, col);

		if(grid[row][col] == BLOCKED)
		{
			grid[row][col] = OPEN;
			numOpen++;
		}

		if((row-1) >= 1 && grid[row-1][col] == OPEN) uf.union(grid2LinIdx(row-1, col), grid2LinIdx(row, col));
		if((row+1) <= n && grid[row+1][col] == OPEN) uf.union(grid2LinIdx(row+1, col), grid2LinIdx(row, col));
		if((col-1) >= 1 && grid[row][col-1] == OPEN) uf.union(grid2LinIdx(row, col-1), grid2LinIdx(row, col));
		if((col+1) <= n && grid[row][col+1] == OPEN) uf.union(grid2LinIdx(row, col+1), grid2LinIdx(row, col));
	}

	/*is site (row, col) open?*/
	public boolean isOpen(int row, int col)
	{
		check(row, col);

		return grid[row][col] == OPEN;
	}
	
	/* is site (row, col) full? */
	public boolean isFull(int row, int col)
	{
		check(row, col);
		return isOpen(row, col) && uf.connected(HEAD, grid2LinIdx(row,col));
	}

	// number of open sites
	public int numberOfOpenSites()
	{
		return numOpen;
	}

	// does the system percolate?
	public boolean percolates()
	{
		return uf.connected(HEAD, TAIL);
	}

	private void check(int row, int col)
	{
		if(row<1 || row>n || col<1 || col>n) throw new java.lang.IllegalArgumentException(String.format("Operation on row = %d, col = %d not permitted", row, col));
	}







	public static void main(String[] args)
	{
		Percolation p = new Percolation(5);
		System.out.println(p.percolates());
		p.open(1,1);
		System.out.println(p.percolates());
		System.out.println(p.numberOfOpenSites());
	}
}