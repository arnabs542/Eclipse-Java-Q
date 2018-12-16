package practiceContent;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class RewriteComparatorForHeap {
	
	/* == Three ways to create a max heap == */	
	private PriorityQueue<Integer> getMaxHeap(int k) {
		
	    /*--- < Syntax 1: Use Collections >---*/	
		PriorityQueue<Integer> maxHeap1 = new PriorityQueue<>(k, Collections.reverseOrder());    
		
		/*--- < Syntax 2: Override Comparator >---*/	
		class ReverseComparator implements Comparator<Integer> {
			@Override
			public int compare(Integer o1, Integer o2) {
				if (o1.equals(o2)) {
					return 0;
				} 
				return o1 > o2 ? -1: 1;
			}
		}	
		PriorityQueue<Integer> maxHeap2 = new PriorityQueue<>(k, new ReverseComparator());
		
		/*--- < Syntax 3: Lambda	 >---*/		
	    PriorityQueue<Integer> maxHeap3 = new PriorityQueue<>(k, new Comparator<Integer>() {
	       @Override
	       public int compare(Integer o1, Integer o2) {
	         if (o1.equals(o2)) { // or o1.intValue() == o2.intValue(); cannot use o1 == o2
			     return 0;
		     }
		     return o1 > o2 ? -1 : 1; // return -1 means o1 has higher priority
		   }
		});	
	    return maxHeap1;
	}
}
