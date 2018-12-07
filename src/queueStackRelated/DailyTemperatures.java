/*
 * == Created Date ==
 * Dec 5, 2018
 * 
 * == Question - Daily Temperatures ==
 *   
 * == Notes == 
 * LeetCode 739
 * 
 */

package queueStackRelated;

import java.util.ArrayDeque;
import java.util.Deque;

public class DailyTemperatures {
    public int[] dailyTemperatures(int[] T) {
        Deque<Integer> desStack = new ArrayDeque<>(); // descending Stack
        int[] res = new int[T.length]; 
        for (int i = 0; i < T.length; i++) {
            while (!desStack.isEmpty() && T[i] > T[desStack.peek()]) {               
                int index = desStack.pop();
                res[index] = i - index;
            }
            desStack.push(i);
        }
        return res;
    }
}
