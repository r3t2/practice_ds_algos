public class ValidSudoku
{
    /*
    Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

    The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
    Note:
    A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.
    */
    public boolean isValidSudoku(char[][] board) {
        int n = board.length;
        int sqrtn = (int) Math.sqrt(n);
        int [] cnt = new int[n+1];
        
        for(int i=0; i<n; i++)
        {
            if(!validate(board, cnt, i, true)) return false;
        }
        
        for(int i=0; i<n; i++)
        {
            if(!validate(board, cnt, i, false)) return false;
        }
        
        for(int i=0; i*i<n; i++)
        {
            for(int j=0; j*j<n; j++)
            {
                if(!validate(board, cnt, sqrtn*i, sqrtn*j)) return false;
            }
        }
        
        return true;
    }
    
    private boolean validate(char [][] board, int [] cnt, int r, boolean isRow)
    {
        int n = board.length;
        
        for(int i=0; i<cnt.length; i++) cnt[i] = 0;
        
        char c;
        int x;
        for(int i=0; i<n; i++)
        {
            if(isRow) c = board[r][i];
            else c = board[i][r];
            if(c == '.') continue;
            
            x = c - '0';
            
            if(cnt[x] != 0) return false;
            else cnt[x] += 1;
        }
        
        return true;
    }
    
    private boolean validate(char [][] board, int [] cnt, int row, int col)
    {
        int n = board.length;
        
        for(int i=0; i<cnt.length; i++) cnt[i] = 0;
        
        char c;
        int x;
        for(int i=0; i*i<n; i++)
        {
            for(int j=0; j*j<n; j++)
            {
                c = board[i+row][j+col];
                
                if(c == '.') continue;
                
                x = c - '0';
                
                if(cnt[x] != 0) return false;
                else cnt[x] += 1;
                
            }
        }
        
        return true;        
    }
}