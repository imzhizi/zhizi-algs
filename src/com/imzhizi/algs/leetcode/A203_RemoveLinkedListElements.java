package com.imzhizi.algs.leetcode;

/**
 * Remove all elements from a linked list of integers that have value val.
 *
 * Example:
 *
 * Input:  1->2->6->3->4->5->6, val = 6
 * Output: 1->2->3->4->5
 * created by zhizi on 8/5/18
 */
public class A203_RemoveLinkedListElements {
    /**
     * 题目分析：
     * 题目不难理解，主要就是链表的遍历，但如何能很好地遍历也不容易
     * 我为空指针问题研究很久，终于行得通了，但运行时长不佳
     *
     * 运行时长：2 ms
     *
     * 总结：
     * 应该有更好的实现的，但没有继续尝试，先这样吧
     */
    static ListNode removeElements(ListNode head, int val) {
        while (head != null && head.val == val) {
            head = head.next;
        }

        ListNode copy = head;

        while (copy != null && copy.next != null) {
            if (copy.next.val == val) copy.next = copy.next.next;
            else copy = copy.next;
        }
        return head;
    }

        public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(6);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(4);
        ListNode node6 = new ListNode(5);
        ListNode node7 = new ListNode(6);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;

        System.out.println(removeElements(node1, 6));
        System.out.println(removeElements(node7, 6));
    }
}
