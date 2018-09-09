/*
 * == Created Date ==
 * September 7, 2018
 * 
 * == Question - Reconstruct Binary Search Tree With Post-order Traversal ==
 * Given the post-order traversal sequence of a binary search tree, reconstruct the original tree.  
 *   
 * == Example == 
 * post-order traversal = {1, 4, 3, 11, 8, 5}
 * The corresponding binary search tree is
 * 
 *        5       
 *     /    \
 *    3       8
 *  /   \       \
 * 1      4      11
 *  
 * == Similar Question == 
 * Reconstruct Binary Search Tree With Pre-order Traversal
 * 
 */

package binaryTreeRelated;

public class ConstructBSTWithPostOrderTraversal {
	
	/* ----------------------< Reconstruct BST With Post Order Traversal >-------------------------*/
	public TreeNode reconstruct(int[] post) {
		int[] index = new int[] {post.length - 1};
		return buildTree(post, index, Integer.MIN_VALUE);
	}
	
	private TreeNode buildTree(int[] post, int[] index, int min) {
		if (index[0] < 0 || post[index[0]] <= min) {
			return null;
		}
		TreeNode root = new TreeNode(post[index[0]--]);
		root.right = buildTree(post, index, root.value);
		root.left = buildTree(post, index, min);
		return root;
	}
	
	// Time Complexity: O(n);
	// Space Complexity: O(height);
	
	/* ----------------------< Reconstruct BST With Pre Order Traversal >-------------------------*/
	public TreeNode reconstructWithPre(int[] pre) {
		int[] index = new int[]{0};
		return buildTree(pre, index, Integer.MAX_VALUE);
	}
	
	private TreeNode buildTreeWithPre(int[] pre, int[] index, int max) {
		if (index[0] >= pre.length || pre[index[0]] >= max) {
			return null;
		}
		TreeNode root = new TreeNode(pre[index[0]++]);
		root.left = buildTree(pre, index, root.value);
		root.right = buildTree(pre, index, max);
		return root;
	}
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		ConstructBSTWithPostOrderTraversal testObj = new ConstructBSTWithPostOrderTraversal();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		int[] post = {1, 4, 3, 11, 8, 5};
		TreeNode root1 = testObj.reconstruct(post);
		TreeNode.printInOrder(root1);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}

}
