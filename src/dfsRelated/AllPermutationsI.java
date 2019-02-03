/*
 * Created Date: June 21, 2018
 * 
 * Question - All Permutations I
 *   Given a string with no duplicate characters, return a list with all permutations of the characters.
 *   
 *   Example: 
 *    Set = “abc”, all permutations are [“abc”, “acb”, “bac”, “bca”, “cab”, “cba”]
 *    Set = "", all permutations are [""]
 *    Set = null, all permutations are []
 *   
 * Follow up:
 *   All Permutations II, with duplicate characters
 *     
 * Updated:
 *   June 30, 2018 : Review
 * 
 */

package dfsRelated;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AllPermutationsI {
	
	/* 1. How many levels and what does it store on each level?
	 *    - Levels: The number of the characters in the given set. 
	 *    - Each level represent one position
	 * 
	 * 2. How many different states should we try to put on this level:
	 *    - The remaining not used characters. 
	 *      If we are at i-th level, there are (n - i) states/branches
	 * 
	 * */
	
	public List<String> permutations(String set) {
		List<String> result = new ArrayList<>();
		if (set == null) { // corner case
			return result;
		}
		char[] input = set.toCharArray();
		dfs(input, 0, result);
		return result;
	}
		  
    private void dfs(char[] input, int index, List<String> result) {
    		if (index == input.length - 1) {
    			result.add(new String(input)); // don't use: input.toString()
    			return;
    		}
    		
    		// all the possible characters could be placed at index are in (index, input.length - 1)
    		for (int i = index; i < input.length; i++) {
    			swap(input, i, index);
    			dfs(input, index + 1, result);
    			swap(input, i, index); // swap back when backtracking to the previous level
    		}
    }
		  
    private void swap(char[] input, int a, int b) {
    		char temp = input[a];
    		input[a] = input[b];
    		input[b] = temp;
    }
    
	// Time Complexity: O(n!);
	// Space Complexity: O(n);
    
    /* ----------------------< same problem, different signature >-------------------------
     * 
     * [p0 p1 p2 p3]
     *               
     *  index
     *  
     *   0                            p0 [p1 p2 p3]                          p1 [p0 p2 p3]          p2 [p0 p1 p3]       p3 [p0 p1 p2] 
     *    
     *                  /                       \             \                    / | \                 / | \              / | \
     *             
     *   1      p0 p1 [p2 p3]              p0 p2 [p1 p3]    p0 p3 [p1 p2]            ...
     *  
     *           /           \                 / \               / \
     *         
     *   2    p0 p1 p2 [p3]  p0 p1 p3 [p2]      ...             ...
     *   
     *         |                |
     *   
     *   3  {p0 p1 p2 p3}   {p0 p1 p2 p3 p2}
     *   
     * */
    
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        dfs(nums, 0, result);
        return result;
    }
    
    private void dfs(int[] nums, int index, List<List<Integer>> result) {
        if (index == nums.length - 1) { // base case
            List<Integer> cur = new LinkedList<>(); 
            for (int ele : nums) {
                cur.add(ele);
            }
            result.add(new LinkedList<Integer>(cur));
            return;
        }
        
        for (int i = index; i < nums.length; i++) {
            swap(nums, i, index);
            dfs(nums, index + 1, result);
            swap(nums, i, index);
        }
    }
    
    private void swap(int[] input, int a, int b) {
        int temp = input[a];
        input[a] = input[b];
        input[b] = temp;
    }
	
	// Another way to convert int[] to list: 
	//	``` List<Integer> cur = Arrays.stream(nums).boxed().collect(Collectors.toList()); ```
    
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		AllPermutationsI testObj = new AllPermutationsI();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		List<String> result  = testObj.permutations("abc");
		 
		for (String s : result) {
			System.out.println(s + " ");
		}
				
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");	
	}
}
