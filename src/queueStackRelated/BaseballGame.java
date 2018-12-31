/*
 * == Created Date ==
 * Dec 29, 2018
 * 
 * == Question - Baseball Game ==
 *   
 * == Notes == 
 * LeetCode 682(E)
 *   
 */

package queueStackRelated;

import java.util.ArrayDeque;
import java.util.Deque;

public class BaseballGame {
	
	/* ----- < Method 1 - Using stack to record the scores > -----
	 * Time Complexity: O(N);
	 * Space Complexity: O(N);
	 * 
	 * */
    public int calPoints(String[] ops) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < ops.length; i++) { // remove last round's score
            if (ops[i].equals("C") && !stack.isEmpty()) {
                stack.pop();
            } else if (ops[i] == "D" && !stack.isEmpty()) { // get doubled data of the last valid round's points.
                stack.push(stack.peek() * 2);
            } else if (ops[i] == "+" && !stack.isEmpty()) { // get sum of the last two valid round's points.
                int top = stack.pop(); 
                int cur = stack.peek() + top; // unhandled cases here: the stack maybe empty
                stack.push(top);
                stack.push(cur);
            } else {
                stack.push(Integer.parseInt(ops[i]));
            }
        }
        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }
        return sum;
    }
    
	/* ----- < Method 1 - Using array to record the scores > -----
	 * Time Complexity: O(N);
	 * Space Complexity: O(N);
	 * 
	 * */
    public int calPointsMeth2(String[] ops) {
        int len = ops.length;
        int[] scores = new int[len]; // point to the position of the next score in the scores array 
        int next = 0;
        for (String s: ops) {
            if (s.equals("+")) {
                scores[next] = scores[next - 1] + scores[next - 2];
                next++;
            } else if(s.equals("D")) {
                scores[next] = 2 * scores[next - 1];
                next++;
            } else if(s.equals("C")) {
                next--;
            } else 
                scores[next++] = Integer.parseInt(s);
        }
        
        int sum = 0;
        for (int i = 0; i < next; i++) {
        		sum += scores[i];
        }
        return sum;
    }
    
    /* -- < Lessons Learned > --
     * 
     * VERY IMPORTAT!!!
     * - Use string.equals() to compare strings instend of "=="
     * 
     * */
    
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		BaseballGame testObj = new BaseballGame();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		String[] ops = new String[] {"5","2","C","D","+"};
		System.out.println(testObj.calPoints(ops));
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}
