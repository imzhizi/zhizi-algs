package com.imzhizi.algs.LeetCode2020;

import com.imzhizi.algs.base.ListNode;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * created by zhizi
 * on 5/5/20 09:15
 * 链表问题
 */
public class IMooC3 {
    /**
     * [206. 反转链表 - 力扣（LeetCode）](https://leetcode-cn.com/problems/reverse-linked-list/ )
     * 是一道非常朴实的题目
     */
    @Test
    public void Q206() {

    }

    // 但如果要求用递归来做呢
    // 要返回的必然是头结点，如果想要保证结点都在链上，相当于进入递归之前已经准备好了后续链表
    // 经过尝试发现进入递归前就处理好关系是不可能的，因为思路一开始就错了
    // 保留结果结点是必须的，另一种做法就是持续地返回之前上一层返回的结点
    // 剩下的问题变成了如何不依赖其他结点帮助实现链表翻转
    // 非常简单精妙的两行代码，将自己的后置指向自己，然后切断对后置的指针
    // 事实上，如果不是为了解决头指针的循环问题，甚至不主动切断也是可以的
    // head.next.next=head;
    // head.next=null;
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode node = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }

    /**
     * [92. 反转链表 II - 力扣（LeetCode）](https://leetcode-cn.com/problems/reverse-linked-list-ii/ )
     * todo 缺少迭代做法
     */
    @Test
    public void Q92() {

    }

    // 借助了题解，递归在链表中的使用还挺神奇的
    // 没有像上一题一样对链表指针进行控制，而是对值进行控制
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode left = new ListNode(0);
        left.next = head;
        for (int i = 0; i < m - 1; i++) {
            left = left.next;
        }
        if (left.next != null) counterpart(left.next, m, m + n);

        return head;
    }

    public ListNode counterpart(ListNode head, int cur, int tar) {
        if (cur << 1 == tar) return head.next;
        if (cur << 1 == tar + 1) return head;
        ListNode pair = counterpart(head.next, cur + 1, tar);
        int temp = head.val;
        head.val = pair.val;
        pair.val = temp;
        return pair.next;
    }


    /**
     * [86. 分隔链表 - 力扣（LeetCode）](https://leetcode-cn.com/problems/partition-list/ )
     */
    @Test
    public void Q86() {

    }

    // 100% beat
    // 如果是大于等于x则无视, 如果小于x则尝试通过头部尾插法转移
    // 循环不变量, insert的next始终为头部的尾部, 用于插入
    public ListNode partition(ListNode head, int x) {
        ListNode ret = new ListNode(0);
        ret.next = head;
        ListNode insert = ret;
        ListNode node = ret;

        while (node.next != null) {
            if (node.next.val < x) {
                if (node.next != insert.next) {
                    ListNode temp = node.next;
                    node.next = node.next.next;
                    temp.next = insert.next;
                    insert.next = temp;
                } else {
                    node = node.next;
                }
                insert = insert.next;
            } else {
                node = node.next;
            }
        }
        return ret.next;
    }


    /**
     * [328. 奇偶链表 - 力扣（LeetCode）](https://leetcode-cn.com/problems/odd-even-linked-list/ )
     */
    @Test
    public void Q328() {

    }

    public ListNode oddEvenList(ListNode head) {
        // 思路就是维护两条链表, 最后拼接在一起
        ListNode odd = new ListNode(1);
        ListNode even = new ListNode(2);
        ListNode head2 = even;
        odd.next = head;

        while (odd.next != null) {
            if (odd.next.next != null) {
                even.next = odd.next.next;
                odd.next.next = odd.next.next.next;
                even = even.next;
                even.next = null;
            }
            odd = odd.next;

        }
        odd.next = head2.next;

        return head;
    }


    /**
     * [445. 两数相加 II - 力扣（LeetCode）](https://leetcode-cn.com/problems/add-two-numbers-ii/)
     * 大数处理
     */
    @Test
    public void Q445() {

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //大数处理
        Deque<Integer> s1 = new ArrayDeque();
        Deque<Integer> s2 = new ArrayDeque();
        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }
        ListNode ret = new ListNode(0);
        int count = 0;
        while (!s1.isEmpty() && !s2.isEmpty()) {
            int sum = s1.pop() + s2.pop() + count;
            count = sum / 10;
            sum = sum % 10;
            ListNode node = new ListNode(sum);
            node.next = ret.next;
            ret.next = node;
        }

        while (!s1.isEmpty()) {
            count = s1.pop() + count;
            ListNode node = new ListNode(count % 10);
            node.next = ret.next;
            ret.next = node;
            count = count / 10;
        }

        while (!s2.isEmpty()) {
            count = s2.pop() + count;
            ListNode node = new ListNode(count % 10);
            node.next = ret.next;
            ret.next = node;
            count = count / 10;
        }


        if (count != 0) {
            ListNode node = new ListNode(count);
            node.next = ret.next;
            ret.next = node;
        }

        return ret.next;
    }
}
