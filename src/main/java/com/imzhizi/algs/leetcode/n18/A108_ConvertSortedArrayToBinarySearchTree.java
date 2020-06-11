package com.imzhizi.algs.leetcode.n18;

import com.imzhizi.algs.Common.TreeNode;

/**
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 * For this problem, a height-balanced binary tree is defined as a binary tree
 * in which the depth of the two subtrees of every node never differ by more than 1.
 * created by zhizi on 2018/5/11
 */
public class A108_ConvertSortedArrayToBinarySearchTree {
    /**
     * 题目分析：
     * 有序序列转化为平衡树，那么两侧的结点一定是相同或者仅相差1，所以只要依次取中间值，两侧重新递归即可
     * 结束体直接分了三种情况，单一结点、两个结点和三个结点
     * 运行时长：1 ms
     *
     * 总结：
     * 需要注意，每个结点都需要new出来，然后nums中数字不便移动，所以直接传输头尾坐标
     */
    public static TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length==0)return null;
        return toTree(nums, 0, nums.length - 1);
    }

    public static TreeNode toTree(int[] nums, int begin, int end) {
        TreeNode root=null;
        if (begin == end) {
            root= new TreeNode(nums[begin]);
        } else if (begin + 1 == end) {
            root = new TreeNode(nums[end]);
            root.left = new TreeNode(nums[begin]);
        } else if (begin + 2 == end) {
            int mid = (end + begin) / 2;
            root = new TreeNode(nums[mid]);
            root.left = new TreeNode(nums[begin]);
            root.right = new TreeNode(nums[end]);
        } else{
            int mid = (end + begin) / 2;
            root = new TreeNode(nums[mid]);
            root.left = toTree(nums, begin, mid - 1);
            root.right = toTree(nums, mid + 1, end);
        }
        return root;
    }

    public static void main(String[] args) {
        System.out.println(sortedArrayToBST(new int[]{0,1,2,3,4,5,6,7}));
    }
}
