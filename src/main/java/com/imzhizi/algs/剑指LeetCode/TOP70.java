package com.imzhizi.algs.剑指LeetCode;

import org.junit.Test;

import java.util.*;

/**
 * created by zhizi
 * on 3/12/20 17:38
 */
public class TOP70 {
    /**
     * [面试题61. 扑克牌中的顺子 - 力扣（LeetCode）](https://leetcode-cn.com/problems/bu-ke-pai-zhong-de-shun-zi-lcof/ )
     */
    @Test
    public void No61() {

    }

    public boolean isStraight(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int[] duplicate = new int[14];
        int count = 0;

        for (int i = 0; i < 5; i++) {
            if (nums[i] != 0) {
                duplicate[nums[i]]++;
                if (duplicate[nums[i]] > 1) return false;

                min = Math.min(min, nums[i]);
                max = Math.max(max, nums[i]);
            } else {
                count++;
            }
        }

        return count == 0 ? (max - min + 1 == 5) : (max - min < 5);
    }

    /**
     * [面试题62. 圆圈中最后剩下的数字 - 力扣（LeetCode）](https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/ )
     * todo dp
     */
    @Test
    public void No62() {

    }

    // DP
    // todo
    public int lastRemaining1(int n, int m) {
        int last = 0;   //存活的最后一个人的位置
        for (int i = 2; i <= n; i++) {
            last = (last + m) % i;
        }
        return last;
    }

    // 模拟
    public int lastRemaining2(int n, int m) {
        List<Integer> loop = new ArrayList<>();
        for (int i = 0; i < n; i++) loop.add(i);
        int index = 0;
        while (loop.size() > 1) {
            index = (index + m - 1) % loop.size();
            loop.remove(index);
        }
        return loop.get(0);
    }

    /**
     * [面试题65. 不用加减乘除做加法 - 力扣（LeetCode）](https://leetcode-cn.com/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof/ )
     * todo 位运算
     */
    @Test
    public void No65() {
        System.out.println(add(111, 289));
    }

    // 使用位运算来计算加法
    // 核心是通过「异或」a、b计算剩下的值作为新 a，通过「与」a、b计算进位
    // 进位后得到的结果作为新 b，重复计算，直到不再产生新的进位为止
    public int add(int a, int b) {
        int xor = a ^ b;
        int and = a & b;

        while (and != 0) {
            and <<= 1;
            int temp = and & xor;
            xor ^= and;
            and = temp;
        }

        return xor;
    }

    /**
     * [面试题66. 构建乘积数组 - 力扣（LeetCode）](https://leetcode-cn.com/problems/gou-jian-cheng-ji-shu-zu-lcof/ )
     */
    @Test
    public void No66() {
    }

    // 更好的做法
    public int[] constructArr1(int[] a) {
        int length = a.length;
        if (length < 2) return a;

        int[] result = new int[length];
        int left = 1;
        int right = 1;

        for (int i = 0; i < length; i++) {
            result[i] = left;
            left *= a[i];
        }

        for (int i = length - 1; i > -1; i--) {
            result[i] *= right;
            right *= a[i];
        }
        return result;
    }

    // 由于结果是由两个值产生的，在求第二个值的时候就可以得出结果
    public int[] constructArr2(int[] a) {
        int length = a.length;
        if (length < 2) return a;

        int[] front = new int[length];
        int[] back = new int[length];
        front[0] = a[0];
        back[length - 1] = a[length - 1];

        for (int i = 1; i < length - 1; i++) {
            front[i] = front[i - 1] * a[i];
            back[length - 1 - i] = back[length - i] * a[length - 1 - i];
        }

        for (int i = 1; i < length - 1; i++) {
            a[i] = front[i - 1] * back[i + 1];
        }

        a[0] = back[1];
        a[length - 1] = front[length - 2];
        return a;
    }
}
