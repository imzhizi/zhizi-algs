package com.imzhizi.algs.LeetCode2018;

/**
 * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
 * The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.
 * You may assume the integer does not contain any leading zero, except the number 0 itself.
 * <p>
 * Example 1:
 * Input: [1,2,3]
 * Output: [1,2,4]
 * Explanation: The array represents the integer 123.
 * <p>
 * Example 2:
 * Input: [4,3,2,9]
 * Output: [4,3,3,0]
 * Explanation: The array represents the integer 4321.
 * created by zhizi on 2018/4/26
 */
public class A66_PlusOne {
    /**
     * 函数描述：
     * 给数组的最后一位增加1，返回增加后的数组，主要难点在于处理[9,9,9]这种有进位的情况，写的不好看只是跑过了
     * 运行时长：0 ms/1 ms
     *
     * 总结：
     * 这道题意义不太大，数据集也比较小，也难以看出优化效果
     * 因为进位的存在，所以只能在需要进位的时候临时创建数组来存放
     */
    public static int[] plusOne(int[] digits) {
        int length = digits.length - 1;
        digits[length]++;
        if (digits[length] < 10) {
            return digits;
        } else {
            for (int i = length; i >= 0; i--) {
                if (digits[i] > 9) {
                    digits[i] -= 10;
                    if (i == 0) {//需要进位，临时创建长度多1的数组
                        int[] temp = new int[length + 2];
                        temp[0] = 1;
                        digits = temp;
                    } else {
                        digits[i - 1]++;
                    }
                }
            }
            return digits;
        }
    }

    public static void main(String[] args) {
        int[] nums = plusOne(new int[]{1, 2, 3});
        for (int i = 0; i < nums.length; i++) {
            System.out.printf(nums[i] + ",");
        }
    }
}
