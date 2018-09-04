/*
 * == Created Date ==
 * September 3, 2018
 * 
 * == Question - Binary Tree Path Sum To Target II, find all paths ==
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 *   
 * == Example == 
 *       5
 *      / \
 *     4   8
 *    /   / \
 *   11  13  4
 *  /  \      \
 * 7    2      1
 * 
 * sum = 22, 
 * return [ [5,4,11,2], [5,8,4,5]]
 * 
 */

package binaryTreeRelated;

import java.util.LinkedList;
import java.util.List;

public class PathSumToTargetII {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> curList = new LinkedList<>();
        pathSum(root, sum, res, curList);
        return res;
    }
    
    private void pathSum(TreeNode root, int sum, List<List<Integer>> res, List<Integer> curList) {
        if (root == null) {
            return;
        }
        
        curList.add(root.value);
        
        if (root.left == null && root.right == null) {
            if (sum == root.value) {
                res.add(new LinkedList<Integer>(curList));
                return;
            }
        }
        
        if (root.left != null) {
            pathSum(root.left, sum - root.value, res, curList);
            curList.remove(curList.size() - 1);
        }

        if (root.right != null) {
            pathSum(root.right, sum - root.value, res, curList);
            curList.remove(curList.size() - 1);
        }
    }

}
