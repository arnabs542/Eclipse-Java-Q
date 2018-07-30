/*
 * Created Date: July 30, 2018
 * 
 * Question - Spiral Order Traverse I (medium):
 *   Traverse an N N 2D array in spiral order clock-wise starting from the top left corner. 
 *   Return the list of traversal sequence.
 *   
 *   Example: 
 *    { {1,  2,  3},
 *      {4,  5,  6},
 *      {7,  8,  9} }
 *    the traversal sequence is [1, 2, 3, 6, 9, 8, 7, 4, 5]
 * 
 */

package arrayRelated;

import java.util.ArrayList;
import java.util.List;

public class SpiralOrderTraverseI {
	
	public List<Integer> spiral(int[][] matrix)  {
		List<Integer> res = new ArrayList<>();
	    helper(matrix, matrix.length, 0, res);
	    return res;
	}
	
	private void helper(int[][]m, int size, int offset, List<Integer> res) {
		if (size <= 1) {
			if (size == 1) {
				res.add(m[offset][offset]);
			}
			return;
		}
		int rowEnd = m.length - 1;
		int colEnd = m.length - 1;
		
		for (int i = offset; i < colEnd - offset; i++) {
			res.add(m[offset][i]);
		}
		
		for (int i = offset; i < rowEnd - offset; i++) {
			res.add(m[i][colEnd - offset]); 
		}
		
		for (int i = colEnd - offset; i > offset; i--) {
			res.add(m[rowEnd - offset][i]);
		}
		
		for (int i = rowEnd - offset; i > offset; i--) {
			res.add(m[i][offset]);
		}
		
		helper(m, size - 2, offset + 1, res);
	}
}
