package com.imzhizi.algs.leetcode_2018;

import java.util.Arrays;

/**
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * <p>
 * Note:
 * The number of elements initialized in nums1 and nums2 are m and n respectively.
 * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
 * Example:
 * <p>
 * Input:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * Output: [1,2,2,3,5,6]
 * created by zhizi on 2018/5/6
 */
public class A88_MergeSortedArray {
    /**
     * 题目分析：
     * 使用了空间换时间的方法，整体实现上来看比较纠结，先把顺序合并到r[]中，然后重新复制到nums1[]中
     * nums1[]的长度为m+n，带来了许多不必要的问题
     * 或许出题人希望就地处理，但是就地处理总是难免要进行顺序移位，不可接受
     * 运行时长：5 ms
     *
     * 总结：无
     */
    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        int[] r = new int[m + n];
        int j = 0;
        int k = 0;
        for (int i = 0; i < m + n; i++) {
            if (j < m && (k == n || nums1[j] <= nums2[k])) {
                r[i] = nums1[j++];
            } else
                r[i] = nums2[k++];
        }

        for (int i = 0; i < m + n; i++) {
            nums1[i] = r[i];
        }
    }

    /**
     * 题目分析：先合并，再排序，十分无脑，当然效率也差
     * 运行时长：7 ms
     * 总结：无
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i < n; i++) {
            nums1[m + i] = nums2[i];
        }
        Arrays.sort(nums1);
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 0, 0, 0};
        int[] nums2 = {1, 5, 6};
        merge2(nums1, 3, nums2, 3);
    }
}
