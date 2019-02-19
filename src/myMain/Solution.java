package myMain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

	
	/* Trie Tree
	 * 
	 * 
	 * */
	
	class TrieNode {
		boolean isWord;
		Map<Character, TrieNode> map;
		
		public TrieNode() {
			isWord = false;
			map = new HashMap<>();
		}
	}
	
	TrieNode root;
	
	public Solution() {
		root = new TrieNode();
	}
	
	public void insert(String word) {
		TrieNode cur = root;
		for (int i = 0; i < word.length(); i++) {
			char curChar = word.charAt(i);
			if (cur.map.containsKey(curChar)) {
				cur = cur.map.get(curChar);
			} else {
				TrieNode newNode = new TrieNode();
				cur.map.put(curChar, newNode);
			}
			
			if (i == word.length()) {
				cur.isWord = true;
			}
		}
	}
	
	// cat, cane, cot
	
	// ca
	/*          
	 *      c    
	 *    a   o
	 *  t  n    t
	 *      e
	 * 
	 * 
	 * 
	 * */
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		
	}
}
