package com.imzhizi.algs.LeetCode.LeetCode2018;

/**
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 *
 * Example 1:
 * Input: [1,2,3,4,5,6,7] and k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 *
 * created by zhizi on 7/25/18
 */
public class A189_RotateArray {
    /**
     * 题目分析：
     * 虽然翻译为旋转数组，其实是把数组最后的 k 个数字放到数组的最前面，可以简单地通过遍历解决 O(k*n)
     *
     * 运行时长：112 ms/52 ms
     *
     * 总结：
     * 运行时长果然非常长，尝试使用 Java API，没想到效果非常明显，运行时缩短了一倍，但还是一个**垃圾方法**
     */
    static void rotate1A(int[] nums, int k) {
        for (int i = 0; i < k; i++) {
            int end=nums[nums.length-1];
            for (int j = nums.length-1; j >0; j--) {
                nums[j]=nums[j-1];
            }
            nums[0]=end;
        }
    }
    static void rotate1B(int[] nums, int k) {
        for (int i = 0; i < k; i++) {
            int end=nums[nums.length-1];
            System.arraycopy(nums,0,nums,1,nums.length-1);
            nums[0]=end;
        }
    }

    /**
     * 题目分析：
     * 之前我是一个位置一个位置的挪动，但其实我完全可以一次性全部替换，但是交换是需要临时数组保存数据的
     *
     * 运行时长：0 ms
     *
     * 总结：
     * 这样数据交换的总次数为 l+k，运行时改善非常明显
     */
    static void rotate2(int[] nums, int k) {
        int l=nums.length;
        k=k%l;
        int[] head=new int[k];
        System.arraycopy(nums,l-k,head,0,k);
        System.arraycopy(nums,0,nums,k,l-k);
        System.arraycopy(head,0,nums,0,k);
    }


    /**
     * 题目分析：
     * 题目还给出了一个小问题「Could you do it in-place with O(1) extra space?」
     * 假如不建临时数组该怎么做呢？我还没来得及想，但不小心在讨论区学到了这个方法，不想再自己实现了，只总结一下思路好了，
     * 其实我觉得还是用了额外空间，或许这个方法并不是最优解……
     *
     * 思路：
     * 假如想要不借助额外空间，重点在于如何把数组尾部数据和头部数据互换
     * 大家的思路就是先把数组整体翻转，然后根据 k 分成前后两段，各自翻转即可。
     */

    public static void main(String[] args) {
        int[] nums=new int[]{1,2,3,4,5,6,7};
        rotate1A(nums,3);
//        rotate1B(nums,3);
//        rotate2(nums,3);
    }
}
