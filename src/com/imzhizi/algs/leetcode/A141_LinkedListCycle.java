package com.imzhizi.algs.leetcode;

import java.util.HashSet;

/**
 * created by zhizi on 2018/7/9
 */
public class A141_LinkedListCycle {
    public static boolean hasCycle(ListNode head) {
        HashSet<ListNode> nodes=new HashSet<>();
        while(head!=null){
            if (nodes.contains(head))
                return true;
            nodes.add(head);
            head=head.next;
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode node1=new ListNode(1);
        ListNode node2=new ListNode(2);
        ListNode node3=new ListNode(3);
        ListNode node4=new ListNode(4);
        ListNode node5=new ListNode(3);
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=node5;
        System.out.println(hasCycle(node1));
    }
}
