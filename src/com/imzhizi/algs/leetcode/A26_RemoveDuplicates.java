package com.imzhizi.algs.leetcode;

/**
 * Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 * <p>
 * Example 1:
 * Given nums = [1,1,2],
 * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
 * It doesn't matter what you leave beyond the returned length.
 */
public class A26_RemoveDuplicates {
    /**
     * 函数描述：
     * 如果不同，则放到自己的下一位，这道题抽象到最简之后的内核
     * 运行时长：14 ms
     *
     * 总结：无
     */
    public static int removeDuplicates(int[] nums) {
        int l = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[l]) {
                nums[++l] = nums[i];
            }
        }
        return l + 1;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        System.out.println(removeDuplicates(nums));
    }
}
