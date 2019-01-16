/*
 * == Created Date ==
 * Jan 16, 2019
 * 
 * == Question - Implement Trie (Prefix Tree) ==
 * 
 * Implement a trie with insert, search, and startsWith methods.
 *   
 * == Notes == 
 * LeetCode 208(M)
 * 
 */

package trieRelated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImplementTrie {

	// Time Complexity: O(L), L is the the average lenght of a word
	// Space Complexity: O(n * L);
	
	class TrieNode {
		private boolean isWord;
		Map<Character, TrieNode> children; // key: char, value: children TrieNode 
		public TrieNode() {
            children = new HashMap<>();
            isWord = false;
		}
	}
	// If charset only contain 26 lower case english char, each index -> char
	// ``` TrieNode[] children; ``` 
	
	private TrieNode root;
	 
    /** Initialize your data structure here. */
    public ImplementTrie() {
    		root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode cur = root;
        // For each char in the word, see if there is an edge associated with it for the next level
        for (int i = 0; i < word.length(); i++) {
        		// if there is no such edge, create the child node
            cur.children.putIfAbsent(word.charAt(i), new TrieNode());
            
            // go to the child node
            cur = cur.children.get(word.charAt(i));
        }
        cur.isWord = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
    		TrieNode cur = find(word);
    		return cur != null && cur.isWord;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
		TrieNode cur = find(prefix);
		return cur != null;
    }
	
    private TrieNode find(String prefix) {
        TrieNode cur = root;
		for (int i = 0; i < prefix.length(); i++) {
			if (cur.children.get(prefix.charAt(i)) == null) {
				return null;
			}
			cur = cur.children.get(prefix.charAt(i));
		}
		return cur;
    }
	
    
    /** Returns all the words with the given prefix. */
    public List<String> findAllWordWithPrefix(String prefix) {
    		TrieNode matchNode = find(prefix);
    		List<String> result = new ArrayList<>();
    		if (matchNode == null) {
    			return result;
    		}
    		findAllWordsUnderNode(matchNode, new StringBuilder(prefix), result);
    		printResult(result);
    		return result;
    }
    
    private void findAllWordsUnderNode(TrieNode cur, StringBuilder curPath, List<String> result) {
    		// base case, when we find a word
    		if (cur.isWord) { 
    			result.add(curPath.toString());
    		}
    		// check all braches
    		for (Map.Entry<Character, TrieNode> entry : cur.children.entrySet()) {
    			curPath.append(entry.getKey());
    			findAllWordsUnderNode(entry.getValue(), curPath, result);
    			curPath.deleteCharAt(curPath.length() - 1); // backtracking
    		}
    }
    
    
    
	/* ----------------------< test stub >-------------------------*/
    private void printResult(List<String> result) {
    		for (String word : result) {
    			System.out.println(word);
    		}
    }
    
	public static void main(String[] args) {
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		ImplementTrie trie = new ImplementTrie();

		trie.insert("apple");
		System.out.println(trie.search("apple"));   // returns true
		System.out.println(trie.search("app"));      // returns false
		System.out.println(trie.startsWith("app")); // returns true
		trie.insert("app");   
		System.out.println(trie.search("app"));     // returns true
		
		trie.insert("application");  
		trie.insert("ape");  
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		trie.findAllWordWithPrefix("a");
	}
	
	
}
