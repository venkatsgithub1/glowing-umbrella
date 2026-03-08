package io.learn.trees;


import java.util.List;

public class KthSmallestBST {
    private static int result = -1;
    private static int counter = 0;

    public static void main(String[] args) {
        TreeNode root = TreeNode.constructTree(List.of(3, 1, 4, -1, 2));
        assert kthSmallest(root, 1) == 1 : "Wrong result";
    }

    private static int kthSmallest(TreeNode root, int k) {
        inorder(root, k);
        return result;
    }

    private static void inorder(TreeNode root, int k) {
        if (root != null) {
            if (result == -1) {
                inorder(root.left, k);
                counter++;
            }
            if (counter == k) {
                result = root.val;
            }
            if (result == -1) {
                inorder(root.right, k);
            }
        }
    }
}
