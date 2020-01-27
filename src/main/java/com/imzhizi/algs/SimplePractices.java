package com.imzhizi.algs;

import org.junit.Test;

import java.util.ArrayList;

public class SimplePractices {
    @Test
    public void 快速排序() {
        int[] nums = {4, 7, 5, 6, 3, 8, 2};
        sort(nums, 0, nums.length - 1);

        for (int i = 0; i < nums.length; i++) {
            System.out.printf(nums[i] + ",");
        }

        ArrayList<int[]> ss=new ArrayList<>();
    }

    public void sort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }

        int tail = end;
        for (int i = start; i < end; i++) {
            int temp = nums[i + 1];
            if (nums[i] > temp) {
                nums[i + 1] = nums[i];
                nums[i] = temp;
            } else {
                nums[i + 1] = nums[end];
                nums[end] = temp;
                end--;
                i--;
            }
        }

        sort(nums, start, end);
        sort(nums, end + 1, tail);
    }
}
