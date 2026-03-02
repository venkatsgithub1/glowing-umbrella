package io.learn.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {
    public static void main(String[] args) {
        TreeNode root = TreeNode.constructTree(List.of(3, 9, 20, -1, -1, 15, 7));
        List<List<Integer>> result = traverse(root);
        assert result.get(0).get(0) == 3 : "Wrong result";
        assert result.get(result.size() - 1).get(result.get(result.size() - 1).size() - 1) == 7 : "Wrong result";
    }

    private static List<List<Integer>> traverse(TreeNode root) {
        if (root == null) {
            return List.of();
        }
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            result.add(temp);
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                temp.add(current.val);
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
