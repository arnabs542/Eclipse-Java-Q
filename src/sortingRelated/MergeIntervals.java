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
 * 
 */

package sortingRelated;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

}
