/*
 * == Created Date ==
 * Jan 15, 2019
 * 
 * == Question - K Empty Slots ==
 * There is a garden with N slots. In each slot, there is a flower. 
 * The N flowers will bloom one by one in N days. 
 * In each day, there will be exactly one flower blooming and it will be in the status of blooming since then.
 * 
 * Given an array flowers consists of number from 1 to N. 
 * Each number in the array represents the place where the flower will open in that day.
 * 
 * For example, flowers[i] = x means that the unique flower that blooms at day i will be at position x, 
 *   where i and x will be in the range from 1 to N.
 *   
 * Also given an integer k, you need to output in which day there exists two flowers in the status of blooming, 
 *   and also the number of flowers between them is k and these flowers are not blooming.
 *   
 * If there isn't such day, output -1.
 *   
 * == Example 1 == 
 * Input: 
 * flowers: [1,3,2]
 * k: 1
 * Output: 2
 * 
 *            1  2  3
 * 1st day :  f  
 * 2nd day :  f     f   (the number of blooming flowers between the first and the third is 1)
 * 3rd day :  f  f  f
 * 
 * == Example 2 == 
 *  * Input: 
 * flowers: [1,2,3]
 * k: 1
 * Output: -1
 * Explanation: no such day
 * 
 *            1  2  3
 * 1st day :  f  
 * 2nd day :  f  f   
 * 3rd day :  f  f  f
 * 
 * == Notes == 
 * LeetCode 683*(H)
 * 
 */

package binarySearchTreeRelated;

import java.util.TreeSet;

public class KEmptySlots {
	
	/* ----- < Method 1 - Brute Force > -----
	 * Time Complexity: O(N * 2k); // each step - O(2k)
	 * Space Complexity: O(N);
	 * 
	 */
    public int kEmptySlotsI(int[] flowers, int k) {
        if (flowers.length == 0 || k >= flowers.length) {
        		return -1;
        }
        int[] states = new int[flowers.length + 1]; // 1: bloom, 0: doesn't bloom
        
        for (int i = 0; i < flowers.length; i++) {
            if (IsValid(flowers[i], k, flowers.length, states)) {
            		return i + 1;
            }
        }
        return -1;
    }
    
    private boolean IsValid(int curPos, int k, int n, int[] states) {
    		states[curPos] = 1; // marked it as bloom
    		
    		// check if there exists an eligible flower in the right side
        searchRight: if (curPos + k + 1 <= n && states[curPos + k + 1] == 1) {
            // check if the flowers in between are blooming
            for (int i = 1; i <= k; i++) {
                if (states[curPos + i] == 1) {
                    break searchRight; 
                }
            }
            return true;
        }
        
        // check if there exists an eligible flower in the left side
        if (curPos - k - 1 > 0 && states[curPos - k - 1] == 1) {
            for (int i = 1; i <= k; i++) {
            		if (states[curPos - i] == 1) {
            			return false;
            		}
            }
            return true;
        }
        return false;
   } 

        
	/* ----- < Method 2 - Binary Search Tree > -----
	 * Time Complexity: O(NlogN); // N is the length of flowers, each step is O(logN)
	 * Space Complexity: O(N);
	 * 
	 * == Intuition ==
	 * Let's add flowers in the order they bloom. 
	 * When each flower blooms, we check it's neighbors to see if they can satisfy the condition with the current flower.
	 * 
	 * == Algorithm ==
	 * We'll maintain BST, a sorted data structure containing every flower that has currently bloomed. 
	 * When we add a flower to BST, we should check it's lower and higher neighbors. 
	 * If some neighbor satisfies the condition, we know the condition occurred first on this day.
	 * 
	 * x - lower(x) - 1 == k
	 * higer(x) - k - 1 == k 
	 * 
	 *      1
	 *     / 
	 *  null  3
	 *  
	 * */
    public int kEmptySlotsII(int[] flowers, int k) {
        TreeSet<Integer> bst = new TreeSet<>();
        for (int day = 0; day < flowers.length; day++) {
        		int curBloom = flowers[day];
        		bst.add(curBloom);
        		
        		Integer leftBloom = bst.lower(curBloom);
        		if (leftBloom != null && curBloom - leftBloom - 1 == k) {
        			return day;
        		}
        		
        		Integer rightBloom = bst.higher(curBloom);
        		if (rightBloom != null && rightBloom - curBloom - 1 == k) {
        			return day;
        		}
        }
        return -1;
    }
	
	/* ----- < Method 3 - Buckets > -----
	 * Time Complexity: O(N); 
	 * Space Complexity: O(n / (k + 1));
	 * 
	 * partion the slots into buckets size of k + 1
	 * 
	 * 1 2 3 4 5 6
	 * 
	 * bucket[0]: 1 ~ k + 1 // 1 2
	 * bucket[1]: k + 2 ~  2k + 2 // 3 4
	 * bucket[2]: 2k + 3 ~ 3k + 3 // 5 6
	 * 
	 * Track the min/max of each bucket
	 * 
	 * For each flower at postion x, it belongs to bucket[x / (k + 1)]
	 *  - if x is the min in the bucket, check if the max is x - k - 1
	 *  - if x is the max in the bucket, check if the min of next bucket is x + k + 1
	 * */
    public int kEmptySlotsIII(int[] flowers, int k) {
    	
    	
    		return 0;

    }
    
    /* ----- < Method 4 - Sliding Window > -----
     * Time Complexity: O(N); 
     * Space Complexity: O(N);
     * 
     * 
     * Use posAndBloomDay[x] to record the time that the flower at position x blooms. 
     * We wanted to find candidate intervals [left, right] 
     *   where posAndBloomDay[left], posAndBloomDay[right] (right - left = k + 1) are the two smallest values in 
     *   posAndBloomDay [left, right]
     *   
     * If we can find a smaller one in int interval posAndBloomDay [left, right], update left and the slingding window
     * 
     */
    public int kEmptySlotsIV(int[] flowers, int k) {
    	    // posAndBloomDay is for the time that the flower at position x blooms
    		// eg: posAndBloomDay[i] = 2 means the flower at (i-th + 1) slot bloom at Day2
        int[] posAndBloomDay = new int[flowers.length]; 
        
        /*          0  1  2
         * flowers: 1  3  2
         *    
         *                 0   1   2
         * posAndBloomDay: 1   3   2
         * 
         * */
        for (int day = 0; day < flowers.length; day++) {
        		int slotIndex = flowers[day] - 1;
        		posAndBloomDay[slotIndex] = day + 1;
        }

        int result = Integer.MAX_VALUE;
        int left = 0;
        int right = k + 1;
        int numSlots = flowers.length;
        
        search: while (right < numSlots) {
            for (int i = left + 1; i < right; i++) {
                if (posAndBloomDay[i] < posAndBloomDay[left] || posAndBloomDay[i] < posAndBloomDay[right]) {
                    left = i;
                    right = i + k + 1;
                    continue search;
                }
            }
            result = Math.min(result, Math.max(posAndBloomDay[left], posAndBloomDay[right]));
            left = right;
            right = left + k + 1;
        }
        return result < Integer.MAX_VALUE ? result : -1;
    }
}
