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
     * 虽然搞了点小手段希望能优化，但也没什么效果，就这样吧
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
     * 1ms / 82%
     *
     * 总结
     *
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head=l1;
        int add=0;
        while (l1 != null || l2 != null) {
            int sum = l1.val + l2.val + add;
            add=0;
            if (sum > 9) {
                add=1;
                sum=sum-10;
            }
            l1.val=sum;
            l1=l1.next;
            l2=l2.next;
        }
        if (add!=0){
            l1=new ListNode(add);
        }
        return head;
    }

    @Test
    public void test2(){
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
        ListNode node11=new ListNode(1);
        node10.next=node11;

        Assert.assertEquals(node10, addTwoNumbers(node6,node9));

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

}
