package io.learn.trees;

import java.util.List;

public class MaxDepth {
    public static void main(String[] args) {
        TreeNode root = TreeNode.constructTree(List.of(3, 9, 20, -1, -1, 15, 7));
        assert maxDepth(root) == 3 : "Incorrect answer";
    }

    private static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return Integer.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
