package com.imzhizi.algs.leetcode;

/**
 * Given a singly linked list, determine if it is a palindrome.
 *
 * Example 1:
 * Input: 1->2
 * Output: false
 *
 * Example 2:
 * Input: 1->2->2->1
 * Output: true
 * created by zhizi on 11/18/18
 */
public class A234_PalindromeLinkedList {
    /**
     * 题目分析：
     * 判断一个链表是否是对称的，很容易想到的思路就是遍历一遍，用数组保存每个值，然后再判断是否对称
     * 这样的话时间复杂度是 O(2n)，空间复杂度是 O(n)，还是有点慢的
     * 那该怎么搞呢？我从讨论区获得了一些启发，很重要的一点事学会找到链表的中点，也就是 ==设置快慢结点一起遍历==
     * 还有就是链表是单向的，即便我拿到了中间结点，也没办法直接开始比较，所以需要把其中一部分翻转
     * 而慢结点把前半段逐个遍历，就很适合在其中进行翻转
     *
     * 运行时长：98% / 1ms
     *
     * 总结：
     * 快慢结点是链表的一个技巧，应该记下来
     */
    static boolean isPalindrome(ListNode head) {
        if(head == null) {
            return true;
        }

        ListNode front = new ListNode(0);
        ListNode slow = head;
        ListNode fast = head;
        // 一次前进两个结点，通过遍历找到中间结点
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            ListNode pre = slow;
            slow = slow.next;
            pre.next = front.next;
            front.next = pre;
        }

        // 奇偶结点判断
        if (fast != null) {
            slow = slow.next;
        }

        front = front.next;
        while (slow != null) {
            if (front.val != slow.val) return false;
            front = front.next;
            slow = slow.next;
        }
        return true;
    }

    public static void main(String[] args) {
        ListNode node1=new ListNode(1);
        ListNode node2=new ListNode(2);
        ListNode node3=new ListNode(3);
        ListNode node4=new ListNode(2);
        ListNode node5=new ListNode(1);
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=node5;

        System.out.println(isPalindrome(node1));
    }
}
