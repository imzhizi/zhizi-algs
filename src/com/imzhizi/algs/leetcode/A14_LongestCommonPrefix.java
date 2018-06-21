package com.imzhizi.algs.leetcode;

/**
 * 14. Longest Common Prefix
 * Write a function to find the longest common prefix string amongst an array of strings.
 * created by zhizi on 2018/4/15
 */
public class A14_LongestCommonPrefix {
    /**
     * 函数描述：
     * 要找出一个String数组的公共前缀，首先想到的依然是暴力解法
     * 时长：16ms
     *
     * 总结：
     * 要考虑两点，一是空String[]，String[]中String为null，String[]中String为空
     * 另外是每次循环的次数取决于当下的公共前缀序列的长度和将要前缀比较的序列长度
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0 || strs[0] == null || strs[0].length() == 0) return "";
        String common = strs[0];
        for (int i = 1; i < strs.length; i++) {
            if (strs[i] == null || strs[i].isEmpty()) return "";
            int length = Math.min(common.length() ,strs[i].length());
            int j;
            for (j = 0; j < length; j++) {
                if (common.charAt(j) != strs[i].charAt(j)) {
                    if(j==0)return "";
                    break;
                }
            }
            common = common.substring(0, j);
        }
        return common;
    }

    public static void main(String[] args) {
        String[] strs = {"flower","flow","flight"};
        System.out.println(longestCommonPrefix(strs));
        String[] strs1 = {"","",""};
        System.out.println(longestCommonPrefix(strs1));
        String[] strs2 = {"c","c"};
        System.out.println(longestCommonPrefix(strs2));
    }
}