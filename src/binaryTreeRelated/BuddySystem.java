/*
 * == Created Date ==
 * March 10, 2019
 * 
 * == Question - Buddy System ==
 * 
 * == Notes == 
 * Pure Storage onsite
 * 
 */

package binaryTreeRelated;

public class BuddySystem {
	
	/*
	 * The tree is like:
                  0
              /       \
             0          1
           /  \        /  \
          1     0     1    1
         / \   / \   / 
        1   1 1   0  1
        Since it's complete binary tree, the nodes can be stored in an array:
        [0,0,1,1,0,1,1,1,1,1,0,1] 
        
           i/2
          /    \   
		i     i + 1
        
        a non-leaf node is 1 only when both its children is 1, otherwise 0
        
        input:
        int[][] bits
        bit[level][num]
	 * 
	 * 
	 * 
	 * 
	 * */
	
	/*
	 * 
    level3                0
                      /       \
    level2         0           1
                 /  \         /  \
    level1      1     0      1    1
               / \   / \    / 
    level0    1   1 0   0   1
        
              0   1 2   3   4
        
		clearBits(bits, 2, 3)
	 * 
	 * */
	
	public void clearBits(int[][] bits, int start, int len) {
		int left = start; 
		int right = start + len - 1; 
		for (int i = 0; i < bits.length; i++) {
			for (int j = left; j <= right; j++) {
				bits[i][j] = 0;
			}
			left = left / 2;
			right = right / 2;
		}
	}
	
	public void setBits(int[][] bits, int start, int len) {
		
	}
	
	
}
