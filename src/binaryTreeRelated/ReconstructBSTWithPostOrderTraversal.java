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
 * 
 */

package binaryTreeRelated;

public class ReconstructBSTWithPostOrderTraversal {
	
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
	
	// Time Complexity: O(?);
	// Space Complexity: O(?);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		ReconstructBSTWithPostOrderTraversal testObj = new ReconstructBSTWithPostOrderTraversal();
		
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
