/*
 * == Created Date ==
 * Jan 6, 2019
 * 
 * == Question - Flatten 2D Vector ==
 * Implement an iterator to flatten a 2d vector.
 *   
 * == Example == 
 * Input: 2d vector =
 * [
 *   [1,2],
 *   [3],
 *   [4,5,6]
 * ]
 * Output: [1,2,3,4,5,6]
 * Explanation: By calling next repeatedly until hasNext returns false, 
 *              the order of elements returned by next should be: [1,2,3,4,5,6].
 *                           
 * == Notes == 
 * LeetCode 251(M) 
 * 
 */
package practiceContent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Flatten2DVector {
		
	/* ----- < Solution - Using only iterators in C++ or iterators in Java > -----
	 * Time Complexity: O(?);
	 * Space Complexity: O(?);
	 * 
	 * */
	private final Iterator<List<Integer>> rowIterator;
    private Iterator<Integer> colIterator;
    private Iterator<Integer> preIterator;
    
    public Flatten2DVector(List<List<Integer>> vec2d) {
    		rowIterator = vec2d.iterator();
        advanceListIterator();
    }

    private void advanceListIterator() {
        while (rowIterator.hasNext()) {
        		colIterator = rowIterator.next().iterator();
            if (colIterator.hasNext()) {
                break;
            }
        }
    }
    
    public Integer next() {
        if (!hasNext()) {
            throw new java.util.NoSuchElementException();
        }
        preIterator = colIterator;
        Integer ret = colIterator.next();
        if (!colIterator.hasNext()) {
            advanceListIterator();
        }
        return ret;
    }

    public boolean hasNext() {
        return colIterator != null && colIterator.hasNext();
    }
    
    public void remove() {
    		preIterator.remove();
    }
    
	/* ----------------------< test stub >-------------------------*/
    private static void print2dVector(List<List<Integer>> vec2d) {
		for (List<Integer> row : vec2d) {
			for (int num : row) {
				System.out.print(num + " ");
			}
		}
		System.out.println();
    }
    
	public static void main(String[] args) {
			
		/* Test Case 0 */
		System.out.println("---< Test Case 0 - Test iterator in Java >---");
		
		List<Integer> testList = new ArrayList<>();
		testList.add(1);
		testList.add(2);
		testList.add(3);
		testList.add(4);
		
		Iterator<Integer> iter = testList.iterator();
		
		// Remove the next element in the iteration
		// This method can be called only once per call to next()
		while (iter.hasNext()) {
			System.out.println("next = " + iter.next() + " ");
			iter.remove();
			
			for (int num : testList) {
				System.out.print(num + " ");
			}
			System.out.println();
		}

		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		List<Integer> list1 = new ArrayList<>();
		list1.add(1);
		list1.add(2);
		
		List<Integer> list2 = new ArrayList<>();
		
		List<Integer> list3 = new ArrayList<>();
		list3.add(3);
		
		List<Integer> list4 = new ArrayList<>();
		list4.add(4);
		list4.add(5);
		
		//   0 1   2    3 4
		// [[1,2] [3], [4,5]]
		List<List<Integer>> vec2d = new ArrayList<>();
		vec2d.add(list1);
		vec2d.add(list2);
		vec2d.add(list3);
		vec2d.add(list4);
		
		Flatten2DVector testObj = new Flatten2DVector(vec2d);
		
		while (testObj.hasNext()) {
			System.out.println("next = " + testObj.next() + " ");
			testObj.remove();
			print2dVector(vec2d);
		}

		/* Test Case 2 */
		System.out.println("\n---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}
