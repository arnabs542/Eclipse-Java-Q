package binarySearchTreeRelated;

import java.util.HashSet;
import java.util.Set;

import binaryTreeRelated.TreeNode;

public class TwoSumInBST {
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> visited = new HashSet<>();
        return inorder(root, visited, k);
    }
    
    private boolean inorder(TreeNode root, Set<Integer> visited, int target) {
        if (root == null) {
            return false;
        }
        boolean left = inorder(root.left, visited, target);
        if (visited.contains(target - root.value)) {
            return true;
        } 
        visited.add(root.value);
        boolean right = inorder(root.right, visited, target);
        return left || right;
    }
}
