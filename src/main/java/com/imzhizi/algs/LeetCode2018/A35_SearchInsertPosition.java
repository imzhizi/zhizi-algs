package com.imzhizi.algs.LeetCode2018;

/**
 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 * You may assume no duplicates in the array.
 * <p>
 * Example 1:
 * Input: [1,3,5,6], 5
 * Output: 2
 * created by zhizi on 2018/4/22
 */
public class A35_SearchInsertPosition {
    /**
     * 函数描述：
     * 一次遍历，如果大于前面，小于等于后面即可插入，否则说明比所有都大，插入数组末尾
     * 运行时长：6 ms
     *
     * 总结：总是要考虑取左边等于还是右边等于，使得总是有一位被忽略考虑，需要额外处理
     */
    public static int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (target<=nums[i])
                return i;
        }
        return nums.length;
    }

    /**
     * 函数描述：
     * 参考二分查找思想便编写的查找算法，可能因为数据较少，时间效率差别不大
     * 运行时长：5 ms
     *
     * 总结：本来也是用的二分查找，但写的不太好，之后看了下书中的讲解，写了一个经典版本的二分查找，效果不错
     */
    public static int searchInsert2(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int mid;
        while (low<=high) {
            mid = (high + low) / 2;
            if (target == nums[mid]) {
                return mid;
            } else if (target < nums[mid]) {
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        System.out.println(searchInsert(new int[]{1}, 1));//0
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 2));//1
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 7));//4
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 0));//0
    }
}
