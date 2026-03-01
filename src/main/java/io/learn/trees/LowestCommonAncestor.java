package io.learn.trees;

import java.util.List;

public class LowestCommonAncestor {
    public static void main(String[] args) {
        TreeNode root = TreeNode.constructTree(List.of(2, 1));
        assert lowestCommonAncestor(root, root, root.left) == root : "Wrong result";
    }

    private static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // base case
        if (root == null || root == p || root == q) {
            // this means, either p and q is not found at all, case 1
            // root = p, this means that ancestor is found (itself), we don't need to do down
            // and look for q, as we know from constraints that p and q are present, which means
            // if for example, the path didn't have it would've returned null, if the alternate
            // path returned null and this returned ancestor, ancestor is the answer
            // on the contrary, if other path alternate to this, has returned a node,
            // that must be the other node say q or vice versa.
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // left and right are not null, that means p and q are found on either side, which means
        // they are connected by this root as lca.
        if (left != null && right != null) {
            return root;
        }

        return left == null ? right : left;
    }
}
