package com.imzhizi.algs.剑指LeetCode;

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
     * [面试题47. 礼物的最大价值 - 力扣（LeetCode）](https://leetcode-cn.com/problems/li-wu-de-zui-da-jie-zhi-lcof/ )
     */
    @Test
    public void No47() {
    }

    public int maxValue(int[][] grid) {
        int m = grid.length - 1;
        int n = grid[0].length - 1;
        return maxValue(grid, 0, 0, m, n);
    }

    // 简单说就是做过的就不要再做了，用符号判断工作是否已经做过
    public int maxValue(int[][] grid, int r, int c, int m, int n) {
        if (r == m && c == n) return grid[r][c];

        if (grid[r][c] >= 0) {
            if (r < m && c < n) {
                grid[r][c] = (Math.max(maxValue(grid, r + 1, c, m, n), maxValue(grid, r, c + 1, m, n)) + grid[r][c]) * -1;
            } else if (r < m) {
                grid[r][c] = (maxValue(grid, r + 1, c, m, n) + grid[r][c]) * -1;
            } else {
                grid[r][c] = (maxValue(grid, r, c + 1, m, n) + grid[r][c]) * -1;
            }
        }
        return grid[r][c] * -1;
    }

    // 轻松超时
    // 如何优化，剪枝，直接提供最大值
    public int maxValue2(int[][] grid, int r, int c, int m, int n) {
        if (r == m && c == n) return grid[r][c];

        if (r < m && c < n) {
            return Math.max(maxValue(grid, r + 1, c, m, n), maxValue(grid, r, c + 1, m, n)) + grid[r][c];
        } else if (r < m) {
            return maxValue(grid, r + 1, c, m, n) + grid[r][c];
        } else {
            return maxValue(grid, r, c + 1, m, n) + grid[r][c];
        }
    }

    /**
     * [面试题48. 最长不含重复字符的子字符串 - 力扣（LeetCode）](https://leetcode-cn.com/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof/ )
     */
    @Test
    public void No48() {
    }

    // 优化版本，不必再逐个移除，而是通过 head 判断重复键的有效性
    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.isEmpty()) return 0;

        int max = Integer.MIN_VALUE;
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>(chars.length);

        int head = 0;
        for (int i = 0; i < chars.length; i++) {
            if (map.containsKey(chars[i]) && map.get(chars[i]) >= head) {
                max = Math.max(max, i - head);
                head = map.get(chars[i]) + 1;
            }
            map.put(chars[i], i);
        }
        return Math.max(max, chars.length - head);
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) return 0;

        int max = Integer.MIN_VALUE;
        char[] chars = s.toCharArray();
        Set<Character> set = new HashSet<>(chars.length);

        int head = 0;
        for (int i = 0; i < chars.length; i++) {
            if (set.contains(chars[i])) {
                // 发现包含chars[i], 要怎么操作
                // 首先记录当前长度, 然后向下滑动
                max = Math.max(max, set.size());
                // 从j到i, 逐个remove直到不存在重复键
                for (; head < i; head++) {
                    set.remove(chars[head]);
                    if (chars[head] == chars[i]) {
                        head++;
                        break;
                    }
                }
            }
            set.add(chars[i]);
        }
        return Math.max(max, set.size());
    }

    /**
     * [面试题49. 丑数 - 力扣（LeetCode）](https://leetcode-cn.com/problems/chou-shu-lcof/ )
     * 代码指针前进部分看似有点傻，但隐含了一件事情，就是淘汰的是值，有可能在一轮中两个指针都移动
     */
    @Test
    public void No49() {
        nthUglyNumber(10);
    }

    public int nthUglyNumber(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        int[] result = new int[n];
        result[0] = 1;
        int p2 = 0;
        int p3 = 0;
        int p5 = 0;

        for (int i = 1; i < n; i++) {
            result[i] = Math.min(result[p2] * 2, Math.min(result[p3] * 3, result[p5] * 5));
            if (result[i] == result[p2] * 2) p2++;
            if (result[i] == result[p3] * 3) p3++;
            if (result[i] == result[p5] * 5) p5++;
        }

        return result[n - 1];
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
