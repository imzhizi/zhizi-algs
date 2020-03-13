package com.imzhizi.algs.剑指LeetCode;

import com.imzhizi.algs.base.TreeNode;
import org.junit.Test;

import java.util.*;

/**
 * created by zhizi
 * on 3/11/20 17:19
 */
public class TOP40 {
    /**
     *
     */
    @Test
    public void No31() {

    }

    /**
     *
     */
    @Test
    public void No32() {

    }

    /**
     * [面试题32 - II. 从上到下打印二叉树 II - 力扣（LeetCode）](https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof/ )
     */
    @Test
    public void No32II() {

    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        List<TreeNode> stack = new ArrayList<>();
        stack.add(root);
        int index = 0;
        while (index != stack.size()) {
            int size = stack.size();
            List<Integer> list = new ArrayList<>();
            for (; index < size; index++) {
                TreeNode temp = stack.get(index);
                list.add(temp.val);
                if (temp.left != null) stack.add(temp.left);
                if (temp.right != null) stack.add(temp.right);
            }
            result.add(list);
        }
        return result;
    }

    /**
     *
     */
    @Test
    public void No33() {

    }

    /**
     *
     */
    @Test
    public void No34() {

    }

    /**
     * [面试题39. 数组中出现次数超过一半的数字 - 力扣（LeetCode）](https://leetcode-cn.com/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof/ )
     */
    @Test
    public void No39() {

    }

    public int majorityElement(int[] nums) {
        int count = 0;
        int result = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == result) {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                result = nums[i + 1];
            }
        }
        return result;
    }

    /**
     * [面试题40. 最小的k个数 - 力扣（LeetCode）](https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/ )
     */
    @Test
    public void No40() {

    }

    // 巨慢的实现
    // 时间复杂度为 O(k*n)
    public int[] getLeastNumbers(int[] arr, int k) {
        int[] ks = new int[k];
        for (int i = 0; i < k; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
            ks[i] = arr[i];
        }
        return ks;
    }

    // 桶排序？时间复杂度为 O(n+10001)
    public int[] getLeastNumbers2(int[] arr, int k) {
        int[] res = new int[k];
        if (k == 0) return res;
        if (k == arr.length) return arr;

        //桶思想
        int[] bucket = new int[100001];
        for (int value : arr) {
            bucket[value]++;
        }

        //遍历 bucket,跳过元素0的下标
        int index = 0;
        for (int i = 0; i < bucket.length; i++) {
            // 主要用于潘通重复出现的数字
            while (bucket[i] != 0) {
                res[index++] = i;
                bucket[i]--;
                if (index == k) return res;
            }
        }
        return res;
    }
}
