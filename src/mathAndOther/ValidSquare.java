/*
 * == Created Date ==
 * Feb 2, 2019
 * 
 * == Question - Valid Square ==
 * Given the coordinates of four points in 2D space, return whether the four points could construct a square.
 * 
 * The coordinate (x,y) of a point is represented by an integer array with two integers.
 * 
 * == Example ==
 * Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
 * Output: True
 * 
 * Note:
 * All the input integers are in the range [-10000, 10000].
 * A valid square has four equal sides with positive length and four equal angles (90-degree angles).
 * Input points have no order.
 *   
 * == Notes == 
 * LeetCode 593 (M) - Valid Square
 * 
 */

package mathAndOther;

public class ValidSquare {
	
	
	/* ----- < Solution 1 - Brute Force> -----
	 * Time Complexity: O(4!);
	 * Space Complexity: O(1);
	 * 
	 * */
    public double dist(int[] p1, int[] p2) {
        return (p2[1] - p1[1]) * (p2[1] - p1[1]) + (p2[0] - p1[0]) * (p2[0] - p1[0]);
    }
    
    public boolean check(int[] p1, int[] p2, int[] p3, int[] p4) {
        return dist(p1,p2) > 0 
        		   && dist(p1, p2) == dist(p2, p3) // check adjacent sides
        		   && dist(p2, p3) == dist(p3, p4) 
        		   && dist(p3, p4) == dist(p4, p1) 
        		   && dist(p1, p3) == dist(p2, p4); // check lengths of the diagonals formed between the corners 
    }
    
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[][] p = {p1, p2, p3, p4};
        return checkAllPermutations(p, 0);
    }
    
    private boolean checkAllPermutations(int[][] p, int index) {
        if (index == 4) { // base case, we get one permutation
            return check(p[0], p[1], p[2], p[3]); // check if this permutation can form a square
        } else {
            boolean res = false;
            for (int i = index; i < 4; i++) {
                swap(p, index, i);
                res |= checkAllPermutations(p, index + 1);
                swap(p, index, i);
            }
            return res;
        }
    }
    
    public void swap(int[][] p, int x, int y) {
        int[] temp = p[x];
        p[x] = p[y];
        p[y] = temp;
    }
    

}
