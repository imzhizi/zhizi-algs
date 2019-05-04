package com.imzhizi.algs.LeetCode.LeetCode2018;

import java.util.HashMap;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * Eg.
 * Given nums = [2, 7, 11, 15], target = 9,
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 */
public class A1_twoSum {
    /**
     * 题目分析：
     * 很容易理解的一道题，在一个数组里寻找相加和为目标数字的两个数，并返回由其脚标组成的数组
     *
     * 时长：24ms / 27%
     *
     * 总结：
     * 将函数写成静态也是可以正常执行的，暂时还没发现效率上的影响，之后的题目为了方便都会写成静态的。
     */
    static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target)
                    return new int[]{i, j};
            }
        }
        return null;
    }

    /**
     * 题目分析：
     * 使用了更高级的数据结构来处理题目，哈希的优势得到展现
     *
     * 运行时长：4ms / 97.82%
     *
     * 总结：
     * 要善用高级数据结构
     */
    static int[] twoSum0(int[] nums, int target) {
        HashMap<Integer,Integer> map=new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int temp=target-nums[i];
            if (map.containsKey(temp)) return new int[]{map.get(temp),i};
            map.put(nums[i],i);
        }

        return null;
    }

        /**
         * 题目分析：
         * 原本设想了一个两端逐渐向中间逼近的算法，但后来发现数组居然不是有序的，于是失败
         *
         * 运行时长：失败
         *
         * 总结：
         *
         */
    static int[] twoSum2(int[] nums, int target) {
        int head=0;
        int end=nums.length-1;

        while (head<end){
            if (nums[head]+nums[end]==target) break;
            else if (nums[head]+nums[end]>target) end--;
            else head++;
        }
        return new int[]{head,end};
    }


    public static void main(String[] args) {
        int[] nums=twoSum0(new int[]{3,3},6);
        System.out.println(nums[0] + " " + nums[1]);
    }
}
