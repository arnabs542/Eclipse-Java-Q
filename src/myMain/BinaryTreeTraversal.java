

package myMain;

class TreeNode {
	public int value;
	public TreeNode left;
	public TreeNode right;
	
	public TreeNode(int v) {
		value = v;
		left = null;
		right = null;
	}
}

public class BinaryTreeTraversal {
	
	// Time Complexity: O(n) 
	// Space Complexity: O(height), worst case: O(n), balanced BST: O(logn)
	public static boolean checkBalanced2(TreeNode node) {
		if(node == null) return true; // corner case
		if(helper(node) == -1) return false;
		else return true;
	}

	static int helper(TreeNode node) {
		if(node == null) return 0; 
		
		int leftHeight = helper(node.left);
		int rightHeight = helper(node.right);
		
		if(leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
			return -1;
		} // post-order traversal
		return Math.max(leftHeight, rightHeight) + 1;
	}
	
	// Time Complexity: O(n)
	// Space Complexity: O(height)
	public static int getHeight(TreeNode node) {		
		if(node == null) return 0;
		
		int leftHeight = getHeight(node.left);
		int rightHeight = getHeight(node.right);
		return Math.max(leftHeight, rightHeight) + 1;	// post-order traversal
	}

	// if there's parent pointer? how to do traversal

	public static void main(String[] args) {
		
		TreeNode root = new TreeNode(10);
		root.left = new TreeNode(5);
		root.left.left = new TreeNode(2);
		root.left.left.left = new TreeNode(1);
		root.left.right = new TreeNode(7);
		root.right = new TreeNode(15);
		root.right.left = new TreeNode(12);
		root.right.right = new TreeNode(20);	
	
		
		int h = getHeight(root);
		System.out.print(h + "\n");	
		
		boolean rst = checkBalanced2(root);
		System.out.print(rst);	
	}
}
