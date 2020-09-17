package com.imzhizi.algs.leetcode.curated;

import org.junit.Test;

import java.util.*;

/**
 * created by zhizi
 * on 9/14/20 13:26
 */
public class DP {
    /**
     * [139. 单词拆分 - 力扣（LeetCode）](https://leetcode-cn.com/problems/word-break/)
     */
    Set<String> dict = new HashSet<>();
    int maxSize = 0;
    int minSize = Integer.MAX_VALUE;
    boolean[] able;

    public boolean wordBreak(String s, List<String> wordDict) {
        for (String value : wordDict) {
            dict.add(value);
            maxSize = Math.max(maxSize, value.length());
            minSize = Math.min(minSize, value.length());
        }
        able = new boolean[s.length()];
        return wordBreak(s, 0);
    }


    public boolean wordBreak(String s, int start) {
        if (start == s.length()) {
            return true;
        } else if (start > s.length()) {
            return false;
        }

        for (int i = start + minSize; i <= start + maxSize && i <= s.length(); i++) {
            if (!able[i - 1]) {
                if (dict.contains(s.substring(start, i))) {
                    able[i - 1] = true;
                    if (wordBreak(s, i)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

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
            current = nums[i] + Math.max(current, 0);
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

    /**
     * [300. 最长上升子序列 - 力扣（LeetCode）](https://leetcode-cn.com/problems/longest-increasing-subsequence/)
     * 经典的DP题目，也不是很难，关键还是弄清楚转移方程
     * 时间复杂度不算低
     */
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int ret = 0;
        for (int i = 0; i < nums.length; i++) {
            dp[i]++;
            int maxValue = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && dp[j] > maxValue) {
                    maxValue = dp[j];
                }
            }
            dp[i] += maxValue;
            ret = Math.max(ret, dp[i]);
        }
        return ret;
    }


    /**
     * [918. 环形子数组的最大和 - 力扣（LeetCode）](https://leetcode-cn.com/problems/maximum-sum-circular-subarray/)
     * 前缀和算法，没太理解为什么么这么算
     */
    public int maxSubarraySumCircular(int[] A) {
        int[] preSum = new int[A.length * 2];
        for (int i = 1; i <= A.length; i++) {
            preSum[i] = A[i - 1] + preSum[i - 1];
        }
        for (int i = 1; i < A.length; i++) {
            preSum[A.length + i] = A[i - 1] + preSum[A.length + i - 1];
        }

        Deque<Integer> deque = new ArrayDeque<>();

        deque.offer(0);
        int ret = Integer.MIN_VALUE;
        for (int i = 1; i < preSum.length; i++) {
            if (!deque.isEmpty() && i - A.length > deque.peek()) {
                deque.poll();
            }
            if (!deque.isEmpty()) {
                ret = Math.max(ret, preSum[i] - preSum[deque.peek()]);
            }
            while (!deque.isEmpty() && preSum[deque.peekLast()] >= preSum[i]) {
                deque.pollLast();
            }
            deque.offer(i);
        }
        return ret;
    }
}
