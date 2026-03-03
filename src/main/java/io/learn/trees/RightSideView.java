package io.learn.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RightSideView {
    public static void main(String[] args) {
        TreeNode root = TreeNode.constructTree(List.of(1, 2, -1, -1, 3, -1, 5, -1, 4));
        List<Integer> result = getRightSideView(root);
        assert result.get(0) == 1 && result.get(result.size() - 1) == 4 : "Wrong result";
    }

    private static List<Integer> getRightSideView(TreeNode root) {
        if (root == null) {
            return List.of();
        }
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                if (i == size - 1) {
                    result.add(current.val);
                }
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
        }
        return result;
    }
}
