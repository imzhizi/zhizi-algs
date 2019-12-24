package com.imzhizi.algs.LeetCode2018;

/**
 * You are climbing a stair case. It takes n steps to reach to the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * Note: Given n will be a positive integer.
 * <p>
 * Example
 * In:2 Ou:2    In:5 OU:8
 * <p>
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * created by zhizi on 2018/5/4
 */
public class A70_ClimbingStairs {
    /**
     * 题目分析：
     * 一看到这道题，第一反应就是，肯定用递归啊，三下五除二就写出来了（见函数2），但果不其然，超时了
     * 不用递归要怎么做呢？我对题目分析了一下，找阶梯层数的关系（以5层为例）
     * 5层的话有三种步数分配，0X2+5X1，1X2+3X1，2X2+1X1，因为每多一个2层/步，步数就减1，
     * 此情况中有k个2层/步，能达成的走法就是C(n-k)k
     * 所以总情况应该是从0到n/2，总步数=Cn0+Cn1+……+C(n-n/2)(n/2)
     *
     * 如何求排列组合，我搜索后发现Math不提供排列组合的计算，于是就打算自己写一个
     * 我的思路也比较简单，就是先行计算出0……n之间所有自然数的阶乘，因为之后都会用到
     * 然后再根据Cnk=n!/[(n-k)! * k!]来计算
     * 运行时长：4ms
     *
     * 总结：
     * 阶乘的数量级增长的很快，int很快就GG了，换了long之后也没坚持很久
     * 就在我要怀疑自己方法不对的时候我又尝试了double，不过double在计算除法转int的时候
     * 所以直接把total也用了double
     * 而最终结果用了Math.round()函数来帮助四舍五入
     */
    public static int climbStairs(int n) {
        double[] factorial = new double[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }
        double total = 0;
        for (int i = 0; i <= n / 2; i++) {
            total += factorial[n - i] / factorial[n - i - i] / factorial[i];
        }
        return (int) Math.round(total);
    }

    public static int climbStairs2(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        else
            return climbStairs(n - 1) + climbStairs(n - 2);
    }

    public static void main(String[] args) {
        System.out.println(climbStairs(39));
    }
}
