package com.imzhizi.algs.leetcode;

/**
 * 7. Reverse Integer
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
     * 功能描述
     * 使用StringBuilder进行翻转，因为翻转后可能会大于整形数字，因此使用Long.valueOf()来接收翻转后的数字
     * 时长：43ms
     *
     * 总结
     * int型的负数转换成正数时会有一位越界
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
     * 函数描述：
     * 使用数学方法进行翻转，思路是从个位开始求余数，然后将余数变成最高位
     * 第一个循环计算长度，第二个循环依次处理余数
     * 时长：52ms
     *
     * 总结：
     * 没想到这种方法时间竟然比StringBuilder翻转，可见JavaAPI函数优化的非常好
     */
    public static int reverse2(int x) {
        int length = 0;
        for (int i = x; i != 0; i /= 10) {
            length++;
        }
        long result = 0;
        for (; x != 0; x /= 10) {
            int rest = x % 10;
            result += rest * Math.pow(10, --length);
        }
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) return 0;
        else return (int) result;
    }

    public static void main(String[] args) {
        System.out.println(reverse2(2147483647));
    }
}
