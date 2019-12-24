package com.imzhizi.algs.LeetCode2018;

import java.util.Arrays;

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 * created by zhizi on 2018/4/15
 */
public class A14_LongestCommonPrefix {
    /**
     * 函数描述：
     * 要找出一个String数组的公共前缀，首先想到的依然是暴力解法
     *
     * 时长：9ms / 20%
     *
     * 总结：
     * 要考虑三点，字符串数组为空、字符串数组中字符串为null、字符串数组中字符串为空
     * 另外是每次循环的次数取决于当下的公共前缀序列的长度和将要前缀比较的序列长度
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0 || strs[0] == null || strs[0].length() == 0) return "";//字符串数组为空、字符串数组中字符串为null、字符串数组中字符串为空
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

    /**
     * 函数描述：
     * 取巧利用 JavaAPI 中 Arrays 的排序函数，得到结果后直接处理第一个和最后一个
     *
     * 时长：8ms / 35%
     *
     * 总结：
     * 稍微快了一丢丢，这个方法易于实现也主要是依赖于 JavaAPI 中排序函数的优化了
     */
    public static String longestCommonPrefix2(String[] strs) {
        if (strs.length == 0 || strs[0] == null) return "";
        if (strs[0].length() == 0 || strs.length == 1) return strs[0];
        Arrays.sort(strs);
        String start = strs[0];
        String end = strs[strs.length-1];
        int minLen = Math.min(start.length(), end.length());

        for (int i=0; i<minLen; i++) {
            if (start.charAt(i) != end.charAt(i)) return start.substring(0,i);
        }
        return start;
    }

        public static void main(String[] args) {
        String[] strs = {"flower","flow","flight"};
        System.out.println(longestCommonPrefix2(strs));
        String[] strs1 = {"","",""};
        System.out.println(longestCommonPrefix2(strs1));
        String[] strs2 = {"c","c"};
        System.out.println(longestCommonPrefix2(strs2));
    }
}
