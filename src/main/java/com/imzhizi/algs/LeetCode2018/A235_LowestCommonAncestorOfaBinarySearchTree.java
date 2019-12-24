package com.imzhizi.algs.LeetCode2018;

import com.imzhizi.algs.TreeNode;

/**
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 *
 * Given binary search tree:  root = [6,2,8,0,4,7,9,null,null,3,5]
 *
 *         _______6______
 *        /              \
 *     ___2__          ___8__
 *    /      \        /      \
 *    0      _4       7       9
 *          /  \
 *          3   5
 * created by zhizi on 11/20/18
 */
public class A235_LowestCommonAncestorOfaBinarySearchTree {
    /**
     * 题目分析：
     * 最近公共祖先（LCA）是经典算法题，其实是三种情况，看小的是否比 root 还大，大的是否比 root 还小，这两种情况都要向下递归
     * 若两种情况都不满足，则说明 root 位于中间（也可能其中一个等于 root），则 root 必然是最近公共祖先
     *
     * 运行时长：1 - 47% / 8ms
     * 运行时长：2 - 100% / 4ms
     *
     * 总结：
     * 但我的写法比较丑陋，代码读起来可能也相对费解，后来学习了一下简洁写法，通过使用 Math 的 max min 函数，提到代码可读性
     */
    static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val>q.val){
            TreeNode tmp = p;
            p = q;
            q = tmp;
        }
        if (p.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else if (p.val < root.val) {
            if (q.val < root.val) return lowestCommonAncestor(root.left, p, q);
            else if (q.val == root.val) return q;
            else return root;
        } else {
            return p;
        }
    }

    static TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val > Math.max(p.val, q.val)) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (root.val < Math.min(p.val, q.val)) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }
    }

        public static void main(String[] args) {
        TreeNode t1 = new TreeNode(2);
        TreeNode t2 = new TreeNode(9);
        TreeNode t3 = new TreeNode(3);
        t1.left = t2;
        t1.right = t3;

        System.out.println(lowestCommonAncestor(t1, t1, t3).val);
    }
}
