package io.learn.trees;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this(val);
        this.left = left;
        this.right = right;
    }

    public static TreeNode constructTree(List<Integer> nodeVals) {
        TreeNode root = null;
        if (!nodeVals.isEmpty()) {
            root = new TreeNode(nodeVals.get(0));
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        for (int i = 1; i < nodeVals.size() && !queue.isEmpty(); i += 2) {
            TreeNode current = queue.poll();
            current.left = new TreeNode(nodeVals.get(i));
            if (i + 1 < nodeVals.size()) {
                current.right = new TreeNode(nodeVals.get(i + 1));
            }
            if (current.left.val != -1) {
                queue.add(current.left);
            }
            if (current.right == null || current.right.val != -1) {
                queue.add(current.right);
            }
        }
        return root;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(this);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                if (current != null) {
                    builder.append(current.val).append(" ");
                    if (current.left != null) {
                        queue.add(current.left);
                    }
                    if (current.right != null) {
                        queue.add(current.right);
                    }
                }
            }
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }
}
