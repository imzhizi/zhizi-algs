package com.imzhizi.algs.Common;

import com.imzhizi.algs.base.ListNode;
import com.imzhizi.algs.base.TreeNode;
import org.junit.Test;

import java.util.*;

public class BasicAlgs {
    /**
     * [21. 合并两个有序链表 - 力扣（LeetCode）](https://leetcode-cn.com/problems/merge-two-sorted-lists/ )
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode node = new ListNode(0);
        ListNode index = node;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                index.next = l2;
                l2 = l2.next;
            } else {
                index.next = l1;
                l1 = l1.next;
            }
            index = index.next;
        }
        if (l1 != null) {
            index.next = l1;
        } else {
            index.next = l2;
        }
        return node.next;
    }


    /**
     * [面试题52. 两个链表的第一个公共节点 - 力扣（LeetCode）](https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/ )
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lena = 0;
        int lenb = 0;
        ListNode a = headA;
        ListNode b = headB;
        while (a != null) {
            lena++;
            a = a.next;
        }
        while (b != null) {
            lenb++;
            b = b.next;
        }
        while (lena > lenb) {
            lena--;
            headA = headA.next;
        }

        while (lena < lenb) {
            lenb--;
            headB = headB.next;
        }

        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }

        return headB;
    }

    // todo 不常见 循环做法
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        ListNode p1 = headA;
        ListNode p2 = headB;
        if (p1 == null || p2 == null)
            return null;

        while (p1 != p2) {
            p1 = p1 == null ? headB : p1.next;
            p2 = p2 == null ? headA : p2.next;
        }

        return p1;
    }


    /**
     * [206. 反转链表 - 力扣（LeetCode）](https://leetcode-cn.com/problems/reverse-linked-list/ )
     */
    public ListNode reverseList0(ListNode head) {
        ListNode node = new ListNode(0);

        while (head != null) {
            ListNode temp = head.next;
            head.next = node.next;
            node.next = head;
            head = temp;
        }

        return node.next;
    }

    // todo，递归做法
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        } else {
            ListNode node = reverseList(head.next);
            head.next.next = head;
            head.next = null;
            return node;
        }
    }


    /**
     * [94. 二叉树的中序遍历 - 力扣（LeetCode）](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/ )
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        dfs(result, root);
        return result;
    }

    // 使用递归实现dfs，实现简单，性能也好
    public void dfs(List<Integer> result, TreeNode root) {
        if (root == null) return;

        dfs(result, root.left);
        result.add(root.val);
        dfs(result, root.right);
    }

    //  同样的dfs，但是不用递归实现
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }


    /**
     * [探索算法面试题汇总 - 力扣 (LeetCode)](https://leetcode-cn.com/explore/interview/card/top-interview-quesitons-in-2018/261/before-you-start/1106/ )
     */
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int i : nums) {
            result ^= i;
        }
        return result;
    }


}
