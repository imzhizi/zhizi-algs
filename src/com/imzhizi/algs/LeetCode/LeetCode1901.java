package com.imzhizi.algs.LeetCode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class LeetCode1901 {
    /**
     * 可以根据题目序号或题目名称搜索找到对应题目
     * 名称包含在链接当中，序号为 test 函数后面的数字
     * 一道题可能有多个解法，欢迎反馈
     */


    /**
     * 题目: [Middle of the Linked List - LeetCode](https://leetcode.com/problems/middle-of-the-linked-list/)
     *
     * 分析
     * 最近懈怠了许多，一直没刷题，新的一年也要加油啊～
     * 题目就是很蠢的找一个链表的中间节点，没想到什么好办法，就老老实实遍历，时间复杂度为 O(n+n/2)
     *
     * 时长: 1ms / 82%
     *
     * 总结
     * 虽然搞了点小手段希望能优化（也就是判断链表长度，一次移动两个结点），但也没什么效果，就这样吧
     */
    public ListNode middleNode(ListNode head) {
        ListNode node = head;
        int num = 0;
        while (node != null) {
            node = node.next;
            num++;
        }

        int mid = num / 2;

        for (int i = 0; i < mid; i++) {
            if (mid - i > 2) {
                head = head.next.next;
                i++;
            } else {
                head = head.next;
            }
        }
        return head;
    }

    @Test
    public void test876() {
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);
        ListNode node5 = new ListNode(6);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);

        Assert.assertEquals(node3, middleNode(node1));
    }


    /**
     * 题目: [Add Two Numbers - LeetCode](https://leetcode.com/problems/add-two-numbers/)
     *
     * 分析
     * 两个链表中的数字逐个相加，主要问题就是进位问题，如果选一个链表存储结果，当链表不够的时候需要新建结点
     *
     * 时长: 23ms / 82%
     *
     * 总结
     * 新建结点问题比预想中的要复杂，因为新建的结点需要有前置结点，因此需要总是用前置结点来操作
     * 两链表长度可能会不同，因此在过程中要拼接链表形成最长的结果链表
     * 进位判断使用  add = sum / 10; sum = sum % 10; 比最初使用 if(sum>9) 效果好很多
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode result = head;
        head.next = l1;
        int add = 0;
        int sum = 0;

        while (l1 != null || l2 != null) {
            if (l1 == null) {
                sum = l2.val + add;
                head.next = l2;
                l2 = l2.next;
            } else if (l2 == null) {
                sum = l1.val + add;
                head.next = l1;
                l1 = l1.next;
            } else {
                sum = l1.val + l2.val + add;
                l1 = l1.next;
                l2 = l2.next;
            }

            add = sum / 10;
            sum = sum % 10;

            head.next.val = sum;
            head = head.next;
        }

        if (add != 0) {
            head.next = new ListNode(add);
        }

        return result.next;
    }

    @Test
    public void test2() {
        ListNode node = new ListNode(0);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;

        ListNode node4 = new ListNode(5);
        ListNode node5 = new ListNode(6);
        ListNode node6 = new ListNode(4);
        node4.next = node5;
        node5.next = node6;


        ListNode node7 = new ListNode(7);
        ListNode node8 = new ListNode(0);
        ListNode node9 = new ListNode(8);
        node7.next = node8;
        node8.next = node9;

        Assert.assertEquals(node7, addTwoNumbers(node1, node4));

        ListNode node10 = new ListNode(2);
        node10.next = new ListNode(1);

        Assert.assertEquals(node10, addTwoNumbers(node6, node9));

        Assert.assertEquals(node10, addTwoNumbers(node10, node));

        Assert.assertEquals(node10, addTwoNumbers(node, node10));
    }


    /**
     * 题目: [Delete Node in a Linked List - LeetCode](https://leetcode.com/problems/delete-node-in-a-linked-list/)
     *
     * 分析
     * 其实没太看懂这道题的意思，个人理解是为 ListNode 编写一个成员函数，功能是删除一个特定的 node
     *
     * 时长: N/A
     *
     * 总结
     * 并没有弄明白这道题的点，但对于链表的操作，学到新的一招
     * 对于 head，如果想要删除自身，那么可以把 next 的 val 复制过来，然后把 next 给删掉
     */

    @Test
    public void test237() {
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(5);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(9);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);

        node1.deleteNode(node2);
    }

    /**
     * 题目: [Rotate List - LeetCode](https://leetcode.com/problems/rotate-list/)
     *
     * 分析
     * 旋转链表，两种思路，一种是实现一个链表旋转函数，然后根据输入的 k 的不同执行不同的次数
     * 根据长度直接找到旋转的位置，直接找到旋转的最终状态，打算试试这种思路
     *
     * 时长: 7ms / 97%
     *
     * 总结
     * 执行了第二种方法，过滤掉了不需要旋转的可能（k%n为0），直接找到最终循转状态
     * 关键点为把后一部分挪到前一部分的前面，也就是要让尾的 next 指向 head，然后断掉新 head 前的连接
     * 巧妙点在于计算链表长度的时候做成了循环链表， 仅需断开而不需要再去链接 head
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;

        ListNode node = head;

        //计算 head 长度
        int n = 1;
        while (node.next != null) {
            n++;
            node = node.next;
        }
        if (k % n == 0) return head;

        // 直接做成循环链表，在必要的时候断开即可
        node.next = head;

        // 1->2->3->4->null 循转3次，也就是倒数第3个结点为 head
        // 此时需要的结点就位于head的倒数第3个结点之前的那个结点，k：4-3=1 也就是目标结点
        k = n - (k % n);
        node = head;
        // 而移动到第 k 个结点需要移动 k-1 次
        while (--k != 0) {
            node = node.next;
        }

        ListNode result = node.next;
        node.next = null;

        return result;
    }

    @Test
    public void test61() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        System.out.println(rotateRight(node1, 2));
    }


    /**
     * 题目: [Squares of a Sorted Array - LeetCode](https://leetcode.com/problems/squares-of-a-sorted-array/)
     *
     * 分析
     * 将一个有序的递增整数数组平方，结果仍然保持有序、递增，关键点就在于负数的平方
     * 不难想到最大的数字一定来自于数组中最大或者最小的，也就是数组的左右两端
     * 左右两端比较平方或绝对值均可，总之更大的放在结果数组的末尾，逐次前移
     *
     * 时长: 7ms / 96%
     *
     * 总结
     */
    public int[] sortedSquares(int[] A) {
        int length = A.length;
        int[] result = new int[length];
        int head = 0;
        int foot = length - 1;

        for (int i = length - 1; i >= 0; i--) {
            if (Math.abs(A[head]) <= Math.abs(A[foot])) {
                result[i] = A[foot] * A[foot];
                foot--;
            } else {
                result[i] = A[head] * A[head];
                head++;
            }
        }
        return result;
    }


    @Test
    public void test977() {
        int[] a = {-4, -1, 0, 3, 10};
        int[] b = {0, 1, 9, 16, 100};
        Assert.assertArrayEquals(b, sortedSquares(a));
    }

    /**
     * 题目: [Sort Array By Parity - LeetCode](https://leetcode.com/problems/sort-array-by-parity/)
     *
     * 分析
     * 把数组中的元素分成偶数和奇数两部分，可以想到的方法就是遍历数组，然后在结果数组中从两端开始填充
     *
     * 时长: 法一：10ms / 法二：9ms - 90%
     *
     * 总结
     * 第一种思路和分析中一样，非常规整的写法
     * 但第一种方法实际上做了许多的无效移动，同时，似乎可以直接在原数组上存储结果
     * 由于是不要求顺序、仅做奇偶划分，所以假如已经是奇数/偶数，则原地保留，无需移动
     * 所以有了第二种做法，在空间上和时间上都有优化
     */
    public int[] sortArrayByParity(int[] A) {
        int length = A.length;
        int[] result = new int[length];
        int head = 0;
        int foot = length - 1;
        for (int i : A) {
            if (i % 2 == 0) {
                result[head++] = i;
            } else {
                result[foot--] = i;
            }
        }
        return result;
    }

    public int[] sortArrayByParity2(int[] A) {
        int foot = A.length - 1;
        int temp;
        for (int i = 0; i < foot; i++) {
            if (A[i] % 2 != 0) {
                while (A[foot] % 2 != 0 && foot > i)
                    foot--;
                temp = A[foot];
                A[foot] = A[i];
                A[i] = temp;
                foot--;
                i--;
            }
        }
        return A;
    }

    @Test
    public void test905() {
        int[] a = {-4, -1, 0, 3, 10};
        a = sortArrayByParity(a);
        for (int i : a)
            System.out.println(i);

        a = sortArrayByParity2(a);
        for (int i : a)
            System.out.println(i);
    }

    /**
     * 题目: [Longest Continuous Increasing Subsequence - LeetCode](https://leetcode.com/problems/longest-continuous-increasing-subsequence/)
     *
     * 分析
     * 求最长连续递增子串的长度，无外乎遍历一次，计算每一个递增子串的长度，把其中最大的保存起来
     *
     * 时长: 2ms / 100%
     *
     * 总结
     * 将过程中的较大子串长度保存起来的位置值得考虑，可以每次循环都保存，但其实每次子串结束保存即可
     */
    public int findLengthOfLCIS(int[] nums) {
        if (nums.length == 0) return 0;

        int m = 1;
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                m++;
            } else {
                max = Math.max(m, max);
                m = 1;
            }
        }
        return Math.max(m, max);
    }

    @Test
    public void test674() {
        int[] a = {1, 3, 5, 4, 7};
        Assert.assertEquals(3, findLengthOfLCIS(a));
    }

    /**
     * 题目: [Flipping an Image - LeetCode](https://leetcode.com/problems/flipping-an-image/)
     *
     * 分析
     * 把一个矩阵先水平翻转，然后取反，可以想到的是，两个操作可以同步进行
     * 同时取反可以通过和 1 异或实现，1^1=0 1^0=1
     *
     * 时长: 3ms - 100% / 4ms
     *
     * 总结
     * 似乎 LeetCode 的 OJ 有点问题，时长并不完全靠谱，第一种方法是对称交换的方法
     * 第二种则是新建一个结果数组，然后依次将结果保存进去，其实 意义不大
     */
    public int[][] flipAndInvertImage(int[][] A) {
        if (A.length == 0 || A[0].length == 0) return A;
        int height = A.length;
        int width = A[0].length;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < (width + 1) / 2; j++) {
                int head = A[i][j];
                A[i][j] = A[i][width - 1 - j] ^ 1;
                A[i][width - 1 - j] = head ^ 1;
            }
        }

        return A;
    }

    public int[][] flipAndInvertImage2(int[][] A) {
        if (A.length == 0 || A[0].length == 0) return A;
        int height = A.length;
        int width = A[0].length;
        int[][] result = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                result[i][j] = A[i][width - 1 - j] ^ 1;
            }
        }

        return result;
    }

    @Test
    public void test832() {
        int[][] A = {
                {1, 1, 0},
                {1, 0, 1},
                {1, 1, 1}
        };
        int[][] B = {
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        Assert.assertArrayEquals(B, flipAndInvertImage2(A));
        // 数组对象均为地址传递，方法一对A进行了修改，所以先执行方法二
        Assert.assertArrayEquals(B, flipAndInvertImage(A));
    }

    /**
     * 题目: [ Array Partition I - LeetCode ](https://leetcode.com/problems/array-partition-i/)
     *
     * 分析
     * 把一个数组中的数字结成一对一对的，然后计算其中较小数字的和
     * 可以想到的一种方法是先排序，然后计算偶数位上的和
     *
     * 时长: 21 ms / 83 %
     *
     * 总结
     * 也想过在排序的同时进行比较和求和，但还是失败了，目前这种方法也还可以接受
     */
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i += 2) {
            sum += nums[i];
        }
        return sum;
    }

    @Test
    public void test561() {
        int[] nums = {1, 4, 3, 2};
        Assert.assertEquals(4, arrayPairSum(nums));
    }

    /**
     * 题目: [Sort Array By Parity II - LeetCode](https://leetcode.com/problems/sort-array-by-parity-ii/)
     *
     * 分析
     * 最近几道数列的题目都还不错，支持多种答案，题意也似乎有一些实际场景在其中
     * 这一题是让奇数数字位于奇数位，偶数在偶数位保存
     * 最简单的思路，遍历数组，逐个保存在结果数组中，这种方法的时间复杂度和空间复杂度都是 O(n)
     * 如果不新建结果数组，可以只遍历奇数位，在出现偶数时到偶数位遍历
     *
     * 时长: 5 ms / 98 %
     *
     * 总结
     * 无
     */
    public int[] sortArrayByParityII(int[] A) {
        int odd = 1;
        for (int even = 0; even < A.length; even += 2) {
            if (A[even] % 2 != 0) {
                while (A[odd] % 2 != 0) odd += 2;
                int temp = A[even];
                A[even] = A[odd];
                A[odd] = temp;
                odd += 2;
            }
        }
        return A;
    }

    @Test
    public void test922() {
        int[] a = {4, 2, 5, 7};
        int[] b = {4, 5, 2, 7};
        Assert.assertArrayEquals(b, sortArrayByParityII(a));
    }

    /**
     * 题目: [Fibonacci Number - LeetCode](https://leetcode.com/problems/fibonacci-number/)
     *
     * 分析
     * 很简单的一道题，很容易想到循环和递归两种做法
     *
     * 时长: 1ms - 100% / 12ms
     *
     * 总结
     * 无
     */
    public int fib(int N) {
        int llast = 0;
        int last = 1;
        for (int i = 2; i <= N; i++) {
            int temp = last;
            last = llast + last;
            llast = temp;
        }
        return last;
    }

    public int fib2(int N) {
        if (N == 0) return 0;
        if (N == 1) return 1;
        return fib2(N - 1) + fib2(N - 2);
    }

    @Test
    public void test509() {
        System.out.println(fib(5));
        System.out.println(fib(13));
        System.out.println(fib(25));
    }
}
