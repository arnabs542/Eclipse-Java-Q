/*
 * == Created Date ==
 * Dec 5, 2018
 * 
 * == Question - Rounding In Price Break Down ==
 *   
 * == Notes == 
 * AIR - OA 12
 * 
 */

package dfsRelated;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class RoundingInPriceBreakDown {
	
	    
    /* ----------------------< Solution: DFS >-------------------------
     * 
     * Time Complexity: O(2^n);
     * Space Complexity: O(n);	
     * 	
	 *                                  []
	 *      		             /                  \
	 * level  1            floor(x1)           ceil(x1)                     2^1
	 *                    /      \            /           \
	 * level  2      floor(x2)    ceil(x2)  floor(x2)    ceil(x2)           2^2
	 *                / \          /\         /\           /\
	 * level  3     f     c       f  c       f  c         f  c              2^n
	 * 
	 * 
	 * */
	
	private double minDiff = Double.MAX_VALUE;
	List<Integer> result = null;
	
	public List<Integer> Rounded(List<Double> prices, int target) {
		dfs(prices, 0, target, 0, new ArrayList<Integer>());
		return result;
	}
	
	private void dfs(List<Double> prices, int index, int target, double totalDiff, List<Integer> curList) {
		if (index >= prices.size() ) {
			if (target == 0 && totalDiff < minDiff) {
				minDiff = totalDiff;
				result = new ArrayList<Integer>(curList);
			}
			return;
		}
		double cur = prices.get(index);
		
		int floor = (int)Math.floor(cur);
		curList.add(floor);
		dfs(prices, index + 1, target - floor, totalDiff + Math.abs(cur - floor), curList);	
		curList.remove(curList.size() - 1);
		
		int ceil = (int)Math.ceil(cur);
		curList.add(ceil);
		dfs(prices, index + 1, target - ceil, totalDiff + Math.abs(cur - ceil), curList);
		curList.remove(curList.size() - 1);
	}
	
	
	/* ----------------------< Solution: Using Max Heap >-------------------------
	 * 
     * Time Complexity: O(nlogn);
     * Space Complexity: O(n);
     * 
	 */
	
	static class Pair {
		private int index;
		private double diff;
		Pair(int index, double diff) {
			this.index = index;
			this.diff = diff;
		}
	}
	
	public static List<Integer> Solution2(List<Double> prices, int target) {
		List<Pair> pairList = new ArrayList<>();
		List<Integer> result = new ArrayList<>();
		int totalFloor = 0;
		
		PriorityQueue<Pair> maxHeap = new PriorityQueue<>(prices.size(), new Comparator<Pair>() {
		       @Override
		       public int compare(Pair o1, Pair o2) {
		         if (o1.diff == o2.diff) { 
				     return 0;
			     }
			     return o1.diff > o2.diff ? -1 : 1; 
			   }
		});
		
		// SC: O(nlogn), get the total floor value, how many elements should add 1
		for (int i = 0; i < prices.size(); i++) {
			
			int floor = (int)Math.floor(prices.get(i));
			
			result.add(floor);
			totalFloor += floor;
			
			double diff = prices.get(i) - floor;
			Pair pair = new Pair(i, diff);
			pairList.add(pair);
			
			maxHeap.offer(pair); // O(log(n))
		}
		int remNum = target - totalFloor; // get the number of elements that needs to increase by 1
		
		// SC: O(remNum) = O(n)
		for (int i = 0; i < remNum; i++) {
			int index = maxHeap.poll().index;
			result.set(index, result.get(index) + 1);
		}	
		return result;
	}
	
	/* ----------------------< Solution: Using Min Heap >-------------------------
	 * 
     * Time Complexity: O(nlogk), worst case O(nlogn)
     * Space Complexity: O(n);
     * 
	 */
	
	public static List<Integer> Solution3(List<Double> prices, int target) {
		List<Pair> pairList = new ArrayList<>();
				
		List<Integer> result = new ArrayList<>();
		int totalFloor = 0;
		
		// SC: O(n)
		for (int i = 0; i < prices.size(); i++) {
			
			int floor = (int)Math.floor(prices.get(i));
			
			result.add(floor); // fill the result list with floor values
			totalFloor += floor;
			
			double diff = prices.get(i) - floor;
			Pair pair = new Pair(i, diff);
			pairList.add(pair);
			
			
		}
		int remNum = target - totalFloor; // get the number of elements that needs to increase by 1
			
		PriorityQueue<Pair> minHeap = new PriorityQueue<>(remNum, new Comparator<Pair>() {
		       @Override
		       public int compare(Pair o1, Pair o2) {
		         if (o1.diff == o2.diff) { 
				     return 0;
			     }
			     return o1.diff < o2.diff ? -1 : 1; 
			   }
		});
		
		// SC: O(n*log(k)), k is remNum 
		for (int i = 0; i < pairList.size(); i++) {
			if (i < remNum) {
				minHeap.offer(pairList.get(i)); 
			} else if (pairList.get(i).diff > minHeap.peek().diff) {
				minHeap.poll();
				minHeap.offer(pairList.get(i));
			}
		}
			
		// SC: O(remNum) = O(n)
		for (int i = 0; i < remNum; i++) {
			int index = minHeap.poll().index;
			result.set(index, result.get(index) + 1);
		}	
		return result;
	}
	
	
	/* ----------------------< Solution: Using Min Heap & Hash Table >-------------------------
	 * 
     * Time Complexity: O(nlogk), worst case O(nlogn)
     * Space Complexity: O(n);
     * 
	 */
	
	public static List<Integer> Solution4(List<Double> prices, int target) {
		
		Map<Integer, Double> diffIndexMap = new HashMap<>(); // key: index, value: difference of orginal element and floor
		
		List<Integer> result = new ArrayList<>();
		int totalFloor = 0;
		
		// Step1: Iterate prices list, get diffIndexMap
		// SC: O(n)
		for (int i = 0; i < prices.size(); i++) {
			
			int floor = (int)Math.floor(prices.get(i));
			
			result.add(floor); // fill the result list with floor values
			totalFloor += floor;
			
			diffIndexMap.put(i, prices.get(i) - floor);			
		}
		int remedyNum = target - totalFloor; // get the number of elements that needs to increase by 1
		System.out.println(target);
		System.out.println(totalFloor);
		System.out.println(remedyNum);
		
		// Step2: Initialize a Min Heap
		PriorityQueue<Map.Entry<Integer, Double>> minHeap = new PriorityQueue<>(remedyNum + 1, new Comparator<Map.Entry<Integer, Double>>() {
		       @Override
		       public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2) {
		    	   		return o1.getValue().compareTo(o2.getValue());
			   }
		});
		// HarkerRank Run time Error, if using 
		// ``` 
		// new PriorityQueue<>(remedyNum, new Comparator<Map.Entry<Integer, Double>>() ... 
		// ```
		// The size the PriorityQueue should be larger than remedyNum, or when removing elments will gice errors
		
		
		// Step3: Use Min Heap to get the elements with highest difference
		// SC: O(n * logk), k is remedyNum, worst case O(n * log(n))
		for (Map.Entry<Integer, Double> entry : diffIndexMap.entrySet()) {
			if (minHeap.size() < remedyNum) {
				minHeap.offer(entry);
			} else if (!minHeap.isEmpty() && entry.getValue() > minHeap.peek().getValue()) {
				minHeap.remove();
				minHeap.offer(entry);
			}			
		}
			
		// Step4: Increase the elements found out by Min Heap by 1 to make up the gap
		// SC: O(k), worst case O(n)
		for (int i = 0; i < remedyNum; i++) {
			int index = minHeap.poll().getKey();
			result.set(index, result.get(index) + 1);
		}	
		return result;
	}
	
	
    /* ----------------------< Solution using sort >-------------------------
     * 
     * Time Complexity: O(nlogn);
     * Space Complexity: O(n);	
     * 
     * 
     * */
	
	static class DataEntry {
        int index;
        int floorVal;
        double diff;
        public DataEntry(int index, int floorVal, double differenceFromFloor) {
            this.index = index;
            this.floorVal = floorVal;
            this.diff = differenceFromFloor;
        }
    }
 
    public static List<Integer> Solution5(List<Double> prices, int target) {
        DataEntry[] entries = new DataEntry[prices.size()];
        int floorSum = 0, xFloor;
        double x;
        for (int i = 0; i < prices.size(); i++){
            x = prices.get(i);
            xFloor = (int)x;
            floorSum += xFloor;
            DataEntry newDataEntry = new DataEntry(i, xFloor, x - xFloor);
            entries[i] = newDataEntry;
        }
 
        int totalDiff = target - floorSum;
        Arrays.sort(entries, (a, b) -> a.diff >= b.diff ? -1 : 1); // sort entries according to difference
 
        int idx = 0;
        while (idx < totalDiff) {
            entries[idx] = new DataEntry(entries[idx].index, entries[idx].floorVal+1, entries[idx].diff); // add 1 to the first diff entries
            idx++;
        }
 
        Arrays.sort(entries, (a, b) -> a.index < b.index ? -1 : 1); // sort entries according to original idx
 
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < prices.size(); i++){
            result.add(entries[i].floorVal);
        }
        return result;
    }
    
    
	/* ----------------------< test stub >-------------------------*/
	
	private static void print(List<Integer> result) {
		if (result != null) {
			for (int i: result) {
				System.out.print(i + " ");
			}
		} else {
			System.out.println("Cannot rounded to target");
		}
		System.out.println();
	}
	
	
	public static void main(String[] args) {

		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		double num1 = 1.1;
		double res;
		
		res = Math.round(num1);
		res = Math.floor(num1);
		res = Math.ceil(num1);
		
		System.out.println(res);
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		List<Double> prices = new ArrayList<>();
		prices.add(0.7);
		prices.add(2.80);
		prices.add(4.90);
		RoundingInPriceBreakDown testObj = new RoundingInPriceBreakDown();
		
        
		print(testObj.Rounded(prices, 7));
		print(RoundingInPriceBreakDown.Solution2(prices, 7));
		print(RoundingInPriceBreakDown.Solution3(prices, 7));
		print(RoundingInPriceBreakDown.Solution4(prices, 7));
		print(RoundingInPriceBreakDown.Solution5(prices, 7));
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
        double[] arr2 = { 1.2, 3.7, 2.3, 4.8, 5.0, 6.7}; // fine
        List<Double> prices2 = Arrays.stream(arr2).boxed().collect(Collectors.toList());
		
		print(testObj.Rounded(prices2, 24));
		print(RoundingInPriceBreakDown.Solution2(prices2, 24));
		print(RoundingInPriceBreakDown.Solution3(prices2, 24));
		print(RoundingInPriceBreakDown.Solution4(prices2, 24));
		print(RoundingInPriceBreakDown.Solution5(prices2, 24));
        
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
		double[] arr3 = {1.2, 2.3, 3.4};
		List<Double> prices3 = Arrays.stream(arr3).boxed().collect(Collectors.toList());
		
		print(testObj.Rounded(prices3, 7));
		print(RoundingInPriceBreakDown.Solution2(prices3, 7));
		print(RoundingInPriceBreakDown.Solution3(prices3, 7));
		print(RoundingInPriceBreakDown.Solution4(prices3, 7));
		print(RoundingInPriceBreakDown.Solution5(prices3, 7));
	}
}
