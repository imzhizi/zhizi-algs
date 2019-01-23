package com.imzhizi.algs.leetcode_2018;

import java.util.HashMap;

/**
 * Given a pattern and a string str, find if str follows the same pattern.
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
 *
 * Example 1:
 * Input: pattern = "abba", str = "dog cat cat dog"
 * Output: true
 * created by zhizi on 2018/12/04
 */
public class A290_WordPattern {
    static boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if (words.length == 1)
            words = str.split("");
        char[] patterns = pattern.toCharArray();
        if (words.length!=patterns.length) return false;

        HashMap<String, Integer> map = new HashMap<>();
        String[] pam = new String[26];
        for (int i = 0; i < patterns.length; i++) {
            int index = patterns[i] - 97;
            if (!map.containsKey(words[i])) {
                map.put(words[i], index);
            }
            if (pam[index] == null || pam[index] == "")
                pam[index] = words[i];

            if (!pam[index].equals(words[i]) || map.get(words[i]) != index) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(wordPattern("jquery","jquery"));
        System.out.println(wordPattern("abba","dog cat cat dog"));
        System.out.println(wordPattern("abba","dog dog dog dog"));
        System.out.println(wordPattern("abba","dog cat cat fish"));
    }
}
