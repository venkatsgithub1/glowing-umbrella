package io.learn.trees;

import java.util.List;

public class BinaryTreeMaxPathSum {
    private static int globalMax = Integer.MIN_VALUE;

    public static void main(String[] args) {
        TreeNode root = TreeNode.constructTree(List.of(5, 10, -1, 20, 30));
        maxPathSum(root);
        assert globalMax == 60 : "Wrong result";
    }

    private static int maxPathSum(TreeNode root) {
        if (root != null) {
            int leftSum = Integer.max(maxPathSum(root.left), 0);
            int rightSum = Integer.max(maxPathSum(root.right), 0);
            int cursum = root.val + leftSum + rightSum;
            globalMax = Integer.max(globalMax, cursum);
            return root.val + Integer.max(leftSum, rightSum);
        }
        return 0;
    }
}
