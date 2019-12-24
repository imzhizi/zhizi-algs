package com.imzhizi.algs.LeetCode2018;

/**
 * Write a program to check whether a given number is an ugly number.
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 *
 * Example 1:
 * Input: 6
 * Output: true
 * Explanation: 6 = 2 × 3
 *
 * created by zhizi on 11/21/18
 */
class A263_UglyNumber {
    /**
     * 题目分析：
     * 每什么好分析的，就循环
     *
     * 运行时长：1ms /100%
     *
     * 总结：
     *
     */
    public static boolean isUgly(int num) {
        if (num <= 0)
            return false;
        else {
            while(num%2==0)
                num/=2;
            while(num%3==0)
                num/=3;
            while(num%5==0)
                num/=5;
            if (num==1) return true;
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(isUgly(45));
        System.out.println(isUgly(6));
        System.out.println(isUgly(17));
    }
}
