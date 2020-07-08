package com.imzhizi.algs.leetcode.curated;

import com.imzhizi.algs.common.TreeNode;
import org.junit.Test;

import java.util.*;

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
        Deque<Integer> queue = new ArrayDeque();
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
    public void Q1028() {

    }

    /**
     * [125. 验证回文串 - 力扣（LeetCode）](https://leetcode-cn.com/problems/valid-palindrome/)
     */
    @Test
    public void Q125() {

    }

    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        s = s.toLowerCase();
        while (left < right) {
            while (left < right && (s.charAt(left) < '0' || s.charAt(left) > '9' && s.charAt(left) < 'A' || s.charAt(left) > 'Z' && s.charAt(left) < 'a' || s.charAt(left) > 'z')) {
                left++;
            }
            while (right > left && (s.charAt(right) < '0' || s.charAt(right) > '9' && s.charAt(right) < 'A' || s.charAt(right) > 'Z' && s.charAt(right) < 'a' || s.charAt(right) > 'z')) {
                right--;
            }
            if (left < right && s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * [41. 缺失的第一个正数 - 力扣（LeetCode）](https://leetcode-cn.com/problems/first-missing-positive/)
     */
    @Test
    public void Q41() {

    }

    public int firstMissingPositive(int[] nums) {
        boolean[] flags = new boolean[nums.length];

        for (int num : nums) {
            if (num > 0 && num <= nums.length) {
                flags[num - 1] = true;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (!flags[i]) {
                return i + 1;
            }
        }

        return nums.length + 1;
    }


    /**
     * [面试题 16.11. 跳水板 - Collarborate](https://leetcode-cn.com/problems/diving-board-lcci/)
     */
    @Test
    public void Q1611() {

    }

    //    一种非常傻的实现，先用HashSet去重，后面还要排序
    public int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0) return new int[0];

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i <= k; i++) {
            int sum = shorter * (k - i) + longer * (i);
            set.add(sum);
        }

        int[] ret = new int[set.size()];
        int index = 0;
        for (Integer i : set) {
            ret[index++] = i;
        }
        Arrays.sort(ret);
        return ret;
    }

    // 把问题复杂化了，其实根本不会出现重复的数字，也就是说能够产生的结果的数量是一定的
    public int[] divingBoard2(int shorter, int longer, int k) {
        int[] ret;
        if (k == 0) {
            ret = new int[0];
            return ret;
        }
        if (shorter == longer) {
            ret = new int[1];
            ret[0] = shorter * k;
            return ret;
        }

        ret = new int[k + 1];
        for (int i = 0; i <= k; i++) {
            ret[i] = shorter * (k - i) + longer * i;
        }
        return ret;
    }
}
