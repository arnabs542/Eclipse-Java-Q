/*
 * == Created Date ==
 * June 19, 2018
 * 
 * == Question - Kth Smallest Number In Sorted Matrix ==
 * Given a matrix of size N x M. 
 * For each row the elements are sorted in ascending order, 
 * and for each column the elements are also sorted in ascending order. 
 * Find the Kth smallest number in it.
 *   
 * == Assumptions ==
 * The matrix is not null, N > 0 and M > 0
 * K > 0 and K <= N * M
 *     
 * == Example ==
 *   
 *   { {1,  3,  5,   7},
 *     {2,  4,  8,   9},
 *     {3,  5,  11, 15},
 *     {6,  8,  13, 18} }
 *     
 *     the 5th smallest number is 4
 *     the 8th smallest number is 6
 *   
 * == Mirror Question ==
 * Search In Sorted Matrix I (binary search)
 * Dijkstra's Algorithm
 * 
 * == Update ==
 * October 6, 2018: Review, in Fall 1 class
 * Don't be intimadated by the long statements below, just basic operations to matrix... 	
 * 
 */

package bfsRelated;

import java.util.PriorityQueue;

public class KthSmallestInSortedMatrix {
		
	/* ------< Method 1 - Use Min Heap + Selp-defined Data Structure >------
	 * 
	 * Time Complexity: O( k * logk );
	 * Space Complexity: O( k + n * m );
	 * 
	 */
	static class Cell implements Comparable<Cell> {
		int row;
		int col;
		int val;
		
		Cell(int r, int c, int v) {
			row = r;
			col = c;
			val = v;
		}
		
	    @Override
	    public int compareTo (Cell that) {
	        return this.val - that.val;
	    }
	}
	
	public int kthSmallest(int[][] matrix, int k) {	
		// assume the input matrix is valid, N > 0, M > 0
		// k > 0 && k <= N * M
		
		PriorityQueue<Cell> minHeap = new PriorityQueue<>(k); // supported only by Java 8
		int rows = matrix.length;
		int cols = matrix[0].length;
		boolean[][] visited = new boolean[rows][cols];
		
		minHeap.offer(new Cell(0, 0, matrix[0][0])); // initial state
		visited[0][0] = true; // deduplication: all generated cells will be marked true to avoid being generated more than once
		
		// iterate k - 1 rounds!!!, keep the Kth smallest at the top of the heap when exit the loop
		for (int i = 0; i < k - 1; i++) {
			Cell cur = minHeap.poll(); // expand a node
			if (cur.col + 1 < cols && !visited[cur.row][cur.col + 1]) {
				minHeap.offer(new Cell(cur.row, cur.col + 1, matrix[cur.row][cur.col + 1])); // generate
				visited[cur.row][cur.col + 1] = true;
			}
			if (cur.row + 1 < rows && !visited[cur.row + 1][cur.col]) {
				minHeap.offer(new Cell(cur.row + 1, cur.col, matrix[cur.row + 1][cur.col])); // generate
				visited[cur.row + 1][cur.col] = true;			
			}
		}
		return minHeap.peek().val;		
	}	
	
	/* ------< Method 2 - Binary Search >------
	 * 
	 * Time Complexity: O( k * logk );
	 * Space Complexity: O( k + n * m );
	 * 
	 */
	
	public int kthSmallestMeth2(int[][] matrix, int k) {
		if (k == 1) {
			return matrix[0][0];
		}
		int n = matrix.length;
		if (k == n * n) {
			return matrix[n - 1][n - 1];
		}
		    
	    int min = matrix[0][0];
	    int max = matrix[n - 1][n - 1];
	    
	    while (min <= max) {
	      int mid = min + (max - min) / 2;
	      int count = countLessEqual(matrix, mid);
	      if (count < k) {
	        min = mid + 1;
	      } else {
	        max = mid - 1;
	      }
	    }
	    return min;
	}
		    
	private int countLessEqual(int[][] matrix, int target) {
		int count = 0;
		int row = matrix.length - 1, col = 0;
		while (row >= 0 && col < matrix.length) {
		  if (matrix[row][col] <= target) {
		    col++;
		    count += row + 1;
		  } else {
		    row--;
		  }
		}
		return count;
	}

	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		KthSmallestInSortedMatrix testObj = new KthSmallestInSortedMatrix();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		int[][] matrix = { {1,  3,  5,   7},
				           {2,  4,  8,   9},
				           {3,  5,  11, 15},
				           {6,  8,  13, 18} };
		
		int result = testObj.kthSmallest(matrix, 5); 
		System.out.println(result);// expected: 4
		
		result = testObj.kthSmallest(matrix, 8); 
		System.out.println(result);// expected: 6
		
		result = testObj.kthSmallestMeth2(matrix, 5); 
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");		
	}
}


