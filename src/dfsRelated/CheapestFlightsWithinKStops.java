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
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

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
	
	private void printResult() {
		System.out.println("cost = " + minCost);
		for (int city : finalPath) {
			System.out.print(city + " ");
		}
		System.out.println();
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
	
    /* ----------------------< Method 2 : Modified Dijkstra Algorithm >-------------------------
     * 
     * Time Complexity: O(?);
     * Space Complexity: O(?);	
     * 
     * */
    private class City implements Comparable<City> {
        private int id;
        private int costFromSrc;
        private int stopFromSrc;
        private int from;
        public City(int id, int costFromSrc, int stopFromSrc, int from) {
            this.id = id;
            this.costFromSrc = costFromSrc;
            this.stopFromSrc = stopFromSrc;
            this.from = from;
        }
        
        public int compareTo(City c) {
            return this.costFromSrc - c.costFromSrc;
        }
    }

    public int findCheapestPriceII(int n, int[][] flights, int src, int dst, int K) {
    		// Use adjacency matrix to represent city graph
        int[][] srcToDst = new int[n][n];
        for (int i = 0; i < flights.length; i++) {
            srcToDst[flights[i][0]][flights[i][1]] = flights[i][2]; 
        }
            
        PriorityQueue<City> minHeap = new PriorityQueue<>();
        minHeap.offer(new City(src, 0, 0, 0));
				
        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[src] = 0;
        
        int[] from = new int[n];
        Arrays.fill(from, Integer.MAX_VALUE);
        from[src] = 0;
        
        while (!minHeap.isEmpty()) {
            City curCity = minHeap.poll();
            
            from[curCity.id] = curCity.from;
            
            if (curCity.id == dst) {
            		printPath(from, src, dst, curCity.costFromSrc);
                return curCity.costFromSrc;
            }
            
            if (curCity.stopFromSrc == K + 1) {
                continue;
            }
            
            int[] nexts = srcToDst[curCity.id];
            for (int i = 0; i < n; i++) {
                if (nexts[i] != 0) {
                    int newCost = curCity.costFromSrc + nexts[i];
                    int newStop = curCity.stopFromSrc + 1;
                    if (newCost < cost[i]) {
                        minHeap.offer(new City(i, newCost, newStop, curCity.id));
                        cost[i] = newCost;
                    } else {
                        minHeap.offer(new City(i, newCost, newStop, curCity.id));
                    }
                }
            }
        }
       
        printPath(from, src, dst, cost[dst]);
        return cost[dst] == Integer.MAX_VALUE ? -1 : cost[dst];
    }

    private void printPath(int[] from, int src, int dst, int cost) {
    		System.out.println("II cost = " + cost);
    		List<Integer> path = new ArrayList<>();
        
        int cur = dst;
        while (cur != src) {
        		path.add(from[cur]);
        		cur = from[cur];
        }
        for (int i = path.size() - 1; i >= 0; i--) {
        		System.out.print(path.get(i) + " -> ");
        }
        System.out.print(dst);
        System.out.println();
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
		
		// function signiture: int findCheapestPrice(int n, int[][] flights, int src, int dst, int K)
		testObj.findCheapestPrice(3, flights, 0, 2, 1);
		
		testObj.findCheapestPriceII(3, flights, 0, 2, 1);
		
		/* Test Case 2 */
		System.out.println("\n---< Test Case 2 >---");

		// same flights information but requires 0 stops
		testObj.findCheapestPrice(3, flights, 0, 2, 0);
		testObj.findCheapestPriceII(3, flights, 0, 2, 0);
		
		/* Test Case 3 */
		System.out.println("\n---< Test Case 3 >---");
		int[][] flights3 = new int[][] {{0,1,5}, {1,2,5}, {0,3,2}, {3,1,2}, {1,4,1}, {4,2,1}};
		
		testObj.findCheapestPrice(5, flights3, 0, 2, 2);
		testObj.findCheapestPriceII(5, flights3, 0, 2, 2);
		
		/* Test Case 4 */
		System.out.println("\n---< Test Case 4 >---");
		int[][] flights4 = new int[][] {{0, 1, 1},{1, 2, 1},{2, 3, 1}, 
										{3, 4, 1},{4, 5, 1}, {0, 6, 300},
										{6, 4, 100}, {0, 7, 350}, {7, 8, 1}, {8, 4, 1}};
										
		testObj.findCheapestPrice(9, flights4, 0, 5, 3);
		testObj.findCheapestPriceII(9, flights4, 0, 5, 3);
	}
}
