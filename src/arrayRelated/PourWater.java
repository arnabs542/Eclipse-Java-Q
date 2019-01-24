/*
 * == Created Date ==
 * Jan 3, 2019
 * 
 * == Question - Pour Water ==
 *   
 * == Smilar Question == 
 * LeetCode 755* (M), pour water without printing
 * 
 * Asked frequently by Airbnb
 * 
 */


package arrayRelated;

public class PourWater {
	
	/* ---------< Situation 1 - Assume the left and right borders have infinite hight >-------------------------
	 * Time Complexity: O(V * N), where N is the length of heights. 
	 * Space Complexity: O(1)
	 * 
	 */
    public int[] pourWaterI(int[] heights, int V, int K) {
    		int[] waters = new int[heights.length];
    		int start = V;
    		
    		while (V > 0) {
    			print(heights, waters, start - V);
            int pos = K; // this will be the final position for this drop
            // move pos left, to the place where this drop will eventually fall into
            for (int i = K - 1; i >= 0; i--) {
                if (heights[i] + waters[i] > heights[pos] + waters[pos]) {
                    break;
                } else if (heights[i] + waters[i] < heights[pos]  + waters[pos]) { 
                		pos = i; // why not ``` index-- ```
                }
            }
            
            // if position doesn't stay the K, we find a position in the left side
            if (pos != K) {
                V--;
                	waters[pos]++;
                continue; // no need to check right side
            }
            
            // if the drop will not falls in the left side, check its right side
            for (int i = K + 1; i < heights.length; i++) {
                if (heights[i] + waters[i] > heights[pos] + waters[pos]) {
                    break;
                } else if (heights[i] + waters[i] < heights[pos] + waters[pos]) { 
                		pos = i;
                }
            }
            
            // falls to the postion in the right side, or stay the original postion
            V--;
            waters[pos]++;
        }
    		print(heights, waters, start - V);
        return heights;
    }
    
    private void print(int[] heights, int[] waters, int drops) {
        int maxHeight = 0;
        for (int i = 0; i < heights.length; i++) {
            maxHeight = Math.max(maxHeight, heights[i] + waters[i]);
        }
        
		System.out.println("\nDrops = " + drops);
		
		while (maxHeight > 0) {
	    		for (int i = 0; i < heights.length; i++) {
	    			if (heights[i] >= maxHeight) {
	    				System.out.print("+");
	    			} else if (heights[i] + waters[i] >= maxHeight) {
	    				System.out.print("@");
	    			} else {
	    				System.out.print(" ");
	    			}
	    		}
	    		maxHeight--;
	    		System.out.println();
		}
    }
    
    private void printI(int[] heights, int[] waters, int drops) {
        int maxHeight = 0;
        for (int i = 0; i < heights.length; i++) {
            maxHeight = Math.max(maxHeight, heights[i] + waters[i]);
        }
        
        System.out.println("Total drops = " + drops);
        printExtraBorder(heights, 2); // if the left and right border are infinite
        for (int height = maxHeight; height > 0; height--) {
            for (int i = -1; i <= heights.length; i++) {
            		if (i == -1 || i == heights.length) {
            			System.out.print("|");
            		} else if (height <= heights[i]) {
                    System.out.print("+");
                } else if (height <= heights[i] + waters[i]) {
                    System.out.print("@");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    
    private void printExtraBorder(int[] heights, int h) {
    		while (h > 0) {
    			System.out.print("|");
            for (int i = 0; i < heights.length; i++) {
            		System.out.print(" ");
            	}
            System.out.print("|\n");
            h--;
    		}
    }
    
	/* ---------< Situation 2 - without left and right borders >-------------------------
	 * This situation will be the same as without left border, but right border is infinite
	 * 
	 * The situation that with without right border, but left border is infinite, need to discuss with interviewer
	 * Time Complexity: O(V * N), where N is the length of heights. 
	 * Space Complexity: O(1)
	 * 
	 * The first and last element in the input heights[] represents the left and right border
	 */
    public int[] pourWaterII(int[] heights, int V, int K) {
		int[] waters = new int[heights.length];
		int start = V;
		int leftM = 0;
		
		while (V > 0) {
			printII(heights, waters, start - V);
    		
	        int pos = K; // this will be the final position for this drop
	        
	        // move pos left, to the place where this drop will eventually fall into
	        for (int i = K - 1; i >= 0; i--) {
	            if (heights[i] + waters[i] > heights[pos] + waters[pos]) {
	            		// stop at the place where the drop cannot fall 
	            		leftM = Math.max(leftM, heights[i] + waters[i]);
	                break;
	            } else if (heights[i] + waters[i] < heights[pos]  + waters[pos]) { 
	            		// otherwise, continue to find a place that is lower that the current place
	            		pos = i; 
	            }
	        }
	        
	        // if position doesn't stay the K or 0 (left border), we find a position in the left side
	        if (pos != K && pos != 0) {
	            V--;
	            	waters[pos]++;
	            continue; // no need to check right side
	        }
	        
	        // if the drop will not falls in the left side, check its right side
	        pos = K;
	        for (int i = K + 1; i < heights.length; i++) {
	            if (heights[i] + waters[i] > heights[pos] + waters[pos]) {
	                break;
	            } else if (heights[i] + waters[i] < heights[pos] + waters[pos]) { 
	            		pos = i;
	            }
	        }
	                    
	        // if position doesn't stay the K or heights.length - 1 (right border), 
	        // we find a position in the left side
            if (pos != K && pos != heights.length - 1) {
                V--;
                	waters[pos]++;
                continue; 
            }
            
            // if cannot find a place for the drop to fall, 
            // check if the current postion higher than the the max height of both side
            if (heights[pos] + waters[pos] < leftM) {
                V--;
                waters[pos]++;
                continue;
            }
	        // Otherwise, falls down from borders
	        V--;
		}
		printII(heights, waters, start - V);
		return heights;
    }
    
    private void printII(int[] heights, int[] waters, int drops) {
    		int maxHeight = 0;
    		for (int h : heights) {
    			maxHeight = Math.max(maxHeight, h);
    		}
    		
    		System.out.println("\nDrops = " + drops);
    		
    		while (maxHeight > 0) {
        		for (int i = 1; i < heights.length - 1; i++) {
        			if (heights[i] >= maxHeight) {
        				System.out.print("+");
        			} else if (heights[i] + waters[i] >= maxHeight) {
        				System.out.print("@");
        			} else {
        				System.out.print(" ");
        			}
        		}
        		maxHeight--;
        		System.out.println();
    		}
    }

	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		PourWater testObj = new PourWater();
		
		/* Test Case 1 */
		System.out.println("---------< Test Case 1 - With infinite hight borders >---------");
		int[] waterLand = new int[]{2, 1, 1, 2, 1, 2, 2};
		
		testObj.pourWaterI(waterLand, 8, 2);
		
		/* Test Case 2 */
		System.out.println("---------< Test Case 2 - Without borders >---------");
		
		//                            0  1  2  3  4  5  6  7   8
		int[] waterLand2 = new int[]{-1, 2, 1, 1, 2, 1, 2, 2, -1};
		testObj.pourWaterII(waterLand2, 5, 3); // drop at index  
		
		/* Test Case 3 */
		System.out.println("---------< Test Case 3 - Without borders >---------");
		
		testObj.pourWaterII(waterLand2, 5, 6); // drop at index  

		/* Test Case 4 */
		System.out.println("---------< Test Case 4 - With left infinite borders >---------");
		
		//                            0  1  2  3  4  5  6  7 
		int[] waterLand4 = new int[]{-1, 2, 1, 1, 2, 1, 2, 2};
		
		/* Test Case 5 */
		System.out.println("---< Test Case 5 >---");
		int[] waterLand5 = new int[]{5,4,2,1,3,2,2,1,0,1,4,3};
		testObj.pourWaterI(waterLand5, 9, 5);
		
	}
    
}
