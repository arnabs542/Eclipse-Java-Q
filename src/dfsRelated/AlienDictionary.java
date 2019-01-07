/*
 * == Created Date ==
 * Jan 2, 2019
 * 
 * == Question - Alien Dictionary ==
 * 
 * There is a new alien language which uses the latin alphabet. 
 * However, the order among letters are unknown to you. 
 * You receive a list of non-empty words from the dictionary, 
 *   where words are sorted lexicographically by the rules of this new language. 
 * Derive the order of letters in this language.
 *  
 * == Example ==
 * Input:
 * [
 *   "wrt",
 *   "wrf",
 *   "er",
 *   "ett",
 *   "rftt"
 * ]
 * 
 * Output: "wertf"
 *   
 * == Notes == 
 * LeetCode 269* (H)
 * 
 */

package dfsRelated;

/*
 * [
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]

r [t]
t [f]
e [r]
w [e] 


f t r e w

wertf

 * 
 * */
import java.util.Map;
import java.util.Queue;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class AlienDictionary {
	
    public String alienOrder(String[] words) {
        Map<Character, Integer> inDegree = new HashMap<>();
		Map<Character, List<Character>> graph = getGraph(words, inDegree);
		
		Queue<Character> queue = new ArrayDeque<>();
		int numOfChar = graph.size();
		StringBuilder order = new StringBuilder();
		
		for (Map.Entry<Character, List<Character>> entry : graph.entrySet()) {
            // we only add chararters with non-zero in-degree to the inDegree map,
            // thus the chararters that don't exist in inDegree map have 0 in-degree, add them to the queue
			if (!inDegree.containsKey(entry.getKey())) {
				queue.add(entry.getKey());
			}
		}
		
		int count = 0;
		while (!queue.isEmpty()) {
			char cur = queue.remove();
			count++;
			order.append(cur);
			for (char nei : graph.get(cur)) { // Reduce the in-degree of each neighbor by 1
				inDegree.put(nei, inDegree.getOrDefault(nei, 0) - 1);
				if (inDegree.get(nei) == 0) { // If in-degree of a neighbor becomes 0, add it to the queue
					queue.add(nei);
				}    					
			}
		}
		
	    if (count == numOfChar) {
	        return order.toString();
	    }
		return "";
    }
    
    private Map<Character, List<Character>> getGraph(String[] words, Map<Character, Integer> inDegree) {
		Map<Character, List<Character>> graph = new HashMap<>();
        
		// Initialize the graph (key: every character in words array, value: neighbors list)
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                char c = words[i].charAt(j);
                if (!graph.containsKey(c)) {
				    graph.put(c, new ArrayList<Character>());
			    }
            }
        }
        
        // convert the relationship of each character to the adjacency list representation of the graph
		for (int i = 0; i + 1 < words.length; i++) {
			int index = 0;
			int len1 = words[i].length();
			int len2 = words[i + 1].length();
			while (index < len1 && index < len2 && words[i].charAt(index) == words[i + 1].charAt(index)) {
				index++;
			}
			if (index != len1 && index != len2) {
				char src = words[i].charAt(index);
				char dst = words[i + 1].charAt(index);
				inDegree.put(dst, inDegree.getOrDefault(dst, 0) + 1);
				graph.get(src).add(dst);
			}
		}
		return graph;
	}
    
	
	/* ----------------------< test stub >-------------------------*/
	
	private static void printGraph(Map<Character, List<Character>> graph, Map<Character, Integer> inDegree) {
		System.out.println();
		for (Map.Entry<Character, List<Character>> node : graph.entrySet()) {
			System.out.print(node.getKey() + ": ");
			for (char nei : node.getValue()) {
				System.out.print(nei + " ");
			}
			System.out.println();
		}
		
		System.out.println("\nin-degree");
		for (Map.Entry<Character, Integer> entry : inDegree.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}
	
	public static void main(String[] args) {
		
		AlienDictionary testObj = new AlienDictionary();
		
		/* ----- < Test Case 0 : Test helper function getGraph() > ----- */
		System.out.println("---< Test Case 0 >---");
		
		String[] words0 = new String[] {"wrt","wrf","er","ett","rftt"};
		Map<Character, Integer> indegree0 = new HashMap<>();
		printGraph(testObj.getGraph(words0, indegree0), indegree0);

		String[] words00 = new String[] {"a","a"};
		Map<Character, Integer> indegree00 = new HashMap<>();
		printGraph(testObj.getGraph(words00, indegree00), indegree00);
		
		/* Test Case 1 */
		System.out.println("\n---< Test Case 1 >---");
		
		System.out.println(testObj.alienOrder(words0)); // expected: wertf
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		System.out.println(testObj.alienOrder(words00)); // expected: a
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
	
	}	
}
