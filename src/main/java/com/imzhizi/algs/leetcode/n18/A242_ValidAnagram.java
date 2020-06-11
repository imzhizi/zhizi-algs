package com.imzhizi.algs.leetcode.n18;

/**
 * Given two strings s and t , write a function to determine if t is an anagram of s.
 *
 * Example 1:
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 *
 * Example 2:
 * Input: s = "rat", t = "car"
 * Output: false
 * created by zhizi on 11/21/18
 */
public class A242_ValidAnagram {
    /**
     * 题目分析：
     * Anagram 意为由同样字母组成、顺序不同而产生的不同单词，同字母易序词
     * 思路就是数每个字母出现的次数，如果相同，就说明两个单词由相同的字母组成
     * 具体来说，使用一个长度为 26 的数组，s 中字母每出现一次，就在对应位置上加一，t 中每出现一次就在对应位置减一
     *
     * 运行时长：2ms / 96%
     *
     * 总结：
     * 本来想使用位运算优化到一次遍历，但是发现无法处理“aa”、“bb”的情况，遂放弃
     */
    boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;
        int[] alpha = new int[26];
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        for (int i = 0; i < sc.length; i++) {
            alpha[sc[i] - 97]++;
            alpha[tc[i] - 97]--;
        }
        for (int i = 0; i < 26; i++) {
            if (alpha[i] != 0)
                return false;
        }
        return true;
    }

    // 失败的方法
    static boolean isAnagram2(String s, String t) {
        if (s.length() != t.length())
            return false;
        int alpha = 0;
        for (int i = 0; i < s.length(); i++) {
            alpha = alpha ^ s.charAt(i) ^ t.charAt(i);
        }
        if (alpha != 0)
            return false;
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isAnagram2("ab", "ba"));
    }
}
