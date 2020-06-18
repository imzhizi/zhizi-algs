package com.imzhizi.algs.leetcode.curated;

import org.junit.Test;

/**
 * created by zhizi
 * on 6/15/20 10:50
 */
public class Hot100 {
    /**
     * [5. 最长回文子串 - 力扣（LeetCode）](https://leetcode-cn.com/problems/longest-palindromic-substring/)
     */
    @Test
    public void Q5() {

    }

    public String longestPalindrome(String s) {
        if (s.isEmpty()) return "";
        String max = s.substring(0, 1);
        for (int i = 1; i < s.length(); i++) {
            String even = expand(s, i - 1, i);
            if (even.length() > max.length()) {
                max = even;
            }
            String odd = expand(s, i, i);
            if (odd.length() > max.length()) {
                max = odd;
            }
        }
        return max;
    }

    public String expand(String s, int left, int right) {
        while (left > -1 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left + 1, right);
    }
}
