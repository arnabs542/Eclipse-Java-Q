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
 *   Assumptions:
 *     The graph is represented by a list of nodes, and the list of nodes is not null.
 *   
 * Need to Review in June 17!!!
 * 
 * Updated:
 * 
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
	
	public boolean isBipartite(List<GraphNode> graph) {
		HashMap<GraphNode, Integer> visited = new HashMap<GraphNode, Integer>();
		for (GraphNode node : graph) {
			if(!BFS(node, visited)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean BFS(GraphNode node, HashMap<GraphNode, Integer> visited) {
		if (visited.containsKey(node)) {
			return true;
		}
		Queue<GraphNode> queue = new LinkedList<GraphNode>();
		queue.offer(node);
		visited.put(node, 0);
		while (!queue.isEmpty()) {
			GraphNode cur = queue.poll();
			int curGroup = visited.get(cur);
			int neiGroup = curGroup == 0 ? 1 : 0;
			for (GraphNode nei : cur.neighbors) {
				if (!visited.containsKey(nei)) {
					visited.put(nei, neiGroup);
					queue.offer(nei);
				} else if (visited.get(nei) != neiGroup) {
					return false;
				}			
			}
		}
		return true;
	}
}
