package com.imzhizi.algs.leetcode.curated;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * [17. 电话号码的字母组合 - 力扣（LeetCode）](https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/)
     */
    @Test
    public void Q17() {
        System.out.println(letterCombinations("23"));
    }

    private static Map<Character, String> map = new HashMap<>();

    static {
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
    }

    public List<String> letterCombinations(String digits) {
        List<String> ret = new ArrayList<>();
        if (!digits.isEmpty()) build(digits, 0, ret, "");
        return ret;
    }

    public void build(String digits, int nth, List<String> ret, String curr) {
        if (digits.length() == nth) {
            ret.add(curr);
            return;
        }

        String s = map.get(digits.charAt(nth));
        for (int i = 0; i < s.length(); i++) {
            String str = curr + s.charAt(i);
            build(digits, nth + 1, ret, str);
        }
    }

    /**
     * [22. 括号生成 - 力扣（LeetCode）](https://leetcode-cn.com/problems/generate-parentheses/)
     */
    @Test
    public void Q22() {

    }

    public List<String> generateParenthesis(int n) {
        List<String>[] lists = new ArrayList[n];
        lists[0] = new ArrayList<>();
        lists[0].add("()");

        for (int i = 1; i < n; i++) {

        }


        return lists[n - 1];
    }
}
