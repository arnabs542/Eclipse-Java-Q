/*
 * == Created Date ==
 * Dec 24, 2018
 * 
 * == Question - Check Log History ==
 * Given a list of lock acquire and release events(counting from 1), checks if there were any problems
 * (dangling acquired lock, acquire-release order problem, acquiring a lock twice, releasing a free lock)
 * 
 * Return:
 * - 0, if no lock-related problem
 * - N + 1, if the only issue after the program termination were dangling acquired lock
 * - K, in case event number K violated any of the priciples (release a lock not acquired previously,
 *   acquire an already held lock OR violate lock acquire-release ordering)
 * 
 * == Notes == 
 * Pure Storage OA
 * 
 */
package queueStackRelated;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CheckLogHistory {
	
	// Time Complexity: O(N);
	// Space Complexity: O(N);
	public static int checkLogHistory(List<String> events) {
		// Use a set to record the acquired locks 
	    Set<Integer> logSet = new HashSet<Integer>();
	    
	    // Use a stack to keep the order of the acquired locks 
	    Deque<Integer> logStack = new ArrayDeque<Integer>();
	    
	    for (int i = 0; i < events.size(); i++) {
	        String cur = events.get(i);
	        int spaceInx = cur.indexOf(" ");
	        int lockNum = Integer.parseInt(cur.substring(spaceInx + 1));
	        
	        if (cur.substring(0, spaceInx).equals("ACQUIRE")) {
	            if (logSet.contains(lockNum)) { // acquire already held lock
	                return i + 1;
	            } else {
	                logStack.push(lockNum);
	                logSet.add(lockNum);
	            }           
	        } else {
	            if (!logSet.contains(lockNum)) { // release a lock not acquired before
	                return i + 1;
	            }
	            if (lockNum != logStack.peek()) { // violate acquire-release order
	                return i + 1;
	            }
	            logStack.pop();
	            logSet.remove(lockNum);
	        }
	    }
	    
	    if (logStack.size() != 0) { // dangling acquired locks
	        return events.size() + 1;
	    }
	    return 0;
	}
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		String testStr1 = "ACQUIRE 4444"; 
		String testStr2 = "RELEASE 1000000000"; // 1,000,000,000
		
		int spaceIndex = testStr1.indexOf(" ");
		System.out.println(testStr1.substring(0, spaceIndex));
		System.out.println(testStr1.substring(0, spaceIndex) == "ACQUIRE"); // BE CAREFUL ABOUT "=="
		System.out.println(testStr1.substring(0, spaceIndex).equals("ACQUIRE"));
		
		System.out.println(testStr1.substring(spaceIndex + 1));
		
		spaceIndex = testStr2.indexOf(" ");
		System.out.println(testStr2.substring(0, spaceIndex).equals("RELEASE"));
		int num = Integer.parseInt(testStr2.substring(spaceIndex + 1));
		System.out.println(num);
		 
		System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE); // 2,147,483,647
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		String str1 = "ACQUIRE 10";
		String str2 = "ACQUIRE 20";
		String str3 = "ACQUIRE 30";
		String str4 = "RELEASE 30";
		String str5 = "RELEASE 20";
//		String str6 = "RELEASE 10";
		List<String> list = new ArrayList<>();
		list.add(str1);
		list.add(str2);
		list.add(str3);
		list.add(str4);
		list.add(str5);
//		list.add(str6);
		System.out.println(checkLogHistory(list));
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
	}
}
