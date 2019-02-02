/*
 * == Created Date ==
 * Feb 1, 2019
 * 
 * == Question - Kill Process ==
 * 
 * Given n processes, each process has a unique PID (process id) and its PPID (parent process id).
 * Each process only has one parent process, but may have one or more children processes. 
 * This is just like a tree structure. 
 * Only one process has PPID that is 0, which means this process has no parent process.
 * All the PIDs will be distinct positive integers.
 * 
 * We use two list of integers to represent a list of processes, 
 *   where the first list contains PID for each process and the second list contains the corresponding PPID.
 *   
 * Now given the two lists, and a PID representing a process you want to kill, 
 *   return a list of PIDs of processes that will be killed in the end. 
 * 
 * You should assume that when a process is killed, all its children processes will be killed. 
 * No order is required for the final answer.
 *   
 * == Example == 
 * Input: 
 * pid =  [1, 3, 10, 5]
 * ppid = [3, 0, 5, 3]
 * kill = 5
 * Output: [5,10]
 * 
 * Explanation: 
 *          3
 *        /   \
 *       1     5
 *            /
 *          10
 *          
 * Kill 5 will also kill 10
 * 
 * == Notes == 
 * LeetCode 582*(M)
 * 
 */


package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KillProcess {
	
	/* ----- < Solution 1 - DFS > -----
	 * Time Complexity: O(n^n);
	 * Space Complexity: O(n);
	 * 
	 * Traverse over the ppid array and find out all the children of the process to be killed. 
	 * Further, for every child chosen to be killed, we recursively make call to the killProcess function 
	 *   now treating this child as the new parent to be killed. 
	 * In every such call, we again traverse over the ppidppid array now considering the id of the child process, and continue in the same fashion. 
	 * 
	 * */
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        List<Integer> killList = new ArrayList<>();
        kill(pid, ppid, kill, killList);
        return killList;
    }
    
    private void kill(List<Integer> pid, List<Integer> ppid, int target, List<Integer> killList) {
        if (pid == null || pid.size() == 0) {
            return;
        }
        
        // recursively kill all the child processes of target 
        for (int i = 0; i < ppid.size(); i++) {
            if (ppid.get(i) == target) {
                kill(pid, ppid, pid.get(i), killList);
            }
        }
        
        // kill the target
        for (int i = 0; i < pid.size(); i++) {
            if (pid.get(i) == target) {
                killList.add(target);
                // pid.remove(i); // remove elements will cause error when returning to the previous level
                // ppid.remove(i);
            }
        }
    }
	
	/* ----- < Solution 2 - Tree Simulation > -----
	 * Time Complexity: O(n);
	 * Space Complexity: O(n);
	 * 
	 * */
    public List<Integer> killProcessII(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, List<Integer>> tree = buildTree(pid, ppid);
        List<Integer> killList = new ArrayList<>();
        kill(tree, kill, killList);
        return killList;
    }
    
    // 1 
    // 3 : 1, 5
    // 10
    // 5 : 10
    private void kill(Map<Integer, List<Integer>> tree, int target, List<Integer> killList) {
        for (int child : tree.get(target)) {
            kill(tree, child, killList);
        }
        killList.add(target);
    }
    
    // pid =  [1, 3, 10, 5]
    // ppid = [3, 0, 5, 3]
    // 1 
    // 3 : 1, 5
    // 10
    // 5 : 10
    private Map<Integer, List<Integer>> buildTree(List<Integer> pid, List<Integer> ppid) {
        Map<Integer, List<Integer>> tree = new HashMap<>();
        for (int i = 0; i < pid.size(); i++) {
            int childId = pid.get(i);
            tree.putIfAbsent(childId, new ArrayList<>());
            
            int parentId = ppid.get(i);
            if (parentId != 0) {
                tree.putIfAbsent(parentId, new ArrayList<>());
                tree.get(parentId).add(childId);
            }
        }
        return tree;
    }
}
