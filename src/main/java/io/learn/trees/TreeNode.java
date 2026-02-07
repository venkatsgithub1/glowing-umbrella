package io.learn.trees;

import java.util.List;
import java.util.Stack;

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
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        for (int i = 1; i < nodeVals.size() && !stack.isEmpty(); i += 2) {
            TreeNode current = stack.pop();
            current.left = new TreeNode(nodeVals.get(i));
            if (i + 1 < nodeVals.size()) {
                current.right = new TreeNode(nodeVals.get(i + 1));
            }
            if (current.left.val != -1) {
                stack.add(current.left);
            }
            if (current.right == null || current.right.val != -1) {
                stack.add(current.right);
            }
        }
        return root;
    }

    @Override
    public String toString() {
        Stack<TreeNode> stack = new Stack<>();
        stack.add(this);
        while (!stack.isEmpty()) {
            int size = stack.size();
            for (int i = 0; i < size; i++) {
                TreeNode current = stack.pop();
                System.out.print(current.val+" ");
                if (current.left != null) {
                    stack.add(current.left);
                }
                if (current.right != null) {
                    stack.add(current.right);
                }
            }
            System.out.println();
        }
        return "";
    }
}
