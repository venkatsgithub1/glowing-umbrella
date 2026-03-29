package io.learn.trees;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SerdeBT {
    private static int preorderIdx = 0;

    public static void main(String[] args) {
        TreeNode root = TreeNode.constructTree(List.of(1, 2, 3, -1, -1, 4, 5));
        String serRes = serialize(root);
        TreeNode desRes = deserialize(serRes);
        assert desRes.val == root.val && desRes.left.val == root.left.val && desRes.right.val == root.right.val : "Wrong result";
    }

    public static String serialize(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        preorder(root, builder);
        return builder.toString();
    }

    public static TreeNode deserialize(String data) {
        List<String> preorderList = Arrays.stream(data.split(",")).filter(x -> !x.equals("")).collect(Collectors.toList());
        if (preorderList.isEmpty()) {
            return null;
        }
        preorderIdx = 0;
        return buildTree(preorderList);
    }

    private static TreeNode buildTree(List<String> preorderList) {
        if (preorderIdx >= preorderList.size()) {
            return null;
        }
        String item = preorderList.get(preorderIdx++);
        if (item.equals("X")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(item));
        root.left = buildTree(preorderList);
        root.right = buildTree(preorderList);
        return root;
    }

    private static void preorder(TreeNode root, StringBuilder builder) {
        if (root == null) {
            builder.append("X,");
            return;
        }
        builder.append(root.val + ",");
        preorder(root.left, builder);
        preorder(root.right, builder);
    }
}
