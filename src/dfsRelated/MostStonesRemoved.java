/*
 * == Created Date ==
 * Jan X, 2019
 * 
 * == Question - Most Stones Removed with Same Row or Column ==
 * On a 2D plane, we place stones at some integer coordinate points.  
 * Each coordinate point may have at most one stone.
 * 
 * Now, a move consists of removing a stone that shares a column or row with another stone on the grid.
 * What is the largest possible number of moves we can make?
 *   
 * == Example 1 == 
 * Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 * Output: 5  
 * 
 * == Example 2 == 
 * Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
 * Output: 3
 * 
 * == Notes == 
 * LeetCode 947(M)
 * 
 */

package dfsRelated;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MostStonesRemoved {
	
	/* ----- < Method 1 - DFS, count the number of connected components > -----
	 * Time Complexity: O(n^2), n is the length of stones
	 * 		Every time before we call function dfs, we always check if this stone is visited. 
	 *      The time to visit stones is also O(n) because we never visit a stone for more than once. 
	 *      So in total, it is O(n^2)
	 *      
	 * Space Complexity: O(n);
	 * 
	 * */
    public int removeStonesI(int[][] stones) {
        Set<int[]> visited = new HashSet<>();
        int numIslands = 0;
        for (int[] stone : stones) {
            if (!visited.contains(stone)) {
                numIslands++;
                dfs(stones, stone, visited);
            }
        }
        return stones.length - numIslands; // WHY?
    }
    
    // recursively connect the nodes that in the same row or colum
    private void dfs(int[][] stones, int[] curStone, Set<int[]> visited) {
        visited.add(curStone);
        for (int[] stone : stones){
            if (!visited.contains(stone)) {
                if (curStone[0] == stone[0] || curStone[1] == stone[1]) {
                    dfs(stones, stone, visited);
                }
            }
        }
    } 
    
	/* ----- < Method 2 - Union-Find > -----
	 * Time Complexity: O(n)
	 * Space Complexity: O(n);
	 * 
	 * == Algorithm ==
	 * Let's connect row i to column j, which will be represented by j+10000. 
	 * The answer is the number of components after making all the connections.
	 * 
	 * */
    public int removeStonesII(int[][] stones) {
        UnionFindSet set = new UnionFindSet(20000);

        for (int[] stone: stones) {
        		set.Union(stone[0], stone[1] + 10000);
        }

        Set<Integer> seen = new HashSet<>();
        for (int[] stone: stones) {
        		seen.add(set.Find(stone[0]));
        }
        return stones.length - seen.size();
    }

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
}
