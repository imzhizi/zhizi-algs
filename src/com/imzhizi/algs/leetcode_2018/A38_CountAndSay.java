package com.imzhizi.algs.leetcode_2018;

/**
 * The count-and-say sequence is the sequence of integers with the first five terms as following:
 * 1. 1
 * 2. 11
 * 3. 21
 * 4. 1211
 * 5. 111221
 *
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 * created by zhizi on 2018/4/22
 */
public class A38_CountAndSay {
    /**
     * 函数描述：
     * 一道很别扭的字符串处理的题目，写了很久没想出来如何处理，最后还是选用了最暴力的方法
     * 为了字符串拼接方便，使用了StringBuilder
     * 第一层while循环控制n，第二层控制字符串的每个字符，第三层计算相同元素的个数
     * 运行时长：5 ms
     *
     * 总结：
     * 这种字符串处理问题总是解决的不太好，很少能想出暴力以外的方法
     */
    public static String countAndSay(int n) {
        if (n == 1) return "1";
        if (n == 2) return "11";
        String str = "11";
        while (n-- != 2) {
            StringBuilder result = new StringBuilder();
            int length = str.length();
            for (int i = 0; i < length; i++) {
                int count = 0;
                while (count + i < length && str.charAt(i) == str.charAt(count + i)) {
                    count++;
                }
                result.append(count).append(str.charAt(i));
                i = i + count - 1;
            }
            str = result.toString();
        }
        return str;
    }

    public static void main(String[] args) {
        System.out.println(countAndSay(2));
        System.out.println(countAndSay(3));
        System.out.println(countAndSay(4));
        System.out.println(countAndSay(5));
        System.out.println(countAndSay(6));
        System.out.println(countAndSay(7));
    }
}
