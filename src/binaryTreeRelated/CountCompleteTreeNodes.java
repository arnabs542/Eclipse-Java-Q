/*
 * == Created Date ==
 * October 2, 2018
 * 
 * == Question - Count Complete Tree Nodes (M) ==
 * Given a complete binary tree, count the number of nodes.
 * 
 * Note:
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree every level, except possibly the last, is completely filled, 
 * and all nodes in the last level are as far left as possible. 
 * It can have between 1 and 2^h nodes inclusive at the last level h. 
 *   
 * == Example == 
 *   Input: 
 *      1
 *     / \
 *    2   3
 *   / \  /
 *  4  5 6
 *  
 *  Output: 6
 * 
 * == Notes ==
 * LeetCode 222(M)
 * 
 * Jan 17, 2019 - G phone inverview question
 * 
 */

package binaryTreeRelated;

public class CountCompleteTreeNodes {
	
	// == Naive Solution 
	// Traverse all the node -> Time limited exceeded

	public int postOrder(TreeNode root) {
	    if (root == null) {
	        return 0;
	    }
        return 1 + postOrder(root.left) + postOrder(root.right);
	}

	/* == Solution 1 == 
	 * To avoid redundant calculation, utilize the feature of a full tree: number of nodes = 2 ^ height - 1; 
	 * 
	 * For a current node, if its left height == right height, 
	 * number of nodes can be calcuted directly from its height, so don't need to traverse this subtree; 
	 * if not, we continue to count the nodes
	 * 
	 * Time: O(logN ^ 2)
	 * Time: O(logN)
	 * 
	 */
    public int countNodesI(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = 0;       
        TreeNode left = root.left;
        while (left != null) {
            leftHeight++;
            left = left.left;
        }
        
        int rightHeight = 0;    
        TreeNode right = root.right;
        while (right != null) {
            rightHeight++;
            right = right.right;
        }
        
        if (leftHeight == rightHeight) {
            return (1 << (leftHeight + 1)) - 1; // pow(2, leftHeight + 1) - 1
        }
        return countNodesI(root.left) + countNodesI(root.right) + 1;
    }
    
    /* == Solution 1* Optimized == 
     * We can continue to optimize the above solution
     * 
     * */
    public int countNodesII(TreeNode root) {
        return countNodesII(root, -1, -1);
    }
 
    public int countNodesII(TreeNode node, int leftHeight, int rightHeight) {
        if (node == null) {
            return 0;
        }
 
        TreeNode tmp = node.left;
        if (leftHeight == -1) { // leftHeight == -1 means the leftHeight is unknown
            leftHeight = 0;
            while (tmp != null) {
                leftHeight++;
                tmp = tmp.left;
            }
        }
 
        tmp = node.right;
        if (rightHeight == -1) {
            rightHeight = 0;
            while (tmp != null) {
                rightHeight++;
                tmp = tmp.right;
            }
        }
 
        if (leftHeight == rightHeight) { // found a full tree           
            return (1 << (leftHeight + 1)) - 1; // 2^(leftHeight + 1) - 1
        }
 
        return 1 + countNodesII(node.left, leftHeight - 1, -1) + countNodesII(node.right, -1, rightHeight - 1);
    }
    
    /* == Solution 2  == 
	 * Time: O(logN ^ 2)
	 * Time: O(logN)
	 * 
     * */
    public int countNodes(TreeNode root) {
        if (root == null) {
        		return 0;
        }
        int leftHeight = height(root.left);
        int rightHeight= height(root.right);     
        if (leftHeight == rightHeight) {
            // if left and right height is the same, the left subtree is a full tree
            // check the nodes of right subtree recursively
            return (1 << leftHeight) + countNodes(root.right); 
            // actually is : ((1 << leftHeight) - 1)  + 1 + countNodes(root.right); 
        } else {
            // othwise, the right subtree is a full tree
            // check the nodes of left subtree recursively
            return (1 << rightHeight) + countNodes(root.left); 
            // actually is : ((1 << rightHeight) - 1) + 1 + countNodes(root.left);
        }
    }
    
    private int height(TreeNode root) { //get the height of a complete binary tree (the lenght of left side)
        if (root == null) {
        		return 0;
        }
        return 1 + height(root.left);
    }
}
