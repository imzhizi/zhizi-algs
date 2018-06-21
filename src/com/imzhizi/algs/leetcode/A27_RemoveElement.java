package com.imzhizi.algs.leetcode;

/**
 * 27. Remove Element
 * Given an array nums and a value val, remove all instances of that value in-place and return the new length.
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 * <p>
 * Example 1:
 * Given nums = [3,2,2,3], val = 3,
 * Your function should return length = 2, with the first two elements of nums being 2.
 * It doesn't matter what you leave beyond the returned length.
 * created by zhizi on 2018/4/20
 */
public class A27_RemoveElement {
    /**
     * 函数描述：
     * 应该是这道题的最简形式，主要是利用了题目一再强调的一个条件，
     * It doesn't matter what you leave beyond the new length.
     * 这说明只要关注不等于val的部分，后面不用考虑，所以可以直接遍历，把不等于val的拷贝到数组前面即可
     * 运行时长：10ms
     *
     * 总结：
     * 要认真读题，题目给的条件往往是快速解题的途径
     */
    public static int removeElement2(int[] nums, int val) {
        int position = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]!=val)
                nums[position++]=nums[i];
        }
        return position;
    }

    /**
     * 函数描述：
     * 原始的思路，也就是把等于的都拷贝到数组的最后，如果最后已经为val，那就倒数第二位
     * 整体上显得比较笨拙
     * 运行时长：10ms
     *
     * 总结：
     * 但其实运行也是一次遍历，时间复杂度仍未O(n)，但空间上和代码可读性上相差很大
     * 其实思想上对上一道题有借鉴
     */
    public static int removeElement(int[] nums, int val) {
        int position = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                if (nums[position] == val) {
                    position--;
                    i--;
                } else {
                    int temp = nums[position];
                    nums[position--] = nums[i];
                    nums[i] = temp;
                }
            }
            if (position == i) break;
        }
        return position+1;
    }



    public static void main(String[] args) {
        int[] nums1={3,2,2,3};
        int[] nums2 = {0, 1, 2, 2, 3, 0, 4, 2};
        int[] nums3 = {3,1,3,3,3};
        System.out.println(removeElement2(nums1,3));
        System.out.println(removeElement2(nums2, 2));
        System.out.println(removeElement2(nums3, 3));
    }
}
