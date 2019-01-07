/*
 * == Created Date ==
 * Jan 6, 2019
 * 
 * == Question - Flatten 2D Vector ==
 * 
 *   
 * == Example == 
 *   
 * == Notes == 
 *  
 * 
 */
package practiceContent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Flatten2DVector {
	
	private final Iterator<List<Integer>> listIterator;
    private Iterator<Integer> sublistIterator;
    
    public Flatten2DVector(List<List<Integer>> vec2d) {
        listIterator = vec2d.iterator();
        advanceListIterator();
    }

    private void advanceListIterator() {
        while (listIterator.hasNext()) {
            sublistIterator = listIterator.next().iterator();
            if (sublistIterator.hasNext()) {
                break;
            }
        }
    }
    
    public Integer next() {
        if (!hasNext()) {
            throw new java.util.NoSuchElementException();
        }
        Integer ret = sublistIterator.next();
        if (!sublistIterator.hasNext()) {
            advanceListIterator();
        }
        return ret;
    }

    public boolean hasNext() {
        return sublistIterator != null && sublistIterator.hasNext();
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
