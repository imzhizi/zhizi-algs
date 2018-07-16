package com.imzhizi.algs.leetcode;

import java.util.HashMap;

/**
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 * You may assume that the array is non-empty and the majority element always exist in the array.
 *
 * Example 1:
 * Input: [3,2,3]
 * Output: 3
 * created by zhizi on 2018/7/13
 */
public class A169_MajorityElement {
    /**
     * 题目分析：
     * 看到题目，按照最暴力的解法，可以使用 HashMap 统计每个数字出现的次数，哪一个出现次数超过 2/n
     *
     * 运行时长：18ms
     *
     * 总结：
     * 为了维持一个HashMap的成本太高了，一看就需要优化
     */
    static int majorityElement(int[] nums) {
        HashMap<Integer,Integer> map=new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i]))
                map.put(nums[i],1);
            else map.put(nums[i],map.get(nums[i])+1);
            if(map.get(nums[i])>nums.length/2)
                return nums[i];
        }
        return 0;
    }

    /**
     * 题目分析：
     * 学习到的一种非常好的做法，值得反复回味
     * 为什么会可行呢，举个例子 1 2 1 3 1 4 1，由于大量元素总是比一半还多，所以
     *
     * 运行时长：4ms
     *
     * 总结：
     * 无
     */
    public static int majorityElement2(int[] nums) {
        int majority =nums[0];
        int count=0;
        for (int i = 0; i < nums.length; i++) {
            if (majority==nums[i])count++;
            else count--;

            if(count==0){
                majority=nums[i];
                count++;
            }
        }
        return 0;
    }
}
