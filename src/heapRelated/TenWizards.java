/*
 * == Created Date ==
 * Jan 6, 2019
 * 
 * == Question - 10 Wizards ==
 * There are 10 wizards, 0-9, you are given a list that each entry is a list of wizards known by wizard.
 * Define the cost between wizards and wizard as square of different of I and j. 
 * To find the min cost between 0 and 9.
 * 
 * == Example == 
 * wizard[0] list: 1, 4, 5
 * wizard[4] list: 9
 *   
 * wizard 0 to 9 min distance is (0-4) ^ 2 + (4-9) ^ 2 = 41 (wizard[0]->wizard[4]->wizard[9])]
 * 
 * == Notes == 
 * Airbnb Interview Problem
 * 
 */

package heapRelated;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class TenWizards {
	
	/* ------------------- < Assumption >------------------
	 * As this a not a standard problem in LeetCode, we don't have specified input type.
	 * (Remember to discuss the following questions with the interviewee.)
	 * 
	 * Assume all the cost are Integer;
	 * If from source node, we cannot reach target node, return -1
	 * Assume the input is a list of 10 wizards and their relationship;
	 * Assume the relationship is bidirectional;
	 * Assume all the input are valid;
	 * Thus, the abstract data structure is undirected weighted graph.
	 * 
	 * */
	
	/* ------------------- < Method 1: BFS (Dijkstra) >------------------
	 * Use Dijkstra's Algorithm to find the minimum cost path between two wizards
	 * 
	 * Time Complexity: O(E + VlogV), where V is the number of nodes, and E is the number of edges
	 * Space Complexity: O(V)
	 * 
	 * == Data Structure ==
	 * Min Heap (Java: PriorityQueue)
	 * Hash Table (Java: HashSet)
	 * 
	 * == Algorithm == 
	 * 
	 * Initilization: enqueue the source node
	 * 
	 * For each step:
	 * 	Expand:
	 *  		- dequeue the node with the smallest cost from source node - O(logV)
	 *  		- only generate neighbor for nodes that hasn't been visited before
	 * 	Generation:
	 *  		- if we reach the target node, we find the answer
	 *  		- otherwise, update the costFromSrc and parent for neighbor nodes
	 *  		- only enqueue nodes that the new cost is smaller than before  
	 * 
	 * Termination: when queue is empty, or we find the answer during the process
	 * 
	 * == Just for refreshing the knowledge - Properties Dijkstra's Algorithm == 
	 * - one node is expanded only once
	 * - when the node is poped out from expasion, it's cost from the source node is the shortest cost
	 * - one node can be generated many times (cost can be reduced over time)
	 * - all the cost of the nodes expanded from the heap are monotonically increasing
	 * 
	 */
	
	class Wizard {
		int id;
		int costFromSrc = Integer.MAX_VALUE;
		int parent = -1;
		Map<Integer, Integer> neighbors = null;
		Wizard(int id) {
			this.id = id;
			this.neighbors = new HashMap<Integer, Integer>();
		}
	}
	
	public int TenWizardsI(List<List<Integer>> relationship, int src, int dst) {
		List<Wizard> graph = BuildWizardGraph(relationship);
		
		Set<Wizard> visted = new HashSet<>();
		
		// Initilization: enqueue the source node
		PriorityQueue<Wizard> minHeap = new PriorityQueue<>((a, b) -> (a.costFromSrc - b.costFromSrc));
		graph.get(src).costFromSrc = 0;
		graph.get(src).parent = 0;
		minHeap.offer(graph.get(src));
		
		while (!minHeap.isEmpty()) {
			Wizard curWizard = minHeap.poll(); // dequeue the node with the smallest cost from source node
			
			if (visted.add(curWizard)) { // only generate neighbor for nodes that hasn't been visited before
				
				if (curWizard.id == dst) { // if we reach the target node, we find the answer
					printPath(graph, src, dst);
					return curWizard.costFromSrc;
				}
				
				// generate its neighbor nodes
				for (Map.Entry<Integer, Integer> neiInfo : curWizard.neighbors.entrySet()) {
					Wizard neighbor = graph.get(neiInfo.getKey());
					int newCost = curWizard.costFromSrc + neiInfo.getValue();
					
					// update the costFromSrc and parent for neighbor nodes, 
					if (newCost < neighbor.costFromSrc) { 
						neighbor.parent = curWizard.id;
						neighbor.costFromSrc = newCost;
						minHeap.offer(neighbor); // only enqueue nodes that the new cost is smaller than before  
					}
				}
			}
		}
		return -1; 
	}
	
	private List<Wizard> BuildWizardGraph(List<List<Integer>> relationship) {
		List<Wizard> graph = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			graph.add(new Wizard(i));
		}
		for (int i = 0; i < 10; i++) {
			for (int nei : relationship.get(i)) {
				int cost = (int) Math.pow(nei - i, 2);
				graph.get(i).neighbors.put(nei, cost);
				graph.get(nei).neighbors.put(i, cost);
			}
		}
		return graph;
	}

	private void printPath(List<Wizard> graph, int src, int dst) {
		Deque<Integer> path = new ArrayDeque<>();
		while (dst != src) {
			path.push(dst);
			dst = graph.get(dst).parent;
		}
		path.push(src);
		while (!path.isEmpty()) {
			System.out.print(path.pop() + " ");
		}
	}
	
	/* ------------------- < Method 2: DFS >------------------
	 * Time Complexity: O(?) 
	 * Space Complexity: O(?)
	 * 
	 * == Data Structure ==
	 * Min Heap (Java: PriorityQueue)
	 * Hash Table (Java: HashSet)
	 * 
	 * == Algorithm == 
	 * - What does it store on each level? 
	 * 
	 * - How many different states should we try to explore on this level?
	 * wizard[0] list: 1, 4, 5
	 * wizard[4] list: 9
	 * 
	 *                        0
	 *                   /    |    \
	 * 
	 */
	
	public int TenWizardsII(List<List<Integer>> wizards, int src, int dst) {
		List<List<Integer>> graph = BuildWizardGraphII(wizards);
		int[] min = new int[] {Integer.MAX_VALUE};
		Map<Integer, List<List<Integer>>> pathMap = new HashMap<>();
		dfs(graph, src, dst, 0, new HashSet<>(), min, new ArrayList<>(), pathMap);
		
		// print all paths with mininum cost 
		if (min[0] == Integer.MAX_VALUE) {
			return -1;
		}
		for (List<Integer> minPath: pathMap.get(min[0])) {
			for (int node : minPath) {
				System.out.print(node + " ");
			}
		}
		System.out.println();
		return min[0];
	}
	
	private void dfs(List<List<Integer>> graph, int cur, int dst, int cost, 
					Set<Integer> visited, int[] min, List<Integer> path, Map<Integer, List<List<Integer>>> pathMap) {
		if (cur == dst) {
			min[0] = Math.min(min[0], cost);
			storePath(path, dst, cost, pathMap);
			return;
		}
		visited.add(cur);
		path.add(cur);
		for (int nei : graph.get(cur)) {
			if (!visited.contains(nei)) {
				int newCost = cost + (int) Math.pow(nei - cur, 2);
				if (newCost < min[0]) { // brune for time optimization, remove it to print all the possible paths
					dfs(graph, nei, dst, newCost, visited, min, path, pathMap);
				}
			}
		}
		visited.remove(cur);
		path.remove(path.size() - 1);
	}
	
	private void storePath(List<Integer> path, int dst, int cost, Map<Integer, List<List<Integer>>> pathMap) {
		path.add(dst);
		if (!pathMap.containsKey(cost)) {
			pathMap.put(cost, new ArrayList<>());
		} 
		pathMap.get(cost).add(new ArrayList<>(path));	
		path.remove(path.size() - 1);
		
		// print all possible paths
//		for (int node : path) {
//			System.out.print(node + " > ");
//		}
//		System.out.print(dst + " [cost: " + cost + "]\n");
	}
	
	private List<List<Integer>> BuildWizardGraphII(List<List<Integer>> wizards) {
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < 10; i++) {
			for (int nei : wizards.get(i)) {
				graph.get(i).add(nei);
				graph.get(nei).add(i);  // undirected map
			}
		}
		return graph;
	}
	
	/* ----------------------< test stub >-------------------------*/
	
	public static List<List<Integer>> createWizardList(int[][] relationship) {
        List<List<Integer>> wizards = new ArrayList<>();
        for (int i = 0; i < relationship.length; i++) {
            List<Integer> wizard = new ArrayList<>();
            for (int j = 0; j < relationship[i].length; j++) {
                wizard.add(relationship[i][j]);
            }
            wizards.add(wizard);
        }
        return wizards;
	}
	
	public static void main(String[] args) {
		
		TenWizards testObj = new TenWizards();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
        int[][] relationship1 = {{1, 2, 3}, {5}, {4, 5}, {2}, {5, 9}, {9}, {}, {}, {}, {}};

        List<List<Integer>> wizards = createWizardList(relationship1);
        System.out.println("cost:" + testObj.TenWizardsII(wizards, 0, 9));
        System.out.println("\nI cost:" + testObj.TenWizardsI(wizards, 0, 9));
        
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		int[][] relationship2 = {{1, 4, 5}, {}, {}, {}, {9}, {}, {}, {}, {}, {}};
		List<List<Integer>> wizards2 = createWizardList(relationship2);
		System.out.println("cost:" + testObj.TenWizardsII(wizards2, 0, 9));
		System.out.println("\nI cost:" + testObj.TenWizardsI(wizards2, 0, 9));
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");

		
	}
}