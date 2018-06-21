package com.imzhizi.algs.leetcode;

/**
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 * <p>
 * Example 1:
 * Input: 1->1->2
 * Output: 1->2
 * <p>
 * Example 2:
 * Input: 1->1->2->3->3
 * Output: 1->2->3
 * created by zhizi on 2018/5/4
 */
public class A83_RemoveDuplicatesFromSortedList {
    /**
     * 题目分析：
     * 不复杂，效率
     * 运行时长：1 ms
     *
     * 总结：
     *
     */
    public static ListNode deleteDuplicates(ListNode head) {
        if(head==null) return head;
        ListNode node=head;
        while(node.next!=null){
            if (node.val<node.next.val)
                node=node.next;
            else
                node.next=node.next.next;

        }
        return head;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next=new ListNode(1);
        l1.next.next=new ListNode(2);
        l1.next.next.next=new ListNode(3);
        l1.next.next.next.next=new ListNode(3);

        ListNode node=deleteDuplicates(l1);
        while (node!=null){
            System.out.println(node.val);
            node=node.next;
        }
    }
}