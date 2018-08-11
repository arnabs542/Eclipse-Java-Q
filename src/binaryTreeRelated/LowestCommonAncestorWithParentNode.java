/*
 * Created Date: August 8, 2018
 * 
 * Question - Lowest Common Ancestor With Parent Node:
 *   Given two nodes in a binary tree (with parent pointer available), find their lowest common ancestor.  
 * 
 */

package binaryTreeRelated;

import java.util.HashSet;
import java.util.Set;

class TreeNodeP {
   public int key;
   public TreeNodeP left;
   public TreeNodeP right;
   public TreeNodeP parent;
   public TreeNodeP(int key, TreeNodeP parent) {
     this.key = key;
      this.parent = parent;
    }
  }


public class LowestCommonAncestorWithParentNode {
	
	public TreeNodeP lowestCommonAncestor(TreeNodeP one, TreeNodeP two) {
		// traverse from node one to the root,
		// use a hash set to record all the passing nodes
		Set<TreeNodeP> set = new HashSet<>();
		while (one != null) {
			set.add(one);
			one = one.parent;
		}		
		// traverse from node two to the root,
		// if pass a node that existed in the set, return the node
		while (two != null) {
			if (set.contains(two)) {
				return two;
			}
			two = two.parent;
		}
		// one and two are not in the tree, return null
		return null;
	}
}
