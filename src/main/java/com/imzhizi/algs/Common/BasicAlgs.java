package com.imzhizi.algs.Common;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

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


    /**
     * [最短路径__牛客网](https://www.nowcoder.com/questionTerminal/bcf3a66a571f4cd2aa4173db3dafc8ee )
     */
    static class Dijkstra {
        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
            int n = input.nextInt();
            int m = input.nextInt();
            int[][] matrix = new int[n + 1][n + 1];
            for (int i = 0; i < m; i++) {
                int r = input.nextInt();
                int c = input.nextInt();
                if (matrix[r][c] != 0) matrix[r][c] = Math.min(matrix[r][c], input.nextInt());
                else matrix[r][c] = input.nextInt();

            }
            input.close();

//            floyd(matrix);

            displayMatrix(matrix);

            dijkstra(matrix, 1);

            displayMatrix(matrix);
        }

        // 单源最短路径
        public static void dijkstra(int[][] matrix, int source) {
            int[] dist = new int[matrix.length];
            dist[source]++;
            int min = Integer.MAX_VALUE;
            int transI = 0;
            int minI = 0;

            for (int l = 1; l < matrix.length; l++) {
                for (int i = 1; i < matrix.length; i++) {
                    if (dist[i] == 0) {
                        if (matrix[source][transI] != 0 && matrix[transI][i] != 0) {
                            if (matrix[source][i] != 0) {
                                matrix[source][i] = Math.min(matrix[source][i], matrix[source][transI] + matrix[transI][i]);
                            } else matrix[source][i] = matrix[source][transI] + matrix[transI][i];
                        }
                        if (matrix[source][i] != 0 && min > matrix[source][i]) {
                            minI = i;
                            min = matrix[source][i];
                        }
                    }
                }
                transI = minI;
                dist[transI]++;
                min = Integer.MAX_VALUE;
            }

        }

        // [最短路径__牛客网](https://www.nowcoder.com/questionTerminal/a29d0b5eb46b4b90bfa22aa98cf5ff17?toCommentId=1177781 )

        // 多源最短路径
        public static void floyd(int[][] matrix) {
            for (int trans = 1; trans < matrix.length; trans++) {
                for (int i = 1; i < matrix.length; i++) {
                    if (i != trans) {
                        for (int j = 1; j < matrix.length; j++) {
                            if (i != j) {
                                if (matrix[i][trans] != 0 && matrix[trans][j] != 0) {
                                    if (matrix[i][j] != 0)
                                        matrix[i][j] = Math.min(matrix[i][j], matrix[i][trans] + matrix[trans][j]);
                                    else matrix[i][j] = matrix[i][trans] + matrix[trans][j];
                                }
                            }
                        }
                    }
                }
            }

        }
    }

    // 失败的尝试
    @Test
    public void shortPath() {
        int[][] matrix = new int[5][5];
        visited = new boolean[4];
        int longest = shortestPath(matrix, 1, 2);
        System.out.println(longest == Integer.MAX_VALUE ? -1 : longest);
    }

    static boolean[] visited;

    public int shortestPath(int[][] matrix, int from, int to) {
        if (visited[from] || matrix[from][to] == Integer.MAX_VALUE) {
            return 0;
        }

        if (matrix[from][to] < 0) {
            return -matrix[from][to];
        }

        visited[from] = true;
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < matrix.length; i++) {
            int temp;
            if (i == from || i == to) {
                temp = matrix[i][to];
            } else {
                temp = shortestPath(matrix, i, to);
                if (temp > 0 && matrix[from][i] > 0) temp += matrix[from][i];
                else temp = 0;
            }
            if (temp > 0) min = Math.min(min, temp);
        }
        visited[from] = false;

        matrix[from][to] = -min;
        return -matrix[from][to];
    }

    public static void displayMatrix(int[][] matrix) {
        System.out.println();
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
