/*
 * == Created Date ==
 * Dec 18, 2018
 * 
 * == Question - Meeting Rooms ==
 * Given an array of meeting time intervals consisting of start and end times 
 * [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.  
 * 
 * == Example ==
 * Given intervals = [[0,30],[5,10],[15,20]], return false.
 * 
 * == Notes == 
 * LeetCode 252* - Medium+
 * LintCode 920
 * LaiCode
 * 
 */

package sortingRelated;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class MeetingRooms {
	
	// Same question, different input data type
	public boolean canAttendMeetings(Interval[] intervals) {
		if (intervals == null || intervals.length <= 1) {
			return true;
		}
		Arrays.sort(intervals, new Comparator<Interval>() {
			@Override
			public int compare(Interval o1, Interval o2) {
				return o1.start - o2.start;
			}
		});
		
		for (int i = 1; i < intervals.length; i++) {
			if (intervals[i].start < intervals[i - 1].end) {
				return false;
			}
		}
		return true;
	}
	
	// Same question, different input data type
    public boolean canAttendMeetings(List<Interval> intervals) {
    		if (intervals == null || intervals.size() <= 1) {
    			return true;
    		}
        Collections.sort(intervals, (a, b) -> (a.start - b.start));
        
        for (int i = 1; i < intervals.size(); i++) {
        		if (intervals.get(i).start < intervals.get(i - 1).end) {
        			return false;
        		}
        }
    		return true;
    }
    
	// Same question, different input data type
	public boolean canAttendMeetings(int[][] intervals) {
		if (intervals == null || intervals.length <= 1) {
			return true;
		}
		
	    Arrays.sort(intervals, new Comparator<int[]>(){
	        @Override
	        public int compare(int[] o1, int[] o2) {
	          return ((Integer)o1[0]).compareTo(o2[0]);
	        }
	    });
		
	    // Or using lambda:
		// Arrays.sort(intervals, Comparator.comparingInt(arr -> arr[0]));
		    
		for (int i = 1; i < intervals.length; i++) {
		   if (intervals[i][0] > intervals[i - 1][1]) {
		       return false;
		   }
		}
		return true;
	}
}
