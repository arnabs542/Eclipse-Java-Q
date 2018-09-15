package binaryTreeRelated;

import java.util.ArrayList;
import java.util.List;

public class GenerateUniqBST {
	
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<TreeNode>();
        }
        return helper(1, n);
    }

    public List<TreeNode> helper(int left, int right) {
        List<TreeNode> result = new ArrayList<TreeNode>();
        if (left > right) {
            result.add(null);
            return result;
        }

        for (int i = left; i <= right; i++) {
            List<TreeNode> leftList = helper(left, i - 1);
            List<TreeNode> rigthList = helper(i + 1, right);

            for (TreeNode leftChild: leftList) {

                for (TreeNode rightChild: rigthList) {
                		
                    TreeNode curr = new TreeNode(i);
                    curr.left = leftChild;
                    curr.right = rightChild;
                    result.add(curr);               
                }
            }
        }
        return result;
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
		List<TreeNode> res = testObj.generateTrees(3);
		
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
