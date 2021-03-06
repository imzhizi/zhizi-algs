package com.imzhizi.algs.common;

import java.util.Objects;

/**
 * created by zhizi on 2018/5/4
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    /**
     * [Delete Node in a Linked List - LeetCode](https://leetcode.com/problems/delete-node-in-a-linked-list/)
     */
    public void deleteNode(ListNode node) {
        ListNode head = this;
        while (head.val != node.val) {
            head = head.next;
        }
        head.val = head.next.val;
        head.next = head.next.next;
    }

    @Override
    public String toString() {
        if (this.next != null)
            return this.val + "->" + this.next.toString();
        else return this.val + "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListNode node = (ListNode) o;

        if (val != node.val) return false;
        return Objects.equals(next, node.next);
    }

    @Override
    public int hashCode() {
        int result = val;
        result = 31 * result + (next != null ? next.hashCode() : 0);
        return result;
    }
}
