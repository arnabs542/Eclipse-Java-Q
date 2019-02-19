/*
 * == Created Date ==
 * Feb 4, 2019
 * 
 * == Question - Valid Square II ==
 * 
 * Follow up for LeetCode 593 (M) - Valid Square
 * Count the number of valid square that can be formed from n points
 */

package mathAndOther;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ValidSquareII {
	/* ----- < Solution 1 > -----
	 * Time Complexity: O(n^2);
	 * Space Complexity: O(?);
	 * 
	 * */
    public boolean validSquareV(int[] p1, int[] p2, int[] p3, int[] p4) {
        if (p1 == null || p2 == null || p3 == null || p4 == null) return false;
        Map<String, Integer> map = new HashMap<>();
        List<int[]> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        int count = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (isSamePoint(list.get(i), list.get(j))) {
                		continue;
                }
                String str = genStr(list.get(i), list.get(j));
                map.put(str, 1 + map.getOrDefault(str, 0));
            }
        }
        
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (isSamePoint(list.get(i), list.get(j))) {
                		continue;
                }
                String diag = createDiag(list.get(i), list.get(j));
                if (diag.length() == 0) continue;
                count += map.getOrDefault(diag, 0);
            }
        }
        count >>= 1;
        return count > 0;
    }
    
    private boolean isSamePoint(int[] p1, int[] p2) {
        return p1[0] == p2[0] && p1[1] == p2[1];
    }
    
    private String genStr(int[] p1, int[] p2) {
        if (p1[0] < p2[0] || (p1[0] == p2[0] && p1[1] < p2[1])) {
            return String.format("%d,%d;%d,%d", p1[0], p1[1], p2[0], p2[1]);
        } else {
            return String.format("%d,%d;%d,%d", p2[0], p2[1], p1[0], p1[1]);
        }
        
    }
    
    private String createDiag(int[] a, int[] c) {
        int midX = a[0] + c[0]; // x1 + x2
        int midY = a[1] + c[1]; // y1 + y2

        int Ax = 2*a[0] - midX; // x1 - x2
        int Ay = 2*a[1] - midY; // y1 - y2
        int bX = midX - Ay; // x1 + x2 - y1 + y2
        int bY = midY + Ax;

        int cX = 2*c[0] - midX;
        int cY = 2*c[1] - midY;
        int dX = midX - cY;
        int dY = midY + cX;
        
        if ((bX & 1) == 0 && (bY & 1) == 0 && (dX & 1) == 0 && (dY & 1) == 0) {
            if (bX < dX || (bX == dX && bY < dY)) {
                return String.format("%d,%d;%d,%d", bX/2, bY/2, dX/2, dY/2);
            } else {
                return String.format("%d,%d;%d,%d", dX/2, dY/2, bX/2, bY/2);
            }
        } else {
            return "";
        }
    }
    
    
    // Notes from the phone interview with PureStorage
 /*
 (x, y) * 4

 a     b


 c     d

            b
 a



                d
    c


 a   b
 |\
 | \
 c---d

 ac^2 + cd^2 = ad^2

 a   d
 |\
 | \
 c---b

 b   a
 |\
 | \
 c---d

 int[]

 four equals sides
 90 degree angle

 3   4
 |\
 | \
 1---2

 3   2
 |\
 | \
 1---4

 p2(0, 1)     p3(1, 1)
  

 p1(0, 0)     p4(1, 0)

 1 2 3
 2 3 4
 1 2 4
 1 3 4

 set: 
 numSquare 4

     O(n^4)
     => O(n^3) => O(n^2)
     magic (p1, p2, p3) => p4 or null
     newMagic(p1, p2) => (p3, p4);
      
          p3
     
     p1        p2
     
          p4
     
     p1 p2 p3 p4
     p1 p2 p4 p3
     
 */
 	private int[] getForthPos(int[] p1, int[] p2, int[] p3) {
 		// not implemented 
 		return null;
 	}
 	
     public int countSqure2(List<int[]> points) {
         int numSquare = 0;
         Set<int[]> set = new HashSet<>();
         for (int[] point : points) {
             set.add(point);
         }
         
         for (int i = 0; i < points.size() - 2; i++) {
             for (int j = i + 1; j < points.size() - 1; j++) {
                 for (int k = j + 1; k < points.size(); k++) {
                     int[] p4 = getForthPos(points.get(i), points.get(j), points.get(k));
                     if (set.contains(p4)) {
                         numSquare++;
                     }
                 }
             }
         }
         return numSquare / 4; // !!!!
      }
     
     // O(n^4)
     public int countSqure1(List<int[]> points) {
         int numSquare = 0;
         for (int i = 0; i < points.size() - 3; i++) {
             for (int j = i + 1; j < points.size() - 2; j++) {
                 for (int k = j + 1; k < points.size() - 1; k++) {
                     for (int s = k + 1; s < points.size(); s++) {
                         if (isSquare(points.get(i), points.get(j), points.get(k), points.get(s))) {
                             numSquare++;
                         }
                     }
                 }
             }
         }
         return numSquare; 
     }
     
     public boolean isSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
         // find the 90 degree angle & compare the four sides
         if (  isRightAngle(p1, p2, p3) && areEqualSides(p1, p3, p4, p2)
            || isRightAngle(p1, p2, p4) && areEqualSides(p1, p2, p3, p4)
            || isRightAngle(p1, p3, p4) && areEqualSides(p1, p3, p2, p4)) {
                return true;
            }
        return false;
     }
     
     // p1 p2 p3 p4
     private boolean areEqualSides(int[] p1, int[] p2, int[] p3, int[] p4) {
         int dis12 = distance(p1, p2); // 1
         int dis23 = distance(p2, p3); // 1
         int dis34 = distance(p3, p4); // 1
         int dis41 = distance(p4, p1); // 1
         return dis12 == dis23 && dis23 == dis34 && dis34 == dis41;
     }
     
     private boolean isRightAngle(int[] p1, int[] p2, int[] p3) {
         int dis12 = distance(p1, p2); // 1
         int dis13 = distance(p1, p3); // 1
         int dis23 = distance(p2, p3); // 2
         return dis12 + dis13 == dis23; 
     }
     
     // sqrt(3) 1.7....
     
     private int distance(int[] p1, int[] p2) {
         // sqrt( (x1 - x2)^2 + (y1 - y2)^2 )
         return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
     }
}
