/*
 * == Created Date ==
 * Dec 5, 2018
 * 
 * == Question - Ticket Resellers ==
 * Ticket prices are variable and based on the number of tickets a reseller has: one uite price for each ticket in hand.
 * For example, a reseller has 3 tickets to sell. The first would be priced at 3, the second at 2 and the last at 1.
 * To maximize their profits, they decide to pool their tickets and offer the highest priced ones first.
 * 
 * For example, there are two  resellers with a = [3, 5] tickests to sell. They have buyers for six of them. 
 * Maximum profit is made by selling 1 for 5, 1 for 4 and 2 each at 3 and 2. 
 * This leaves each reseller with single ticket, and their income is 5 + 4 + 2 * 3 + 2 * 2 = 19.
 * 
 * == Notes == 
 * Airbnb Practice Coding Challenge 
 * 
 */

package heapRelated;

import java.io.*;
import java.util.*;

public class TicketResellers {
	
	/*
     * Complete the 'maximumAmount' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY a
     *  2. LONG_INTEGER k
     */

	/* ----------------------< Solution using Priority Queue >-------------------------
	 * 
	 * Time exceeds limitation
	 * 
	 */
    public static long maximumAmountUsingPriorityQueue(List<Integer> a, long k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(a.size(), Collections.reverseOrder());
        long result = 0;
        for (int item : a) { //nlogn
            maxHeap.offer(item);
        }
        while (k > 0) { // k * logn
            int cur = maxHeap.poll();
            while (k > 0 && cur >= maxHeap.peek()) {
                result += cur;
                cur--;
                k--;
            }
            maxHeap.offer(cur);
        }
        return result;
    }

	/* ----------------------< Solution using extra space >-------------------------
	 * 
	 * O(n)
	 * 
	 * such a brilliant solution... 
	 * 
	 */
    public static long maximumAmount(List<Integer> a, long k) {
        //         2   4   6   8  10
        //data 0 0 1 0 1 0 1 0 1 0 1 
        long[] data = new long[100000];
        int max = Integer.MIN_VALUE;
        int maxIndex = -1;
        long res = 0;
        for (int i = 0; i < a.size(); i++) {
            data[a.get(i)]++;
            if (a.get(i) > max) {
                max = a.get(i);
                maxIndex = a.get(i);
            }
        }
        while (k > 0 && maxIndex >= 0) {
            long num = Math.min(k, data[maxIndex]);
            res += maxIndex * num;
            k -= num;
            data[maxIndex] = 0;
            data[maxIndex - 1] += num;  
            maxIndex--;
        }
        return res;
    }
    
    /* ----------------------< test stub >-------------------------*/
    public static void main(String[] args) throws IOException {
    	
    		TicketResellers testObj = new TicketResellers();
    		
    		/* Test Case 0 */
    		System.out.println("---< Test Case 0 >---");
    		
    		/* Test Case 1 */
    		System.out.println("---< Test Case 1 >---");
    		
    		List<Integer> tickets = new ArrayList<>();
    		int[] t = new int[] {3,5};
    		for (int element : t) {
    			tickets.add(element);
    		}
    		System.out.println(testObj.maximumAmountUsingPriorityQueue(tickets, 6));
    		System.out.println(testObj.maximumAmount(tickets, 6));
    		
    		/* Test Case 2 */
    		System.out.println("---< Test Case 2 >---");
    		
    		List<Integer> tickets2 = new ArrayList<>();
    		int[] t2 = new int[] {2, 4, 6, 8, 10};
    		for (int element : t2) {
    			tickets2.add(element);
    		}
    		
    		System.out.println(testObj.maximumAmountUsingPriorityQueue(tickets2, 6));
    		System.out.println(testObj.maximumAmount(tickets2, 6));
    		
    		/* Test Case 3 */
    		System.out.println("---< Test Case 3 >---");
    		
    }
}
