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

	/* == Better Solution == 
	 * To avoid redundant calculation, utilize the feature of a full tree: number of nodes = 2 ^ height - 1; 
	 * 
	 * For a current node, if its left height == right height, 
	 * number of nodes can be calcuted directly from its height, so don't need to traverse this subtree; 
	 * if not, we continue to count the nodes
	 * 
	 */
	
    public int countNodesMeth1(TreeNode root) {
        
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
        return countNodesMeth1(root.left) + countNodesMeth1(root.right) + 1;
    }
    
    /* == Better Solution 2 == 
     * We can continue to optimize the above solution
     * 
     * 
     * */
    public int countNodesMeth2(TreeNode root) {
        return countNodes(root, -1, -1);
    }
 
    public int countNodes(TreeNode node, int leftHeight, int rightHeight) {
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
 
        if (leftHeight == rightHeight) { // found complete tree           
            return (1 << (leftHeight + 1)) - 1; // 2^(leftHeight + 1) - 1
        }
 
        return 1 + countNodes(node.left, leftHeight - 1, -1) + countNodes(node.right, -1, rightHeight - 1);
    }
}
