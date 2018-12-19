/*
 * == Created Date ==
 * October 13, 2018
 * 
 * == Question - Merge Intervals ==
 * Given a collection of intervals, merge all overlapping intervals.  
 *   
 * == Example == 
 * Input: [[1,3],[15,18],[8,10],[2,6]]
 * Output: [[1,6],[8,10],[15,18]]
 * 
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * 
 * == Notes == 
 * LeetCode 56 - Medium
 * 
 * Dec 18, 2018: Review, add lamda syntax of Comparator of Collections.sort();
 */

package sortingRelated;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

class Interval {
	int start;
	int end;
	Interval() { start = 0; end = 0; }
	Interval(int s, int e) { start = s; end = e; }
}

public class MergeIntervals {
	
    public List<Interval> merge(List<Interval> intervals) {
        Collections.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval o1, Interval o2) {
            		if (o1.start == o2.start) {
            			return 0;
            		}
            		return o1.start < o2.start ? -1 : 1;
            }
        });
        
        // Same as above:
        // Collections.sort(intervals, (a, b) -> (a.start - b.start));
        
        List<Interval> result = new ArrayList<>();
        for (Interval interval : intervals) {
        		int last = result.size() - 1;
        		if (result.isEmpty() || result.get(last).end < interval.start) {
        			result.add(interval);
        		} else {
        			// don't forget corner cases such as [[1,4],[2,3]]
        			result.get(last).end = Math.max(result.get(last).end, interval.end);
        		}
        }
        
        return result;
    }
    
	// Time Complexity: O(nlogn);
	// Space Complexity: O(1);
    
    // The same alogrithm, different syntax
    public List<Interval> mergeSyntax1(List<Interval> intervals) {
    	
    		Collections.sort(intervals, (a, b) -> (a.start - b.start));

        LinkedList<Interval> merged = new LinkedList<Interval>();
        
        for (Interval interval : intervals) {
            // if the list of merged intervals is empty 
        		//or if the current interval does not overlap with the previous, simply append it.
            if (merged.isEmpty() || merged.getLast().end < interval.start) {
                merged.add(interval);
            }
            
            // otherwise, there is overlap, so we merge the current and previous
            // intervals.
            else {
                merged.getLast().end = Math.max(merged.getLast().end, interval.end);
            }
        }
        
        return merged;
    }
}
