package com.imzhizi.algs.LeetCode2018;

import com.imzhizi.algs.base.TreeNode;

/**
 * Invert a binary tree.
 *
 * Example:
 *
 * Input:
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 *
 * Output:
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 * created by zhizi on 11/15/18
 */
public class A226_InvertBinaryTree {
    /**
     * 题目分析：
     * 递归
     *
     * 运行时长：100% / 0ms
     *
     * 总结：
     * 无
     */
    TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        if (root.left != null)
            root.left = invertTree(root.left);
        if (root.right != null)
            root.right = invertTree(root.right);
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        return root;
    }
}
