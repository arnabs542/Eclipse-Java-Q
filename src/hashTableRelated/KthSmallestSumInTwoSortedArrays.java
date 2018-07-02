/*
 * Created Date: June 30, 2018
 * 
 * Question - Kth Smallest Sum In Two Sorted Arrays:
 *   Given two sorted arrays A and B, of sizes m and n respectively. 
 *   Define s = a + b, where a is one element from A and b is one element from B. 
 *   Find the Kth smallest s out of all possible s'.   
 *   
 *   Example:
 *     A = {1, 3, 5}, B = {4, 8}
 *      1st smallest s is 1 + 4 = 5
 *      2nd smallest s is 3 + 4 = 7
 *      3rd, 4th smallest s are 9 (1 + 8, 4 + 5) 
 *      5th smallest s is 3 + 8 = 11
 *   
 *   Mirror Question:
 *     Kth Smallest Number In Sorted Matrix (BFS, heap)
 *   
 * Updated:
 *   July 1, 2018: Mid term exam problem 5
 *   July 2, Copy from solution, need to review as soon as possible
 * 
 */

package hashTableRelated;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class KthSmallestSumInTwoSortedArrays {
	
	class Element {
		int x;
		int y;
		int sum;
		public Element(int x, int y, int sum) {
			this.x = x;
			this.y = y;
			this.sum = sum;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (!(obj instanceof Element)) {
				return false;
			}
			Element other = (Element) obj;
			return this.x == other.x && this.y == other.y && this.sum == other.sum;
		}
		
		@Override
		public int hashCode() {
			return this.x * 31 * 31 + this.y * 31 + this.sum;
		}		
	}
	
	class ElementComparator implements Comparator<Element> {
		@Override
		public int compare(Element o1, Element o2) {
			if (o1.sum == o2.sum) {
				return 0;
			} 
			return o1.sum < o2.sum ? -1: 1;
		}
	}
	
	public int kthSum(int[] A, int[] B, int k) {
		int m = A.length;
		int n = B.length;
		Set<Element> set = new HashSet<Element>();
		PriorityQueue<Element> minHeap = new PriorityQueue<Element> (new ElementComparator());
		Element cur = new Element(0, 0, A[0] + B[0]);
		minHeap.offer(cur);
		int result = A[0] + B[0];
		for (int i = 0; i < k; i++) {
			cur = minHeap.poll();
			result = cur.sum;
			if (cur.x < m - 1) {
				Element next = new Element(cur.x + 1, cur.y, A[cur.x + 1] + B[cur.y]);
				if (set.add(next)) {
					minHeap.offer(next);
				}				
			}
			if (cur.y < n - 1) {
				Element next = new Element(cur.x, cur.y + 1, A[cur.x] + B[cur.y + 1]);
				if (set.add(next)) {
					minHeap.offer(next);
				}				
			}
		}
		return result;
	} 
	
}


