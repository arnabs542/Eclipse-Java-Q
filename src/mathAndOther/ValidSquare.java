/*
 * == Created Date ==
 * Feb 2, 2019
 * 
 * == Question - Valid Square ==
 * Given the coordinates of four points in 2D space, return whether the four points could construct a square.
 * 
 * The coordinate (x,y) of a point is represented by an integer array with two integers.
 * 
 * == Example ==
 * Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
 * Output: True
 * 
 * Note:
 * All the input integers are in the range [-10000, 10000].
 * A valid square has four equal sides with positive length and four equal angles (90-degree angles).
 * Input points have no order.
 *   
 * == Notes == 
 * LeetCode 593 (M) - Valid Square
 * 
 */

package mathAndOther;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ValidSquare {
	
	
	/* ----- < Solution 1 - Brute Force > -----
	 * Time Complexity: O(4!);
	 * Space Complexity: O(1);
	 * 
	 * */
    public double dist(int[] p1, int[] p2) {
        return (p2[1] - p1[1]) * (p2[1] - p1[1]) + (p2[0] - p1[0]) * (p2[0] - p1[0]);
    }
    
    public boolean check(int[] p1, int[] p2, int[] p3, int[] p4) {
        return dist(p1,p2) > 0 
        		   && dist(p1, p2) == dist(p2, p3) // check adjacent sides
        		   && dist(p2, p3) == dist(p3, p4) 
        		   && dist(p3, p4) == dist(p4, p1) 
        		   && dist(p1, p3) == dist(p2, p4); // check lengths of the diagonals formed between the corners 
    }
    
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[][] p = {p1, p2, p3, p4};
        return checkAllPermutations(p, 0);
    }
    
    private boolean checkAllPermutations(int[][] p, int index) {
        if (index == 4) { // base case, we get one permutation
            return check(p[0], p[1], p[2], p[3]); // check if this permutation can form a square
        } else {
            boolean res = false;
            for (int i = index; i < 4; i++) {
                swap(p, index, i);
                res |= checkAllPermutations(p, index + 1);
                swap(p, index, i);
            }
            return res;
        }
    }
    
    public void swap(int[][] p, int x, int y) {
        int[] temp = p[x];
        p[x] = p[y];
        p[y] = temp;
    }
    
    // There are only two equal longest lenghts and the non longest lengths are all equal.
    public boolean validSquareI(int[] p1, int[] p2, int[] p3, int[] p4) {
    		long[] lengths = { length(p1, p2), length(p2, p3), length(p3, p4),
    						   length(p4, p1), length(p1, p3),length(p2, p4)}; // all 6 sides

    		long max = 0, nonMax = 0;
    		for (long len : lengths) {
    			max = Math.max(max, len);
    		}
    		int count = 0;
    		for (int i = 0; i < lengths.length; i++) {
    			if (lengths[i] == max) {
    				count++;
    			} else {
    				nonMax = lengths[i]; // non diagonal side.
    			}
    		}
    		
    		if (count != 2) {
    			return false; // diagonals lenghts have to be same.
    		}

    		for (long len : lengths) {
    			if(len != max && len != nonMax) return false; // sides have to be same length
    		}
    		return true;
    	}
    
    private long length(int[] p1, int[] p2) {
    		return (long)Math.pow(p1[0]-p2[0],2) + (long)Math.pow(p1[1]-p2[1], 2);
    }
    
	/* ----- < Solution 2 - Check adjacent sides and diagonals > -----
	 * Time Complexity: O(1);
	 * Space Complexity: O(1);
	 * 
	 * Only length of four sides are the same is not enough:
	 * 
	 * Parallelogram
	 * 	  ------
	 *   /     /
	 *  /     /
	 *  ------
	 *  
	 * Diamond
	 *   /\
	 *  /  \
	 *  \  /
	 *   \/
	 *   
	 * edges cases:
	 * 1. two points overlap
	 * 
	 * 2. three points form Equilateral triangle, the 4th point is at the center of the triangle
	 *      a
	 *    /   \ 
	 *   /  d  \
	 *  b------ c
	 *       
	 *  sqrt(3)
	 *  
	 * 
	 * */
    public boolean validSquareIIa(int[] p1, int[] p2, int[] p3, int[] p4) {
    		// TODO: check corner cases
    	
        Set<Integer> set = new HashSet<>();
        set.add(dis(p1, p2));
        set.add(dis(p1, p3));
        set.add(dis(p1, p4));
        set.add(dis(p2, p3));
        set.add(dis(p2, p4));
        set.add(dis(p3, p4));
        
        if (set.contains(0) || set.size() != 2) {
        		return false;
        }
        
        int[] sides = new int[2];
        int i = 0;
        for (int side : set) {
        		sides[i++] = side;
        }
        if (sides[0] != 2 * sides[1] && sides[1] != 2 * sides[0]) {
        		return false;
        }
        return true;
    }
    
    private int dis(int[] p1, int[] p2) {
        double distance = Math.pow((p1[0] - p2[0]), 2) + Math.pow((p1[1] - p2[1]), 2);
        return (int)distance;
    }
    
    /*----- < similar to above, record the frequency of long and short sides > -----*/
    public boolean validSquareIIB(int[] p1, int[] p2, int[] p3, int[] p4) {
        Map<Integer, Integer> map = new HashMap<>();
        int dis12 = dis(p1, p2);
        int dis13 = dis(p1, p3);
        int dis14 = dis(p1, p4);
        int dis23 = dis(p2, p3);
        int dis24 = dis(p2, p4);
        int dis34 = dis(p3, p4);

        map.put(dis12, map.getOrDefault(dis12, 0) + 1);
        map.put(dis13, map.getOrDefault(dis13, 0) + 1);
        map.put(dis14, map.getOrDefault(dis14, 0) + 1);
        map.put(dis23, map.getOrDefault(dis23, 0) + 1);
        map.put(dis24, map.getOrDefault(dis24, 0) + 1);
        map.put(dis34, map.getOrDefault(dis34, 0) + 1);
        
        return !map.containsKey(0) && map.size() == 2 && (map.get(dis12) == 2 || map.get(dis12) == 4);
    }

    /* ----- < double version, doesn't work, don't know how to compare square root > -----*/
    
    public boolean validSquareII(List<double[]> points) {
    	
        Set<Double> set = new HashSet<>();
        set.add(dis(points.get(0), points.get(1)));
        set.add(dis(points.get(0), points.get(2)));
        set.add(dis(points.get(0), points.get(3)));
        set.add(dis(points.get(1), points.get(2)));
        set.add(dis(points.get(1), points.get(3)));
        set.add(dis(points.get(2), points.get(3)));
        
        if (set.contains(0.0) || set.size() != 2) {
        		return false;
        }
        
        double[] sides = new double[2];
        int i = 0;
        for (double side : set) {
        		sides[i++] = side;
        }
        
        if (sides[0] != 2 * sides[1] && sides[1] != 2 * sides[0]) {
        		return false;
        }
        return true;
    }
    
    private double dis(double[] p1, double[] p2) {
        double distance = Math.pow((p1[0] - p2[0]), 2) + Math.pow((p1[1] - p2[1]), 2);
        System.out.println(distance);
        return distance;
    }
    
	/* ----- < Solution 3 - Sort to find the diagonals > -----
	 * Time Complexity: O(4!);
	 * Space Complexity: O(1);
	 * 
	 * */
    public boolean validSquareIII(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[][] p={p1,p2,p3,p4};
        Arrays.sort(p, (l1, l2) -> l2[0] == l1[0] ? l1[1] - l2[1] : l1[0] - l2[0]);
        return dist(p[0], p[1]) != 0 
        		   && dist(p[0], p[1]) == dist(p[1], p[3]) 
        		   && dist(p[1], p[3]) == dist(p[3], p[2]) 
        		   && dist(p[3], p[2]) == dist(p[2], p[0])   
        		   && dist(p[0],p[3]) == dist(p[1],p[2]);
    }
    
    
	/* ----- < Solution 4 - Check angles and length > -----
	 * Time Complexity: O(1);
	 * Space Complexity: O(1);
	 * 
	 * */
    public boolean validSquareIV(int[] p1, int[] p2, int[] p3, int[] p4) {
        if (   (isRightAngle(p1, p2, p3) && areEqualSides(p1, p2, p4, p3))
            || (isRightAngle(p1, p2, p4) && areEqualSides(p1, p2, p3, p4))
            || (isRightAngle(p1, p3, p4) && areEqualSides(p1, p3, p2, p4))) {
            return true;
        }
        return false;
    }
    
    // (x1 - x2)^2 + (y1 - y2)^2
    private long distance(int[] p1, int[] p2) {
        return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }
    
    /*
      clock-wise
      
      p2 _____ p4
         |   |
      p1 ----- p3
      
      
      p2 _____ p3
         |   |
      p1 ----- p4


      p3 _____ p2
         |   |
      p1 ----- p4
      
    */
    private boolean areEqualSides(int[] p1, int[] p2, int[] p3, int[] p4) {
        long dist12 = distance(p1, p2);
        long dist23 = distance(p2, p3);
        long dist34 = distance(p3, p4);
        long dist41 = distance(p4, p1);
        if (dist12 == 0 || dist23 == 0 || dist34 == 0|| dist41 == 0) {
            return false;
        }
        return dist12 == dist23 && dist23 == dist34 && dist34 == dist41;
    }

    private boolean isRightAngle(int[] p1, int[] p2, int[] p3) {
        long dist12 = distance(p1, p2);
        long dist13 = distance(p1, p3);
        long dist23 = distance(p2, p3);
        return dist12 + dist13 == dist23;
    }
    
    /*  
     *          y2 - y1
     * slope = ---------
     *          x2 - x1
     * 
     *  p2 |
     *     |
     *     |_____
     *  p1       p3
     * 
     * slope(p1p2) = - slope(p1p3)
     * 
     *  y1 - y2       y1 - y3
     * --------- = - ---------
     *  x1 - x2       x1 - x3
     *  
     *  (y1 - y2)(x1 - x3) = - (y1 - y3)(x1 - x2)
     *  
     *  (y1 - y2)(x1 - x3) + (y1 - y3)(x1 - x2) == 0
     *  
     * */

    private boolean equalAngleII(int[] p1, int[] p2, int[] p3) {
        System.out.println("(x1,y1) = " + p1[0] + "," + p1[1]);
        System.out.println("(x2,y2) = " + p2[0] + "," + p2[1]);
        System.out.println("(x3,y3) = " + p3[0] + "," + p3[1]);
        // (x1 - x2)(x1 - x3) + (y1 - y2)(y1 - y3) = 0
        return (p1[0] - p2[0]) * (p1[0] - p3[0]) + (p1[1] - p2[1]) * (p1[1] - p3[1]) == 0;
    }

	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		ValidSquare testObj = new ValidSquare();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
    
		List<double[]> points = new ArrayList<>();
		points.add(new double[] {1, 3});
		points.add(new double[] {3, 3});
		points.add(new double[] {1, 1});
		points.add(new double[] {3, 1});
//		System.out.println(testObj.validSquareII(points));
	    
		System.out.println(Math.sqrt(3));
		
		System.out.println(Math.pow(Math.sqrt(3), 2));
		
		List<double[]> points2 = new ArrayList<>();
		points2.add(new double[] {0, 0});
		points2.add(new double[] {2, 0});
		points2.add(new double[] { 1, Math.sqrt(3) });
		points2.add(new double[] { 1, Math.sqrt(3) / 2.0 });
		System.out.println(testObj.validSquareII(points2));
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");

    
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
	}
}
