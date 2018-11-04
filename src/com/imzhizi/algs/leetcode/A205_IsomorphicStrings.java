package com.imzhizi.algs.leetcode;

/**
 * Given two strings s and t, determine if they are isomorphic.
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 * All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.
 *
 * Example 1:
 * Input: s = "egg", t = "add"
 * Output: true
 *
 * Example 2:
 * Input: s = "foo", t = "bar"
 * Output: false
 * created by zhizi on 11/4/18
 */
public class A205_IsomorphicStrings {
    /**
     * 题目分析：
     * 判断两个字符串是否同构，本来的想法是使用 HashMap，然后一一对应，后来发现用 Character 包装类实在麻烦
     * 索性直接用 int 数组来处理，最初有一点没考虑到就是 s 映射到 t 的同时，t 也要映射到 s
     *
     * 运行时长：2ms / 99%
     *
     * 总结：
     * 好久没写了，果然比已经迟钝了不少，之后要坚持
     */
    static boolean isIsomorphic(String s, String t) {
        int[] smap = new int[256];
        int[] tmap = new int[256];
        char[] cs = s.toCharArray();
        char[] ct = t.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            if (smap[cs[i]] != 0 || tmap[ct[i]] != 0) {
                if (smap[cs[i]] != ct[i]) return false;
                if (tmap[ct[i]] != cs[i]) return false;
            } else {
                smap[cs[i]] = ct[i];
                tmap[ct[i]] = cs[i];
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isIsomorphic("ab", "aa"));
        System.out.println(isIsomorphic("egg", "add"));
        System.out.println(isIsomorphic("13", "42"));
        System.out.println(isIsomorphic("abcdefghijklmnopqrstuvwxyz", "nopqrstuvwxyzabcdefghijklm"));
    }

}
