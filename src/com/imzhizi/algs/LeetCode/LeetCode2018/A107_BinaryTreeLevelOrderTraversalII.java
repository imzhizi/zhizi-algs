package com.imzhizi.algs.LeetCode.LeetCode2018;

import com.imzhizi.algs.LeetCode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values.
 * (ie, from left to right, level by level from leaf to root).
 * created by zhizi on 2018/5/10
 */
public class A107_BinaryTreeLevelOrderTraversalII {
    /**
     * 题目分析：层次遍历，经典的做法就是用一个队来进行结点输入输出的控制
     *          看到有用递归实现的更好的方法，之后再去研究
     * 运行时长：3 ms
     * 总结：连同之前的树的遍历、树的深度和今天的树的层次遍历，都是基本操作
     */
    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> levelList = new ArrayList<>();
        if (root == null) return levelList;

        List<TreeNode> treeNodes=new ArrayList<>();
        treeNodes.add(root);

        while(!treeNodes.isEmpty()){
            List<Integer> integers=new ArrayList<>();
            int size=treeNodes.size();
            for (int i = 0; i < size; i++) {
                TreeNode temp=treeNodes.remove(0);
                integers.add(temp.val);
                if(temp.left!=null) treeNodes.add(temp.left);
                if(temp.right!=null) treeNodes.add(temp.right);
            }
            levelList.add(0,integers);
        }
        return levelList;
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

        System.out.println(levelOrderBottom(null).toString());
    }
}
