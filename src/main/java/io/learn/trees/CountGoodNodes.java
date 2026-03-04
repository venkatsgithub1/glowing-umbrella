package io.learn.trees;

import java.util.List;

public class CountGoodNodes {
    public static void main(String[] args) {
        TreeNode root = TreeNode.constructTree(List.of(3, 1, -1, -1, 4, 4, -1, 1, 5));
        assert findGoodNodes(root, Integer.MIN_VALUE) == 4 : "Wrong result";
    }

    private static int findGoodNodes(TreeNode root, int maxNum) {
        if (root != null) {
            int count = 0;
            if (maxNum == Integer.MIN_VALUE || root.val >= maxNum) {
                maxNum = root.val;
                count++;
            }
            int rightAns = 0;
            int leftAns = 0;
            if (root.left != null) {
                leftAns += findGoodNodes(root.left, maxNum);
            }
            if (root.right != null) {
                rightAns += findGoodNodes(root.right, maxNum);
            }
            count += leftAns + rightAns;
            return count;
        }
        return 0;
    }
}
