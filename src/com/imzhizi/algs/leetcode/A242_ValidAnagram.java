package com.imzhizi.algs.leetcode;

/**
 * created by zhizi on 11/21/18
 */
public class A242_ValidAnagram {
    /**
     * 题目分析：
     * Anagram 意为由同样字母组成、顺序不同而产生的不同单词，同字母易序词
     *
     * 运行时长：2ms / 96%
     *
     * 总结：
     * 无
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
