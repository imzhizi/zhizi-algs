package com.imzhizi.algs.LeetCode2018;

import com.imzhizi.algs.base.ListNode;

/**
 * Merge two sorted linked lists and return it as a new list.
 * The new list should be made by splicing together the nodes of the first two lists.
 *
 * Example:
 *  Input: 1->2->4, 1->3->4
 *  Output: 1->1->2->3->4->4
 * created by zhizi on 2018/4/19
 */
public class A21_MergeTwoSortedLists {
    /**
     * 函数描述：
     * 一道典型的数据结构题，单链表合并主要是头插法和尾插法
     * 这道题我用的是头插法，但因为 Java 没有指针的原因，所以不能很好的进行内存操作
     * keep 指的是被插入的链表，insert 指的是要插入到keep的链表
     * 要使用 keep.next 进行引用操作才不会丢失头指针
     *
     * 运行时长：9ms / 65%
     *
     * 总结：
     * 方法的运行时间似乎并不总是相同，毫秒的精度出现较小的差距也很正常，算是最优解了
     */
    public static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode start = new ListNode(-1);
        ListNode head=start;

        while ( l1 != null && l2 != null){
            if (l1.val<=l2.val) {
                start.next = l1;
                l1=l1.next;
            }
            else {
                start.next = l2;
                l2=l2.next;
            }
            start = start.next;
        }

        start.next=l1==null?l2:l1;

        return head.next;
    }

        public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(1);

        l1.next=new ListNode(2);
        l1.next.next=new ListNode(4);

        l2.next=new ListNode(3);
        l2.next.next=new ListNode(4);

        ListNode l3 = mergeTwoLists2(l1, l2);

        while (l3 != null) {
            System.out.println(l3.val);
            l3 = l3.next;
        }
    }
}
