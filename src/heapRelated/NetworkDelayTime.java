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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class NetworkDelayTime {
	
	public int networkDelayTime(int[][] times, int N, int K) {
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
