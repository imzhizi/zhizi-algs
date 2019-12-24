package com.imzhizi.algs.LeetCode2018;

/**
 * Reverse bits of a given 32 bits unsigned integer.
 *
 * Example:
 *  Input: 43261596
 *  Output: 964176192
 * Explanation:
 *  43261596 represented in binary as 00000010100101000001111010011100,
 *  return 964176192 represented in binary as 00111001011110000010100101000000.
 *
 * created by zhizi on 7/26/18
 */
public class A190_ReverseBits {
    /**
     * 题目分析：
     * 拿到题目，感觉非常熟悉，这不就是当初我在北邮面试时的题目嘛，当时非常蠢用String怎么也没搞出来，最后逐项用数组保存了 32 位数，然后反向累加得到结果；
     * 这次觉得通过 `Integer.toBinaryString()` 应该能得到结果，但很快就发现是行不通的，当输入1时，对应的翻转二进制是 10···32个0···0，这已经超过int最大值了；
     * 我又尝试了很久，最终才明白题目所说的「unsigned integer」是什么意思，也就是说他们对于输入输出结果其实只看二进制，根本不管对应的值在 Java 中具体是哪个值，
     * 这样的话，10···32个0···0 对应的数在 Java 中其实是 -2^31（此处涉及到了 Java 中数字的存储实现）
     * 当理解了这一层之后，其实只要依次读取二进制的最后一位累加即可
     *
     * 运行时长：1 ms
     *
     * 总结：
     * 一方面对于 Java 位运算符不了解，另一方面对于 Java 内部实现也了解有限
     */
    static int reverseBits1A(int n) {
        int result = 0;
        for (int i = 0; i < 32 && n != 0; i++) {
            if (n % 2 != 0) result += 1 << (31 - i);//判断最后一位是0是1
            n >>= 1;
        }
        return result;
    }

    /**
     * 题目分析：
     *
     * 运行时长：1 ms
     *
     * 总结：
     * 这是从评论区学习来的一个方法，本质上和我的实现相同的，不过获取最后一位的方法较高级？美观？总之有学习意义
     */
    static int reverseBits1B(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            int curBit = (n >> i) & 1;//只保留最后一位，
            result += (curBit << (31 - i));
        }
        return result;
    }
}
