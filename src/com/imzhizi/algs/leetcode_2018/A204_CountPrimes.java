package com.imzhizi.algs.leetcode_2018;

/**
 * Count the number of prime numbers less than a non-negative number, n.
 * created by zhizi on 9/1/18
 */
public class A204_CountPrimes {
    /**
     * 题目分析：
     * 我没有想到什么判断质数的好办法，最初的想法是按照定义，依次去除，看是否有整数，但这种方法太慢了
     * 后来参考了他人的方法，思路是把不是质数的部分标记出来，那么剩下的就都是质数了，同时因为偶数必然不是质数，可以进一步优化
     *
     * 运行时长：23ms / 42%
     *
     * 总结：
     * 做了很多小补丁，不是一个特别完美的方法
     */
    static int countPrimes(int n) {
        if (n < 3) return 0;
        int count = 1;
        boolean[] notPrime = new boolean[n];
        for (int i = 3; i < n; i += 2) {
            if (notPrime[i] == false) count++;
            for (int j = 3; i * j < n; j += 2) {
                notPrime[i * j] = true;
            }
        }
        return count;
    }

    //运行时长：23ms / 54%， 小改动
    static int countPrimes2(int n) {
        if (n < 3) return 0;
        int count = 1;
        boolean[] notPrime = new boolean[n];
        for (int i = 3; i < n; i += 2) {
            if (notPrime[i] == false) count++;
            for (long j = i; (long) (i * j) < n; j += 2) {
                notPrime[(int) (i * j)] = true;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(countPrimes(4));
        System.out.println(countPrimes(10));
        System.out.println(countPrimes(12));
    }
}
