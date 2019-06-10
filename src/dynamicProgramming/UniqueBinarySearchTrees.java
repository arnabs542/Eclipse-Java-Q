/*
 * == Question - Unique Binary Search Trees ==
 * 
 * == Created Date ==
 * June 9, 2019
  
 * == Notes == 
 * LeetCode 96(M)
 * 
 */

package dynamicProgramming;

public class UniqueBinarySearchTrees {
    public int numTrees(int n) {
        int[] M = new int[n + 1];
        M[0] = 1;
        M[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                M[i] += M[j] * M[i - j - 1];
            }
        }
        return M[n];
    }
}
