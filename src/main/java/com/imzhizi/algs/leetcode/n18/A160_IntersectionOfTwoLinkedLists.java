package com.imzhizi.algs.leetcode.n18;

import com.imzhizi.algs.common.ListNode;

/**
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 *
 * For example, the following two linked lists:
 * A:          a1 → a2
 *                    ↘
 *                      c1 → c2 → c3
 *                    ↗
 * B:     b1 → b2 → b3
 * begin to intersect at node c1.
 * created by zhizi on 2018/6/25
 */
public class A160_IntersectionOfTwoLinkedLists {
    /**
     * 题目分析：
     * 题目要找两个链表的交集，返回结果要求不高，只要是交集的第一个节点即可；
     * 要怎么做呢？仔细分析两个链表，可以发现，如果是交集，那么A、B的剩下节点的长度应该是相同的；
     * 可以想到的一种办法是先行统计出两个链表的长度，然后根据长度把双方结点移动到相同的位置开始比较，即可找到
     *
     * 运行时长：
     *
     * 总结：
     * 好在一旦是交集则地址相同，只要比较了正确位置的第一个节点，就能得到结果。
     */
    static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = length(headA);
        int lenB = length(headB);
        while (lenA > lenB) {
            headA = headA.next;
            lenA--;
        }
        while (lenA < lenB) {
            headB = headB.next;
            lenB--;
        }
        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }

    static int length(ListNode node){
        int length = 0;
        while (node != null) {
            node = node.next;
            length++;
        }
        return length;
    }
}
