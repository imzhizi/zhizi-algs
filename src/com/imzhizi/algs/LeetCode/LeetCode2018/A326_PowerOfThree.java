package com.imzhizi.algs.LeetCode.LeetCode2018;

/**
 * Given an integer, write a function to determine if it is a power of three.
 *
 * Example 1:
 *
 * Input: 27
 * Output: true
 * Example 2:
 *
 * Input: 0
 * Output: false
 * created by zhizi on 2018-12-26
 */
public class A326_PowerOfThree {
    /**
     * 题目分析：
     * 不能被 2、5、7 整除，那 11…… 想不出来好办法，先循环吧
     *
     * 运行时长：13ms / 92%
     *
     * 总结：
     * 就朴实地循环一下，时间复杂度还可以接受
     */
    static boolean isPowerOfThree1(int n) {
        while(n>3){
            if (n%3!=0) return false;
            else n=n/3;
        }
        if (n==1) return true;
        else return false;
    }

    static boolean isPowerOfThree2(int n) {
        // 想尝试但没想到好办法
        return false;
    }

        public static void main(String[] args) {
        System.out.println(isPowerOfThree1(45));
    }
}
