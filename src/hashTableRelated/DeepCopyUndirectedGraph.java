/*
 * Created Date: August 8, 2018
 * 
 * Question - Deep Copy Undirected Graph (medium):
 *   Make a deep copy of an undirected graph, 
 *     there could be cycles in the original graph.
 * 
 */

package hashTableRelated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GraphNode {
   public int key;
   public List<GraphNode> neighbors;
   public GraphNode(int key) {
     this.key = key;
     this.neighbors = new ArrayList<GraphNode>();
   }
}

public class DeepCopyUndirectedGraph {
	
	public List<GraphNode> copy(List<GraphNode> graph) {
		Map<GraphNode, GraphNode> map = new HashMap<>();
		List<GraphNode> newGraph = new ArrayList<>();
		for (GraphNode node : graph) {
			if (!map.containsKey(node)) {
				GraphNode newNode = new GraphNode(node.key);
				map.put(node, newNode);
				newGraph.add(newNode);
				DFS(node, newGraph, map);
			}
		}
		return newGraph;
	}
	
	private void DFS(GraphNode node, List<GraphNode> newGraph, Map<GraphNode, GraphNode> map) {
		for (GraphNode nei : node.neighbors) {
			if (!map.containsKey(nei)) {
				GraphNode newNode = new GraphNode(nei.key);
				map.put(nei, newNode);
				newGraph.add(newNode);
				DFS(nei, newGraph, map);
			} 
			map.get(node).neighbors.add(nei);
		}
	}
	
	/* Another way with more concise syntax */
	
	public List<GraphNode> copy1(List<GraphNode> graph) {
		Map<GraphNode, GraphNode> map = new HashMap<>();
		for (GraphNode node : graph) {
			if (!map.containsKey(node)) {
				map.put(node, new GraphNode(node.key));
				DFS(node, map);
			}
		}
		return new ArrayList<>(map.values()); // the biggest difference from above syntax
	}
	
	private void DFS(GraphNode node, Map<GraphNode, GraphNode> map) {
		for (GraphNode nei : node.neighbors) {
			if (!map.containsKey(nei)) {
				map.put(nei, new GraphNode(nei.key));
				DFS(nei, map);
			} 
			map.get(node).neighbors.add(nei);
		}
	}
	
	/* Same problem in LeetCode but different given signature */
	
    public GraphNode cloneGraph(GraphNode node) {
        Map<GraphNode, GraphNode> map = new HashMap<>();
        map.put(node, new GraphNode(node.key));
        DFS_LeetCode(node, map);
        return map.get(node);
    }
    
    private void DFS_LeetCode(GraphNode node, Map<GraphNode, GraphNode> map) {
        for (GraphNode nei : node.neighbors) {
            if (!map.containsKey(nei)) {
                map.put(nei, new GraphNode(nei.key));
                DFS_LeetCode(nei, map); // made a mistake here, only DFS a node that hasn't been visited, or the recursion will not end 
            } 
            map.get(node).neighbors.add(map.get(nei));
        }
    }
}
