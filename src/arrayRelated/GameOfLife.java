/*
 * == Created Date ==
 * Dec 28, 2018
 * 
 * == Question - Game Of Life ==
 *   
 * == Notes == 
 * LeetCode 289, medium
 * 
 */

package arrayRelated;

public class GameOfLife {
	
    /*
    
    [0,1,0],
    [0,0,1],
    [1,1,1],
    [0,0,0]

    (x-1, y-1), (x-1, y), (x-1, y+1)
    (x, y-1)),  (x,y),    (x, y+1)
    (x+1, y-1), (x+1, y), (x+1, y+1)

  
     1 -> 0 = 3
     0 -> 0
     1 -> 1
     0 -> 1 = 2
     
     state live (1) or dead (0).
     
     */
	
    public void gameOfLife(int[][] board) {
    		if (board == null || board.length == 0) {
    			return;
    		}
        							      
        for (int x = 0; x < board.length; x++) {
        		for (int y = 0; y < board[0].length; y++) {
        			
                int liveNeighbors = liveNeighbors(board, x, y);
        			
        			// if the cell is live and it will die in the next generation
        			if (board[x][y] == 1) {
        				if (liveNeighbors < 2 || liveNeighbors > 3) {
        					board[x][y] = 3;
        				}     				
        			}
        			
        			// if the cell is dead and it will live in the next generation
        			if (board[x][y] == 0 && liveNeighbors == 3) {
        				board[x][y] = 2;
        			}
        			
        		}
        }
        
        // update the board
        for (int x = 0; x < board.length; x++) {
        		for (int y = 0; y < board[0].length; y++) {
        			if (board[x][y] == 3) {
        				board[x][y] = 0;
        			} else if (board[x][y] == 2) {
        				board[x][y] = 1;
        			}
        		}
        }       	                                                        
    }
    
    private int liveNeighbors(int[][] board, int x, int y) {
    		int liveNeighbors =  0;
        int[][] offsets = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1} };
		      
		// calcute the states of the current cell's neighbors
		for (int i = 0; i < 8; i++) {
			int neiRow = x + offsets[i][0];
			int neiCol = y + offsets[i][1];
			if (neiRow >= 0 && neiRow < board.length && neiCol >= 0 && neiCol < board[0].length) {
				int neiState = board[neiRow][neiCol];
				liveNeighbors += neiState == 1 || neiState == 3 ? 1 : 0;
			}
		}	      	      
    		return liveNeighbors;
    }

	// Time Complexity: O(n);
	// Space Complexity: O(1);
	
    /* -- < Lessons Learned > --
     * 
     * 1. Small coding technique
     * int[][] offsets = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1} };
     * 
     * 2. Be careful of the condition!!
     * 
     * Mistakes:
     * 
     * ```
     * // if the cell is live and it will die in the next generation
     * if (board[x][y] == 1 && liveNeighbors < 2 || liveNeighbors > 3) {
     *     board[x][y] = 3;   				
     * } 
     * 
     * */
    
	/* ----------------------< test stub >-------------------------*/
    private static void print(int[][] board) {
        for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
        System.out.println();
    }
	public static void main(String[] args) {
		
		GameOfLife testObj = new GameOfLife();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		int[][] board = new int[][] {{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
		print(board);
		
		testObj.gameOfLife(board);
		print(board);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
	
}
