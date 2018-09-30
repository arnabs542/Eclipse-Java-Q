/* 
 * == Question - Merge k Sorted Lists (hard) ==
 * Merge k sorted linked lists and return it as one sorted list. 
 * Analyze and describe its complexity. 
 *   
 * == Example == 
 * Input:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 * 
 * 
 */
 
package myLinkedList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MergeKSortedLists {
	
	/* --- < Solution 1, using sort>---
	 * N: average number of nodes in each list, K lists
	 * Time Complexity: O ( N * K log(N * K) )
	 * Space Complexity: O (N * K)
	 */
	public ListNode mergeKListsUsingSort(ListNode[] lists) {
		List<ListNode> mergeList = new ArrayList<>();
		for (ListNode head : lists) {
			while (head != null) {
				mergeList.add(head);
				head = head.next;
			}
		}
		
		class MyComparator implements Comparator<ListNode> {
			@Override
			public int compare(ListNode o1, ListNode o2) {
				if (o1.value == o2.value) {
					return 0;
				}
				return o1.value < o2.value ? -1 : 1;
			}
		}
		Collections.sort(mergeList, new MyComparator());
		
		ListNode dummy = new ListNode(0);
		ListNode cur = dummy;
		for (ListNode node : mergeList) {
			cur.next = node;
			cur = cur.next;
		}
		return dummy.next;
	}
	
	/* --- < Solution 2, using binary reduction >--- 
	 * N: average number of nodes in each list, K lists
	 * Time Complexity: O ( N * K * log(K) )
	 * Space Complexity: O (N * K)
	 * 
	 * A1  -> A12
	 * A2          -> A1234            
	 * A3  -> A34
	 * A4                     -> A12345678               
	 * A5  -> A56
	 * A6          -> A5678 
	 * A7  -> A78  
	 * A8      
	 *       
	 *     K * N     K * N    K * N
	 *   
	 * */
    public ListNode mergeKListsUsingBinaryReduction(ListNode[] lists) {
        return partition(lists, 0, lists.length-1);
    }
    
    public ListNode partition(ListNode[] lists, int start, int end){
        if (start == end) {
            return lists[start];
        }         
        if (start < end) {
            int mid = (start + end) / 2;
            ListNode l1 = partition(lists, start, mid);
            ListNode l2 = partition(lists, mid+1, end);
            return merge(l1, l2);
        }
        return null;
    }
    public ListNode merge(ListNode l1, ListNode l2){
        if (l1==null) {
        	    return l2;
        }
        if (l2==null) {
         	return l1;
        }
        if (l1.value<l2.value) {
            l1.next = merge(l1.next, l2);
            return l1;
        } else {
            l2.next = merge(l1, l2.next);
            return l2;
        }
    }
    
	/* --- < Solution using max heap >--- 
	 * N: average number of node in each list, K lists
	 * Time Complexity: O ( N * K * log(K) )
	 * Space Complexity: O (K)
	 * 
	 * l1: Xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
	 *     i
	 *     
	 * l2: yyyyyYyyyyyyyyyyyyyyyyy
	 *          j
	 * 
	 * l3: zzzzzzzzzZzzzzzzzzz
	 *              k 
	 *              
	 * Use heap to automatically select the smallest among k arrays each time 
	 * 
	 * */
	public ListNode mergeKListsWithHeap(ListNode[] lists) {
		
		// create a max heap
		class MyComparator implements Comparator<ListNode> {
			@Override
			public int compare(ListNode o1, ListNode o2) {
				if (o1.value == o2.value) {
					return 0;
				}
				return o1.value < o2.value ? -1 : 1;
			}
		}
		PriorityQueue<ListNode> minHeap = new PriorityQueue<>(new MyComparator());
		
		ListNode dummy = new ListNode(0);
		ListNode cur = dummy;
		
		// offer the first node of all the lists to the min heap
		for (ListNode node : lists) {
			if (node != null) {
				minHeap.offer(node);
			}
		}
		
		// link the smallest node in the head to the final list, offer its next node to the heap
		while (!minHeap.isEmpty()) {
			ListNode smallest = minHeap.poll();
			if (smallest.next != null) {
				minHeap.offer(smallest.next);
			}
			cur.next = smallest;
			cur = cur.next;
		}
		return dummy.next;
	}	
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		MergeKSortedLists testObj = new MergeKSortedLists();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		ListNode head1 = ListNode.genLinkedList(new int[] {1,4,8});
		ListNode head2 = ListNode.genLinkedList(new int[] {3,5,7});
		ListNode head3 = ListNode.genLinkedList(new int[] {2,6,9});
		ListNode[] list = new ListNode[] {head1, head2, head3};
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
//		ListNode result = testObj.mergeKListsUsingBinaryReduction(list);
//		ListNode result = testObj.mergeKListsWithHeap(list);
		ListNode result = testObj.mergeKListsUsingSort(list);
		result.printList(result);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}
