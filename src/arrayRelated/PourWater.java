/*
 * == Created Date ==
 * Jan 3, 2019
 * 
 * == Question - Pour Water ==
 *   
 * == Notes == 
 * LeetCode 755* (M)
 * 
 * Asked frequently Airbnb
 */


package arrayRelated;

public class PourWater {

	/* ----------------------< Solution: Simulation >-------------------------
	 * 
	 * Time Complexity: O(V * N), where N is the length of heights. 
	 * Space Complexity: O(1)
	 * 
	 */

    public int[] pourWater(int[] heights, int V, int K) {
        while (V > 0) {
            int index = K;
            // move left
            for (int i = K - 1; i >= 0; i--) {
                if (heights[i] > heights[index]) {
                    break;
                } else if (heights[i] < heights[index]) { // move index to the place that drop will eventually fall 
                    index = i; // why node ``` index-- ```
                }
            }
            
            // if curIdx doesn't stay the K, this drop falls in left side
            if (index != K) {
                V--;
                heights[index]++;
                continue; // no need to check right side
            }
            
            // if the drop will not falls in the left side, check its righ side
            for (int i = K + 1; i < heights.length; i++) {
                if (heights[i] > heights[index]) {
                    break;
                } else if (heights[i] < heights[index]) { 
                    index = i;
                }
            }
            
            heights[index]++;
            V--;
        }
        return heights;
    }
    
}
