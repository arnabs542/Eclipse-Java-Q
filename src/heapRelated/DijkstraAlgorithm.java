/*
 * == Created Date ==
 * Jan 1, 2019
 * 
 * == Question - Dijkstra's Algorithm ==
 * 
 */

package heapRelated;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;


public class DijkstraAlgorithm {

    private class Node implements Comparable<Node> {
        private int id;
        private int costFromSrc;
        private int from;
        public Node(int id, int costFromSrc, int from) {
            this.id = id;
            this.costFromSrc = costFromSrc;
            this.from = from;
        }
        
        public int compareTo(Node c) {
            return this.costFromSrc - c.costFromSrc;
        }
    }

    public int dijkstraAlgorithm(int n, int[][] edges, int src, int dst) {
    		// Use adjacency matrix to represent city graph
        int[][] srcToDst = new int[n][n];
        for (int i = 0; i < edges.length; i++) {
            srcToDst[edges[i][0]][edges[i][1]] = edges[i][2]; 
        }
            
        PriorityQueue<Node> minHeap = new PriorityQueue<>();
        minHeap.offer(new Node(src, 0, 0));
				
        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[src] = 0;
        
        int[] from = new int[n];
        Arrays.fill(from, Integer.MAX_VALUE);
        from[src] = 0;
        
        Set<Integer> visted = new HashSet<>();
        
        while (!minHeap.isEmpty()) {
        		Node curNode = minHeap.poll();
        		if (visted.add(curNode.id)) {
            		from[curNode.id] = curNode.from;
            		
                if (curNode.id == dst) {
                		printPath(from, src, dst);
                    return curNode.costFromSrc;
                }

                int[] neighbors = srcToDst[curNode.id];
                for (int i = 0; i < n; i++) {
                    if (neighbors[i] != 0) {
                        int newCost = curNode.costFromSrc + neighbors[i];
                        if (newCost < cost[i]) {
                            minHeap.offer(new Node(i, newCost, curNode.id));
                            cost[i] = newCost;
                        } 
                    }
                }
        		}
        }
       
        printPath(from, src, dst);
        return cost[dst] == Integer.MAX_VALUE ? -1 : cost[dst];
    }

    private void printPath(int[] from, int src, int dst) {
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
    
    public int dijkstraAlgorithmII(int n, int[][] edges, int src, int dst) {
    		List<Map<Integer,Integer>> graph = getGraph(n, edges);
    		
    		PriorityQueue<Map.Entry<Integer,Integer>> minHeap = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());
//    		minHeap.offer(new Map.Entry<Integer,Integer>(0, 0));
    		
    		return 0;
    }

	private List<Map<Integer,Integer>> getGraph(int n, int[][] edges) {
		// Use List<Map<Integer,Integer>> to store the source node, target node and the distance between them.
		List<Map<Integer,Integer>> graph = new ArrayList<>();
		for (int i = 0; i < n; i++) {
            graph.add(new HashMap<Integer,Integer>());
        }
        
		for (int i = 0; i < edges.length; i++) {
            graph.get(edges[i][0]).put(edges[i][1], edges[i][2]);
		}
		return graph;
	}
	// Time Complexity: O(E + VlogV); // E = (V ^ 2)
	// Space Complexity: O(V);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		DijkstraAlgorithm testObj = new DijkstraAlgorithm();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		int[][] edges = new int[][] {{0,1,3}, {1,2,5}, {0,3,2}, {3,1,2}, {1,4,1}, {4,2,1}};
		
		int cost = testObj.dijkstraAlgorithm(5, edges, 0, 2);
		System.out.println(cost);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}
