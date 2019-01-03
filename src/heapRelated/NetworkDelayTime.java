/*
 * == Created Date ==
 * Jan 1, 2019
 * 
 * == Question - Network Delay Time ==
 * There are N network nodes, labelled 1 to N.
 * Given times, a list of travel times as directed edges times[i] = (u, v, w), 
 *   where u is the source node, v is the target node, 
 *   and w is the time it takes for a signal to travel from source to target.
 * 
 * Now, we send a signal from a certain node K. 
 * How long will it take for all nodes to receive the signal? If it is impossible, return -1.
 *   
 * == Notes == 
 * LeetCode 743 (E), easy????
 * 
 */

package heapRelated;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class NetworkDelayTime {
	
    /* ----------------------< Syntax 1 >-------------------------
     * 
     * Time Complexity: O();
     * Space Complexity: O();	
     * 
     * */
    public int networkDelayTime(int[][] times, int N, int K) {
    	
        List<Map<Integer, Integer>> graph = getGraph(N + 1, times);
        
        int[] cost = new int[N + 1];
        Arrays.fill(cost, Integer.MAX_VALUE);
        
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
        
        minHeap.add(new int[] {K, 0});
        cost[K] = 0;
        int visitedNodes = 0;
        
        while (!minHeap.isEmpty() && visitedNodes < N) {
            int[] cur = minHeap.poll();
            if (cur[1] > cost[cur[0]]) {
                continue;
            }
            visitedNodes++;
            
            for (Map.Entry<Integer, Integer> nei : graph.get(cur[0]).entrySet()) {
            		int neiNode = nei.getKey();
                int newCost = cur[1] + nei.getValue();
                if (newCost >= cost[neiNode]) {
                    continue;
                }
                cost[neiNode] = newCost;
                minHeap.add(new int[] {neiNode, newCost});
            }
        }
        
        if (visitedNodes != N) {
            return -1;
        }
        
        int result = 0;
        for (int i = 1; i <= N; i++) {
            result = Math.max(result, cost[i]);
        }
        return result;
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


    
    /* ----------------------< Syntax 2 >-------------------------
     * 
     * Time Complexity: O();
     * Space Complexity: O();	
     * 
     * */
	public int networkDelayTimeII(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge: times) {
            if (!graph.containsKey(edge[0])) {
            		graph.put(edge[0], new ArrayList<int[]>());
            }
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }
        
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>((info1, info2) -> info1[0] - info2[0]);
        
        heap.offer(new int[]{0, K});

        Map<Integer, Integer> dist = new HashMap<>();

        while (!heap.isEmpty()) {
            int[] info = heap.poll();
            int d = info[0];
            int node = info[1];
            if (dist.containsKey(node)) {
            		continue;
            }
            dist.put(node, d);
            if (graph.containsKey(node)) {
                for (int[] edge: graph.get(node)) {
                    int nei = edge[0];
                    	int d2 = edge[1];
                    if (!dist.containsKey(nei)) {
                    		heap.offer(new int[]{d+d2, nei});
                    }
                }
            }
        }

        if (dist.size() != N) {
        		return -1;
        }
        
        int ans = 0;
        for (int cand: dist.values()) {
        		ans = Math.max(ans, cand);
        }
        return ans;
    }
}
