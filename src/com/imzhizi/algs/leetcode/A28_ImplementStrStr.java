package com.imzhizi.algs.leetcode;

/**
 * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 *
 * Example 1:
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 *
 * Example 2:
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 *
 * return 0 when needle is an empty string.
 * created by zhizi on 2018/4/21
 */
public class A28_ImplementStrStr {
    /**
     * 函数描述：
     * 手动实现的子串匹配函数，使用了最简单的暴力匹配
     * 因为会有很多情况，因此增加了很多丑陋的if判断
     *
     * 运行时长： 9ms / 23%
     *
     * 总结：
     * 没动什么脑筋，KMP应该可以做，但没有尝试
     */
    public static int strStr2(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        if (haystack.length() < needle.length()) return -1;
        for (int i = 0; i < haystack.length(); i++) {
            if (needle.length() > haystack.length() - i) break;
            for (int j = 0; j < needle.length(); j++) {
                if (haystack.charAt(j + i) != needle.charAt(j)) break;
                if (j == needle.length() - 1) return i;
            }
        }
        return -1;
    }

    /**
     * 函数描述：
     * JavaAPI已经实现了此函数，时间果然优于手写的
     * 运行时长：7ms / 40%
     *
     * 总结：
     */
    public static int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    public static void main(String[] args) {
        System.out.println(strStr2("hello", "ll"));
        System.out.println(strStr2("aaaaaa", ""));
        System.out.println(strStr2("a", "a"));
        System.out.println(strStr2("mississippi", "sippia"));
    }
}
