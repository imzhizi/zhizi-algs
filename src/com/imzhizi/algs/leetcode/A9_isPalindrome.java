package com.imzhizi.algs.leetcode;


/**
 * 9. Palindrome Number
 * 判断一个整数是否是回文数。不能使用辅助空间。
 * 2147447412
 */
public class A9_isPalindrome {
    /**
     * 函数描述：
     * 使用StringBuilder的reverse函数进行翻转，然后使用equals函数进行比较
     * 运行时长：未知
     *
     * 总结：
     * 使用JavaAPI函数，非常简单
     */
    public static boolean isPalindrome(int x) {
        if (x >= 0) {
            StringBuilder string = new StringBuilder(Long.toString(x));
            return string.reverse().equals(string);
        } else {
            return false;
        }
    }

    /**
     * 函数描述：
     * 手动使用char[]进行对称检查，若对称则回文
     * 运行时长：未知
     *
     * 总结：
     * 无
     */
    public static boolean isPalindrome2(int x) {
        if (x < 0) return false;
        char[] chars = String.valueOf(x).toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
            if (chars[i] != chars[chars.length - 1 - i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(A9_isPalindrome.isPalindrome2(2147447412));//isPalindrome2
    }
}
