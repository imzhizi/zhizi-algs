package com.imzhizi.algs.LeetCode.LeetCode2018;

/**
 * Given a column title as appear in an Excel sheet, return its corresponding column number.
 *
 * For example:
 *     A -> 1
 *     ...
 *     AA -> 27
 *     ...
 *
 * Example 1:
 *      Input: "A"
 *      Output: 1
 * created by zhizi on 2018/7/23
 */
public class A171_ExcelSheetColumnNumber {
    /**
     * 题目分析：
     * 跟之前的题目相辅相成，就是把转成特殊26进制的数字转回来，这就很容易了，利用 ASCII 轻松得到结果
     *
     * 运行时长：1 ms
     *
     * 总结：
     * 无，ASCII 总是有些莫名奇妙的用法
     */
     static int titleToNumber(String s) {
        char[] cs=s.toCharArray();
        int sum=0;
        for (int i = 0; i < cs.length; i++) {
            sum+=(cs[i]-64)*Math.pow(26,cs.length-1-i);
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(titleToNumber("AB"));
    }
}
