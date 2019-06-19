/*
 * == Question - Task Scheduler ==
 * 
 * == Created Date ==
 * June 11, 2019
  
 * == Notes == 
 * LeetCode 621(M)
 * 
 * == Related Topic ==
 * Greedy
 * 
 */

package mathAndOther;

import java.util.Arrays;

public class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        int[] count = new int[26];
        for (char t : tasks){
            count[t - 'A']++;
        }
        
        // k is the highest freqency
        // n is the cooldowm
        // p is the number of task that has the highest freqency
        // ans = (k - 1) * (n + 1) + p
        
        Arrays.sort(count);
        int p = 1;
        int k = count[25];
        for (int i = 24; i >= 0; i--) {
            if (count[i] == k) {
                p++;
            }
        }

        // case2: if the number of tasks is larger than ans, 
        // then the final result is the number of tasks
    
        return Math.max(tasks.length, (k - 1) * (n + 1) + p);
    }

}
