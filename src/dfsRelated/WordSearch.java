package dfsRelated;

public class WordSearch {
	
	/* ------------------------ < Wrong Approach !!! >--------------------------- */
    public boolean existWrong(char[][] board, String word) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (explore(board, row, col, word)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean explore(char[][] board, int row, int col, String word) {
                
        if (board[row][col] != word.charAt(0)) {
            return false;
        }
        
        boolean[][] visited = new boolean[board.length][board[0].length];
        
        for (int i = 0; i < word.length() - 1; i++) {
            visited[row][col] = true;
            if (row + 1 < board.length && !visited[row + 1][col] && board[row + 1][col] == word.charAt(i + 1)) {
                row++;
                continue;
            } else if (row - 1 >= 0 && !visited[row - 1][col] && board[row - 1][col] == word.charAt(i + 1)) {
                row--;
                continue;
            } else if (col - 1 >= 0 && !visited[row][col - 1] && board[row][col - 1] == word.charAt(i + 1)) {
                col--;
                continue;
            } else if (col + 1 < board[0].length && !visited[row][col + 1] && board[row][col + 1] == word.charAt(i + 1)) {
                col++;
                continue;
            }
            return false;
        }
        return true;
    }
    
   
    /* ------------------------ < DFS !!! >--------------------------- */
    public boolean exist(char[][] board, String word) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                boolean[][] visited = new boolean[board.length][board[0].length];
                if (dfs(board, row, col, word, 0, visited)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean dfs(char[][] board, int row, int col, String word, int index, boolean[][] visited) {
            
        
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return false;
        }
        
        if (visited[row][col]) {
            return false;
        }
        
        if (board[row][col] != word.charAt(index)) {
            return false;           
        } 
        
        if (index + 1 == word.length()) {
            return true;
        }
        
        visited[row][col] = true;
          
        boolean result =	 dfs(board, row + 1, col, word, index + 1, visited) || 
        					 dfs(board, row - 1, col, word, index + 1, visited) ||
        					 dfs(board, row, col + 1, word, index + 1, visited) || 
        					 dfs(board, row, col - 1, word, index + 1, visited);
        visited[row][col] = false;
        return result;
    }
    
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		WordSearch testObj = new WordSearch();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		char[][] board1 = new char[][]{
			                             new char[] {'a', 'b'}, 
			                             new char[] {'c', 'd'}
			                           };
			                           
		System.out.println(testObj.existWrong(board1, "acdb")); // expected: true 
		
		System.out.println(testObj.exist(board1, "acdb")); // expected: true 
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		char[][] board2 = new char[][]{ 
										new char[] {'C', 'A', 'A'}, 
									    new char[] {'A', 'A', 'A'},
									    new char[] {'B', 'C', 'D'}
									  };
									   
		System.out.println(testObj.existWrong(board2, "AAB")); // expected: true, BUT 'false' !!!
		
		System.out.println(testObj.exist(board2, "AAB")); // expected: true 
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
		char[][] board3 = new char[][]{ 
									    new char[] {'A', 'B', 'C', 'E'}, 
			   						    new char[] {'S', 'F', 'E', 'S'},
			   						    new char[] {'A', 'D', 'E', 'E'},
									  };
									  
		System.out.println(testObj.exist(board3, "ABCESEEEFS")); // expected: true 
		
	}

}
