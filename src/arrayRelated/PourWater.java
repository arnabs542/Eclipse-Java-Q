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
	

	/* ---------< left and right borders have infinite hight >-------------------------
	 * 
	 * Time Complexity: O(V * N), where N is the length of heights. 
	 * Space Complexity: O(1)
	 * 
	 */

    public int[] pourWaterI(int[] heights, int V, int K) {
    		int[] waters = new int[heights.length];
    		int start = V;
    		while (V > 0) {
    			printI(heights, waters, start - V);
        		
            int pos = K; // this will be the final position for this drop
            // move pos left, to the place where this drop will eventually fall into
            for (int i = K - 1; i >= 0; i--) {
                if (heights[i] + waters[i] > heights[pos] + waters[pos]) {
                    break;
                } else if (heights[i] + waters[i] < heights[pos]  + waters[pos]) { 
                		pos = i; // why node ``` index-- ```
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
                        
            // this drop will eight fall in the right side or stay in the starting position
            V--;
            waters[pos]++;
        }
    		printI(heights, waters, start - V);
        return heights;
    }
    
    private void printI(int[] heights, int[] waters, int drops) {
        int n = heights.length;

        int maxHeight = 0;
        for (int i = 0; i < n; i++) {
            maxHeight = Math.max(maxHeight, heights[i] + waters[i]);
        }
        
        System.out.println("Total drops = " + drops);
        printExtraBorder(heights, 2); // if the left and right border are infinite
        for (int height = maxHeight; height > 0; height--) {
            for (int i = -1; i <= n; i++) {
            		if (i == -1 || i == n) {
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
    
	/* ---------< left and right borders have limited hight >-------------------------
	 * 
	 * Time Complexity: O(V * N), where N is the length of heights. 
	 * Space Complexity: O(1)
	 * 
	 */
    
    public int[] pourWaterII(int[] heights, int V, int K, int leftBorder, int rightBorder) {
		int[] waters = new int[heights.length];
		int start = V;
		int leftM = 0;
		int rightM = 0;
		while (V > 0) {
			print(heights, waters, leftBorder, rightBorder, start - V);
    		
			int pos = K; // this will be the final position for this drop
			// move pos left, to the place where this drop will eventually fall into
			for (int i = K - 1; i > 0; i--) {
	            if (heights[i] + waters[i] > heights[pos] + waters[pos]) {
	            		leftM = Math.max(leftM, heights[i] + waters[i]);
	                break;
	            } else if (heights[i] + waters[i] < heights[pos]  + waters[pos]) { 
	            		pos = i; // why node ``` index-- ```
	            }
			}
        
	        // if position doesn't stay the K, we find a position in the left side
	        if (pos != K) {
	            V--;
	            	waters[pos]++;
	            continue; // no need to check right side
	        }
	        
	        // if the drop will not falls in the left side, check its righ side
	        for (int i = K + 1; i < heights.length; i++) {
	            if (heights[i] + waters[i] > heights[pos] + waters[pos]) {
	            		rightM = Math.max(rightM, heights[i] + waters[i]);
	                break;
	            } else if (heights[i] + waters[i] < heights[pos] + waters[pos]) { 
	            		pos = i;
	            }
	        }
        
	        // if position doesn't stay the K, we find a position in the right side
	        if (pos != K) {
	            V--;
	            	waters[pos]++;
	            	continue;
	        }
	        
	        // if the water will falls beyond borders
	        int possibleHeight = waters[pos] + heights[pos] + 1;
	        if (possibleHeight > leftM || possibleHeight > rightBorder) {
		        if (possibleHeight > leftBorder || possibleHeight > rightBorder) {
	        			V--;
	        			continue;
		        } 
	        }
	        
	        // otherwise, this drop stay in the starting position
	        V--;
	        waters[pos]++;
	    }
		print(heights, waters, leftBorder, rightBorder, start - V);
		return heights;
    }

    private void print(int[] heights, int[] waters, int left, int right, int V) {
	    int n = heights.length;
	
	    int maxHeight = 0;
	    for (int i = 0; i < n; i++) {
	        maxHeight = Math.max(maxHeight, heights[i] + waters[i]);
	    }
	    
	    maxHeight = Math.max(maxHeight, Math.max(left, right));
	    
	    System.out.println("Total drops = " + V);
	    for (int height = maxHeight; height > 0; height--) {
	        for (int i = -1; i <= n; i++) {
	        		if (i == -1 || i == n) {
	               	if (i == -1 && height <= left) {
	            			System.out.print("|");
	            		} else {
	            			System.out.print(" ");
	            		}
	            			
	            		if (i == n && height <= right) {
	            			System.out.print("|");
	            		} else {
	            			System.out.print(" ");
	            		}
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


	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		PourWater testObj = new PourWater();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		int[] waterLand = new int[]{2, 1, 1, 2, 1, 2, 2};
		
		testObj.pourWaterI(waterLand, 6, 4);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		testObj.pourWaterII(waterLand, 6, 4, 3, 2); // with left border is 3 and right border is 2
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		testObj.pourWaterII(waterLand, 6, 4, 1, 3);
		
		/* Test Case 4 */
		System.out.println("---< Test Case 4 >---");
		int[] waterLand4 = new int[]{5,4,2,1,3,2,2,1,0,1,4,3};
		testObj.pourWaterI(waterLand4, 8, 5);
		
	}
    
}
