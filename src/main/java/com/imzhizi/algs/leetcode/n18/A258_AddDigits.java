package com.imzhizi.algs.leetcode.n18;

/**
 * Write a program to check whether a given number is an ugly number.
 *
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 *
 * Example 1:
 * Input: 6
 * Output: true
 * Explanation: 6 = 2 × 3
 * created by zhizi on 2018-11-23
 */
class A258_AddDigits {
    /**
     * 题目分析：
     * 每次计算出一个 sum 是一个循环，直到 sum 是个位数也就是大于9就循环
     * 内部假如 num 大于 0 就继续计算 sum，然后 num 除以 10 去掉个位数
     *
     * 运行时长：1ms /100%
     *
     * 总结：
     * 无
     */
    public int addDigits(int num) {
        int sum = num;
        while (sum > 9) {
            num = sum;
            sum = 0;
            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }
        }
        return sum;
    }
}
