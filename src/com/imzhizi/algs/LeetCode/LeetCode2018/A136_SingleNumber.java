package com.imzhizi.algs.LeetCode.LeetCode2018;

import java.util.HashSet;

/**
 * Given a non-empty array of integers, every element appears twice except for one. Find that single one.
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * created by zhizi on 2018/6/11
 */
public class A136_SingleNumber {
    /**
     * 题目分析：
     * 感觉上利用HashSet应该挺容易的，就实现了一个，没什么难度，但效果很差，改天再改进一下
     *
     * 运行时长：15 ms
     *
     * 总结：无
     */
    int singleNumber(int[] nums) {
        HashSet<Integer> set=new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if(set.contains(nums[i])){
                set.remove(nums[i]);
            }else {
                set.add(nums[i]);
            }
        }
        int j=0;
        for(int i:set){
            j=i;
        }
        return j;
    }

    /**
     * 题目分析：
     * 又学到一个高级用法，异或！，如果连续异或一个数字两次，就会像没异或一样
     * 让题目在O(n)内就解决问题了，简单的三行代码，是分强大
     *
     * 运行时长：1ms
     *
     * 总结：高级，高级
     *
     */
    int singleNumber2(int[] nums) {
        int ans=0;
        for (int i = 0; i < nums.length; i++)
            ans=ans^nums[i];
        return ans;
    }
}
