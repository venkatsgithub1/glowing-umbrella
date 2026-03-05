package io.learn.trees;

import java.util.List;

public class IsValidBST {
    public static void main(String[] args) {
        TreeNode root = TreeNode.constructTree(List.of(2, 1, 3));
        assert isValidBST(root) : "Wrong result";
    }

    private static boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    private static boolean isValidBST(TreeNode root, Integer left, Integer right) {
        if (root != null) {
            if ((left != null && root.val <= left) && (right != null && root.val >= right)) {
                return false;
            }
        }
        return true;
    }
}
