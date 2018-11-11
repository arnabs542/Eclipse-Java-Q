package binarySearchTreeRelated;
import binaryTreeRelated.TreeNode;

public class RangeSum {
	
    
    public int rangeSumBST(TreeNode root, int L, int R) {
    		int[] res = new int[]{0};
        inOrder(root, L, R, res);
        return res[0];
    }
    
    private void inOrder(TreeNode root, int L, int R, int[] res) {
        if (root == null) {
            return;
        }
        if (root.value < L || root.value > R) {
            return;
        }
        
        inOrder(root.left, L, R, res);
        
        if (root.value >= L && root.value <= R) {
        		res[0] += root.value;
        }
        
        inOrder(root.right, L, R, res);
    }
    
	// Time Complexity: O(?);
	// Space Complexity: O(?);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		TreeNode root = new TreeNode(10);
		root.left = new TreeNode(5);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(7);
		root.right = new TreeNode(15);
		root.right.right = new TreeNode(18);
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		RangeSum testObj = new RangeSum();
		System.out.println(testObj.rangeSumBST(root, 7, 18));
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}
