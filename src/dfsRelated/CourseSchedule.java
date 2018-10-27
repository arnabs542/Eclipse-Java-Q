/*
 * == Created Date ==
 * October 26, 2018
 * 
 * == Question - Course Schedule==  
 *     
 * == Notes == 
 * LeetCode 207 
 * 
 */

package dfsRelated;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class CourseSchedule {
	
	/* ------------------- < Topological Sort Graph Algorithm >------------------
	 * Not the solution of this problem 
	 *
	 * Data Structure:
	 * 	1. Set to remember all the visited node.
	 * 	2. Stack to remmber the order
	 * 
	 * Steps: 
	 * 1. Pick a random node from the graph to start. 
	 * 2. Use DFS to recursively check its neighbors, use a Set to remember all the visited node.
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
	
    public void topologicalSort(int nums, int[][] edges) {
		List<List<Integer>> graph = getGraph(nums, edges);
		Deque<Integer> ordreStack = new ArrayDeque<>();
		Set<Integer> visited = new HashSet<>();		
		for (int i = 0; i < nums; i++) {
			if (!visited.contains(i)) {
				dfsForTopoSort(graph, visited, i, ordreStack);
			}		
		}
		printForTopoSort(ordreStack);
    }
    
	private void dfsForTopoSort(List<List<Integer>> graph, Set<Integer> visited, int cur, Deque<Integer> ordreStack) {
		if (visited.contains(cur)) {
			return;
		}
		visited.add(cur);
		for (int nei : graph.get(cur)) {
			dfsForTopoSort(graph, visited, nei, ordreStack);
		}
		ordreStack.add(cur);
	}
	
    private void printForTopoSort(Deque<Integer> ordreStack) {
		System.out.println("\nTopological Sort: ");
		while (!ordreStack.isEmpty()) {
			System.out.print(ordreStack.pop() + " ");
		}
		System.out.println();
    }

	/* ------------- < Solution1: Use DFS to detect if cycle exists in a Directed Graph >------------ 
	 * 
	 * The problem equals to detect whether a cycle exists in a Directed Graph or not
	 * 
	 * Data Structure:
	 * List<List<Integer>> graph, convert the given edges matrix to the Adjacency List representation
	 * List<Integer> state - 0: unknown; 1: visiting; 2: visited 
	 * 
	 * 
	 */
    public boolean canFinishMeth1(int numCourses, int[][] prerequisites) {
    		List<List<Integer>> graph = getGraph(numCourses, prerequisites);
    		int[] state = new int[numCourses]; // 0: unknown; 1: visiting; 2: visited    
    		
    		for (int i = 0; i < numCourses; i++) {
    			if (detectCycle(graph, i, state)) {
    				return false;
    			}
    		}
        return true;
    }
    
    private boolean detectCycle(List<List<Integer>> graph, int cur, int[] state) {
    		if (state[cur] == 1) {
    			return true;
    		} 
    		if (state[cur] == 2) {
    			return false;
    		}
    		state[cur] = 1;
    		for (int nei: graph.get(cur)) {
    			if (detectCycle(graph, nei, state)) {
    				return true;
    			}
    		}
    		state[cur] = 2;
    		return false;
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
    
	// Time Complexity: O(N); // N: edges
	// Space Complexity: O(N); 
    
    // --------------------------------- < Solution1: Use BFS >------------------------ //
    public boolean canFinishMeth2(int numCourses, int[][] prerequisites) {
    		int[] inDegree = new int[numCourses];
    		Map<Integer, List<Integer>> graph = getGraph(prerequisites, inDegree);
    		Queue<Integer> queue = new ArrayDeque<>();
    		
    		for (int i = 0; i < numCourses; i++) {
    			if (inDegree[i] == 0) {
    				queue.offer(i);
    			}
    		}
    		
    		while (!queue.isEmpty()) {
    			int cur = queue.poll();
    			for (int nei : graph.get(cur)) {
    				inDegree[nei]--;
    				if (inDegree[nei] == 0) {
    					if (graph.containsKey(nei)) {
    						queue.offer(nei);
    					}    					
    				}
    			}
    		}
    		
    		for (int i = 0; i < numCourses; i++) {
    			if (inDegree[i] != 0) {
    				return false;
    			}
    		}
    		return true;
    }

    private Map<Integer, List<Integer>> getGraph(int[][] prerequisites, int[] inDegree) {       
   		Map<Integer, List<Integer>> graph = new HashMap<>();
   		
		for (int i = 0; i < prerequisites.length; i++) {
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
    
	/* ----------------------< test stub >-------------------------*/    
    private void print(List<List<Integer>> graph) {
    		int course = 0;
		for (List<Integer> neiList : graph) {			
			System.out.print("\n" + course++ + ": ");
			for (int nei : neiList) {
				System.out.print(nei + " ");
			}
		}
		System.out.println();
    }
    
	public static void main(String[] args) {
		
		CourseSchedule testObj = new CourseSchedule();
		int[][] prerequisites = { 
								  {1,0}, 
								  {2,1}, 
								  {2,0},
								  {3,4},
								  {4,5}
								};
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		testObj.print(testObj.getGraph(6, prerequisites));
		testObj.topologicalSort(6, prerequisites);
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		System.out.println(testObj.canFinishMeth1(6, prerequisites));
		System.out.println(testObj.canFinishMeth2(6, prerequisites));
				
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		int[][] prerequisites2 = { 
								 	{1,0}, 
								 	{2,1}, 
								 	{0,2},
								 };
		System.out.println(testObj.canFinishMeth1(3, prerequisites2));
		System.out.println(testObj.canFinishMeth2(3, prerequisites2));
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}

}
