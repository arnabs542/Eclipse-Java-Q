/*
 * == Created Date ==
 * Jan 1, 2019
 * 
 * == Question - Number of Connected Components in an Undirected Graph==
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), 
 * write a function to find the number of connected components in an undirected graph.
 * 
 * == Example ==
 * 
 * Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]
 * 
 *   0          3
 *   |          |
 *   1 --- 2    4 
 *   
 * Output: 2 
 *   
 * == Notes == 
 * LeetCode 323* (M)
 * 
 */

package dfsRelated;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumberOfConnectedComponentsInUndirectedGraph {
    public int countComponents(int n, int[][] edges) {
        // Use hash table to record visited node during traversal
        Set<Integer> visited = new HashSet<>();
        
        // Get a graph with adjacency list representation
        List<List<Integer>> graph = getGraph(n, edges);
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (visited.add(i)) {
                count++;
                dfs(graph, visited, i); // marked all the connected nodes using dfs
            }
        }
        return count;
    }
    
    private void dfs(List<List<Integer>> graph, Set<Integer> visited, int node) {
        visited.add(node);
        for (int nei : graph.get(node)) {
            if (!visited.contains(nei)) {
                dfs(graph, visited, nei);
            }
        }
    }
    
   
    private List<List<Integer>> getGraph(int n, int[][] edges) {
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<Integer>());
        }
        
		for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]); // Undirected Graph
		}
		return graph;
	}
}
