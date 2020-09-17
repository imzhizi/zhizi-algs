package com.imzhizi.algs.leetcode.curated;

import com.imzhizi.algs.common.TreeNode;
import org.junit.Test;

/**
 * created by zhizi
 * on 9/11/20 17:55
 */
public class BinaryTreeII {
    /**
     * [654. 最大二叉树 - 力扣（LeetCode）](https://leetcode-cn.com/problems/maximum-binary-tree/)
     */
    @Test
    public void constructMaximumBinaryTree() {

    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTree(nums, 0, nums.length - 1);
    }

    public TreeNode constructMaximumBinaryTree(int[] nums, int start, int end) {
        if (start == end) {
            return new TreeNode(nums[start]);
        }
        if (start > end) {
            return null;
        }
        int mid = findMax(nums, start, end);
        TreeNode node = new TreeNode(nums[mid]);
        node.left = constructMaximumBinaryTree(nums, start, mid - 1);
        node.right = constructMaximumBinaryTree(nums, mid + 1, end);
        return node;
    }

    public int findMax(int[] nums, int start, int end) {
        int max = start;
        for (int i = start; i <= end; i++) {
            if (nums[i] > nums[max]) {
                max = i;
            }
        }
        return max;
    }

    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        return Math.max(dfs(root), max);
    }

    /**
     * [124. 二叉树中的最大路径和 - 力扣（LeetCode）](https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/)
     */
    public int dfs(TreeNode root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }

        int left = dfs(root.left);
        int right = dfs(root.right);
        int val = Math.max(left, right);
        // 处理一下不联络路径
        max = Math.max(max, val);

        if (left > 0 && right > 0) {
            max = Math.max(max, left + root.val + right);
        }

        if (val > 0) {
            return root.val + val;
        } else {
            return root.val;
        }
    }
}
