/*
 * == Created Date ==
 * Jan 16, 2019
 * 
 * == Question - Minimum Area Rectangle ==
 * Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from these points, 
 *   with sides parallel to the x and y axes.
 *   
 * If there isn't any rectangle, return 0.
 *   
 * == Example == 
 *   
 * == Notes == 
 * LeetCode 939(M)
 * 
 */

package hashTableRelated;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MinimumAreaRectangle {

    /*
     * == Algorithm - HashTable + Brute Force ==
     * Time complexity: O(n^2)
     * Space complexity: O(n) 
     * 
     * For a pair of diagonal: (x1, y1), (x2, y2) , x1 != x2 and y1 != y2,
     *   to form a rectangle, the other two points will be (x0, y1), (x1, y0)
     * 
     *  (x1, y1)   (x1, y2)
     *      *       *
     *      
     *      *       *
     *  (x2, y1)   (x2, y2)
     *  
     * Try all pairs of points to form a diagonal and see whether pointers of another diagonal exist or not.
     * Use a hash table to help searching.
     * 
     * If found four points that can form a rectangle, update the result
     * 
     */
    public int minAreaRect(int[][] points) {
    		// key: row, value: for the current row, the columns of each points
        Map<Integer, Set<Integer>> rowColumnMap = new HashMap<>();
        for (int[] point : points) {
        		int x = point[0];
        		int y = point[1];
        		rowColumnMap.putIfAbsent(x, new HashSet<>());
        		rowColumnMap.get(x).add(y);
        }
        int min = Integer.MAX_VALUE;
        for (int[] point1 : points) {
            for (int[] point2 : points) {
                if (point1[0] == point2[0] || point1[1] == point2[1]) { // this pair is not diagonal
                    continue;
                }
                int x1 = point1[0];
                int y1 = point1[1];
                int x2 = point2[0];
                int y2 = point2[1];
                if (rowColumnMap.get(x1).contains(y2) && rowColumnMap.get(x2).contains(y1)) { // find other two points
                    min = Math.min(min, Math.abs(x2 - x1) * Math.abs(y2 - y1));
                }
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
