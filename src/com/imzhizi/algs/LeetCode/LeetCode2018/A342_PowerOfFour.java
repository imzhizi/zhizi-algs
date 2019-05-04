package com.imzhizi.algs.LeetCode.LeetCode2018;

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
     * 这个比计算 3 的幂有想法，只要是 2 的幂，都是 1 开始，后面全是 0 的二进制
     * 如果是 4 的幂，只要0的个数是2的倍数即可，所以产生了两种写法
     *
     * 运行时长：3ms / 6%
     *
     * 总结：
     * 但没想到效率都很低，可能要使用到位运算才能降低时间复杂度。
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
