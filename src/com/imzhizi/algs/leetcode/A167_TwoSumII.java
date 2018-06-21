package com.imzhizi.algs.leetcode;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * Example:
 * Given nums = [2, 7, 11, 15], target = 9,
 * Because nums[0] + nums[1] = 2 + 7 = 9, return [0, 1].
 * created by zhizi on 2018/6/14
 */
public class A167_TwoSumII {
    /**
     * 题目分析：
     * 居然是第一道题的升级版，唯一的变化就是输入的数列变成有序的了
     * 通过两侧辗转向中间逼近，来找到target
     *
     * 运行时长：2 ms
     *
     * 总结：
     * 我在想是否可以通过这个去改造第一题呢，但我失败了，因为第一题要求返回的是数字原来的位置
     */
    public static int[] twoSum(int[] numbers, int target) {
        int right=numbers.length-1;
        int left=0;
        while (numbers[left]+numbers[right]!=target){
            if(numbers[left]+numbers[right]>target)right--;
            else left++;
        }
        return new int[]{left+1,right+1};
    }

    public static void main(String[] args) {
        int[] nums = twoSum(new int[]{-1, 0,}, -1);
        System.out.println(nums[0] + " " + nums[1]);
    }
}
