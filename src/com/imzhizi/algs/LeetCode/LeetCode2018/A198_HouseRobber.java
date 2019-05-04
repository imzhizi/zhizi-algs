package com.imzhizi.algs.LeetCode.LeetCode2018;

/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 *
 * Example 1:
 *  Input: [1,2,3,1]
 *  Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 *              Total amount you can rob = 1 + 3 = 4.
 * created by zhizi on 7/30/18
 */
public class A198_HouseRobber {
    /**
     * 题目分析：
     * 很烦的一道题，想到的一个方法是搞一个结果树，从中选出一个值最大的分支，结果内存超了
     * 想了办法进行优化，不断传递值，但超时了，可能是用了递归的原因
     *
     * 运行时长：超时
     *
     * 总结：只能求助于讨论区了
     */
    static int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        return Math.max(getRob(nums, 0, 0), getRob(nums, 1, 0));
    }

    static int getRob(int[] nums, int i, int sum) {
        if (i < nums.length - 3) {
            sum += nums[i];
            return Math.max(getRob(nums, i + 2, sum), getRob(nums, i + 3, sum));
        } else if (i > nums.length - 3)
            return sum + nums[i];
        else
            return sum + nums[i] + nums[i + 2];
    }

    /**
     * 题目分析：
     * 原来要使用动态规划，思路也不复杂，但就是一直没想出来，需要积累相关经验
     *
     * 运行时长：3 ms
     *
     * 总结：
     * 多回味，总结思路吧
     */
    static int rob2(int[] nums) {
        if(nums.length==0)
            return 0;
        if(nums.length==1)
            return nums[0];
        int dp[]=new int[nums.length];
        dp[0]=nums[0];
        dp[1]=Math.max(nums[1],nums[0]);
        for(int i=2;i<nums.length;i++)
            dp[i]=Math.max((dp[i-2]+nums[i]),dp[i-1]);
        return dp[nums.length-1];
    }

    public static void main(String[] args) {
        System.out.println(rob2(new int[]{8, 7, 9, 11}));
    }
}
