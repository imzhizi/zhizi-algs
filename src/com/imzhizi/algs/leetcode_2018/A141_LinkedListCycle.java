package com.imzhizi.algs.leetcode_2018;

import com.imzhizi.algs.ListNode;

import java.util.HashSet;

/**
 * Given a linked list, determine if it has a cycle in it.
 * created by zhizi on 2018/7/9
 */
public class A141_LinkedListCycle {
    /**
     * 题目分析：
     * 判断循环链表，很容易想到的就是把每个 node 存起来，如果新加的 node 已存在，则说明成环
     *
     * 运行时长：16ms
     *
     * 总结：
     * 这个方法不算好，实现为先，之后再优化。
     */
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
