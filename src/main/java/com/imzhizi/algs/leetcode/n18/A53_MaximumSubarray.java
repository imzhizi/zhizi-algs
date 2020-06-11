package com.imzhizi.algs.leetcode.n18;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number)
 * which has the largest sum and return its sum.
 * <p>
 * Example:
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * created by zhizi on 2018/4/24
 */
public class A53_MaximumSubarray {
    /**
     * 题目分析：
     * 尝试使用动态规划来写这道题，将问题域进行转化；
     * 使用数组 sum[j]，j表示结尾为j的子串的最大和， ⚠注意：️是️以j为结尾，所以这个结果中一定包含了nums[j]；
     * 如果j结尾的最的子列长度大于1，那么sum[j]=sum[j-1]+num[j]；
     * 子串必定以数据中的某个数字结尾，因此最大和也就是sum[]中的最大值
     * 运行时长：15 ms
     *
     * 总结：
     * 这道题学到很多，亲手把一个三重循环优化到二重再到一重，还少了一次排序，可见算法多么的伟大！
     * 所有保存结果找最大值都可以参考这道题中最大值的求法，边计算边寻找最大值
     * 另一方面如果存在递推，一个结果可能被多次计算，那就可以通过数表来查找数据进而减少计算
     */
    public static int maxSubArray3(int[] nums) {
        int max = nums[0];
        int lmax= nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (lmax > 0) {
                lmax = lmax + nums[i];
            } else {
                lmax = nums[i];
            }
            max = Math.max(max, lmax);
        }
        return max;
    }

    /**
     * 题目分析：
     * 最初只想到穷举法，通过三重循环直接把所有可能的结果累加计算出来，然后通过排序找到最大值
     * 经过优化成为二重循环，因为从i到n的和，都可以通过1到n的和减去1到i-1的和得出，可以节约一重循环
     * 运行时长：133ms / 1.5%
     *
     * 总结：见上方
     */
    public static int maxSubArray2(int[] nums) {
        int max = nums[0];
        int temp = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (i == j)
                    temp = nums[i];//每行重置
                else
                    temp = temp + nums[j];
                max = Math.max(max, temp);//边计算边寻找最大值
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(maxSubArray2(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }
}
