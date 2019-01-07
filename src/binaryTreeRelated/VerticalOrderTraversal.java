/*
 * == Created Date ==
 * September 28, 2018
 * 
 * == Question - Binary Tree Vertical Order Traversal ==
 * Given a binary tree, return the vertical order traversal of its nodes' values.
 * (ie, from top to bottom, column by column).
 * If two nodes are in the same row and column, the order should be from left to right.
 * 
 * == Examples ==
 * 
 * - Given binary tree [3,9,20,null,null,15,7],
	   3
	  / \
	 9  20
	    /\
	  15   7
	return its vertical order traversal as: [9,3,15,20,7]
	
  -	Given binary tree [3,9,8,4,0,1,7],
	     3
	    /\
	   9   8
	  /\  /\
	 4  01   7
	return its vertical order traversal as: [4,9,3,0,1,8,7]
	
  -	Given binary tree [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5),
	     3
	    /\
	   9   8
	  /\  /\

	 4  01   7
	    /\
	   /  \
	   5   2
	return its vertical order traversal as:
	
	[4,9,5,3,0,1,8,2,7]  
 * 
 *  == Notes ==
 *  LeetCode 314*(M) 
 * 
 */

package binaryTreeRelated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.TreeMap;

public class VerticalOrderTraversal {
	
    /* ----------------------< Partial Solution, using Hash table >-------------------------*/
    // Can print the right columns, but the order may not from top to bottom
    
	// Utility function to store vertical order in map 'm' 
    // key: 'hd', the horizontal distance of current node from root. 
    // value: list of nodes that have the same horizontal distance
    static void getVerticalOrder(TreeNode root, int hd, TreeMap<Integer, List<Integer>> map) {        
        if (root == null) { // Base case 
        	    return; 
        }
                  
        List<Integer> get =  map.get(hd); //get the vector list at 'hd' 
          
        // Store current node in map 'm' 
        if (get == null) { 
            get = new ArrayList<>(); 
            get.add(root.value); 
        } else {
        	    get.add(root.value); 
        }         
        map.put(hd, get); 
                 
        getVerticalOrder(root.left, hd-1, map); // Store nodes in left subtree          
        getVerticalOrder(root.right, hd+1, map); // Store nodes in right subtree
    } 
      
    // The main function to print vertical oder of a binary tree with given root 
    static void printVerticalOrder(TreeNode root) {         
    	    TreeMap<Integer,List<Integer>> map = new TreeMap<>(); // Create a map to store the vertical oder 
        int hd = 0; 
        getVerticalOrder(root, hd, map); 
          
        // Traverse the map and print nodes at every horigontal distance (hd) 
        for (Entry<Integer, List<Integer>> entry : map.entrySet()) { 
            System.out.println(entry.getValue()); 
        } 
        System.out.println();
    } 
    
    /* ----------< Solution, level order traversal + self-defined data structure >-------------------------*/
    private class TreeNodeWithHorizDis { 
    		public TreeNode treeNode;
        int horizDis; // horizontal distance
        
        public TreeNodeWithHorizDis(TreeNode node, int horizDis) {
          this.treeNode = node;
          this.horizDis = horizDis;
        }
    }
    
    public List<List<Integer>> verticalOrder(TreeNode root) { 
        List<List<Integer>> result = new ArrayList<>();
	    if (root == null) {
	      return result;
	    }
	    Queue<TreeNodeWithHorizDis> queue = new LinkedList<>();
	    Map<Integer, List<Integer>> map = new HashMap<>();
	    queue.offer(new TreeNodeWithHorizDis(root, 0));
	    int minHorizDis = 0;
	    int maxHorizDis = 0;
	
	    while (!queue.isEmpty()) {    		
            for (int size = queue.size(); size > 0; size--) {
                TreeNodeWithHorizDis curNode = queue.poll();
                if (map.containsKey(curNode.horizDis)) {
                    map.get(curNode.horizDis).add(curNode.treeNode.value);
                } else {
                    List<Integer> newList = new ArrayList<Integer>();
                    newList.add(curNode.treeNode.value);
                    map.put(curNode.horizDis, newList);
                }

                if (curNode.treeNode.left != null) {
                    queue.offer(new TreeNodeWithHorizDis(curNode.treeNode.left, curNode.horizDis - 1));
                    minHorizDis = Math.min(curNode.horizDis - 1, minHorizDis);
                }
                if (curNode.treeNode.right != null) {
                    queue.offer(new TreeNodeWithHorizDis(curNode.treeNode.right, curNode.horizDis + 1));
                    maxHorizDis = Math.max(curNode.horizDis + 1, maxHorizDis);
                }
	        }
	    }
	    
	    for (int i = minHorizDis; i <= maxHorizDis; i++) {   
	    		System.out.println(map.get(i)); 
	    		result.add(map.get(i)); 
	    }
	    return result;
    }
    
	/* ----------------------< test stub >-------------------------*/
    public static void main(String[] args) { 
  
        // TO DO Auto-generated method stub 
        TreeNode root = new TreeNode(1); 
        root.left = new TreeNode(2); 
        root.right = new TreeNode(3); 
        root.left.left = new TreeNode(4); 
        root.left.right = new TreeNode(5); 
        root.left.right.right = new TreeNode(8);
        root.right.left = new TreeNode(6); 
        root.right.left.right = new TreeNode(9); 
        root.right.right = new TreeNode(7); 
               
        //root.right.right.right = new TreeNode(9); 
        System.out.println("Vertical Order traversal is"); 
        printVerticalOrder(root); 
        
        
        /*              1
         *            /    \
         *           2       3
         *         /   \   /   \
         *        4     5 6     7
         *                \ \      
         *                  8 9
         *        |   |  |    |   |       
         *       [4] [2] [1   [3  [7]
         *                5    8
         *                6]   9]    
         */ 
               
        VerticalOrderTraversal testObj = new VerticalOrderTraversal();
        testObj.verticalOrder(root);
    } 

}
