package com.imzhizi.algs.leetcode.n18;

/**
 * Given an integer n, return the number of trailing zeroes in n!.
 * Example 1:
 *    Input: 3
 *    Output: 0
 *
 * Explanation: 3! = 6, no trailing zero.
 * created by zhizi on 2018/7/24
 */
public class A172_FactorialTrailingZeroes {
    /**
     * 题目分析：
     * 一开始并没搞明白题目是什么意思，搜索之后才明白是数末位的0，想也不想就暴力一波，先写个递归求阶乘，再数后面几个0
     * 然后我就轻轻松松收获 stack over error，然后就看到 Your solution should be in logarithmic time complexity.
     * 重新构想时，我想，只要出现了阶乘中出现了一个0，那么最终结果里面一定有0，更深的想，其实有5的倍数和偶数就会多一个0，所以猜测可能跟5有关
     * 算了之后发现，30以前都是有几个5就有几个0，到30就不是了，多一个，进而想到了连续除以 5。
     *
     * 运行时长：16 ms
     *
     * 总结：
     * 多思考吧c
     */
    int trailingZeroes(int n) {
        int ret=0;
        while(n>0){
            n/=5;
            ret+=n;
        }
        return ret;
    }
}
