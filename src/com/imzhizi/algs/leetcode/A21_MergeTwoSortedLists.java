package com.imzhizi.algs.leetcode;

/**
 * Merge two sorted linked lists and return it as a new list.
 * The new list should be made by splicing together the nodes of the first two lists.
 * <p>
 * Example:
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 * created by zhizi on 2018/4/19
 */
public class A21_MergeTwoSortedLists {
    /**
     * 函数描述：
     * 一道典型的数据结构题，单链表合并主要是头插法和尾插法
     * 这道题我用的是头插法，但因为Java没有指针的原因，所以不能很好的进行内存操作
     * 而且提供的数据结构没有头指针，所以我进行了一次比较，选首位小的做头指针
     * keep指的是被插入的链表，insert指的是要插入到keep的链表
     * 运行时长：16 ms
     *
     * 总结：
     * 要使用keep.next进行引用操作才不会丢失头指针
     * 要插入的时候，temp必须是新建的，否则会把keep原内容覆盖掉
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode keep = l1.val > l2.val ? l2 : l1;
        ListNode insert = l1.val <= l2.val ? l2 : l1;
        while (keep.next != null && insert != null) {
            if (keep.val <= insert.val && insert.val < keep.next.val) {
                ListNode temp = new ListNode(insert.val);
                temp.next = keep.next;
                keep.next = temp;
                insert = insert.next;
            } else {
                keep = keep.next;
            }
        }
        if (keep.next == null && insert != null) {
            keep.next = insert;
        }
        return l1.val > l2.val ? l2 : l1;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(1);

        l1.next=new ListNode(2);
        l1.next.next=new ListNode(4);

        l2.next=new ListNode(3);
        l2.next.next=new ListNode(4);

        ListNode l3 = mergeTwoLists(l1, l2);

        while (l3 != null) {
            System.out.println(l3.val);
            l3 = l3.next;
        }
    }
}
