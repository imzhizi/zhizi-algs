package com.imzhizi.algs.CodingInterviews;

import com.imzhizi.algs.LeetCode.ListNode;
import com.imzhizi.algs.LeetCode.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Part1 {
    @Test
    // 核心思想把一次性当道最终位置
    public void No5() {
        // 将空格替换为 %20
        char[] input = "we are happy".toCharArray();
        System.out.println(替换空格(input));


        // 数组B合并到A
        int[] numsA = {1, 3, 5, 7, 0, 0, 0};
        int[] numsB = {2, 4, 6};
        for (int i : 数组合并(numsA, numsB))
            System.out.print(i + " ");
    }

    private char[] 替换空格(char[] input) {
        int charCount = 0;

        for (char c : input) {
            if (c == ' ') charCount++;
        }


        char[] output = new char[input.length + 2 * charCount];
        for (int i = input.length - 1; i >= 0; i--) {
            if (input[i] == ' ') {
                output[i + 2 * charCount] = '0';
                output[i + 2 * charCount - 1] = '2';
                output[i + 2 * charCount - 2] = '%';
                charCount--;
            } else {
                output[i + 2 * charCount] = input[i];
            }
        }

        return output;
    }

    private int[] 数组合并(int[] numsA, int[] numsB) {
        int b = numsB.length - 1;
        int a = numsA.length - numsB.length - 1;
        int l = numsA.length - 1;

        while (l > -1) {
            if (a > -1 && b > -1) {
                if (numsA[a] > numsB[b]) {
                    numsA[l--] = numsA[a--];
                } else {
                    numsA[l--] = numsB[b--];
                }
            } else if (a < 0) {
                numsA[l--] = numsB[b--];
            } else {
                numsA[l--] = numsA[a--];
            }
        }

        return numsA;
    }

    @Test
    public void No6() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;

        从头到尾打印链表(node1);
        System.out.println();
        从头到尾打印链表2(node1);
    }

    private void 从头到尾打印链表(ListNode head) {
        ArrayList<Integer> list = new ArrayList<>();

        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        for (int i = list.size() - 1; i >= 0; i--) {
            System.out.print(list.get(i) + " ");
        }
    }

    private void 从头到尾打印链表2(ListNode head) {
        if (head != null) {
            if (head.next != null)
                从头到尾打印链表2(head.next);
            System.out.print(head.val + " ");
        }
    }

    @Test
    public void No7() {
        // 前序遍历 中序遍历 重建二叉树
        int[] pres = new int[]{1, 2, 4, 5, 3, 6, 7};
        int[] mids = new int[]{4, 2, 5, 1, 6, 3, 7};
        System.out.println(重建二叉树(pres, mids));
    }


    private TreeNode 重建二叉树(int[] pres, int[] mids) {
        return rebuilt(pres, 0, pres.length - 1, mids, 0, mids.length - 1);
    }

    private TreeNode rebuilt(int[] pres, int p1, int p2, int[] mids, int m1, int m2) {
        TreeNode head = new TreeNode(pres[p1]);

        if (p1 == p2 && m1 == m2) {
            return head;
        }

        int mroot = 0;
        for (int i = 0; i < mids.length; i++) {
            if (mids[i] == pres[p1]) {
                mroot = i;
            }
        }

        int np2 = 0;
        for (int i = 0; i < pres.length; i++) {
            if (mids[mroot - 1] == pres[i]) {
                np2 = i;
            }
        }

        head.left = rebuilt(pres, p1 + 1, np2, mids, m1, mroot - 1);
        head.right = rebuilt(pres, np2 + 1, p2, mids, mroot + 1, m2);

        return head;
    }


    @Test
    public void No8() {
        int[] pres = new int[]{1, 2, 4, 5, 3, 6, 7};
        reOrderArray(pres);
        System.out.println(Arrays.toString(pres));
    }

    public void reOrderArray(int[] array) {
        // 感觉只能先搞个临时数组存偶数, 然后奇数覆盖式保存
        // 最后把偶数存回去
        int[] even = new int[array.length];
        int[] odd = new int[array.length];
        int e = 0;
        int o = 0;

        for (int i : array) {
            if (i % 2 != 0) {
                odd[o++] = i;
            } else {
                even[e++] = i;
            }
        }

        System.arraycopy(odd, 0, array, 0, o);
        System.arraycopy(even, 0, array, o, e);
    }

    @Test
    public void No9() {

    }

    @Test
    public void No10() {

    }

    @Test
    public void No11() {

    }

    @Test
    public void No12() {

    }

    @Test
    public void No13() {

    }

    @Test
    public void No14() {

    }

    @Test
    public void No15() {

    }

    @Test
    public void No16() {

    }

    @Test
    public void No17() {

    }

}
