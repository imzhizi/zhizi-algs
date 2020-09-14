package com.imzhizi.algs.leetcode.curated;

import org.junit.Test;

/**
 * created by zhizi
 * on 9/14/20 13:26
 */
public class DP {
    /**
     * [53. 最大子序和 - 力扣（LeetCode）](https://leetcode-cn.com/problems/maximum-subarray/)
     */
    @Test
    public void maxSubArray() {
        System.out.println(maxSubArray(new int[]{-2, -3, 5, 1}));
    }

    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int current = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (current > 0) {
                current += nums[i];
            } else {
                current = nums[i];
            }
            maxSum = Math.max(maxSum, current);
        }

        return maxSum;
    }

    /**
     * [152. 乘积最大子数组 - 力扣（LeetCode）](https://leetcode-cn.com/problems/maximum-product-subarray/)
     */
    public int maxProduct(int[] nums) {
        int max = 1;
        int min = 1;
        int ret = nums[0];
        for (int num : nums) {
            int m = max;
            max = Math.max(num, Math.max(max * num, min * num));
            min = Math.min(num, Math.min(m * num, min * num));
            ret = Math.max(ret, Math.max(max, min));
        }

        return ret;
    }

    public int maxProduct2(int[] nums) {
        int max = 1;
        int min = 1;
        int ret = nums[0];
        for (int num : nums) {
            if (num < 0) {
                int temp = min;
                min = max;
                max = temp;
            }
            max = Math.max(num, max * num);
            min = Math.min(num, min * num);
            ret = Math.max(ret, Math.max(max, min));
        }

        return ret;
    }

}
