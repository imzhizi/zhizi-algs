package com.imzhizi.algs.LeetCode2018;

/**
 * Given two binary strings, return their sum (also a binary string).
 * The input strings are both non-empty and contains only characters 1 or 0.
 * <p>
 * Example 1:
 * Input: a = "11", b = "1"
 * Output: "100"
 * <p>
 * Example 2:
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 * created by zhizi on 2018/4/29
 */
public class A67_AddBinary {
    /**
     * 函数描述：
     * 又是非常别扭的字符串处理题目，而且和上题类似，需要处理进位问题，也没什么好办法，先转成char[]然后每一位按照int处理
     * 运行时长：4 ms
     *
     * 总结：
     * 对于相加进位问题，我自己有个思路是不计进制的先计算出这一位的和，然后再做进位/处理
     * 例如：1010+1011=2021，然后再从末位进行进位处理
     */
    public static String addBinary(String a, String b) {
        char[] acs = a.toCharArray();
        char[] bcs = b.toCharArray();
        int al = acs.length;
        int bl = bcs.length;
        int length = Math.max(al,bl);
        int[] r = new int[length+1];
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            //两个if复则字符相加，为了便于控制，我把相加的结果从0开始保存，到最后将字符串reverse即可
            if(al-->0){
                r[i] += acs[al];
                r[i] -= 48;
            }
            if (bl-->0){
                r[i] += bcs[bl];
                r[i] -= 48;
            }

            //负责处理进位
            if (r[i] > 1) {
                r[i] -= 2;
                if(i==length-1){
                    result.append(r[i]).append(1);
                    break;
                }
                r[i + 1]++;
            }
            result.append(r[i]);
        }
        result.reverse();
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(addBinary("11", "1"));
    }
}
