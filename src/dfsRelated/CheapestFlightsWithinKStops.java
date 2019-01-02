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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class CheapestFlightsWithinKStops {
	
    /* ----------------------< Method 1 : DFS >-------------------------
     * 
     * Time Complexity: O(n^(k+1));
     * Space Complexity: O(k+1);	
     * 
     * */
	
	private int minCost = Integer.MAX_VALUE;
	private List<Map<Integer,Integer>> graph = null;
	private List<Integer> finalPath = null;
	
	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
		graph = getCityGraph(n, flights);
		minCost = Integer.MAX_VALUE; // reset the value
		
		List<Integer> path = new LinkedList<>();
		path.add(src);
		dfs(src, dst, 0, K, 0, path);
		
		printResult();
		return minCost == Integer.MAX_VALUE ? -1 : minCost;
	}
	
	private void printResult() {
		System.out.println("cost = " + minCost);
		for (int city : finalPath) {
			System.out.print(city + " ");
		}
		System.out.println();
	}
	
	private void dfs(int from, int dst, int stops, int K, int cost, List<Integer> path) {
		if (stops > K || cost > minCost) {
			return;
		}
		for (Map.Entry<Integer, Integer> nei : graph.get(from).entrySet()) {
			// if next stop is the destination city, we find one possible path
			path.add(nei.getKey());
			if (nei.getKey() == dst) {
				if (cost + nei.getValue() < minCost) {
					minCost = cost + nei.getValue();
					finalPath = new LinkedList<>(path);
				}
			} else {
				dfs(nei.getKey(), dst, stops + 1, K, cost + nei.getValue(), path);
			}
			path.remove(nei.getKey());
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
	
    /* ----------------------< Method 2 : Dijkstra Algorithm >-------------------------
     * 
     * Time Complexity: O(?);
     * Space Complexity: O(?);	
     * 
     * */
	
    public int findCheapestPriceII(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, Map<Integer, Integer>> prices = new HashMap<>();
        for (int[] f : flights) {
            if (!prices.containsKey(f[0])) prices.put(f[0], new HashMap<>());
            prices.get(f[0]).put(f[1], f[2]);
        }
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> (Integer.compare(a[0], b[0])));
        pq.add(new int[] {0, src, k + 1});
        while (!pq.isEmpty()) {
            int[] top = pq.remove();
            int price = top[0];
            int city = top[1];
            int stops = top[2];
            if (city == dst) return price;
            if (stops > 0) {
                Map<Integer, Integer> adj = prices.getOrDefault(city, new HashMap<>());
                for (int a : adj.keySet()) {
                    pq.add(new int[] {price + adj.get(a), a, stops - 1});
                }
            }
        }
        return -1;
    }


    
	// Follow up: print path

	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		CheapestFlightsWithinKStops testObj = new CheapestFlightsWithinKStops();
		
	/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
	/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		//int n, int[][] flights, int src, int dst, int K
		int[][] flights = new int[][] {{0,1,100},{1,2,100},{0,2,500}};
		
		//int findCheapestPrice(int n, int[][] flights, int src, int dst, int K)
		testObj.findCheapestPrice(3, flights, 0, 2, 1);
		
		System.out.println("II = " + testObj.findCheapestPriceII(3, flights, 0, 2, 1));
		
	/* Test Case 2 */
		System.out.println("\n---< Test Case 2 >---");

		// same flights information but requires 0 stops
		testObj.findCheapestPrice(3, flights, 0, 2, 0);
		
		System.out.println("II = " + testObj.findCheapestPriceII(3, flights, 0, 2, 0));
		
	/* Test Case 3 */
		System.out.println("\n---< Test Case 3 >---");
		int[][] flights3 = new int[][] {{0, 1, 1},{1, 2, 1},{2, 3, 1}, 
										{3, 4, 1},{4, 5, 1}, {0, 6, 300},
										{6, 4, 100}, {0, 7, 350}, {7, 8, 1}, {8, 4, 1}};
										
		testObj.findCheapestPrice(9, flights3, 0, 5, 3);
		
		System.out.println("II = " + testObj.findCheapestPriceII(9, flights3, 0, 5, 3));
	}
}
