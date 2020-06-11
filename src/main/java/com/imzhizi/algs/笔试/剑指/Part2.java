package com.imzhizi.algs.笔试.剑指;

import com.imzhizi.algs.Common.ListNode;
import com.imzhizi.algs.Common.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Part2 {
    /**
     * [表示数值的字符串_牛客网]( https://www.nowcoder.com/practice/6f8c901d091949a5837e24bb82a731f2 )
     */
    @Test
    public void No20() {

    }

    public boolean isNumeric(char[] str) {

        boolean div = false;
        boolean sci = false;

        for (int i = 0; i < str.length; i++) {
            if (str[i] == 'e' || str[i] == 'E') {
                if (i == str.length - 1) return false;
                else sci = true;
            } else if (str[i] == '.') {
                if (div) {
                    return false;
                } else {
                    if (sci) return false;
                    else div = true;
                }
            } else if (str[i] == '+' || str[i] == '-') {
                if (i != 0 && str[i - 1] != 'e' && str[i - 1] != 'E') return false;
            } else if (str[i] < '0' || str[i] > '9') {
                return false;
            }
        }

        return true;
    }


    /**
     * 重排列，使得奇数位于偶数前面
     * [调整数组顺序使奇数位于偶数前面_牛客网]( https://www.nowcoder.com/practice/beb5aa231adc45b2a5dcc5b62c93f593 )
     */
    @Test
    public void No21() {
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

    /**
     * [链表中倒数第k个结点_牛客网]( https://www.nowcoder.com/practice/529d3ae5a407492994ad2a246518148a )
     */
    @Test
    public void No22() {
    }

    public ListNode FindKthToTail(ListNode head, int k) {
        ListNode fast = head;
        while (k != 0 && fast != null) {
            fast = fast.next;
            k--;
        }

        if (k != 0) return null;

        ListNode slow = head;
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }

    /**
     * [链表中环的入口结点_牛客网]( https://www.nowcoder.com/practice/253d2c59ec3e4bc68da16833f79a38e4 )
     * 18ms
     */
    @Test
    public void No23() {
        System.out.println(EntryNodeOfLoop(new ListNode(1)).val);
    }

    public ListNode EntryNodeOfLoop(ListNode pHead) {
        List<ListNode> list = new ArrayList<>();

        while (pHead != null) {
            for (ListNode node : list) {
                if (node.val == pHead.val) {
                    return pHead;
                }
            }
            list.add(pHead);
            pHead = pHead.next;
        }

        return null;
    }

    // 使用HashSet优化了一丢丢
    public ListNode EntryNodeOfLoop2(ListNode pHead) {
        HashSet<Integer> set = new HashSet<>();

        while (pHead != null) {
            if (set.contains(pHead.val)) {
                return pHead;
            }
            set.add(pHead.val);
            pHead = pHead.next;
        }

        return null;
    }

    /**
     * [反转链表_牛客网]( https://www.nowcoder.com/practice/75e878df47f24fdc9dc3e400ec6058ca )
     */
    @Test
    public void No24() {

    }

    public ListNode ReverseList(ListNode head) {
        ListNode result = new ListNode(0);

        while (head != null) {
            ListNode temp = head;
            head = head.next;
            temp.next = result.next;
            result.next = temp;
        }

        return result.next;
    }

    /**
     * [合并两个排序的链表_牛客网]( https://www.nowcoder.com/practice/d8b6b4358f774294a89de2a6ac4d9337 )
     */
    @Test
    public void No25() {

    }

    public ListNode Merge(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(0);

        head.next = list1;

        ListNode node = head;

        while (list2 != null) {
            if (node.next == null) {
                node.next = list2;
                break;
            }

            if (list2.val <= node.next.val) {
                ListNode temp = list2;
                list2 = list2.next;

                temp.next = node.next;
                node.next = temp;
            }
            node = node.next;
        }

        return head.next;
    }

    /**
     * [树的子结构_牛客网]( https://www.nowcoder.com/practice/6e196c44c7004d15b1610b9afca8bd88 )
     */
    @Test
    public void No26() {

    }

    // 在root1种寻找所有val 为 root2 的结点
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) return false;

        if (root1.val == root2.val) {
            return compareChild(root1, root2) || HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
        }

        return HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
    }

    // 原来子结构不是完全一致, 而是局部一致, 因此root2遍历结束即可
    public boolean compareChild(TreeNode root1, TreeNode root2) {
        if (root2 == null) return true;
        if (root1 == null) return false;
        if (root1.val != root2.val) return false;

        return compareChild(root1.left, root2.left) && compareChild(root1.right, root2.right);

    }

    /**
     * [二叉树的镜像_牛客网]( https://www.nowcoder.com/practice/564f4c26aa584921bc75623e48ca3011 )
     */
    @Test
    public void No27() {

    }

    public void Mirror(TreeNode root) {
        if (root == null) return;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        Mirror(root.left);
        Mirror(root.right);
    }

    /**
     * [对称的二叉树_牛客网]( https://www.nowcoder.com/practice/ff05d44dfdb04e1d83bdbdab320efbcb )
     */
    @Test
    public void No28() {

    }

    boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null) return true;
        return compare(pRoot.left, pRoot.right);
    }

    boolean compare(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        if (left.val != right.val) return false;

        return compare(left.left, right.right) && compare(left.right, right.left);
    }

    /**
     * [顺时针打印矩阵_牛客网]( https://www.nowcoder.com/practice/9b4c81a02cd34f76be2659fa0d54342a )
     */
    @Test
    public void No29() {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        ArrayList<Integer> result = printMatrix(matrix);
        result.forEach(integer -> System.out.print(integer + " "));
    }

    public ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> result = new ArrayList<>();

        int h = matrix.length;
        int w = matrix[0].length;

        int i = 0;
        int j = 0;
        int loop = 0;
        int count = h * w;
        while (count > 0) {
            while (count > 0 && j < w - loop) {
                count--;
                result.add(matrix[i][j++]);
            }
            j--;
            i++;
            while (count > 0 && i < h - loop) {
                count--;
                result.add(matrix[i++][j]);
            }
            j--;
            i--;
            while (count > 0 && j >= loop) {
                count--;
                result.add(matrix[i][j--]);
            }

            j++;
            i--;
            loop++;

            while (count > 0 && i >= loop) {
                count--;
                result.add(matrix[i--][j]);
            }

            i++;
            j++;
        }

        return result;
    }
}