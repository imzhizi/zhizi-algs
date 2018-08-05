package com.imzhizi.algs.leetcode;

/**
 * created by zhizi on 2018/5/4
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        if (this.next!=null)
        return this.val+"->"+this.next.toString();
        else return this.val+"";
    }
}