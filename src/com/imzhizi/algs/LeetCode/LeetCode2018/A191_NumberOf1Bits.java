package com.imzhizi.algs.LeetCode.LeetCode2018;

/**
 * Write a function that takes an unsigned integer and returns the number of '1' bits it has (also known as the Hamming weight).
 *
 * Example 1:
 *  Input: 11
 *  Output: 3
 * Explanation: Integer 11 has binary representation 00000000000000000000000000001011
 * created by zhizi on 7/27/18
 */
public class A191_NumberOf1Bits {
    /**
     * 题目分析：
     * 计算一个数字的二进制表示中有几个1，做了昨天的第 190 题之后，感觉非常简单
     * 所以我故伎重施，很容就写出两个做法，还是昨天的两个做法
     *
     * 运行时长：1 ms
     *
     * 总结：
     * 感觉不太合理，这道题应该跟190换一换，梯度就会合理许多
     */
    static int hammingWeight2(int n) {
        int result = 0;
        for (int i = 0; i < 32 && n != 0; i++) {
            if (n % 2 != 0) result++;//判断最后一位是0是1
            n >>= 1;
        }
        return result;
    }
    static int hammingWeight(int n) {
        int nums=0;
        for (int i = 0; i < 32 && n != 0; i++) {
            int temp=(n>>i)&1;
            nums+=temp;
        }
        return nums;
    }

    public static void main(String[] args) {
        System.out.println(hammingWeight(11));
        System.out.println(hammingWeight2(11));
    }
}
