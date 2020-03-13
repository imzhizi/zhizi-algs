package com.imzhizi.algs.剑指LeetCode;

import com.imzhizi.algs.base.TreeNode;
import org.junit.Test;

import java.util.*;

/**
 * created by zhizi
 * on 3/12/20 15:34
 */
public class TOP50 {
    /**
     * [面试题42. 连续子数组的最大和 - 力扣（LeetCode）](https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/ )
     */
    @Test
    public void No42() {
    }

    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int lastValue = nums[0];

        for (int i = 1; i < nums.length; i++) {
            lastValue = Math.max(nums[i], lastValue + nums[i]);
            maxSum = Math.max(maxSum, lastValue);
        }

        return maxSum;
    }


    /**
     * [面试题50. 第一个只出现一次的字符 - 力扣（LeetCode）](https://leetcode-cn.com/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/ )
     */
    @Test
    public void No50() {

    }

    public char firstUniqChar(String s) {
        int[] target = new int[26];
        for (int i = 0; i < s.length(); i++) {
            target[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (target[s.charAt(i) - 'a'] == 1) return s.charAt(i);
        }

        return ' ';
    }

    public char firstUniqChar2(String s) {
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();

        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        for (int key : map.keySet()) {
            if (map.get(key) == 1) {
                return (char) key;
            }
        }
        return ' ';
    }
}
