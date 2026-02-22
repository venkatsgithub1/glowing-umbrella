package io.learn.trees;

import java.util.List;

public class DiameterOfBinaryTree {
    private static int diameter;

    public static void main(String[] args) {
        TreeNode root = TreeNode.constructTree(List.of(1, 2, 3, 4, 5, -1, -1));
        assert diameterHelper(root) == 3 : "Wrong result";
    }

    private static int diameterHelper(TreeNode root) {
        calibrateDiameter(root);
        return diameter;
    }

    private static int calibrateDiameter(TreeNode root) {
        if (root != null) {
            int leftHeight = calibrateDiameter(root.left);
            int rightHeight = calibrateDiameter(root.right);
            diameter = Integer.max(diameter, leftHeight + rightHeight);
            return 1 + Integer.max(leftHeight, rightHeight);
        }
        return 0;
    }
}
