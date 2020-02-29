package com.imzhizi.algs.剑指LeetCode;

import com.imzhizi.algs.base.ListNode;
import com.imzhizi.algs.base.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TOP10 {
    /**
     * [面试题03. 数组中重复的数字 - 力扣（LeetCode）](https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/ )
     */
    @Test
    public void No3() {

    }

    boolean duplicate(int[] numbers, int length, int[] duplication) {
        boolean[] k = new boolean[length];
        for (int i = 0; i < k.length; i++) {
            if (k[numbers[i]]) {
                duplication[0] = numbers[i];
                return true;
            }
            k[numbers[i]] = true;
        }
        return false;
    }

    public int findRepeatNumber(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) {
                index = nums[i];
            } else {
                index = -nums[i];
            }
            if (nums[index] < 0) {
                return index;
            } else {
                nums[index] = -nums[index];
            }
        }
        return 0;
    }

    /**
     * [面试题04. 二维数组中的查找 - 力扣（LeetCode）](https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/ )
     */
    @Test
    public void No4() {

    }

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int r = matrix.length;
        if (r == 0) return false;
        int c = matrix[0].length;
        if (c == 0) return false;

        int m = 0;
        int n = c - 1;
        while (m < r && n > -1) {
            if (target < matrix[m][n]) {
                n--;
            } else if (target > matrix[m][n]) {
                m++;
            } else {
                return true;
            }
        }
        return false;
    }


    /**
     * [面试题05. 替换空格 - 力扣（LeetCode）](https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/ )
     */
    @Test
    public void No5() {

    }

    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                sb.append(s.charAt(i));
            } else {
                sb.append("%20");
            }
        }

        return sb.toString();
    }

    /**
     * [面试题06. 从尾到头打印链表 - 力扣（LeetCode）](https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/ )
     */
    @Test
    public void No6() {

    }

    public int[] reversePrint(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int size = list.size();
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = list.get(size - 1 - i);
        }
        return result;
    }

    /**
     * [面试题07. 重建二叉树 - 力扣（LeetCode）](https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/ )
     */
    @Test
    public void No7() {

    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    public TreeNode buildTree(int[] preorder, int[] inorder, int ph, int pt, int ih, int it) {
        if (ph < 0 || ph >= preorder.length || ph > pt || ih < 0 || it >= inorder.length || ih > it) return null;

        TreeNode node = new TreeNode(preorder[ph]);
        int iroot = 0;
        for (int i = ih; i <= it; i++) {
            if (inorder[i] == preorder[ph]) {
                iroot = i;
                break;
            }
        }

        int pleft = iroot - ih + ph;

        node.left = buildTree(preorder, inorder, ph + 1, pleft, ih, iroot - 1);
        node.right = buildTree(preorder, inorder, pleft + 1, pt, iroot + 1, it);


        return node;
    }

    /**
     * [面试题09. 用两个栈实现队列 - 力扣（LeetCode）](https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/ )
     */
    @Test
    public void No9() {

    }

    class CQueue {
        private Stack<Integer> stackA;
        private Stack<Integer> stackB;

        public CQueue() {
            stackA = new Stack<>();
            stackB = new Stack<>();
        }

        public void appendTail(int value) {
            stackA.push(value);
        }

        public int deleteHead() {
            while (!stackA.isEmpty()) {
                stackB.push(stackA.pop());
            }
            if (stackB.isEmpty()) {
                return -1;
            } else {
                int temp = stackB.pop();
                while (!stackB.isEmpty()) {
                    stackA.push(stackB.pop());
                }
                return temp;
            }
        }
    }

    /**
     * [面试题10- I. 斐波那契数列 - 力扣（LeetCode）](https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/ )
     */
    @Test
    public void No10() {

    }

    public int fib(int n) {
        if (n < 2) return 1;
        int f = 1;
        int s = 0;
        for (int i = 1; i < n; i++) {
            int temp = f;
            f = (f + s) % 1000000007;
            s = temp;
        }
        return f;
    }


    /**
     * [面试题10- II. 青蛙跳台阶问题 - 力扣（LeetCode）](https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/ )
     * 区别是f(0)=1 上一题 f(0)=0
     */
    @Test
    public void No10II() {

    }

    public int numWays(int n) {
        if (n < 2) return 1;
        int f = 1;
        int s = 1;
        for (int i = 1; i < n; i++) {
            int temp = f;
            f = (f + s) % 1000000007;
            s = temp;
        }
        return f;
    }
}
