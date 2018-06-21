package com.imzhizi.algs.leetcode;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 1. twoSum
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * Eg.
 * Given nums = [2, 7, 11, 15], target = 9,
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 */
public class A1_twoSum {
    /**
     * 函数描述：
     * 无
     * 时长：40 ms
     *
     * 总结：
     * 将函数写成静态也是可以正常执行的，暂时还没发现效率上的影响，之后的题目为了方便都会写成静态的。
     */
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target)
                    return new int[]{i, j};
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums=twoSum(new int[]{3,2,4},6);
        System.out.println(nums[0] + " " + nums[1]);
    }
}
