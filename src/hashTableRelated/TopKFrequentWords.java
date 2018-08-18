/*
 * Created Date: June 26, 2018
 * 
 * Question - Top K Frequent Words:
 *   Given a composition with different kinds of words, return a list of the top K most frequent words in the composition.
 *   ordered from most frequent one to least frequent one (the list could be of size K or smaller than K)
 *   
 *   Example: 
 *     Composition = ["a", "a", "b", "b", "b", "b", "c", "c", "c", "d"], top 2 frequent words are [“b”, “c”]
 *     Composition = ["a", "a", "b", "b", "b", "b", "c", "c", "c", "d"], top 4 frequent words are [“b”, “c”, "a", "d"]
 *     Composition = ["a", "a", "b", "b", "b", "b", "c", "c", "c", "d"], top 5 frequent words are [“b”, “c”, "a", "d"]
 *   
 * Updated:
 *   June 27, 2018: Review
 * 
 */

package hashTableRelated;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentWords {
	public String[] topKFrequent(String[] combo, int k)  {
		if (combo.length == 0) {
			return new String[0];
		}
		Map<String, Integer> freqMap = new HashMap<>();
		for (String s : combo) {
			if (!freqMap.containsKey(s)) {
				freqMap.put(s, 1);
			} else {
				int freq = freqMap.get(s);
				freqMap.put(s, freq + 1);
			}
		}
		
		class MapComparator implements Comparator<Map.Entry<String, Integer>> {
			@Override
			public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
				return e1.getValue().compareTo(e2.getValue());
			}
		}
		
		// line below has to be under class MapComparator
		PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(k, new MapComparator());
		
		for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
			if (minHeap.size() < k) {
				minHeap.offer(entry);
			} else if (entry.getValue() > minHeap.peek().getValue()) {
				minHeap.poll();
				minHeap.offer(entry);
			}			
		}

		String[] result = new String[minHeap.size()]; // the size is not k!!! the size of the result can be less than k
		for (int i = minHeap.size() - 1; i >= 0; i--) {
			result[i] = minHeap.poll().getKey();
		}
		return result;
	}
	
	// Time Complexity: O(n);
	// Space Complexity: O(n);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		TopKFrequentWords testObj = new TopKFrequentWords();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		String[] combo1 = {"a", "a", "b", "b", "b", "b", "c", "c", "c", "d"};
		String[] result1 = testObj.topKFrequent(combo1, 2);
		
		for (String s : result1) {
			System.out.print(s + " ");
		}
		
		/* Test Case 2 */
		System.out.println("\n---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}

}
