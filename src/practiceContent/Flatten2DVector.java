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
	private final Iterator<List<Integer>> listIterator;
    private Iterator<Integer> intIterator;
    
    public Flatten2DVector(List<List<Integer>> vec2d) {
        listIterator = vec2d.iterator();
        advanceListIterator();
    }

    private void advanceListIterator() {
        while (listIterator.hasNext()) {
        		intIterator = listIterator.next().iterator();
            if (intIterator.hasNext()) {
                break;
            }
        }
    }
    
    public Integer next() {
        if (!hasNext()) {
            throw new java.util.NoSuchElementException();
        }
        Integer ret = intIterator.next();
        if (!intIterator.hasNext()) {
            advanceListIterator();
        }
        return ret;
    }

    public boolean hasNext() {
        return intIterator != null && intIterator.hasNext();
    }
    
    public void remove() {
    	
    }
    
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
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
		
		List<List<Integer>> vec2d = new ArrayList<>();
		vec2d.add(list1);
		vec2d.add(list2);
		vec2d.add(list3);
		vec2d.add(list4);
		
		Flatten2DVector testObj = new Flatten2DVector(vec2d);
		
		while (testObj.hasNext()) {
			System.out.print(testObj.next() + " ");
		}
		/* Test Case 2 */
		System.out.println("\n---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}
