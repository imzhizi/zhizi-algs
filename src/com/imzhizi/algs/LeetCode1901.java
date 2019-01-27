package com.imzhizi.algs;

import org.junit.Assert;
import org.junit.Test;

public class LeetCode1901 {
    /**
     * 可以根据题目序号或题目名称搜索找到对应题目
     * 名称包含在链接当中，序号为 test 函数后面的数字
     * 一道题可能有多个解法，欢迎反馈
     */



    /**
     * 题目
     * [Middle of the Linked List - LeetCode](https://leetcode.com/problems/middle-of-the-linked-list/)
     *
     * 分析
     * 最近懈怠了许多，一直没刷题，新的一年也要加油啊～
     * 题目就是很蠢的找一个链表的中间节点，没想到什么好办法，就老老实实遍历，时间复杂度为 O(n+n/2)
     *
     * 时长
     * 1ms / 82%
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
     * 题目
     * [Add Two Numbers - LeetCode](https://leetcode.com/problems/add-two-numbers/)
     *
     * 分析
     * 两个链表中的数字逐个相加，主要问题就是进位问题，如果选一个链表存储结果，当链表不够的时候需要新建结点
     *
     * 时长
     * 23ms / 82%
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
    public void test2(){
        ListNode node = new ListNode(0);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(3);
        node1.next=node2;
        node2.next=node3;

        ListNode node4 = new ListNode(5);
        ListNode node5 = new ListNode(6);
        ListNode node6 = new ListNode(4);
        node4.next=node5;
        node5.next=node6;


        ListNode node7 = new ListNode(7);
        ListNode node8 = new ListNode(0);
        ListNode node9 = new ListNode(8);
        node7.next=node8;
        node8.next=node9;

        Assert.assertEquals(node7, addTwoNumbers(node1,node4));

        ListNode node10=new ListNode(2);
        node10.next= new ListNode(1);

        Assert.assertEquals(node10, addTwoNumbers(node6,node9));

        Assert.assertEquals(node10, addTwoNumbers(node10,node));

        Assert.assertEquals(node10, addTwoNumbers(node,node10));
    }



    /**
     * 题目
     * [Delete Node in a Linked List - LeetCode](https://leetcode.com/problems/delete-node-in-a-linked-list/)
     *
     * 分析
     * 其实没太看懂这道题的意思，个人理解是为 ListNode 编写一个成员函数，功能是删除一个特定的 node
     *
     * 时长
     * N/A
     *
     * 总结
     * 并没有弄明白这道题的点，但对于链表的操作，学到新的一招
     * 对于 head，如果想要删除自身，那么可以把 next 的 val 复制过来，然后把 next 给删掉
     */

    @Test
    public void test237(){
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
     * 题目
     * [Rotate List - LeetCode](https://leetcode.com/problems/rotate-list/)
     *
     * 分析
     * 旋转链表，两种思路，一种是实现一个链表旋转函数，然后根据输入的 k 的不同执行不同的次数
     * 根据长度直接找到旋转的位置，直接找到旋转的最终状态，打算试试这种思路
     *
     * 时长
     * 7ms / 97%
     *
     * 总结
     * 执行了第二种方法，过滤掉了不需要旋转的可能（k%n为0），直接找到最终循转状态
     * 关键点为把后一部分挪到前一部分的前面，也就是要让尾的 next 指向 head，然后断掉新 head 前的连接
     * 巧妙点在于计算链表长度的时候做成了循环链表， 仅需断开而不需要再去链接 head
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k==0) return head;

        ListNode node = head;

        //计算 head 长度
        int n = 1;
        while (node.next != null) {
            n++;
            node = node.next;
        }
        if (k % n == 0) return head;

        // 直接做成循环链表，在必要的时候断开即可
        node.next=head;

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
    public void test61(){
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(5);
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;

        System.out.println(rotateRight(node1,2));
    }


    /**
     * 题目
     *
     *
     * 分析
     *
     *
     * 时长
     * 1ms / 82%
     *
     * 总结
     *
     */
    @Test
    public void test(){
    }
}
