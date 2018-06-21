package com.imzhizi.algs.leetcode;

/**
 * created by zhizi on 2018/6/7
 */
public class A122_BestTimeToBuyAndSellStockII {
    public int maxProfit(int[] prices) {
        int profit=0;
        int totalProfit=0;
        int price=prices[0];
        for (int i = 1; i < prices.length; i++) {
            int m=prices[i]-price;
            if(m>profit){
                profit=m;

            }else{

            }
        }
        return 0;
    }

    public static void main(String[] args) {

    }
}
