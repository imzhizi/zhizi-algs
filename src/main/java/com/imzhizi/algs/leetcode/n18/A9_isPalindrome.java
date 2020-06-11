package com.imzhizi.algs.leetcode.n18;


/**
 * 9. Palindrome Number
 * 判断一个整数是否是回文数。不能使用辅助空间。
 * 2147447412
 */
public class A9_isPalindrome {
    /**
     * 函数描述：
     * 使用StringBuilder的reverse函数进行翻转，然后使用equals函数进行比较
     *
     * 运行时长：102ms / 52%
     *
     * 总结：
     * 使用 JavaAPI 函数，非常简单
     */
    public static boolean isPalindrome(int x) {
        if (x < 0) return false;
        String reverse=new StringBuilder(x + "").reverse().toString();
        return reverse.equals(x + "");
    }

    /**
     * 题目分析：
     * 手动使用char[]进行对称检查，若对称则回文
     *
     * 运行时长：100ms / 70%
     *
     * 总结：
     * 手动做总是能获得更好的效果
     */
    public static boolean isPalindrome1(int x) {
        if (x < 0) return false;
        char[] chars = String.valueOf(x).toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
            if (chars[i] != chars[chars.length - 1 - i]) return false;
        }
        return true;
    }

    /**
     * 题目分析：
     * 根据上一道题，可以把数字先进行翻转，然后再将翻转结果和原数字对比，相等即为回文数
     *
     * 运行时长：96ms /94%
     *
     * 总结：
     * 数字方法总是能获得更好的效率
     */
    public static boolean isPalindrome2(int x) {
        if (x < 0) return false;
        int reverse = 0;
        int origin=x;

        while (x != 0) {
            reverse = reverse * 10 + x % 10;
            x /= 10;
        }

        return reverse==origin?true:false;
    }

        public static void main(String[] args) {
//        System.out.println(A9_isPalindrome.isPalindrome2(10));//isPalindrome2
//        System.out.println(A9_isPalindrome.isPalindrome(121));//isPalindrome2
        System.out.println(A9_isPalindrome.isPalindrome2(121));//isPalindrome2
    }
}
