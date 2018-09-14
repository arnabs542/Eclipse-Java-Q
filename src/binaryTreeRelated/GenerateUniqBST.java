package binaryTreeRelated;

import java.util.LinkedList;
import java.util.List;

public class GenerateUniqBST {
	
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> res = new LinkedList<>();
        dfs(res, 1, n, n);
        return res;
    }
    
    private TreeNode dfs(List<TreeNode> res, int left, int right, int n) {
        if (left > right) {
            return null;
        }
        TreeNode root = null;
        boolean flag = false;
        if (left + right - 1 == n) {
            flag = true;
        }
        for (int i = left; i <= right; i++) {
            root = new TreeNode(i);           
            if (left <= i - 1) {
            		root.left = dfs(res, left, i - 1, n);
            } 
            if (i + 1 <= right) {
            		root.right = dfs(res, i + 1, right, n);
            }
            if (flag) {
                res.add(root);
                TreeNode.printLayerByLayer(root);
            } 
        }
        return root;
    }
    
	// Time Complexity: O(?);
	// Space Complexity: O(?);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		GenerateUniqBST testObj = new GenerateUniqBST();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		List<TreeNode> res = testObj.generateTrees(4);
		
		for (TreeNode root : res) {
			System.out.println("Tree");
			TreeNode.printLayerByLayer(root);
		}
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}
