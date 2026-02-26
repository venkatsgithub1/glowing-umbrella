package io.learn.trees;

import java.util.List;

public class IsSameTree {
    public static void main(String[] args) {
        TreeNode root1 = TreeNode.constructTree(List.of(1, 2, 3));
        TreeNode root2 = TreeNode.constructTree(List.of(1, 2, 3));
        assert isSameTree(root1, root2) : "wrong result";
        root1 = TreeNode.constructTree(List.of(1, 2, 3));
        root2 = TreeNode.constructTree(List.of(1, 2, 4));
        assert !isSameTree(root1, root2) : "wrong result";
    }

    private static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
