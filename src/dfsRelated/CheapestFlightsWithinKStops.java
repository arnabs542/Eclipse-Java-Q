/*
 * == Created Date ==
 * Dec 31, 2018
 * 
 * == Question - Cheapest Flights Within K Stops ==
 * 
 * == Example == 
 * 
 * Input: 
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 1
 * Output: 200
 * 
 * == Notes == 
 * LeetCode 787(M)
 *   
 */

package dfsRelated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheapestFlightsWithinKStops {
	
    /* ----------------------< Method 1 : DFS >-------------------------
     * 
     * Time Complexity: O(nlogn);
     * Space Complexity: O(n);	
     * 
     * 
     * */
	
	private int minCost = Integer.MAX_VALUE;
	private List<Map<Integer,Integer>> graph = null;
	
	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
		graph = getCityGraph(n, flights);
		
		for (Map.Entry<Integer, Integer> nei : graph.get(src).entrySet()) {
			if (nei.getKey() == dst && nei.getValue() < minCost) {
			    minCost = nei.getValue();
			} else {
				dfs(nei.getKey(), dst, 1, K, nei.getValue());
			}
		}
		return minCost == Integer.MAX_VALUE ? -1 : minCost;
	}

	private void dfs(int from, int dst, int stops, int K, int cost) {
		if (stops > K || cost > minCost) {
			return;
		}
		for (Map.Entry<Integer, Integer> nei : graph.get(from).entrySet()) {
			// if next stop is the destination city
			if (nei.getKey() == dst) {
				if (cost + nei.getValue() < minCost) {
					minCost = cost + nei.getValue();
				}
			} else {
				dfs(nei.getKey(), dst, stops + 1, K, cost + nei.getValue());
			}
		}
		
	}
	
	private List<Map<Integer,Integer>> getCityGraph(int n, int[][] flights) {
		List<Map<Integer,Integer>> graph = new ArrayList<>();
		for (int i = 0; i < n; i++) {
            graph.add(new HashMap<Integer,Integer>());
        }
        
		for (int i = 0; i < flights.length; i++) {
            graph.get(flights[i][0]).put(flights[i][1], flights[i][2]);
		}
		return graph;
	}
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		CheapestFlightsWithinKStops testObj = new CheapestFlightsWithinKStops();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		//int n, int[][] flights, int src, int dst, int K
		int[][] flights = new int[][] {{0,1,100},{1,2,100},{0,2,500}};
		testObj.findCheapestPrice(3, flights, 0, 2, 1);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}
