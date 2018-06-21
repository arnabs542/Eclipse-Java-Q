/*
 * Created Date: June 16, 2018
 * 
 * Question - Bipartite:  
 *   Determine if an undirected graph is bipartite. 
 *   A bipartite graph is one in which the nodes can be divided into two groups 
 *     such that no nodes have direct edges to other nodes in the same group.
 *      
 *   Example: 
 *   
 *        [1]
 *       /   \
 *     (2)   (3)    is a bipartite graph, use [ ] and ( ) to represent two groups
 *       \   /
 *        [4] 
 *         
 *         [1]
 *       /  |  \
 *     (2)  |  (3)    is not a bipartite graph
 *       \  |  /
 *        ([4]) 
       
 *   
 *   Assumptions:
 *     The graph is represented by a list of nodes, and the list of nodes is not null.
 * 
 * Updated:
 *   June 17, 2018: Review
 */

package bfsRelated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class GraphNode {
	public int key;
    public List<GraphNode> neighbors;
    
    public GraphNode(int key) {
    	    this.key = key;
     	this.neighbors = new ArrayList<GraphNode>();
    }
}
    

public class Bipartite {
	
	/* ---------------< Method 1: Use queue to do BFS >------------------------*/	
	public static boolean isBipartite(List<GraphNode> graph) {
		HashMap<GraphNode, Integer> visited = new HashMap<>();
		for (GraphNode node : graph) {
			if(!BFS(node, visited)) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean BFS(GraphNode node, HashMap<GraphNode, Integer> visited) {
		if (visited.containsKey(node)) {
			return true;
		}
		Queue<GraphNode> queue = new LinkedList<>();
		queue.offer(node);
		visited.put(node, 0);
		while (!queue.isEmpty()) {
			GraphNode cur = queue.poll(); // expand a node cur
			int curGroup = visited.get(cur);
			int neiGroup = curGroup == 0 ? 1 : 0;
			for (GraphNode nei : cur.neighbors) {
				if (!visited.containsKey(nei)) {
					visited.put(nei, neiGroup);
					queue.offer(nei); // generate cur's neighbor node
				} else if (visited.get(nei) != neiGroup) {
					return false;
				}			
			}
		}
		return true;
	}
	
	// Time Complexity: O(V + E); 
	// Space Complexity: O(?);
	
	/* ---------------< Method 2 >------------------------*/	
	public static boolean isBipartiteMeth2(List<GraphNode> graph) {
		HashMap<GraphNode, Integer> visited = new HashMap<>();
		for (GraphNode node : graph) {
			if (!visited.containsKey(node)) {
				visited.put(node, 0);
			}			
			int neiGroup = visited.get(node) == 0 ? 1 : 0;
			for (GraphNode nei : node.neighbors) {
				if (!visited.containsKey(nei)) {
					visited.put(nei, neiGroup);
				} else if (visited.get(nei) != neiGroup) {
					return false;
				}
			}
		}
		return true;
	}	
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		System.out.print(1);
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		 /*        [node1]
		 *         /     \
		 *   (node2)     (node3)   
		 *         \     /
		 *         [node4] 
		 */
		
		List<GraphNode> graph = new LinkedList<>();
		
		GraphNode node1 = new GraphNode(1);
		GraphNode node2 = new GraphNode(2);
		GraphNode node3 = new GraphNode(3);
		GraphNode node4 = new GraphNode(4);
		
		node1.neighbors.add(node2);
		node1.neighbors.add(node3);
		
		node2.neighbors.add(node4);
		node3.neighbors.add(node4);
		
		graph.add(node1);
		graph.add(node2);
		graph.add(node3);
		graph.add(node4);

		System.out.println(isBipartite(graph)); // expected: true
		System.out.println(isBipartiteMeth2(graph)); // expected: true
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		 /*        [node1]
		 *         /  |   \
		 *   (node2)  |   (node3)   
		 *         \  |   /
		 *         [node4] 
		 */
		
		node1.neighbors.add(node4);
		System.out.println(isBipartite(graph)); // expected: false
		System.out.println(isBipartiteMeth2(graph)); // expected: false
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");	
	}
}
