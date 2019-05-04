package com.imzhizi.algs.LeetCode.LeetCode2018;

/**
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 *
 * Example 1:
 * Input: [3,0,1]
 * Output: 2
 *
 * Example 2:
 * Input: [9,6,4,2,3,5,7,0,1]
 * Output: 8
 *
 * created by zhizi on 2018-11-29
 */
public class A268_MissingNumber {
    /**
     * 题目分析：
     * 之前一直在想的位运算又发挥了用场, 包含的数字会和下标一同消去, 缺少的数字就是没有消去的下标
     *
     * 运行时长：0ms / 100%
     *
     * 总结：
     * 总长度是比事实上的个数少一位, 所以需要再乘以应当的长度减一(也就是 length )
     */
    static int missingNumber(int[] nums) {
        int num = 0;
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            num = num ^ i ^ nums[i];
        }
        return num ^ length;
    }

    public static void main(String[] args) {
        System.out.println(missingNumber(new int[]{0,1,2,3,4,5,6,7,9}));
    }
}
