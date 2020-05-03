package com.imzhizi.algs.Common;

import com.imzhizi.algs.base.ListNode;
import com.imzhizi.algs.base.TreeNode;
import org.junit.Test;

import java.util.Random;

/**
 * created by zhizi
 * on 4/3/20 09:28
 */
public class 排序算法 {
    /**
     * [148. 排序链表 - 力扣（LeetCode）](https://leetcode-cn.com/problems/sort-list/ )
     * 使用了插入排序，没想到这么慢
     */
    public ListNode sortList(ListNode head) {
        ListNode result = new ListNode(0);
        ListNode node = result;
        result.next = head;

        while (node.next != null) {
            ListNode fast = node.next;
            while (fast.next != null) {
                if (fast.next.val < node.next.val) {
                    ListNode temp = fast.next;
                    fast.next = fast.next.next;
                    temp.next = node.next;
                    node.next = temp;
                } else {
                    fast = fast.next;
                }
            }
            node = node.next;
        }

        return result.next;
    }


    /**
     * 快排的思想就是分而治之，可以使用递归来做
     * 选定一个元素，然后将其他数据跟其比较放在两侧，而这个元素就相当于到达了最终位置
     * 然后递归地对它的左边和右边执行相同操作，直到数组有序
     * <p>
     * 具体地说，把选定的元素放在数组末端，设置一个指针追踪第一个比其大的值，然后逐个和其比较，
     * 如果比其大就不管就继续遍历
     * 如果比其小就把它和指针指向的值交换，这样原来第一个比其大的值就变成了最后一个比他小的值直到遍历结束
     */
    @Test
    public void 快速排序() {
        int[] nums = {4, 7, 5, 6, 3, 8, 2};
        quickSortX(nums, 0, nums.length - 1);

        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + ",");
        }
    }

    private static final Random RANDOM = new Random();

    public void quickSortX(int[] nums, int head, int tail) {
        // 结束条件
        if (head >= tail) return;

        // 将循环不变量放在tail
        swap(nums, RANDOM.nextInt(tail - head + 1) + head, tail);
        //追踪分界点
        int pivot = head;
        // 遍历
        for (int i = head; i < tail; i++) {
            if (nums[i] < nums[tail]) {
                swap(nums, pivot, i);
                pivot++;
            }
        }
        // 将循环不变量放在分界点
        swap(nums, pivot, tail);

        // 递归分治
        quickSortX(nums, head, pivot - 1);
        quickSortX(nums, pivot + 1, tail);
    }

    // 奇奇怪怪一号
    // 一个问题是，总是把各种交换都变成排序的一部分，这是应该避免的
    public void quickSort(int[] nums, int start, int end) {
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

        quickSort(nums, start, end);
        quickSort(nums, end + 1, tail);
    }

    // 奇奇怪怪二号
    public void quickSort2(int[] nums, int head, int tail) {
        if (head >= tail) return;

        int lead = head;
        int mid = nums[lead];
        int last = tail;
        for (int i = head + 1; i <= last; i++) {
            if (mid < nums[i]) {
                int temp = nums[last];
                nums[last] = nums[i];
                nums[i] = temp;
                last--;
                i--;
            } else {
                nums[lead] = nums[i];
                lead++;
                ;
            }
        }
        nums[lead] = mid;
        if (head + 1 == tail) return;
        quickSort2(nums, head, lead - 1);
        quickSort2(nums, lead + 1, tail);
    }

    /**
     * 同样是基于分治思想
     * 主要分为两部分
     * 一部分是分好之后如何治，分到两个然后更换顺序？还是长度小于4就插入排序/冒泡排序
     * 一部分是治好之后如何合并
     * 合并其实就是两个有序数组的合并，需要额外空间支持
     */
    @Test
    public void 归并排序() {
        int[] nums = {4, 7, 5, 6, 3, 8, 2};
        result = new int[nums.length];
        divSort(nums, 0, nums.length - 1);

        for (int num : nums) {
            System.out.print(num + ",");
        }
    }

    static int[] result;

    public void divSort(int[] nums, int head, int tail) {
        if (head >= tail) {
            return;
        }
        if ((tail - head) < 6) {
            insertSort(nums, head, tail);
            return;
        }
        int mid = (head + tail) >>> 1;
        divSort(nums, head, mid);
        divSort(nums, mid + 1, tail);

        mergeOfTwoSortedArray(nums, head, mid, tail);
    }

    public void mergeOfTwoSortedArray(int[] nums, int head, int mid, int tail) {
        System.arraycopy(nums, head, result, head, tail - head + 1);
        int left = head;
        int right = mid + 1;

        for (int loc = head; loc <= tail && left < mid + 1; loc++) {
            if (right == tail + 1 || result[left] <= result[right]) {
                nums[loc] = result[left];
                left++;
            } else {
                nums[loc] = result[right];
                right++;
            }
        }
    }

    /**
     * 插入排序
     * 作为一个被其他排序调用的排序，他的最大长度和最小长度都应该是符合参数，而不是硬编码
     */
    public void insertSort(int[] nums, int head, int tail) {
        for (int i = head + 1; i <= tail; i++) {
            int k = i;
            int temp = nums[i];

            while (k > head && temp < nums[k - 1]) {
                nums[k] = nums[k - 1];
                k--;
            }
            nums[k] = temp;
        }
    }

    /**
     * 冒泡排序
     */
    public void bubbleSort(int[] nums, int head, int tail) {

    }

    /**
     * 堆排序
     * 以大根堆为例
     * 堆讲求的是元素不断地加入到堆中，不断地调整堆，最终形成一个根堆
     * 所以堆排序也需要先构造出来堆，然后再删除堆顶元素
     */
    @Test
    public void 堆排序() {
        int[] nums = new int[]{-4, -7, -1, 0, 0, -5, -1};
        heapSort(nums, 0, nums.length);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    public void heapSort(int[] nums, int head, int tail) {
        for (int i = 1; i < tail; i++) {
            buildHeap(nums, head, i);
        }

        for (int i = tail - 1; i > 0; i--) {
            swap(nums, head, i);
            rebuildHeap(nums, i, head);
        }
    }

    public void buildHeap(int[] nums, int head, int target) {
        if (target <= head) return;

        int p = (target - 1) / 2;

        if (nums[target] > nums[p]) {
            swap(nums, target, p);
        }
        buildHeap(nums, head, p);
    }

    public void rebuildHeap(int[] nums, int tail, int target) {
        int c = (target + 1) * 2;
        if (c > tail) return;
        else if (c == tail) {
            c = c - 1;
        } else {
            c = nums[c] > nums[c - 1] ? c : c - 1;
        }
        if (nums[target] < nums[c]) swap(nums, target, c);

        rebuildHeap(nums, tail, c);

    }


    @Test
    public void 最小数() {
        int[] nums = new int[]{3, 30, 34, 5, 9};
        System.out.println(minNumber(nums));
    }

    public String minNumber(int[] nums) {
        quickSorts(nums, 0, nums.length - 1);

        StringBuilder sb = new StringBuilder();
        for (int i : nums) {
            sb.append(i);
        }
        return sb.toString();
    }

    public void quickSorts(int[] nums, int head, int tail) {
        if (head >= tail) return;

        int pivot = head;
        for (int i = head; i < tail; i++) {
            if (compare(nums[tail], nums[i])) {
                swap(nums, pivot++, i);
            }
        }
        swap(nums, pivot, tail);

        quickSorts(nums, head, pivot - 1);
        quickSorts(nums, pivot, tail);

    }

    public boolean compare(int a, int b) {
        String str1 = a + "" + b;
        String str2 = b + "" + a;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) > str2.charAt(i)) {
                return true;
            } else if (str1.charAt(i) < str2.charAt(i)) {
                return false;
            }
        }
        return true;
    }


    public void swap(int[] nums, int i, int k) {
        int temp = nums[i];
        nums[i] = nums[k];
        nums[k] = temp;
    }
}
