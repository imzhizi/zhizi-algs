package com.imzhizi.algs.leetcode.n18;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 *
 * Note that an empty string is also considered valid.
 *
 * Example 1:
 *  Input: "()"
 *  Output: true
 *
 * Example 2:
 *  Input: "()[]{}"
 *  Output: true
 *
 * Example 3:
 *  Input: "(]"
 *  Output: false
 *
 * Example 4:
 *  Input: "([)]"
 *  Output: false
 *
 * Example 5:
 *  Input: "{[]}"
 *  Output: true
 * created by zhizi on 2018/4/18
 */
public class A20_ValidParentheses {
    /**
     * 函数描述：
     * 括号匹配是典型的栈的应用，原本想要使用JavaAPI内置的Stack类，但发现用一个char[]和一个栈顶指针可以轻松模拟一个栈
     * 若栈为空，则直接进栈；不为空进栈，与栈顶括号尝试匹配，若匹配则一起出栈，不匹配则进栈
     *
     * 运行时长：4ms / 99%
     *
     * 总结：
     * 栈的简单使用，通过ASCII编码判断左右括号非常快捷
     */
    public static boolean isValid(String s) {
        if (s.length() == 0) return true;
        char[] brackets = new char[s.length()];
        int depth = -1;
        for (int i = 0; i < s.length(); i++) {
            if (depth == -1)
                brackets[++depth] = s.charAt(i);
            else if (brackets[depth] == s.charAt(i) - 1 || brackets[depth] == s.charAt(i) - 2)
                depth--;
            else
                brackets[++depth] = s.charAt(i);
        }
        if (depth == -1)
            return true;
        else return false;
    }

    public static void main(String[] args) {
        System.out.println(isValid("()[]{}"));
        System.out.println(isValid("{[]}"));
        System.out.println(isValid("([)]"));
    }
}
