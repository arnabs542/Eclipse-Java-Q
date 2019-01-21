/*
 * == Created Date ==
 * Jan 20, 2019
 * 
 * == Question - Flatten Nested List Iterator ==
 * Given a nested list of integers, implement an iterator to flatten it.
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 *   
 * == Example == 
 * Input: [[1,1],2,[1,1]]
 * Output: [1,1,2,1,1]
 * Explanation: By calling next repeatedly until hasNext returns false, 
 *              the order of elements returned by next should be: [1,1,2,1,1].
 *                          
 * == Notes == 
 * LeetCode 341(M)  
 * 
 */
package practiceContent;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
 interface NestedInteger {
     // @return true if this NestedInteger holds a single integer, rather than a nested list.
     public boolean isInteger();

     // @return the single integer that this NestedInteger holds, if it holds a single integer
     // Return null if this NestedInteger holds a nested list
     public Integer getInteger();

     // @return the nested list that this NestedInteger holds, if it holds a nested list
     // Return null if this NestedInteger holds a single integer
     public List<NestedInteger> getList();
 }
 
 /**
  * Your NestedIterator object will be instantiated and called as such:
  * NestedIterator i = new NestedIterator(nestedList);
  * while (i.hasNext()) v[f()] = i.next();
  */
 
 
/* ----- < Not a solution - Recursively flatten the nestedList first. Not a good design for Interator > -----
 * comment1: Since you are implementing an Iterator, so you shouldn't pre-compute all the list, 
 *           you should compute the needed information on the fly
 * 
 * comment2: DO NOT flatten the list and store it .. that is not the point of the question...
 * 
 * comment3: Hard to extend the function of remove() for the Interator
 * 
 * Time Complexity: O(N) to construct, O(1) for next(), hasNext();
 * Space Complexity: O(N);
 * 
 * */
class NestedIteratorI implements Iterator<Integer> {

    private int position;
    private List<Integer> flattenList = null;
    
    public NestedIteratorI(List<NestedInteger> nestedList) {
        flattenList = new ArrayList<>();
        traverse(nestedList, flattenList);
        position = 0;
    }

    private void traverse(List<NestedInteger> nestedList, List<Integer> flattenList) {
        for (NestedInteger nested : nestedList) {
            if (nested.isInteger()) {
                flattenList.add(nested.getInteger());
            } else {
                traverse(nested.getList(), flattenList);
            }
        }
    }
        
    @Override
    public Integer next() {
        if (hasNext()) {
            return flattenList.get(position++);
        } 
        return null;
    }

    @Override
    public boolean hasNext() {
        if (position < flattenList.size()) {
            return true;
        }
        return false;
    }
}

/* ----- < Solution - Using only iterators in C++ or iterators in Java > -----
 * Time Complexity: O(?);
 * Space Complexity: O(?);
 * 
 * */

/*
 * == Example == 
 * Input: [ [1,2], 3, [ [4], [5, 6] ] ]
 * 
 * stack: [1,2]
 * 
 * Output: [1,2,3,4,5,6]
 * Explanation: By calling next repeatedly until hasNext returns false, 
 *              the order of elements returned by next should be: [1,1,2,1,1].
 */

class NestedIterator implements Iterator<Integer> {
	
    NestedInteger nextInt;
    Deque<Iterator<NestedInteger>> stack;

    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new ArrayDeque<>();
        stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextInt != null ? nextInt.getInteger() : null; //Just in case
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            if (!stack.peek().hasNext()) {
                // if all the elments in the current NestedInteger (pointed by the top Iterator in stack) 
                // has been visited, pop the Iterator from stack
            		stack.pop();
            } else {
                nextInt = stack.peek().next();

                if (nextInt.isInteger()) {
                    // if the next element of the current NestedInteger is an Integer, assign it to nextInt 
                    // has been visited, pop the Iterator from stack
                    return true;
                } else {
                    // if the next element og the current NestedInteger is a nested list, push it to the stack
                    stack.push(nextInt.getList().iterator());
                }
            }
        }
        return false;
    }
}


public class FlattenNestedListIterator  {


}
