package com.imzhizi.algs.LeetCode.LeetCode2018;

import com.imzhizi.algs.TreeNode;

/**
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
 * Note: A leaf is a node with no children.
 * created by zhizi on 2018/5/30
 */
public class A112_PathSum {
    /**
     * 题目分析：
     * 这道题事实上在寻找一个路径，这个路径和等于给定值，依然是递归题
     * 思路嘛，还是要判定是否是叶子，但比上一道题容易处理，因为通过自身即可验证是否已经找到（root.val == sum）
     * 关键思路在于一旦出现true，那么就要把true一路传上去
     *
     * 运行时长：1 ms
     *
     * 总结：
     * 很快写出来的一个很优雅的递归，很满意。
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left==null&&root.right==null && root.val == sum) return true;
        else return hasPathSum(root.left,sum-root.val)||hasPathSum(root.right,sum-root.val);
    }
}
