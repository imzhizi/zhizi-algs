package com.imzhizi.algs.leetcode_2018;

import com.imzhizi.algs.TreeNode;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 * created by zhizi on 2018/5/8
 */
public class A101_SymmetricTree {
    /**
     * 题目分析：
     * 跟上一题类似的做法，为了达到递归，我甚至新写了一个函数用来递归
     * 可能层次遍历会有更好的效果，因为运行时长的distribution为48%，但暂时没心情了解了
     * 运行时长：16 ms
     *
     * 总结：无
     */
    public static boolean isSymmetric(TreeNode root) {
        if(root==null) return true;
        else return isSymmetric(root.left,root.right);
    }

    public static boolean isSymmetric(TreeNode left,TreeNode right) {
        if((left!=null&&right==null)||(left==null&&right!=null))return false;
        if(left==null&&right==null)return true;
        if(left.val!=right.val)return false;
        return isSymmetric(left.left,right.right)&&isSymmetric(left.right,right.left);
    }

    public static void main(String[] args) {
        TreeNode t1=new TreeNode(1);
        TreeNode t2=new TreeNode(1);
        TreeNode t3=new TreeNode(1);
        TreeNode t4=new TreeNode(2);
        TreeNode t5=new TreeNode(2);

        t1.left=t2;
        t1.right = t3;
        t2.right = t4;
        t3.right = t5;

        System.out.println(isSymmetric(t1));
    }
}
