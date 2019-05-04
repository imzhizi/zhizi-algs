package com.imzhizi.algs.LeetCode.LeetCode2018;

/**
 * Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 *
 * Example:
 *  Given nums = [1,1,2],
 *  Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
 *  It doesn't matter what you leave beyond the returned length.
 */
public class A26_RemoveDuplicates {
    /**
     * 题目分析：
     * 这道题核心思路是——有两个指针，一个是数组的遍历位置，一个是数组去重的位置
     *
     * 运行时长：9ms / 30%
     *
     * 总结：
     * 这种方法已经是O(n)的复杂度了，但运行时仍然很久
     */
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int index = 0;
        for (int loc = 1; loc < nums.length; loc++) {
            if (nums[loc] != nums[index]) {
                index++;
                nums[index] = nums[loc];
            }
        }
        return index + 1;
    }

    /**
     * 题目分析：
     * 其实思路上和第一种方法没区别，只是实现上有一些小的差异
     *
     * 运行时长：8ms / 60%
     *
     * 总结：
     * 说到底，还是自己操纵的越多，效率就越高，一个for循环，一个while循环，努努力也能有一点点差距
     */
    public static int removeDuplicates2(int[] nums) {
        int slow = 1;
        int fast = 1;
        while (fast < nums.length) {
            if (nums[fast] == nums[fast - 1]) {
                fast++;
            } else {
                nums[slow++] = nums[fast++];
            }
        }
        return slow;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        System.out.println(removeDuplicates(nums));
    }
}
