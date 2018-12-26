package com.imzhizi.algs.leetcode;

/**
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 *
 * Example:
 * Input: [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 *
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 *
 * created by zhizi on 2018/11/29
 */
public class A283_MoveZeroes {
    /**
     * 题目分析：
     * 把数组中的 0 都移动到数组的最末尾, 最容易想到的应该是遍历, 如果是 0 则跟末尾的数字交换
     * 尝试之后就发现题目要求数组中原来的数字的位置不变, 那要怎么做, 插入排序? 冒泡排序? 都要很久
     * 后来想到既然是移动 0, 那其实可以通过压缩的形式, 如果 i 位置是0, 那么就把 j 位置上的数字放在 i 上
     * 最后剩下的位置全都填上 0, 达到 O(n) 的时间复杂度
     *
     * 运行时长：2ms / 50%
     *
     * 总结：无
     */
    static void moveZeroes(int[] nums) {
        int fast = 0;
        int slow = 0;
        for (fast = 0; fast < nums.length; fast++) {
            if(nums[fast]!=0) {
                nums[slow++]=nums[fast];
            }
        }

        for (int i = slow; i < nums.length; i++) {
            nums[i]=0;
        }
    }
}
