package com.imzhizi.algs.leetcode.n18;

/**
 * Given an integer, write a function to determine if it is a power of two.
 *
 * Example 1:
 * Input: 1
 * Output: true
 * Explanation: 20 = 1
 *
 * Example 2:
 * Input: 16
 * Output: true
 * Explanation: 24 = 16
 *
 * Example 3:
 * Input: 218
 * Output: false
 * created by zhizi on 11/16/18
 */
public class A231_PowerOfTwo {
    /**
     * 题目分析：
     * 判断一个数字是否是2的幂，可以想到，分为循环部分和结束判断两部分，循环就是无限地除以2，除到什么时候结束呢？
     * 一个正常的 2 的幂可以除到 1，同时 1 也不再是2的倍数，所以这里为结束点
     *
     * 运行时长：100% / 1ms
     *
     * 总结：
     * 无
     */
    static boolean isPowerOfTwo(int n) {
        while (n != 0 && n % 2 == 0)
            n /= 2;

        if (n == 1) return true;
        else return false;
    }

    public static void main(String[] args) {
        System.out.println(isPowerOfTwo(16));
    }
}
