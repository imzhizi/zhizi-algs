package com.imzhizi.algs.common;

import org.junit.Test;

/**
 * created by zhizi
 * on 4/27/20 20:17
 */
public class 查找算法 {
    @Test
    public void 二分查找() {
        int[] nums = new int[]{1, 3, 6, 8, 11, 45};
        System.out.println(binarySearch(nums, 0));
        System.out.println(binarySearch(nums, 11));
    }

    public int binarySearch(int[] nums, int target) {
        // 意味着我们要在 [0,n) 中查找target
        // 边界就是所谓的循环不变量
        int head = 0, tail = nums.length;
        // 当范围为[0,0)的时候其实已经不指向任何数字，所以head需要小于tail
        while (head < tail) {
            // head + tail may overflow
            // head + (tail-head)/2 is better
            int mid = head + (tail - head) / 2;
            if (target == nums[mid]) {
                return mid;
            } else if (target > nums[mid]) {
                // left is close, so next is ok
                head = mid + 1;
            } else {
                // right is open, so tail has to greater than new edge
                tail = mid;
            }
        }

        return -1;
    }
}
