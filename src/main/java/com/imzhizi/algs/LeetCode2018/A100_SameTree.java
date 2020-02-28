package com.imzhizi.algs.LeetCode2018;

import com.imzhizi.algs.base.TreeNode;

/**
 * Given two binary trees, write a function to check if they are the same or not.
 * Two binary trees are considered the same if they are structurally identical and the nodes have the same value.
 * <p>
 * Example 1:
 * Input:     1         1
 *           / \       / \
 *          2   3     2   3
 *        [1,2,3],   [1,2,3]
 * Output: true
 * <p>
 * Example 2:
 * Input:     1         1
 *           /           \
 *          2             2
 *         [1,2],     [1,null,2]
 * Output: false
 * created by zhizi on 2018/5/7
 */
public class A100_SameTree {
    /**
     * 题目分析：
     * 比较两个树是否完全相同，显然要用递归，否则就是复杂的层次遍历了
     * 假如能够一次遍历，中间任何一处不同都直接结束遍历应该是效率比较高的
     * 于是就有了下面的代码
     * 运行时长：5 ms
     *
     * 总结：无，一次草率的实现
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if((p!=null&&q==null)||(p==null&&q!=null)) return false;
        else if(p==null&&q==null)return true;
        else if(p.val!=q.val) return false;
        else return isSameTree(p.left,q.left)&&isSameTree(p.right,q.right);
    }

    public static void main(String[] args) {
        TreeNode t1=new TreeNode(1);
        TreeNode t2=new TreeNode(1);
        TreeNode t3=new TreeNode(2);
        TreeNode t4=new TreeNode(2);

        t1.left=t3;
        t1.right = t4;
        t2.right = t4;
        t2.left = t3;

        System.out.println(isSameTree(t1,t2));
    }
}
