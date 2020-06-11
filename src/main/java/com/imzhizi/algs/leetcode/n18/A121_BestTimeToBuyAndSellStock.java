package com.imzhizi.algs.leetcode.n18;


/**
 *Say you have an array for which the ith element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 * Note that you cannot sell a stock before you buy one.
 *
 * Example 1:
 * Input: [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 *              Not 7-1 = 6, as selling price needs to be larger than buying price.
 * created by zhizi on 2018/6/6
 */
public class A121_BestTimeToBuyAndSellStock {
    /**
     * 题目分析：
     * 说是买卖股票的最佳时机，翻译出来，其实是寻找一个最大差值，这个差值必须是后出现的数字减去之前出现的
     * 思路也很朴实，就是找出数组已被遍历部分中最小的值，然后不断与下一个值计算差值，若比之前大，则保留
     *
     * 运行时长：2ms
     *
     * 总结：
     * 任何需要保存结果数组，之后再对结果进行排序都需要考虑这样是否是必要的，一般可以优化
     */
    public static int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int min = prices[0];
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            int m = prices[i] - min;
            if (m > 0) {
                if (profit < m) profit = m;
            } else {
                min = prices[i];
            }
        }
        return profit;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{2, 1, 4}));
    }
}
