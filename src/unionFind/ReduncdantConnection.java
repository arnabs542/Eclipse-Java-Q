/*
 * == Created Date ==
 * Jan 14, 2019
 * 
 * == Question - Reduncdant Connection ==
 * 
 * == Notes == 
 * LeetCode 684(M+) 
 * 
 */
package unionFind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ReduncdantConnection {

	/* ------------------- < Method : DFS >------------------
	 * 
	 * For each edge {u,v}, use DFS to check whether u,v are already connected
	 * 
	 * Steps: 
	 * 
	 * 	 1 
	 *  / \
	 * 2 - 3
	 * 
	 * 1 [2, 3]
	 * 2 [1, 3]
	 * 3 [1, 2]
	 * 
	 * 
	 * Time Complexity: O(N^2) // check n nodes, and for each node, check n edges
	 * Space Complexity: O(N)
	 * 
	 */
	public int[] findRedundantConnection(int[][] edges) {
		List<int[]> resultList = new ArrayList<>();
		Map<Integer, Set<Integer>> graph = buildGraph(edges);
		int[] states = new int[graph.size()]; // 0: unknown, 1: visting, 2: visited
		
		for (Map.Entry<Integer, Set<Integer>> entry : graph.entrySet()) {
			dfs(graph, entry.getKey(), states, resultList);
		}
		
		for (int i = edges.length; i >= 0; i--) {
			for (int[] pair : resultList) {
				if (edges[i][0] == pair[0] && edges[i][1] == pair[1]) {
					return pair;
				}
			}
		}
		return null;
	}
	
	private void dfs(Map<Integer, Set<Integer>> graph, Integer cur, 
			 	     int[] states, List<int[]> resultList) {
		states[cur] = 1;
		for (Integer nei : graph.get(cur)) {
			if (states[nei] == 1) {
				resultList.add(new int[] {cur, nei});
			} else if (states[nei] == 0) {
				dfs(graph, nei, states, resultList);
			}
		}
		states[cur] = 2;
	}
	
	private Map<Integer, Set<Integer>> buildGraph(int[][] edges) {
		Map<Integer, Set<Integer>> graph = new HashMap<>();
		for (int[] edge : edges) {
			graph.putIfAbsent(edge[0], new HashSet<>());
			graph.get(edge[0]).add(edge[1]);
			
			graph.putIfAbsent(edge[1], new HashSet<>());
			graph.get(edge[1]).add(edge[0]);
		}
		return graph;
	}

}
