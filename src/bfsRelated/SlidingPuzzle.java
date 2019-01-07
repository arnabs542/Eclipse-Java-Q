/*
 * == Created Date ==
 * Jan 6, 2019
 * 
 * == Question - Sliding Puzzle ==
 * On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.
 * A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.
 * The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].
 * 
 * Given a puzzle board, return the least number of moves required so that the state of the board is solved. 
 * If it is impossible for the state of the board to be solved, return -1.
 * 
 * == Example == 
 * Input: board = [[4,1,2],[5,0,3]]
 * Output: 5
 * 
 * Explanation: 5 is the smallest number of moves that solves the board.
 * An example path:
 * After move 0: [[4,1,2],[5,0,3]]
 * After move 1: [[4,1,2],[0,5,3]]
 * After move 2: [[0,1,2],[4,5,3]]
 * After move 3: [[1,0,2],[4,5,3]]
 * After move 4: [[1,2,0],[4,5,3]]
 * After move 5: [[1,2,3],[4,5,0]]
 * 
 * == Notes == 
 * LeetCode 773 (H)
 * 
 */

package bfsRelated;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class SlidingPuzzle {
	
	/* ----- < Method - BFS > -----
	 * Time Complexity: O(?);
	 * Space Complexity: O(?);
	 * 
	 * We can think of this problem as a shortest path problem on a graph. 
	 * Each node is a different board state, 
	 *  and we connect two boards by an edge if they can be transformed into one another in one move. 
	 * We can solve shortest path problems with breadth first search.
	 * 
	 * == Data Structure ==
	 * Queue
	 * Set
	 * Node: represent each board state
	 * 
	 * 
	 * */
	
    class BoardState {
        int[][] board;
        String boardstring;
        int zeroRowPos;
        int zeroColPos;
        int depth;
        BoardState(int[][] board, int r, int c, int d) {
            this.board = board;
            boardstring = Arrays.deepToString(board);
            zeroRowPos = r;
            zeroColPos = c;
            depth = d;
        }
    }
    
    public int slidingPuzzle(int[][] board) {
        int R = board.length, C = board[0].length;
        
        // Initialize the starting Board State,
        int[] zeroPos = findOriginalZeroPos(board); // find the zero position on the board
        BoardState start = new BoardState(board, zeroPos[0], zeroPos[1], 0);
        
        // Enqueue the starting Board State
        Queue<BoardState> queue = new ArrayDeque<>();
        queue.add(start);

        int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};  // offset for matrix operations
        
        Set<String> seen = new HashSet<>();
        seen.add(start.boardstring);

        String target = generateTargetString(board);

        while (!queue.isEmpty()) {
        		BoardState node = queue.remove(); // expand a board state from the queue
            if (node.boardstring.equals(target)) { 
            		return node.depth;
            }
                
            // generate its neighbor nodes (boards states with 0 swaping with neighboring tiles)
            for (int[] di: directions) {
                int neiRowPos = di[0] + node.zeroRowPos;
                int neiColPos = di[1] + node.zeroColPos;

                if (neiRowPos < 0 || neiRowPos >= R || neiColPos < 0 || neiColPos >= C) { // invalid tile position
                		continue;
                }
                    
                int[][] newboard = new int[R][C];
                int t = 0;
                for (int[] row: node.board) {
                		newboard[t++] = row.clone();
                }
                    
                // swap position
                newboard[node.zeroRowPos][node.zeroColPos] = newboard[neiRowPos][neiColPos];
                newboard[neiRowPos][neiColPos] = 0;

                BoardState nei = new BoardState(newboard, neiRowPos, neiColPos, node.depth + 1);
                if (seen.contains(nei.boardstring)) { // only expand each board states once
                		continue;
                }
                queue.add(nei);
                seen.add(nei.boardstring);
            }
        }
        return -1;
    }
    
    private String generateTargetString(int[][] board) {
    		int num = 1;
    		int[][] targetBoard = new int[board.length][board[0].length];
    		for (int r = 0; r < board.length; r++) {
    			for (int c = 0; c < board[0].length; c++) {
    				if (r == board.length - 1 && c == board[0].length - 1) {
    					targetBoard[r][c] = 0;
    				} else {
        				targetBoard[r][c] = num;
        				num++;
    				}
    			}
    		}
//    		printBoard(targetBoard);
    		return Arrays.deepToString(targetBoard);
    }
    
    private int[] findOriginalZeroPos(int[][] board) {
        for (int r = 0; r < board.length; r++) {
    			for (int c = 0; c < board[0].length; c++) {
    				if (board[r][c] == 0) {
    					return new int[] {r, c};
    				}
    			}
        }
        return null;
    }
    

	/* ----- < Use DFS to print the steps to reach > -----
	 * 
	 * 
	 *                board0
	 *        /       /         \  \
	 *    board1    board2     
	 *   / / \ \
	 *    
	 */
    public void slidingPuzzleII(int[][] board) {
    		int minMoves = slidingPuzzle(board);
    		if (minMoves != -1) {
    	        int[] zeroPos = findOriginalZeroPos(board); // find the zero position on the board
    	        BoardState startBoard = new BoardState(board, zeroPos[0], zeroPos[1], 0);
    	        
    	        String target = generateTargetString(board);
    	        dfs(startBoard, new HashSet<String>(), target, new ArrayList<BoardState>(), 0, minMoves);
    	        return;
    		} 
    		System.out.println("No possible solution");
    }
    
    private void dfs(BoardState curBoard, Set<String> seen, String target, List<BoardState> path, int moves, int minMoves) {
    		if (curBoard.boardstring.equals(target) && moves <= minMoves) {
    			System.out.println("moves:" + moves);
    			path.add(curBoard);
    			for (BoardState board : path) {
    				printBoard(board.board);
    			}
    			path.remove(path.size() - 1);
    			return;
    		}
    		if (moves > minMoves) {
    			return;
    		}
    		int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    		
    		int R = curBoard.board.length;
    		int C = curBoard.board[0].length;
    		
    		if (seen.contains(curBoard.boardstring)) {
    			return;
    		}
    		
    		seen.add(curBoard.boardstring);
    		path.add(curBoard);
    		
        for (int[] di: directions) {
            int neiRowPos = di[0] + curBoard.zeroRowPos;
            int neiColPos = di[1] + curBoard.zeroColPos;

            if (neiRowPos < 0 || neiRowPos >= R || neiColPos < 0 || neiColPos >= C) { // invalid tile position
            		continue;
            }
                
            int[][] newboard = new int[R][C];
            int t = 0;
            for (int[] row: curBoard.board) {
            		newboard[t++] = row.clone();
            }
  
                
            // swap position
            newboard[curBoard.zeroRowPos][curBoard.zeroColPos] = newboard[neiRowPos][neiColPos];
            newboard[neiRowPos][neiColPos] = 0;

            BoardState nei = new BoardState(newboard, neiRowPos, neiColPos, curBoard.depth + 1);
	    		if (!seen.contains(nei.boardstring)) {
	    			dfs(nei, seen, target, path, moves + 1, minMoves);
	    		}
        }
        path.remove(path.size() - 1);
        seen.remove(curBoard.boardstring);    
    }
    
    private void printBoard(int[][] board) {
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[0].length; c++) {
				System.out.print(board[r][c] + " ");
			}
			System.out.println();
		}
		System.out.println("--------------");
}
    
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		SlidingPuzzle testObj = new SlidingPuzzle();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		System.out.println(Arrays.deepToString(new int[][]{{1,2,3}, {4,5,0}}));
		
		int[][] m1 = new int[][]{{1,2,3}, {4,5,6}, {7,8,0}};
		String sm1 = Arrays.deepToString(m1); 
		
		int[][] m2 = new int[][]{{1,2,3}, {4,5,6}, {7,8,0}};
		String sm2 = Arrays.deepToString(m2); 
		
		System.out.println(sm1.equals(sm2));
				
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		int[][] board1 = new int[][]{ {4,1,2}, {5,0,3} };
//		System.out.println(testObj.slidingPuzzle(board1));
		testObj.slidingPuzzleII(board1);
	
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		int[][] board2 = new int[][]{ {3, 1, 4},
            							  {6, 2, 0},
                                      {7, 5, 8} };
//		System.out.println(testObj.slidingPuzzle(board2));
        testObj.slidingPuzzleII(board2);
                                  	
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		int[][] board3 = new int[][]{ {1,2,3}, {5,4,0} };
		System.out.println(testObj.slidingPuzzle(board3));
		testObj.slidingPuzzleII(board3); // no possible path
		
	}
}
