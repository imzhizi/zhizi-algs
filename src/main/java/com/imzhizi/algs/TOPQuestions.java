package com.imzhizi.algs;

import com.imzhizi.algs.base.ListNode;
import org.junit.Test;

/**
 * created by zhizi
 * on 2/23/20 20:28
 * [探索算法面试题汇总 - 力扣 (LeetCode)](https://leetcode-cn.com/explore/interview/card/top-interview-quesitons-in-2018/261/before-you-start/ ）
 */
public class TOPQuestions {
    // [136. 只出现一次的数字 - 力扣（LeetCode）](https://leetcode-cn.com/problems/single-number/ )
    // 异或的应用
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int i : nums) {
            result ^= i;
        }
        return result;
    }

    // [探索算法面试题汇总 - 力扣 (LeetCode)](https://leetcode-cn.com/explore/interview/card/top-interview-quesitons-in-2018/261/before-you-start/1107/ )
    // 多数元素
    // 一次遍历中找到它
    public int majorityElement(int[] nums) {
        int num = nums[0];
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == num) count++;
            else count--;
            if (count == 0 && i < nums.length - 1) num = nums[i + 1];
        }
        return num;
    }

    // [240. 搜索二维矩阵 II - 力扣（LeetCode）](https://leetcode-cn.com/problems/search-a-2d-matrix-ii/ )
    // 巧妙利用矩形的结构，简化问题域
    // 变量还是有意义不容易出错
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        int row = 0;
        int col = matrix[0].length - 1;

        while (col >= 0 && row < matrix.length) {
            if (target > matrix[row][col]) {
                row++;
            } else if (target < matrix[row][col]) {
                col--;
            } else {
                return true;
            }
        }
        return false;
    }

    // [88. 合并两个有序数组 - 力扣（LeetCode）](https://leetcode-cn.com/problems/merge-sorted-array/ )
    // 重点在于边界条件处理
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int loc = m + n - 1;
        m--;
        n--;

        while (m >= 0 && n >= 0) {
            if (nums1[m] > nums2[n]) {
                nums1[loc] = nums1[m];
                m--;
            } else {
                nums1[loc] = nums2[n];
                n--;
            }
            loc--;
        }

        if (loc >= 0 && n >= 0) {
            for (; loc >= 0; loc--) {
                nums1[loc] = nums2[n--];
            }
        }
    }


    @Test
    public void 最大乘积子序列() {
        System.out.println(maxProduct(new int[]{2, 3, -2, 4}));
    }

    public int maxProduct(int[] nums) {
        int min = 1;
        int max = 1;
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                int temp = min;
                min = max;
                max = temp;
            }

            max = Math.max(nums[i], max * nums[i]);
            min = Math.min(nums[i], min * nums[i]);
            System.out.println(max + " " + min);
            maxValue = Math.max(maxValue, max);
        }
        return maxValue;
    }


    // 环形链表
    public boolean hasCycle(ListNode head) {
        ListNode test = new ListNode(0);

        while (head != null && head.next != null) {
            if (head.next == test) {
                return true;
            }
            ListNode node = head;
            head = head.next;
            node.next = test;
        }

        return false;
    }


    // 需要使用归并排序
    // 需要使用递归
    // 归并相当于两个有序链表合并
    // 要用头插法、尾插法之类的方法
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode mid = slow.next;
        slow.next = null;
        ListNode first = sortList(head);
        ListNode second = sortList(mid);
        return merge(first, second);
    }

    public ListNode merge(ListNode a, ListNode b) {
        ListNode result = new ListNode(0);
        ListNode node = result;
        while (a != null && b != null) {
            if (a.val < b.val) {
                ListNode temp = a;
                a = a.next;
                node.next = temp;
            } else {
                ListNode temp = b;
                b = b.next;
                node.next = temp;
            }
            node = node.next;
        }

        if (a != null) {
            node.next = a;
        } else {
            node.next = b;
        }
        return result.next;
    }

}
