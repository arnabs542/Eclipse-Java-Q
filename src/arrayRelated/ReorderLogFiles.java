/*
 * == Created Date ==
 * Nov 21, 2018
 * 
 * == Question - Reorder Log Files ==
 * 
 * == Notes == 
 * LeetCode 937
 * A - OA
 * 
 */

package arrayRelated;

import java.util.Arrays;
import java.util.Comparator;

public class ReorderLogFiles {
	
	/* -------------------------< Syntax 1 >-------------------------- */
    public String[] reorderLogFiles1(String[] logs) {
        Arrays.sort(logs, (log1, log2) -> {
            String[] split1 = log1.split(" ", 2);
            String[] split2 = log2.split(" ", 2);
            boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
            if (!isDigit1 && !isDigit2) {
                int cmp = split1[1].compareTo(split2[1]);
                if (cmp != 0) return cmp;
                return split1[0].compareTo(split2[0]);
            }
            return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
        });
        return logs;
    }
    
    /* -------------------------< Syntax 2 >-------------------------- */
    public String[] reorderLogFiles2(String[] logs) {
        
        Comparator<String> logComparator = new Comparator<String>() {
            @Override
            public int compare(String log1, String log2) {
                String[] split1 = log1.split(" ", 2);
                String[] split2 = log2.split(" ", 2);
                
                // '9' = 57, 'a' = 97     
                if (split1[1].charAt(0) <= '9') {
                    if (split2[1].charAt(0) <= '9') { // both are dig log
                        return 0;
                    } else {
                        return 1;
                    }                  
                }
                if (split2[1].charAt(0) <= '9') { 
                    return -1;
                }

                int cmp = split1[1].compareTo(split2[1]);
                if (cmp != 0) return cmp;
                return split1[0].compareTo(split2[0]);
            }
        };
        
        Arrays.sort(logs, logComparator);
        return logs;
    }
}
