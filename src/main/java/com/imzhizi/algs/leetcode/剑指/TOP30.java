package com.imzhizi.algs.leetcode.剑指;

import com.imzhizi.algs.Common.ListNode;
import com.imzhizi.algs.Common.TreeNode;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * created by zhizi
 * on 2/29/20 20:28
 */
public class TOP30 {
    /**
     * [面试题21. 调整数组顺序使奇数位于偶数前面 - 力扣（LeetCode）](https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/ )
     */
    @Test
    public void No21() {

    }

    public int[] exchange(int[] nums) {
        int head = 0;
        int tail = nums.length - 1;

        while (head < tail) {
            if (nums[head] % 2 == 1) {
                head++;
            } else {
                int temp = nums[head];
                nums[head] = nums[tail];
                nums[tail] = temp;
                tail--;
            }
        }
        return nums;
    }

    /**
     * [面试题22. 链表中倒数第k个节点 - 力扣（LeetCode）](https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/ )
     */
    @Test
    public void No22() {

    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast = head;
        ListNode slow = head;
        for (int i = 0; fast != null && i < k; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }


    /**
     * [面试题24. 反转链表 - 力扣（LeetCode）](https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/ )
     */
    @Test
    public void No24() {

    }

    public ListNode reverseList(ListNode head) {
        ListNode result = new ListNode(0);
        ListNode node = result;

        while (head != null) {
            ListNode temp = head.next;
            head.next = node.next;
            node.next = head;
            head = temp;
        }
        return result.next;
    }


    /**
     * [面试题25. 合并两个排序的链表 - 力扣（LeetCode）](https://leetcode-cn.com/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/ )
     */
    @Test
    public void No25() {
        Queue<Integer> stack = new ArrayDeque<Integer>();
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode node = result;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                ListNode temp = l1;
                l1 = l1.next;
                node.next = temp;
            } else {
                ListNode temp = l2;
                l2 = l2.next;
                node.next = temp;
            }
            node = node.next;
        }
        if (l1 != null) {
            node.next = l1;
        }
        if (l2 != null) {
            node.next = l2;
        }
        return result.next;
    }


    /**
     * [面试题26. 树的子结构 - 力扣（LeetCode）](https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/ )
     */
    @Test
    public void No26() {

    }

    // 慢到家了
    public boolean isSubStructure1(TreeNode A, TreeNode B) {
        if (B == null) return false;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(A);

        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            if (isSame(temp, B)) {
                return true;
            } else {
                if (temp.left != null) queue.add(temp.left);
                if (temp.right != null) queue.add(temp.right);
            }
        }
        return false;
    }

    // 假设所有值都是不重复的，只要有一次相同的，就可以 true/false
    public boolean isSubStructure1改(TreeNode A, TreeNode B) {
        if (B == null) return false;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(A);

        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            if (temp.val == B.val) {
                return isSame(temp, B);
            } else {
                if (temp.left != null) queue.add(temp.left);
                if (temp.right != null) queue.add(temp.right);
            }
        }

        return false;
    }

    // 使用递归后就变快了，这是为什么呢？
    // 使用队列逐个遍历不是也还行吗？
    // 再看看逻辑还是有所不同的，第一种方法假设树中是存在同 val 的结点的
    public boolean isSubStructure2(TreeNode A, TreeNode B) {
        if (B == null || A == null) return false;

        if (A.val == B.val) {
            return isSame(A, B);
        } else {
            return isSubStructure2(A.left, B) || isSubStructure2(A.right, B);
        }
    }

    public boolean isSame(TreeNode A, TreeNode B) {
        if (B == null) return true;
        if (A == null) return false;

        if (A.val == B.val) {
            return isSame(A.left, B.left) && isSame(A.right, B.right);
        } else {
            return false;
        }
    }


    /**
     * [面试题27. 二叉树的镜像 - 力扣（LeetCode）](https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof/ )
     */
    @Test
    public void No27() {

    }

    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            return root;
        }

        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = mirrorTree(right);
        root.right = mirrorTree(left);

        return root;
    }


    /**
     * [面试题28. 对称的二叉树 - 力扣（LeetCode）](https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof/ )
     */
    @Test
    public void No28() {

    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        else return equals(root.left, root.right);
    }

    public boolean equals(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        else if (left == null || right == null) return false;
        else if (left.val == right.val) {
            return equals(left.left, right.right) && equals(left.right, right.left);
        } else {
            return false;
        }
    }


    /**
     * [面试题29. 顺时针打印矩阵 - 力扣（LeetCode）](https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/ )
     */
    @Test
    public void No29() {

    }

    public int[] spiralOrder(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) return new int[0];
        int n = matrix[0].length;
        if (n == 0) return new int[0];

        int[] result = new int[m * n];
        int index = 0;
        int lim = Math.min(m, n) / 2;
        for (int i = 0; i < lim; i++) {
            // 上
            for (int j = i; j < n - 1 - i; j++) result[index++] = matrix[i][j];
            // 右
            for (int j = i; j < m - 1 - i; j++) result[index++] = matrix[j][n - 1 - i];
            // 下
            for (int j = n - 1 - i; j > i; j--) result[index++] = matrix[m - 1 - i][j];
            // 左
            for (int j = m - 1 - i; j > i; j--) result[index++] = matrix[j][i];
        }
        if (Math.min(m, n) % 2 == 1) {
            if (m > n) {
                for (int i = lim; i < m - lim; i++) result[index++] = matrix[i][lim];
            } else if (m < n) {
                for (int i = lim; i < n - lim; i++) result[index++] = matrix[lim][i];
            } else {
                result[index] = matrix[lim][lim];
            }
        }
        return result;
    }

    // 虽然形式没那么一致，但可以少进行一次奇数处理
    public int[] spiralOrder2(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) return new int[0];
        int n = matrix[0].length;
        if (n == 0) return new int[0];

        int[] result = new int[m * n];
        int index = 0;
        int min = Math.min(m, n);
        int lim = (min + 1) / 2;
        for (int i = 0; i < lim; i++) {
            // 上
            for (int j = i; j <= n - 1 - i; j++) {
                result[index++] = matrix[i][j];
            }
            if (index == result.length) break;
            // 右
            for (int j = i + 1; j < m - 1 - i; j++) {
                result[index++] = matrix[j][n - 1 - i];
            }
            if (index == result.length) break;
            // 下
            for (int j = n - 1 - i; j >= i; j--) {
                result[index++] = matrix[m - 1 - i][j];
            }
            if (index == result.length) break;
            // 左
            for (int j = m - 2 - i; j > i; j--) {
                result[index++] = matrix[j][i];
            }
            if (index == result.length) break;
        }
        return result;
    }


    /**
     * [面试题30. 包含min函数的栈 - 力扣（LeetCode）](https://leetcode-cn.com/problems/bao-han-minhan-shu-de-zhan-lcof/ )
     */
    @Test
    public void No30() {
        SingleStack stack = new SingleStack();
        stack.push(7);
        System.out.println("push 7 " + stack);

        stack.push(-2);
        System.out.println("push -2 " + stack);

        stack.pop();
        System.out.println("pop " + stack);

        stack.push(4);
        System.out.println("push 4 " + stack);

        stack.push(2);
        System.out.println("push 2 " + stack);

        stack.push(9);
        System.out.println("push 9 " + stack);


        stack.push(-7);
        System.out.println("push 2 " + stack);

        stack.push(6);
        System.out.println("push 6 " + stack);


        stack.push(9);
        System.out.println("push 9 " + stack);

        stack.pop();
        System.out.println("pop " + stack);
        stack.pop();
        System.out.println("pop " + stack);
        stack.pop();
        System.out.println("pop " + stack);
    }

    static class SingleStack {
        Deque<Integer> data;
        int min;
        int max;

        public SingleStack() {
            data = new ArrayDeque<>(200);
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;
        }


        public void push(int x) {
            if (x <= min) {
                data.push(min);
                min = x;
            }
            if (x >= max) {
                data.push(max);
                max = x;
            }
            data.push(x);
        }

        public void pop() {
            if (data.isEmpty()) return;
            int pop = data.pop();
            if (max == pop) max = data.pop();
            if (min == pop) min = data.pop();
        }

        public int peek() {
            return data.isEmpty() ? -1 : data.peek();
        }

        public int min() {
            return min;
        }

        public int max() {
            return max;
        }

        @Override
        public String toString() {
            return "SingleStack{" +
                    "data=" + data +
                    ", min=" + min +
                    ", max=" + max +
                    '}';
        }
    }

    static class EndStack {
        ArrayDeque<Integer> data;
        ArrayDeque<Integer> min;
        ArrayDeque<Integer> max;

        public EndStack() {
            data = new ArrayDeque<>(1000);
            min = new ArrayDeque<>(1000);
            max = new ArrayDeque<>(1000);
        }

        public void push(int x) {
            data.push(x);
            if (min.isEmpty() || min.peek() >= x) min.push(x);
            if (max.isEmpty() || max.peek() <= x) max.push(x);
        }

        public void pop() {
            Integer pop = data.pop();
            if (pop.equals(min.peek())) min.pop();
            if (pop.equals(max.peek())) max.pop();
        }

        public int top() {
            if (data.isEmpty()) return -1;
            return data.peek();
        }

        public int min() {
            if (min.isEmpty()) return -1;
            return min.peek();
        }

        public int max() {
            if (max.isEmpty()) return -1;
            return max.peek();
        }

        @Override
        public String toString() {
            return "EndStack{" +
                    "data=" + data +
                    ", min=" + min +
                    ", max=" + max +
                    '}';
        }
    }

}
