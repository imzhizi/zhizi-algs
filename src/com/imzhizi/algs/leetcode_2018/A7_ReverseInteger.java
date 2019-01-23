package com.imzhizi.algs.leetcode_2018;

/**
 * Given a 32-bit signed integer, reverse digits of an integer.
 * Example
 * Input: 123
 * Output:  321
 * Input: -123
 * Output: -321
 * Input: 120
 * Output: 21
 */
public class A7_ReverseInteger {
    /**
     * 题目分析：
     * 使用StringBuilder进行翻转，因为翻转后可能会大于整形数字，因此使用Long.valueOf()来接收翻转后的数字
     *
     * 时长：34ms / 6%
     *
     * 总结：
     * 但这种方法往往效率很低，不建议使用
     */
    public static int reverse(int x) {
        long l = x;
        if (l > 0) {
            l = Long.valueOf(new StringBuilder(l + "").reverse().toString());
        } else {
            l = Long.valueOf(new StringBuilder(String.valueOf(l * -1)).reverse().toString()) * -1;
        }
        if (l > Integer.MAX_VALUE || l < Integer.MIN_VALUE) return 0;
        else return (int) l;
    }

    /**
     * 题目分析：
     * 仍然从字符串入手，但是放弃抽象程度过高的 StringBuilder，转而通过 char[] 来计算
     *
     * 运行时长：24ms / 23%
     *
     * 总结：无
     */
    public static int reverse1(int x) {
        long result = 0;

        if (x > 0) {
            char[] chars = (x + "").toCharArray();

            for (int i = 0; i < chars.length; i++)
                result += Math.pow(10, i) * (chars[i] - 48);
        }else {
            char[] chars = ((x*-1) + "").toCharArray();

            for (int i = 0; i < chars.length; i++)
                result += Math.pow(10, i) * (chars[i] - 48);

            result*=-1;
        }

        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) return 0;
        else return (int) result;
    }

    /**
     * 题目分析：
     * 更为数学、数字的一种处理方法，通过逐次取余，累次乘上10，得到最终结果
     * 使用 long result 的时长为 22ms，使用 int 应该可以更短，但要为溢出做另外处理
     *
     * 时长：21ms / 98%
     *
     * 总结：
     * 当数据量足够大的时候，一点点优化也是有效果的
     */
    public static int reverse2(int x) {
        int result = 0;

        while (x != 0) {
            if (Math.abs(result)>Integer.MAX_VALUE/10) return 0;
            result = result * 10 + x % 10;
            x /= 10;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(reverse1(2147483647));
        System.out.println(reverse2(2147483647));
    }
}
