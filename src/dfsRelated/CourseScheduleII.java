/*
 * == Created Date ==
 * Jan 2, 2019
 * 
 * == Question - Course Schedule II ==  
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 * Some courses may have prerequisites, for example to take course 0 
 *   you have to first take course 1, which is expressed as a pair: [0,1]
 *   
 * Given the total number of courses and a list of prerequisite pairs, 
 *   return the ordering of courses you should take to finish all courses.
 *   
 * There may be multiple correct orders, you just need to return one of them. 
 * If it is impossible to finish all courses, return an empty array.
 * 
 * == Example == 
 *  Input: 4, [[1,0],[2,0],[3,1],[3,2]]
 *  
 *  Output: [0,1,2,3] or [0,2,1,3]
 *  
 *  Explanation: There are a total of 4 courses to take. 
 *  	             To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. 
 *                So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .

 * == Notes == 
 * LeetCode 210，medium
 * 
 */

package dfsRelated;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CourseScheduleII {
	
	/* ------------------- < Method 1: Topological Sort Graph Algorithm with DFS >------------------
	 * Not the solution of this problem 
	 *
	 * Data Structure:
	 * 	1. A state array to record the states of nodes (0: unknown; 1: visiting; 2: visited    )
	 * 	2. A stack to record the order
	 * 
	 * Steps: 
	 * 1. Pick a random node from the graph to start. 
	 * 2. Use DFS to recursively check its neighbors, use the state array to mark its states
	 *    - If we find a node with visiting state, we detect a cycle, return false
	 * 3. If all the neighbors have been visited, put this node into the ordreStack
	 * 
	 * 4. Repeat 1 ~ 3, until all the nodes in the graph have been visited
	 * 
	 * Topological order is the reverse order of the ordreStack
	 * 
	 * Time Complexity: O(N) // N: nodes
	 * Space Complexity: O(N)
	 * 
	 */
	public int[] findOrder(int numCourses, int[][] prerequisites) { 
		List<List<Integer>> graph = getGraph(numCourses, prerequisites);
		Deque<Integer> ordreStack = new ArrayDeque<>();
		int[] state = new int[numCourses]; // 0: unknown; 1: visiting; 2: visited    
		
		for (int i = 0; i < numCourses; i++) {
			if (state[i] == 0) {
				if (!dfsForTopoSort(graph, i, ordreStack, state)) {
					return new int[0]; // if detect a cycle, it's impossible to finish all courses
				};
			}		
		}
		
		printForTopoSort(ordreStack);
		
		int[] result = new int[ordreStack.size()];
		for (int i = 0; i < result.length; i++) {
			int pop = ordreStack.pop(); 
			result[i] = pop;
		}
		return result;
	}
	    
	private boolean dfsForTopoSort(List<List<Integer>> graph, 
			                       int cur, Deque<Integer> ordreStack, int[] state) {
		if (state[cur] == 1) { // if found a node marked as visiting, we detect a cycle, stop the recursion
			return false;
		}
		if (state[cur] == 2) { // skip the nodes that has been processed
			return true;
		}
		
		state[cur] = 1; // markes as visiting
		for (int nei : graph.get(cur)) { // Traverse on neighboring vertices
			if (!dfsForTopoSort(graph, nei, ordreStack, state)) {
				return false;
			}
		}
		ordreStack.push(cur);
		state[cur] = 2; // markes as visited
		return true;
	}
    
    private List<List<Integer>> getGraph(int numCourses,int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
        		graph.add(new ArrayList<>());
        }
        for (int i = 0; i < prerequisites.length; i++) {
            graph.get(prerequisites[i][1]).add(prerequisites[i][0]);           
        }
        return graph;
    }

    private void printForTopoSort(Deque<Integer> ordreStack) {
    		System.out.println("\nTopological Sort: ");
    		for (int item : ordreStack) {
    			System.out.print(item + " ");
    		}
		System.out.println();
    }
    
	/* ------------------- < Method 2: Topological Sort Graph Algorithm with BFS >------------------
	 * Not the solution of this problem 
	 *
	 * Data Structure:
	 * 
	 * 
	 * Steps: 
	 * 
	 * Time Complexity: O(N) // N: nodes
	 * Space Complexity: O(N)
	 * 
	 */
    public int[] findOrderII(int numCourses, int[][] prerequisites) {
		int[] inDegree = new int[numCourses];
		Map<Integer, List<Integer>> graph = getGraph(prerequisites, inDegree);
		Queue<Integer> queue = new ArrayDeque<>();
		int[] topologicalOrder = new int[numCourses];
		
		for (int i = 0; i < numCourses; i++) {
			// Add all vertices with 0 in-degree to the queue
			if (inDegree[i] == 0) {
				queue.add(i);
			}
		}
		
		int i = 0;
		while (!queue.isEmpty()) {
			int cur = queue.remove();
			topologicalOrder[i++] = cur;
			if (graph.containsKey(cur)) {
				// Reduce the in-degree of each neighbor by 1
				for (int nei : graph.get(cur)) {
					inDegree[nei]--;
					// If in-degree of a neighbor becomes 0, add it to the queue
					if (inDegree[nei] == 0) {
						queue.add(nei);
					}    					
				}
			}
		}
		
	    if (i == numCourses) {
	    		System.out.println("\nII Topological Sort: ");
	    		for (int item : topologicalOrder) {
	    			System.out.print(item + " ");
	    		}
	        return topologicalOrder;
	    }
	    
		return new int[0];
    }

	// Create the adjacency list representation of the graph and record in-degree of each node
	private Map<Integer, List<Integer>> getGraph(int[][] prerequisites, int[] inDegree) {       
			Map<Integer, List<Integer>> graph = new HashMap<>();
			
		for (int i = 0; i < prerequisites.length; i++) {
			// [0, 1]  1 -> 0
			int pre = prerequisites[i][1];
			int course = prerequisites[i][0];
			
			inDegree[course]++;
			if (!graph.containsKey(pre)) {
				graph.put(pre, new ArrayList<Integer>());
			}
			graph.get(pre).add(course);
		}
		return graph;     
	}

	/* ----------------------------------< test stub >--------------------------------------------*/
	public static void main(String[] args) {
		
		CourseScheduleII testObj = new CourseScheduleII();

		/* Test Case 0 */ // 
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		int[][] prerequisites = { 
				  {1,0}, 
				  {2,0}, 
				  {3,1},
				  {3,2}
				}; // 4, [[1,0],[2,0],[3,1],[3,2]]
		testObj.findOrder(4, prerequisites); // expected: [0,1,2,3] or [0,2,1,3]
		testObj.findOrderII(4, prerequisites); 
		
		/* Test Case 2 */
		System.out.println("\n---< Test Case 2 >---");
		
		int[][] prerequisites2 = { 
								 	{1,0}, 
								 	{2,1}, 
								 	{0,2},
								 };
		testObj.findOrder(3, prerequisites2);
		testObj.findOrderII(3, prerequisites2); 
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}
