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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ReduncdantConnection {

	/* ------------------- < Method : DFS >------------------
	 * 
	 * For each edge {u,v}, use DFS to check whether u,v are already connected
	 * 
	 * {1,2}, {2, 3}, {1,3} 
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
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        
        for (int[] edge: edges) {
        		if (hasPath(graph, edge[0], edge[1], new HashSet<>())) {
        			return edge;
        		}
        		graph.putIfAbsent(edge[0], new HashSet<>());
        		graph.get(edge[0]).add(edge[1]);
        		
        		graph.putIfAbsent(edge[1], new HashSet<>());
        		graph.get(edge[1]).add(edge[0]);
        }
        return null;
    }
    
    private boolean hasPath(Map<Integer, Set<Integer>> graph, int source, int target, Set<Integer> seen) {
        if (source == target) {
        		return true;
        }
        if (!graph.containsKey(source) || !graph.containsKey(target)) {
        		return false;
        }
        seen.add(source);
        for (int nei : graph.get(source)) {
        		if (!seen.contains(nei) && hasPath(graph, nei, target, seen)) {
        			return true;
        		}
        }
        return false;
    }
  
	/* ------------------- < Method : Union-Find >------------------
	 * 
	 * {1,2}, {2, 3}, {1,3} 
	 * 
	 * 	 1 
	 *  / \
	 * 2 - 3
	 * 
	 * 
	 * 
	 * Time Complexity: O(N) // check n nodes, and for each node, check n edges
	 * Space Complexity: O(N)
	 * 
	 */
    public int[] findRedundantConnectionII(int[][] edges) {
    		UnionFindSet set = new UnionFindSet(edges.length);
    		for (int[] edge : edges) {
    			if (!set.Union(edge[0], edge[1])) {
    				return edge;
    			}
    		}
    		return null;
    }
    
    /*
     * == Disjoint-set/Union-find Forest ==
     * The Union-find data structure can be used to maintain knowledge of the connected components of a graph, and query for them quickly. 
     * 
     * Two operations:
     * - Find(x): find the root/cluster-id of x - O(ɑ(n))* ≈ O(1)
     * - Union(x, y): merge two clusters - O(ɑ(n))* ≈ O(1)
     * 
     * Check whether two elements are in the same set or not in O(1)*.
     * 
     * Without optimization: Find: O(n), Two key optimizations:
     * - Path compression: make tree flat
     * - Union by rank: merge low rank tree to high rank one
     * 
     * */
    class UnionFindSet {
    	
        private int[] parent;
        private int[] rank;

        public UnionFindSet(int size) {
            parent = new int[size + 1];
            rank = new int[size + 1];
            for (int i = 0; i < size; i++) {
            		parent[i] = i;
            }
        }

        public int Find(int x) {
            if (parent[x] != x) {
            		parent[x] = Find(parent[x]);
            }
            return parent[x];
        }

        public boolean Union(int x, int y) {
            int rootX = Find(x);
            int rootY = Find(y);
           
            if (rootX == rootY) {
                return false;
            } 
            
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
            return true;
        }
    }
    
		
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		ReduncdantConnection testObj = new ReduncdantConnection();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		testObj.findRedundantConnection(new int[][] {{1,2}, {2, 3}, {1,3}});
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		testObj.findRedundantConnection(new int[][] {{1,2}, {2,3}, {3,4}, {1,4}, {1,5}});
		
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}

}
