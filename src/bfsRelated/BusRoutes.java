/*
 * == Created Date ==
 * October 26, 2018
 * 
 * == Question - Bus Routes ==  
 * We have a list of bus routes. 
 * Each routes[i] is a bus route that the i-th bus repeats forever. 
 * For example if routes[0] = [1, 5, 7], this means that the first bus (0-th indexed) travels in the sequence 1->5->7->1->5->7->1->... forever.
 * We start at bus stop S (initially not on a bus), and we want to go to bus stop T. 
 * Travelling by buses only, what is the least number of buses we must take to reach our destination? 
 * Return -1 if it is not possible.    
 * 
 * == Example ==
 * Input: 
 * routes = [[1, 2, 7], [3, 6, 7]]
 * S = 1
 * T = 6
 * 
 * Output: 2
 * 
 * Explanation: 
 * The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
 * 
 * 
 * 1 -  2
 *  \  /
 *   7 
 *  / \ 
 * 3 - 6
 * 
 * a [b]
 * b [a]
 * 
 * == Notes == 
 * LeetCode 815(H)
 * 
 */

package bfsRelated;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class BusRoutes {

	/* ----- < Time Limit Exceeded Method 1 - Treat Buses as the nodes and Stops as edges, then DFS > -----
	 * Time Complexity: O(?);
	 * Space Complexity: O(?);
	 *  
	 * */
	public int numBusesToDestinationI(int[][] routes, int S, int T) {
        if (S == T) {
            return 0;
        }
		// build the bus graph
		List<Bus> graph = buildBusGraph(routes);
		
		// find the bus line at which the source and target stops locate
		List<Bus> startBuses = new ArrayList<>();
		List<Bus> targetBuses = new ArrayList<>();
		
		for (Bus bus : graph) {
            if (bus.stopsSet.contains(S) && bus.stopsSet.contains(T)) {
                return 1;
            }
			if (bus.stopsSet.contains(S)) {
				startBuses.add(bus);
			}
			if (bus.stopsSet.contains(T)) {
				targetBuses.add(bus);
			}
		}
		
		// dfs to find the minium bus number
		int[] min = new int[] {Integer.MAX_VALUE};
		for (Bus source : startBuses) {
			for (Bus target : targetBuses) {
				dfs(source, target, 1, new HashSet<>(), min);
			}
		}
		return min[0] == Integer.MAX_VALUE ? -1 : min[0];
	}
	
	private void dfs(Bus source, Bus target, int curCost, Set<Bus> seen, int[] min) {
		if (curCost >= min[0]) {
			return;
		}
		seen.add(source);
		for (Bus nei : source.neiBus) {
			if (nei.id == target.id) {
				min[0] = Math.min(min[0], curCost + 1);
				return;
			}
			if (!seen.contains(nei)) {
				dfs(nei, target, curCost + 1, seen, min);
			}
		}
        //seen.remove(source);
	}

	private List<Bus> buildBusGraph (int[][] routes) {
		List<Bus> graph = new ArrayList<>();
		for (int i = 0; i < routes.length; i++) {
			Bus newBus = new Bus(i);
			for (int stops : routes[i]) {
				newBus.stopsSet.add(stops);
			}
			graph.add(newBus);
		}
		
		for (int i = 0; i < graph.size(); i++) {
			Bus curBus = graph.get(i);
			for (int j = i + 1; j < graph.size(); j++) {
				Bus neiBus = graph.get(j);
				findOneNei: for (int stop1 : curBus.stopsSet) {
					for (int stop2 : neiBus.stopsSet) {
						if (stop1 == stop2) {
							curBus.neiBus.add(neiBus);
							neiBus.neiBus.add(curBus);
							break findOneNei;
						}
					}
				}
			}
		}
		return graph;
	}
	
	class Bus {
		private int id;
		private List<Bus> neiBus;
		private Set<Integer> stopsSet;
		
		public Bus(int id) {
			this.id = id;
			neiBus = new ArrayList<>();
			stopsSet = new HashSet<>();
		}
	}
	
	/* ----- < Time Limit Exceeded Method 2 - Treat Buses as the nodes and Stops as edges, then BFS > -----
	 * Time Complexity: O(?);
	 * Space Complexity: O(?);
	 *  
	 * */
	public int numBusesToDestinationII(int[][] routes, int S, int T) {
        if (S == T) { 
            return 0;
        }
		// build the bus graph
		List<Bus> graph = buildBusGraph(routes);
		
		// find the bus line at which the source and target stops locate
		Set<Bus> targetBuses = new HashSet<>();
        Queue<Bus> queue = new ArrayDeque<>();
        Set<Bus> seen = new HashSet<>();
        
		for (Bus bus : graph) {
            if (bus.stopsSet.contains(S) && bus.stopsSet.contains(T)) {
                return 1;
            }
			if (bus.stopsSet.contains(S)) {
				queue.offer(bus);
			}
			if (bus.stopsSet.contains(T)) {
				targetBuses.add(bus);
			}
		}
        
        int numBus = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            numBus++;
            for (int i = 0; i < size; i++) {
                Bus cur = queue.poll();
                seen.add(cur);
                for (Bus nei : cur.neiBus) {
                    if (targetBuses.contains(nei)) {
                       return numBus + 1;
                    } 
                    if (!seen.contains(nei)) {
                        queue.offer(nei);
                    }
                }
            }
        }
        return -1;
	}
	
	/* ----- < Method 1 - Treat Buses as the nodes and Stops as edges, then BFS > -----
	 * Time Complexity: O(?);
	 * Space Complexity: O(?);
	 * 
	 * */
    public int numBusesToDestinationIII(int[][] routes, int S, int T) {
        if (S == T) return 0;
        int N = routes.length;

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < N; ++i) {
            Arrays.sort(routes[i]);
            graph.add(new ArrayList<>());
        }
        Set<Integer> seen = new HashSet<>();
        Set<Integer> targets = new HashSet<>();
        Queue<Point> queue = new ArrayDeque<>();

        // Build the graph. Two buses are connected if they share at least one bus stop.
        for (int i = 0; i < N; ++i)
            for (int j = i + 1; j < N; ++j)
                if (intersect(routes[i], routes[j])) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }

        // Initialize seen, queue, targets.
        // seen represents whether a node has ever been enqueued to queue.
        // queue handles our breadth first search.
        // targets is the set of goal states we have.
        for (int i = 0; i < N; ++i) {
            if (Arrays.binarySearch(routes[i], S) >= 0) {
                seen.add(i);
                queue.offer(new Point(i, 0));
            }
            if (Arrays.binarySearch(routes[i], T) >= 0)
                targets.add(i);
        }

        while (!queue.isEmpty()) {
            Point info = queue.poll();
            int node = info.x, depth = info.y;
            if (targets.contains(node)) return depth + 1;
            for (Integer nei: graph.get(node)) {
                if (!seen.contains(nei)) {
                    seen.add(nei);
                    queue.offer(new Point(nei, depth + 1));
                }
            }
        }
        return -1;
    }

    public boolean intersect(int[] A, int[] B) {
        int i = 0, j = 0;
        while (i < A.length && j < B.length) {
            if (A[i] == B[j]) return true;
            if (A[i] < B[j]) i++; else j++;
        }
        return false;
    }
    
	/* ----- < Method 2 - Treat stops as the nodes, then BFS > -----
	 * Time Complexity: O(m * n), m is the number of bus, 
	 * Space Complexity: O(m * n);
	 * 
	 * == Steps ==
	 * Initialization: add source stop to the queue
	 * 
	 * Expansion: expand a stop from the queue
	 * 
	 * Generation: generate all the stops that can be reached using the same bus lines that come to the current stop
	 *  case1: If this stop is the target, return the result
	 *  case2: Otherwise, push this stop to the queue
	 *  - Deduplication: don't check the same bus route again
	 *  
	 * */
    
    /* routes = [[1, 2, 7], [3, 6, 7]]
     * S = 1
     * T = 6
     * 
     * 1 [0]
     * 2 [0]
     * 3 [1]
     * 6 [1]
     * 7 [0 1]
     * 
     * queue: 6 3
     * numBuses: 2
     * 
     */
    
    public int numBusesToDestination(int[][] routes, int S, int T) {
    		if (S == T) {
    			return 0;
    		}
        
    		// key: stop, value: the buses that come to this stop
    		Map<Integer, List<Integer>> stopAndBusMap = new HashMap<>();
        
    		// create the stopAndBusMap
        for (int busId = 0; busId < routes.length; busId++) {
	    		for (int stop : routes[busId]) {
	    			stopAndBusMap.putIfAbsent(stop, new ArrayList<>());
	    			stopAndBusMap.get(stop).add(busId);
	    		}
        }
        
        Set<Integer> busTaken = new HashSet<>();
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(S);
        int numBuses = 0;
        
        while (!queue.isEmpty()) {
        		numBuses++;
        		for (int i = queue.size(); i > 0; i--) {
        			// expand a stop node from queue
        			int curStop = queue.poll();      
        			
        			// generate all the stops that can be reached using the buses that come to curStop
        			for (int bus : stopAndBusMap.get(curStop)) {
        				if (busTaken.add(bus)) { // not taking the same bus again
        					for (int stop : routes[bus]) {
        						if (stop == T) { // if find a stop that is at the same bus route, return result
        							return numBuses;       
        		                 }
        						 queue.offer(stop); // otherwise, push the stop to the queue for next search
        		             }
        				}
        			}        
        		}      
        }
        return -1;
    }
    
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		BusRoutes testObj = new BusRoutes();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		int[][] routes = new int[][] {{10,13,22,28,32,35,43}, {2,11,15,25,27}, {6,13,18,25,42},
			{5,6,20,27,37,47}, {7,11,19,23,35}, {7,11,17,25,31,43,46,48}, {1,4,10,16,25,26,46},
			{7,11}, {3,9,19,20,21,24,32,45,46,49}, {11,41}
		};
		testObj.numBusesToDestination(routes, 37, 43);
				
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}
