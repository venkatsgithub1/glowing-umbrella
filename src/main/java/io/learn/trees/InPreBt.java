package io.learn.trees;

import java.util.HashMap;
import java.util.Map;

public class InPreBt {
    static int preorderIdx = 0;

    public static void main(String[] args) {
        TreeNode root = buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        assert root != null && root.val == 3 && root.left.val == 9 && root.right.val == 20 : "Wrong result";
    }

    // 3 9 20 15 7
    // 9 3 15 20 7
    private static TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return build(preorder, inorderMap, 0, inorder.length - 1);
    }

    private static TreeNode build(int[] preorder, Map<Integer, Integer> inorderMap, int ioStart, int ioEnd) {
        if (ioStart > ioEnd) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preorderIdx++]);
        if (ioStart == ioEnd) {
            return root;
        }
        int inorderIdx = inorderMap.get(root.val);
        root.left = build(preorder, inorderMap, ioStart, inorderIdx - 1);
        root.right = build(preorder, inorderMap, inorderIdx + 1, ioEnd);
        return root;
    }
}
