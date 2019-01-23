package com.imzhizi.algs.leetcode_2018;

import com.imzhizi.algs.TreeNode;

/**
 * Given a binary tree, find its minimum depth.
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 * Note: A leaf is a node with no children.
 * created by zhizi on 2018/5/29
 */
public class A111_MinimumDepthOfBinaryTree {
    /**
     * 题目分析：
     * 求一个二叉树的最小深度，题中定义说到叶子结点的最小距离才算是最小深度
     * 显然又要用递归，问题就在于怎么把递归用的好看
     * 我用一个愚蠢的做法实现了一个优雅的代码
     *     这个深度计算中，必须保证深度值是来自一个叶子结点，而不是一个空着的左子女或右子女
     *     所以一旦遇到左（右）子女为空的时候，我就把它对应的值设为Integer.MAX_VALUE
     *     这样很有效，可以轻松进行递归，过滤掉无效值
     * 但我没解决好root为null的问题，所以只好建了一个helper方法
     *
     * 运行时长：1 ms
     *
     * 总结：
     * helper方法就helper方法，其实也还行
     */
    public static int minDepth(TreeNode root) {
        if(root==null)return 0;
        else return helper(root);
    }

    public static int helper(TreeNode root) {
        if (root == null) return Integer.MAX_VALUE;
        if (root.left == null && root.right == null) return 1;
        return Math.min(helper(root.left), helper(root.right)) + 1;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(3);
        TreeNode t2 = new TreeNode(9);
        TreeNode t3 = new TreeNode(20);
        TreeNode t4 = new TreeNode(5);

        t1.left = t2;
        t1.right = t3;
        t3.left = t4;

        System.out.println(minDepth(t1));
    }
}
