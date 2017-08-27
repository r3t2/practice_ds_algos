import java.util.Arrays;

public class SparseMatrix
{
  private SparseVector [] sMatrix;
  private int l_rows;
  private int l_columns;

  public SparseMatrix(double[][] mat)
  {
    this.l_rows = mat.length;
    this.l_columns = mat[0].length;

    sMatrix = new SparseVector[l_rows];
    for (int i=0; i<l_rows; i++)
    {
      sMatrix[i] = new SparseVector(mat[i]);
    }

  }

  /* y = A * x. Returns y. A is this matrix. x is input vector*/
  public double [] dot(double [] x)
  {
    if(l_columns != x.length)
    {
      return null;
    }

    double [] y = new double[l_rows];

    for(int i=0; i<l_rows; i++)
    {
      y[i] = sMatrix[i].dot(x);
    }


    return y;
  }

  public static void main(String[] args)
  {
    SparseMatrix sM = new SparseMatrix(new double[][]{{0,1,0},{1,0,0},{0,0,1}});
    System.out.println(Arrays.toString(sM.dot(new double[]{1,2,3})));
  }
}