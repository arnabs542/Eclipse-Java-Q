/*
 * == Created Date ==
 * Jan 4, 2019
 * 
 * == Question - Employee Free Time ==
 * We are given a list schedule of employees, which represents the working time for each employee.
 * Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
 * Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.
 * 
 * == Example ==
 * 
 * Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
 * 
 * Output: [[3,4]]
 * 
 * Explanation:
 * There are a total of three employees, and all common
 * free time intervals would be [-inf, 1], [3, 4], [10, inf].
 * We discard any intervals that contain inf as they aren't finite.
 * 
 * == Notes == 
 * LeetCode 759* - (H-)
 * 
 */
package sortingRelated;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmployeeFreeTime {

	/* ---------------< Solution : Virtually merge intervals >--------------
	 * 
	 * 1. Sort all intervals by start time
	 * 2. Scan each interval,
	 *    - If the start time smaller than the largest end time we seen so fard, we can merger these two
	 *    - Otherwise, there is a gap between two interval, we get one candicate of result
	 *    - Update the largest end time seen so far
	 * 
	 * */
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> allIntervals = new ArrayList<>();
        for (List<Interval> list : schedule) { // O(N)
            for (Interval interval : list) {
                allIntervals.add(interval);
            }
        }
        // [1,3] [2,5] [2,4] [6,7] [9,12]
        Collections.sort(allIntervals, (a, b) -> a.start - b.start); // O(NlogN)
    
        // [1,5] [6,7] [9,12]
        int end = allIntervals.get(0).end;
        List<Interval> freeTimeInteval = new ArrayList<>();
        for (Interval interval : allIntervals) {
            if (interval.start > end) {
                freeTimeInteval.add(new Interval(end, interval.start));
            } 
            end = Math.max(end, interval.end);
        } 
        return freeTimeInteval;
    }
    
	/* ---------------< Solution : virtually merge interval >--------------
	 * 
	 * 1. Sort all intervals by start time
	 * 2. Scan each interval,
	 *    - If the start time smaller than the largest end time we seen so fard, we can merger these two
	 *    - Otherwise, there is a gap between two interval, we get one candicate of result
	 *    - Update the largest end time seen so far
	 * 
	 * */
}
