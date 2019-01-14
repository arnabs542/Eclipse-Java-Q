/*
 * == Created Date ==
 * Jan 14, 2019
 * 
 * == Question - Evaluate Division ==
 * 
 * == Notes == 
 * LeetCode 399(M++) 
 * 
 */

package dfsRelated;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EvaluateDivision {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        double[] result = new double[queries.length];
        Map<String, Map<String, Double>> graph = buildGraph(equations, values);
        for (int i = 0; i < queries.length; i++) {
        	result[i] = divide(graph, queries[i][0], queries[i][1], new HashSet<>());
        }
        return result;
    }
    
    // dfs to find the cost from two nodes
    private double divide(Map<String, Map<String, Double>> graph, String cur, String target, Set<String> visited) {
        if (!graph.containsKey(cur)) {
            return -1.0;
        }
        if (graph.get(cur).containsKey(target)) { // WHY???
            return graph.get(cur).get(target);
        }
        visited.add(cur);
        for (Map.Entry<String, Double> nei : graph.get(cur).entrySet()) {
            if (!visited.contains(nei.getKey())) {
                double temp = divide(graph, nei.getKey(), target, visited);
                if (temp != -1.0) {
                    return temp * nei.getValue();
                }
            }
        }
        // visited.remove(cur); // WHY NOT ???
        return -1.0;
    }
    
    // build directed weighted graph from the equations
    private Map<String, Map<String, Double>> buildGraph(String[][] equations, double[] values) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for (int i = 0; i < equations.length; i++) {
            String first = equations[i][0];
            String second = equations[i][1];
            
            graph.putIfAbsent(first, new HashMap<>());
            graph.get(first).put(second, values[i]);
            
            graph.putIfAbsent(second, new HashMap<>());
            graph.get(second).put(first, 1.0 / values[i]);
        }
        return graph;
    }
    
    /*
    a / b = 2.0, b / c = 3.0
      
    a -> b 2.0
    
    b -> a 1 / 2.0
    
    b -> c 3.0
    
    c -> b 1 / 3.0
    
    a / c = a -> c 
    
    */
}
