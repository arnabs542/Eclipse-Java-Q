/*
 * == Created Date ==
 * Feb 3, 2019
 * 
 * == Question - Zuma Game ==
 *   
 * == Notes == 
 * LeetCode 488(H)
 * 
 */
package dfsRelated;


public class ZumaGame {

	/* ----- < Solution X - > -----
	 * Time Complexity: O((n + m)^5 * n^2);
	 *                  n is the number of balls in the initial row, n <= 20
	 *                  m is the number of balls in hand, m <= 5
	 *                  
	 *                  (n + m)^5 is combination of insertion 5 balls in 25 places
	 *                  n^2 update the boards after each insertion
	 *                  
	 * Space Complexity: O(n * m);
	 * 
	 * */
	public int findMinStep(String board, String hand) {
		int[] balls = new int[128]; // 'R','Y','B','G','W'.
		for (int i = 0; i < hand.length(); i++) {
			balls[hand.charAt(i)]++;
		}
		return dfs(board, balls);
	}
	
	private int dfs(String board, int[] balls) {
		if (board.length() == 0) {
			return 0;
		}
		int result = Integer.MAX_VALUE;
		int start = 0;
		int fast = 0;
		while (start < board.length()) {
			while (fast < board.length() && board.charAt(start) == board.charAt(fast)) {
				fast++;
			}
			// board substring [start, fast - 1] have the same color
			char color = board.charAt(start);
			
			// number of balls needed to clear board [start, fast - 1]
			int numBalls = 3 - (fast - start); // numBalls = 1 or 2
			
			// If have sufficient balls in hand
			if (balls[color] >= numBalls) {
				
				// remove board [start, fast - 1] and update the board
				String newBoard = collapse(board.substring(0, start) + board.substring(fast));
				
				balls[color] -= numBalls;
				
				int num = dfs(newBoard, balls);
				if (num >= 0) {
					result = Math.min(result, num + numBalls);
				}
				
				balls[color] += numBalls; // recover for backtracking
			}
			start = fast;
		}
		return result == Integer.MAX_VALUE ? -1 : result;
	}
	
	// Update the board by removing all consecutive 3+ balls
	private String collapse(String board) {
		int start = 0;
		while (start < board.length()) {
			int fast = start;
			while (fast < board.length() && board.charAt(start) == board.charAt(fast)) {
				fast++;
			}
			if (fast - start >= 3) {
				board = board.substring(0, start) + board.substring(fast);
				start = 0;
			} else {
				start++;
			}
		}
		return board;
	}
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		ZumaGame testObj = new ZumaGame();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		System.out.println(testObj.collapse("aabbbc"));
		testObj.findMinStep("", "YYYYY");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}
