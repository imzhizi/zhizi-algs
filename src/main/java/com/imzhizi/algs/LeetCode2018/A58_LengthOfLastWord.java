package com.imzhizi.algs.LeetCode2018;

/**
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.
 * If the last word does not exist, return 0.
 * Note: A word is defined as a character sequence consists of non-space characters only.
 * <p>
 * Example:
 * Input: "Hello World"
 * Output: 5
 * created by zhizi on 2018/4/26
 */
public class A58_LengthOfLastWord {
    /**
     * 函数描述：
     * 题目并不复杂，只要注意几个特殊的样例即可
     * 其中有："a "，"    "，
     * 运行时长：5 ms
     *
     * 总结：
     * 两种结束情况，一种是遇到最后一个单词之前的空格，一个是整个字符串直接遍历结束
     */
    public static int lengthOfLastWord(String s) {
        int length = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != ' ') length++;
            if (s.charAt(i) == ' ' && length != 0) break;
        }
        return length;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("Hello World"));
    }
}
