package com.imzhizi.algs.leetcode.curated;

import com.imzhizi.algs.common.TreeNode;
import org.junit.Test;
import sun.tools.java.AmbiguousClass;

import java.util.ArrayDeque;

/**
 * created by zhizi
 * on 6/11/20 10:40
 */
public class DailyJune {
    /**
     * [739. 每日温度 - 力扣（LeetCode）](https://leetcode-cn.com/problems/daily-temperatures/)
     */
    @Test
    public void Q739() {
//        int[] ints = dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73});
        int[] ints = dailyTemperatures(new int[]{34, 80, 80, 34, 34, 80, 80, 80, 80, 34});
        for (int anInt : ints) {
            System.out.print(anInt + " ");
        }
    }

    //    %83
    public int[] dailyTemperatures(int[] T) {
        ArrayDeque<Integer> queue = new ArrayDeque();
        queue.offerFirst(0);
        for (int i = 1; i < T.length; i++) {
            if (T[i] < T[queue.peekFirst()]) {
                while (!queue.isEmpty() && T[i] > T[queue.peekLast()]) {
                    int k = queue.pollLast();
                    T[k] = i - k;
                }

                queue.offerLast(i);
            } else if (T[i] > T[queue.peekFirst()]) {
                while (!queue.isEmpty() && T[i] > T[queue.peekFirst()]) {
                    int k = queue.pollFirst();
                    T[k] = i - k;
                }

                queue.offerFirst(i);
            } else {
                while (!queue.isEmpty() && T[i] > T[queue.peekLast()]) {
                    int k = queue.pollLast();
                    T[k] = i - k;
                }
                queue.offerFirst(i);
            }
        }

        while (!queue.isEmpty()) {
            T[queue.poll()] = 0;
        }

        return T;
    }

    /**
     * [70. 爬楼梯 - 力扣（LeetCode）](https://leetcode-cn.com/problems/climbing-stairs/)
     */
    @Test
    public void Q70() {

    }

    public int climbStairs(int n) {
        int fast = 1;
        int slow = 1;
        for (int i = 1; i < n; i++) {
            int temp = fast;
            fast += slow;
            slow = temp;
        }

        return fast;
    }


    /**
     * [297. 二叉树的序列化与反序列化 - 力扣（LeetCode）](https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/)
     * 成功与否在于是否序列化方法的完成度
     */
    @Test
    public void Q297() {
        TreeNode root = new TreeNode(1);
        TreeNode t1 = new TreeNode(2);
        TreeNode t2 = new TreeNode(3);
        TreeNode t5 = new TreeNode(4);
        TreeNode t6 = new TreeNode(5);
        root.left = t1;
        root.right = t2;
        t1.left = null;
        t1.right = null;
        t2.left = t5;
        t2.right = t6;

        String string = serialize(root);
        System.out.println(string);
        TreeNode node = deserialize(string);
        System.out.println(node);
    }

    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        if (root != null) {
            queue.offer(root);
            sb.append(root.val);
        }

        while (!queue.isEmpty()) {
            sb.append(",");
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.offer(node.left);
                sb.append(node.left.val);
            } else {
                sb.append("null");
            }
            sb.append(",");
            if (node.right != null) {
                queue.offer(node.right);
                sb.append(node.right.val);
            } else {
                sb.append("null");
            }

        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] vals = data.split(",");
        TreeNode[] nodes = new TreeNode[vals.length];
        for (int i = 0; i < vals.length; i++) {
            if (vals[i].equals("null")) {
                nodes[i] = null;
            } else {
                nodes[i] = new TreeNode(Integer.parseInt(vals[i]));
            }
        }

        int child = 1;
        for (int i = 0; i < vals.length; i++) {
            if (nodes[i] != null) {
                if (child < vals.length) nodes[i].left = nodes[child++];
                if (child < vals.length) nodes[i].right = nodes[child++];
            }
        }

        return nodes[0];
    }

    /**
     * [1014. 最佳观光组合 - 力扣（LeetCode）](https://leetcode-cn.com/problems/best-sightseeing-pair/)
     */
    @Test
    public void Q1014() {
        System.out.println(maxScoreSightseeingPair(new int[]{6, 2, 5, 4, 8}));
        System.out.println(maxScoreSightseeingPair(new int[]{8, 1, 5, 2, 6}));
    }

    public int maxScoreSightseeingPair(int[] A) {
        int max = 0;
        int mx = 0;

        for (int i = 0; i < A.length - 1; i++) {
            mx = Math.max(mx, A[i] + i);
            max = Math.max(max, A[i + 1] - i - 1 + mx);
        }

        return max;
    }

    /**
     * [1028. 从先序遍历还原二叉树 - 力扣（LeetCode）](https://leetcode-cn.com/problems/recover-a-tree-from-preorder-traversal/)
     * 有点难
     */
    @Test
    public void Q() {

    }
}
