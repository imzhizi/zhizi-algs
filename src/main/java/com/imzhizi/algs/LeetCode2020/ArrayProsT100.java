package com.imzhizi.algs.LeetCode2020;

import org.junit.Test;

public class ArrayProsT100 {
    /**
     * [153. 寻找旋转排序数组中的最小值 - 力扣（LeetCode）](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/)
     */
    @Test
    public void No153() {

    }

    // 双头指针也不够快了
    public int findMin(int[] nums) {
        int h = 0;
        int t = nums.length - 1;
        while (nums[h] > nums[t]) {
            h++;
            t--;
        }

        if (h > 0 && nums[h] < nums[h - 1]) return nums[h];
        else if (t < nums.length - 1) return nums[t + 1];

        return nums[0];
    }

    // 二分查找，时间复杂度为多少呢？时间复杂度为logn
    public int findMin2(int[] nums) {
        int h = 0;
        int t = nums.length - 1;
        while (h < t) {
            int m = h + (t - h) / 2;
            if (nums[m] > nums[t]) {
                h = m + 1;
            } else {
                t = m;
            }
        }
        return nums[h];
    }

    /**
     *
     */
    @Test
    public void No154() {

    }

    /**
     *
     */
    @Test
    public void No162() {

    }

    /**
     *
     */
    @Test
    public void No163() {

    }

    /**
     *
     */
    @Test
    public void No167() {

    }


    /**
     *
     */
    @Test
    public void No169() {

    }


    /**
     *
     */
    @Test
    public void No89() {

    }


    /**
     *
     */
    @Test
    public void No209() {

    }

    /**
     *
     */
    @Test
    public void No216() {

    }

    /**
     *
     */
    @Test
    public void No217() {

    }
}
