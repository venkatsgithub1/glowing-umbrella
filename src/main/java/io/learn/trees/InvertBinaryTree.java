package io.learn.trees;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class InvertBinaryTree {
    public static void main(String[] args) {
        TreeNode root = TreeNode.constructTree(List.of(4, 2, 7, 1, 3, 6, 9));
        assert root.toString().equals("4 \n2 7 \n1 3 6 9 \n"): "Wrong input tree";
        invertTree(root);
        assert root.toString().equals("4 \n7 2 \n9 6 3 1 \n") : "Wrong result";
    }

    private static TreeNode invertTree(TreeNode root) {
        if (root == null || root.left == null && root.right == null) {
            return root;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            TreeNode temp = current.right;
            current.right = current.left;
            current.left = temp;
            if (current.right != null) {
                queue.add(current.right);
            }
            if (current.left != null) {
                queue.add(current.left);
            }
        }
        return root;
    }
}
