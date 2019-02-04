/*
 * == Created Date ==
 * June 30, 2018 
 * 
 * == Question - Permutations II ==
 * Given a string with possible duplicate characters, return a list with all permutations of the characters.
 *   
 * == Example == 
 * Set = “abc”, all permutations are [“abc”, “acb”, “bac”, “bca”, “cab”, “cba”]
 * Set = "aba", all permutations are ["aab", "aba", "baa"]
 * Set = "", all permutations are [""]
 * Set = null, all permutations are []
 * 
 * == Updated == 
 * July 1, 2018 : Review, Mid term exam problem 3
 * Octorber 29, 2018: Review, Fall Class 13 - DFS2
 * Febuary 3, 2019: Add solution 2
 * 
 * LeetCode 47 (M) - Permutations II
 */

package dfsRelated;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PermutationsII {
	
	/* ----- < Solution 1 - Swap + Same level deduplication > -----
	 * Time Complexity: O(n!);
	 * Space Complexity: O(n);
	 * 
	 * */
	
	/* [a a b]           
	 *  
	 *             a [a b]                 a [a b] (repeated)          b [a a]
	 *         a a [b]    a b [a]                                b a [a]     b a [a] (repeated) 
	 *     {a a b}        {a b a}
	 * 
	 * Every level needs a new hash set to deduplicate
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
    		if (index == input.length) {
    			result.add(new String(input)); // don't use: input.toString()
    			return;
    		}	
    		Set<Character> set = new HashSet<>();
    		// all the possible characters could be placed at index are in (index, input.length - 1)
    		for (int i = index; i < input.length; i++) {
    			if (set.add(input[i])) {
    				swap(input, i, index);
    				dfs(input, index + 1, result); // go for the next level
    				swap(input, i, index); // swap back when backtracking to the previous level
    			}
    		}
    }
		
    private void swap(char[] input, int a, int b) {
		char temp = input[a];
		input[a] = input[b];
		input[b] = temp;
    }
    
	/* ----- < Same method, LeetCode signature > ----- */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(nums, 0, result);
        return result;
    }
    
    private void dfs(int[] nums, int index, List<List<Integer>> result) {
        if (index == nums.length) {
            List<Integer> curList = new ArrayList<>();
            for (int element : nums) {
                curList.add(element);
            }
            result.add(curList);
            return;
        }
        
        Set<Integer> set = new HashSet<>();
        for (int i = index; i < nums.length; i++) {
            if (set.add(nums[i])) {
                swap(nums, index, i);
                dfs(nums, index + 1, result);
                swap(nums, index, i);
            }
        }
    }
    
    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
    
	/* ----- < Solution 2 - Sort > -----
	 * Time Complexity: O(n!);
	 * Space Complexity: O(n);
	 * 
	 * */
    
	/*  0 1 2
	 * [1 1 2]           
	 *    v
	 *             1                 1 (need to deduplicate)              2
	 *         1 a    a b                                              b a    b a (need to deduplicate) 
	 *     {a a b}    {a b a}
	 * 
	 * */
    public List<List<Integer>> permutationsII(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) {
        		return res;
        }
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        dfs(nums, used, new ArrayList<Integer>(), res);
        return res;
    }

    public void dfs(int[] nums, boolean[] used, List<Integer> list, List<List<Integer>> res){
        if (list.size() == nums.length){
            res.add(new ArrayList<Integer>(list));
            return;
        }
        
        for (int i = 0; i < nums.length; i++){
            if (used[i]) { // skip itself
            		continue;
            }
            if (i > 0 && nums[i - 1] == nums[i] && !used[i - 1]) { 
            		continue;
            }
            
            used[i] = true; // mark the current element as visited
            list.add(nums[i]);
            dfs(nums,used,list,res);
            
            used[i] = false; // for backtracking
            list.remove(list.size()-1);
        }
    }
    
	/* ----------------------< test stub >-------------------------*/
    private static void print(List<String> result) {
		for (String s : result) {
			System.out.println(s + " ");
		} 
    }
    
    private static void printII(List<List<Integer>> result) {
		for (List<Integer> list : result) {
			for (int ele : list) {
				System.out.print(ele + " ");
			}
			System.out.println();
		} 
    }
    
	public static void main(String[] args) {
		
		PermutationsII testObj = new PermutationsII();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		print(testObj.permutations(null));
		print(testObj.permutations("")); // expected: [""]
		
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		print(testObj.permutations("abc")); // expected: [“abc”, “acb”, “bac”, “bca”, “cab”, “cba”]

		printII(testObj.permutationsII(new int[] {1, 2, 1}));
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		print(testObj.permutations("aba")); // expected: ["aab", "aba", "baa"]
		 

		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");	
		
//		print(testObj.permutations("aabbccd"));
		 		
	}
}
