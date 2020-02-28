package com.imzhizi.algs.剑指LeetCode;

import org.junit.Test;

public class TOP10 {
    /**
     * [面试题03. 数组中重复的数字 - 力扣（LeetCode）](https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/ )
     */
    @Test
    public void No3() {

    }

    boolean duplicate(int[] numbers, int length, int[] duplication) {
        boolean[] k = new boolean[length];
        for (int i = 0; i < k.length; i++) {
            if (k[numbers[i]]) {
                duplication[0] = numbers[i];
                return true;
            }
            k[numbers[i]] = true;
        }
        return false;
    }

    public int findRepeatNumber(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) {
                index = nums[i];
            } else {
                index = -nums[i];
            }
            if (nums[index] < 0) {
                return index;
            } else {
                nums[index] = -nums[index];
            }
        }
        return 0;
    }

    /**
     * [面试题04. 二维数组中的查找 - 力扣（LeetCode）](https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/ )
     */
    @Test
    public void No4() {

    }

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int r=matrix.length;
        if(r==0) return false;
        int c=matrix[0].length;
        if(c==0) return false;

        int m=0;
        int n=c-1;
        while(m<r&&n>-1){
            if(target<matrix[m][n]){
                n--;
            }else if(target>matrix[m][n]){
                m++;
            }else{
                return true;
            }
        }
        return false;
    }


    /**
     *
     */
    @Test
    public void No5() {

    }

    /**
     *
     */
    @Test
    public void No6() {

    }

    /**
     *
     */
    @Test
    public void No7() {

    }

    /**
     *
     */
    @Test
    public void No8() {

    }
}
