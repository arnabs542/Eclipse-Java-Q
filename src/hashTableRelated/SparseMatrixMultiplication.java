/*
 * == Created Date ==
 * Feb 5, 2019
 * 
 * == Question - Sparse Matrix Multiplication ==
 * Given two sparse matrices A and B, return the result of AB.
 * You may assume that A's column number is equal to B's row number.
 *   
 *   | a b |   | e f |   | ae + bg   af + bh |
 *   | c d | x | g h | = | ce + dg   cf + dh |

 * == Example == 
 * A = [
 *       [ 1, 0, 0],
 *       [-1, 0, 3]
 *     ]
 *     
 * B = [
 *       [ 7, 0, 0 ],
 *       [ 0, 0, 0 ],
 *       [ 0, 0, 1 ]
 *     ]
 *    
 * Output:
 * 
 *          |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
 *     AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
 *                       | 0 0 1 |

 * == Notes == 
 * LeetCode 311* (M)
 * 
 */
package hashTableRelated;

public class SparseMatrixMultiplication {

	/* ----- < Solution 1 - Brute Force > -----
	 * Time Complexity: O(n^3);
	 * Space Complexity: O(?);
	 * 
	 * */
	public int[][] multiply(int[][] A, int[][] B) {
	    int m = A.length;
	    int n = A[0].length;
	    int nB = B[0].length;
	    
        int[][] C = new int[m][nB];

        for (int i = 0; i < m; i++) {
        		for (int j = 0; j < nB; j++) {
        			for (int k = 0; k < n; k++) {
        				C[i][j] += A[i][k] * B[k][j];
        			}
            }
        }
        return C;  
    }
	
	/* ----- < Solution 2 - Optimized > -----
	 * Time Complexity: O(n^3);
	 * Space Complexity: O(?);
	 * 
	 * */
	public int[][] multiplyII(int[][] A, int[][] B) {
	    int m = A.length;
	    int n = A[0].length;
	    int nB = B[0].length;
	    int[][] C = new int[m][nB];
	
	    for (int i = 0; i < m; i++) {
		    for (int k = 0; k < n; k++) {
		    		if (A[i][k] != 0) {
		    			for (int j = 0; j < nB; j++) {
		    				C[i][j] += A[i][k] * B[k][j];
		    			}
		    		}
		    }
	    }
	    return C;   
	}
}
