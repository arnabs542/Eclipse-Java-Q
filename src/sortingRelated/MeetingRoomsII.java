/*
 * == Created Date ==
 * Dec 18, 2018
 * 
 * == Question - Meeting Rooms II ==
 * Given an array of meeting time intervals consisting of start and end times
 *  [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
 * 
 * == Example ==
 * Given intervals = [[0,30],[5,10],[15,20]], return 2.
 * 
 * == Notes == 
 * LeetCode 253* - Medium+
 * LintCode 919
 * LaiCode
 * 
 */

package sortingRelated;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MeetingRoomsII {
	
	/* ----------------------< Solution using Sweep Line >-------------------------*/
	
	/* ----------------------< Solution using Sort + Heap to merger interval >-------------------------*/
    public int minMeetingRooms(List<Interval> intervals) {
	    	if (intervals == null || intervals.size() == 0) {
				return 0;
			}
	    Collections.sort(intervals, (a, b) -> (a.start - b.start));
	    
	    PriorityQueue<Interval> minStack = new PriorityQueue<Interval>(intervals.size(), new Comparator<Interval>() {
	    		@Override
	    		public int compare(Interval o1, Interval o2) {
	    			return o1.end - o2.end;
	    		}
	    });
	    // Or use Lambda:
	    // ``` new PriorityQueue<Interval>(intervals.length, (a, b) -> a.end - b.end); ```
	    
	    for (Interval interval : intervals) {
	    		if (minStack.isEmpty() || minStack.peek().end > interval.start) {
	    			minStack.offer(interval);
	    		} else {
	    			Interval cur = minStack.poll();
	    			cur.end = interval.end;
	    			minStack.offer(cur);
	    		}
	    }
	    int res = minStack.size();
		return res;
    }
    
    // Actuall in the above solution, the minStack don't need to store class Interval,
    // just the ending time of each interval
    public int minMeetingRoomsSyntax2(List<Interval> intervals) {
    		if (intervals == null || intervals.size() == 0) {
			return 0;
		}
    		Collections.sort(intervals, (a, b) -> (a.start - b.start));
    
    		PriorityQueue<Integer> minStack = new PriorityQueue<Integer>(intervals.size());
    
    		for (Interval interval : intervals) {
    			if (minStack.isEmpty() || minStack.peek() > interval.start) {
    				minStack.offer(interval.end);
    			} else {
    				minStack.poll();
    				minStack.offer(interval.end);
    			}
    		}
    		int res = minStack.size();
    		return res;
    }
}
