package io.learn.trees;

import java.util.List;

public class BalancedBinaryTree {
    private static boolean balanced = true;

    public static void main(String[] args) {
        TreeNode root = TreeNode.constructTree(List.of(3, 9, 20, -1, -1, 15, 7));
        assert isBalanced(root) : "Incorrect result";
        TreeNode root2 = TreeNode.constructTree(List.of(1, 2, 2, 3, 3, -1, -1, 4, 4));
        assert !isBalanced(root2) : "Incorrect result";
    }

    private static boolean isBalanced(TreeNode root) {
        height(root);
        return balanced;
    }

    private static int height(TreeNode root) {
        if (root != null) {
            int leftHeight = height(root.left);
            int rightHeight = height(root.right);
            if (!balanced || Math.abs(leftHeight - rightHeight) > 1) {
                balanced = false;
                return -1;
            }
            return 1 + Math.max(leftHeight, rightHeight);
        }
        return 0;
    }
}
