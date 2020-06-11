package com.imzhizi.algs.leetcode.n18;

import com.imzhizi.algs.Common.TreeNode;

/**
 * Given a binary tree, determine if it is height-balanced.
 * For this problem, a height-balanced binary tree is defined as:
 * a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 * created by zhizi on 2018/5/28
 */
public class A110_BalancedBinaryTree {
    /**
     * 题目分析：
     * 平衡二叉树的判定，假如你了解什么是平衡二叉树的话，题目很容易理解。
     * 但并不好写，至少我写了很久，问题主要出在一个矛盾的地方，返回值只能有一个，但是既要计算树的深度，又要判断树是否不平衡了
     * 最终思路是选择一个特殊值作为标志位，一旦出现不平衡就直接传递不平衡标志，如果平衡的话就继续传递深度
     *
     * 运行时长：2 ms
     *
     * 总结：
     * 这种基本概念的实现，如果不熟悉的话，并不容易实现，但面试可能还挺容易出现的。
     */
    public static boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return height(root)!=-1;
    }

    public static int height(TreeNode root) {
        if (root == null) return 0;
        int l = height(root.left);
        int r = height(root.right);
        if (l == -1 || r == -1||Math.abs(l - r) > 1) return -1;
        return Math.max(l, r) + 1;
    }


    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(3);
        TreeNode t2 = new TreeNode(9);
        TreeNode t3 = new TreeNode(20);
        TreeNode t4 = new TreeNode(5);
        TreeNode t5 = new TreeNode(7);
        TreeNode t6 = new TreeNode(7);
        TreeNode t7 = new TreeNode(7);

        t1.left = t2;
        System.out.println(isBalanced(t1));
        t2.left = t3;
        System.out.println(isBalanced(t1));
        t3.left = t4;
        System.out.println(isBalanced(t1));
        t1.right = t5;
        t2.right = t6;
        t3.right = t7;
        System.out.println(isBalanced(t1));

    }
}

