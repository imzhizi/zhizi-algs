package com.imzhizi.algs.leetcode;

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


    public static void main(String[] args) {

    }
}
