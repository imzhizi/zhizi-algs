package com.imzhizi.algs.leetcode;

/**
 * Given an integer (signed 32 bits), write a function to check whether it is a power of 4.
 *
 * Example 1:
 * Input: 16
 * Output: true
 *
 * created by zhizi on 2018-12-26
 */
public class A342_PowerOfFour {
    /**
     * 题目分析：
     *
     *
     * 运行时长：3ms / 6%
     *
     * 总结：
     *
     */
    static boolean isPowerOfFour0(int num) {
        char[] chars=Integer.toBinaryString(num).toCharArray();
        if (chars[0]!='1')return false;
        if ((chars.length-1)%2!=0)return false;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i]!='0') return false;
        }
        return true;
    }

    static boolean isPowerOfFour1(int num) {
        String nums = Integer.toBinaryString(num);
        if ((nums.lastIndexOf("1") != 0)) return false;
        if (nums.length() % 2 != 1) return false;
        return true;
    }
}
