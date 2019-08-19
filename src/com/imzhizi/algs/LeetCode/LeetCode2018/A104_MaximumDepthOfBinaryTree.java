package com.imzhizi.algs.LeetCode.LeetCode2018;

import com.imzhizi.algs.TreeNode;

/**
 * Given a binary tree, find its maximum depth.
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 * Note: A leaf is a node with no children.
 * created by zhizi on 2018/5/9
 */
public class A104_MaximumDepthOfBinaryTree {
    /**
     * 题目分析：
     * 求树的最大深度，不复杂，也很容易想出递归体
     * 运行时长：0 ms??
     *
     * 总结：
     * 似乎还是用例太少的缘故，运行时长居然是0ms？
     * 不过在这道题遇到一个别的问题，那就是如果我使用三目表达式就会超时，可见三目不是好东西，以后再也不用了
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1+Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(3);
        TreeNode t2 = new TreeNode(9);
        TreeNode t3 = new TreeNode(20);
        TreeNode t4 = new TreeNode(5);
        TreeNode t5 = new TreeNode(7);

        t1.left = t2;
        t1.right = t3;
        t3.left = t4;
        t3.right = t5;

        System.out.println(maxDepth(t1));
    }
}
