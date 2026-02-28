package io.learn.trees;

import java.util.List;

public class IsSubTree {
    public static void main(String[] args) {
        TreeNode root = TreeNode.constructTree(List.of(3, 4, 5, 1, 2));
        TreeNode subroot = TreeNode.constructTree(List.of(4, 1, 2));
        assert isSubTree(root, subroot) : "Wrong result";
    }

    private static boolean isSubTree(TreeNode root, TreeNode subroot) {
        if (root == null && subroot == null) {
            return true;
        }
        if (root == null || subroot == null) {
            return false;
        }
        if (isSameTree(root, subroot)) {
            return true;
        }
        return isSubTree(root.left, subroot) || isSubTree(root.right, subroot);
    }

    private static boolean isSameTree(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null || node1.val != node2.val) {
            return false;
        }
        return isSameTree(node1.left, node2.left) && isSameTree(node1.right, node2.right);
    }
}
