package com.imzhizi.algs.LeetCode2018;

import java.util.HashSet;

/**
 * Write an algorithm to determine if a number is "happy".
 * A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.
 *
 * Example:
 *  Input: 19
 *  Output: true
 * Explanation:
 *  12 + 92 = 82
 *  82 + 22 = 68
 *  62 + 82 = 100
 *  12 + 02 + 02 = 1
 * created by zhizi on 8/3/18
 */
public class A202_HappyNumber {
    /**
     * 题目分析：
     * 快乐数字，快乐的不难判断，关键在于不快乐的不陷入死循环，我想到的愚蠢的办法就是看数字是否出现过一次
     * 首先我使用的是数组，然后出现则重复则说明不快乐，但是一个 Integer.MAX_VALUE 的数组内存超了（真的很占空间）
     * 所以我用了 HashSet，成功了
     *
     * 运行时长：2 ms
     *
     * 总结：
     * 在一些 beat 100% 的方法中，他们会使用一些预加工的数据，而我觉得预加工有些违背初衷，所以暂且就这样了
     */
    static boolean isHappy(int n) {
        HashSet<Integer> loc=new HashSet<>();
        while(!loc.contains(n)){
            loc.add(n);
            n=sum(n);
            if (n==1)return true;
        }
        return false;
    }

    static int sum(int n) {
        int sum = 0;
        for (; n > 0; n = n / 10) {
            sum += (n%10)*(n%10);
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(isHappy(2));
        System.out.println(isHappy(19));
        System.out.println(isHappy(1047));
        System.out.println(isHappy(1111111));
    }
}
